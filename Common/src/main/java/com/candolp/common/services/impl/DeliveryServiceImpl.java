package com.candolp.common.services.impl;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.*;
import com.candolp.common.repository.DeliveryPackagesRepository;
import com.candolp.common.repository.OrderItemRepository;
import com.candolp.common.repository.OrderRepository;
import com.candolp.common.services.DeliveryService;
import com.candolp.common.services.DroneService;
import com.candolp.common.services.MedicationService;
import com.candolp.common.utils.DroneState;
import com.candolp.common.utils.OrderStatus;
import com.candolp.common.validators.DeliveryPackageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    DeliveryPackagesRepository deliveryPackagesRepository;

    @Autowired
    DroneService droneService;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DeliveryPackageValidator deliveryPackageValidator;

    @Autowired
    MedicationService medicationService;
    @Autowired
    OrderItemRepository orderItemRepository;



    @Override
    public EntitySaveResults<DeliveryPackages> save(DeliveryPackages entity) {
       EntitySaveResults<DeliveryPackages> entitySaveResults = new EntitySaveResults<>();

        //get all paremeters for Delivery
        try{
            deliveryPackageValidator.validateDeliveryPackage(entity);
            entitySaveResults.setEntity(this.deliveryPackagesRepository.save(entity));
            return entitySaveResults;
        }catch (Exception e){
            entitySaveResults.setStatus("FAILED");
//            entitySaveResults.setEntity(entity);
            entitySaveResults.setError(e.getMessage());
            return  entitySaveResults;
        }
    }


    @Override
    public EntitySaveResults<DeliveryPackages> update(DeliveryPackages entity) {
        EntitySaveResults<DeliveryPackages> entitySaveResults = new EntitySaveResults<>();

        //get all paremeters for Delivery
        try{

            entitySaveResults.setEntity(this.deliveryPackagesRepository.save(entity));
            return entitySaveResults;
        }catch (Exception e){
            entitySaveResults.setEntity(null);
            entitySaveResults.setError(e.getMessage());
            return  entitySaveResults;
        }
    }

    @Override
    public void delete(DeliveryPackages entity) {
        this.deliveryPackagesRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {

        this.deliveryPackagesRepository.deleteById(id);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteInBatch(List<DeliveryPackages> entities) {
        this.deleteInBatch(entities);
    }

    @Override
    public List<DeliveryPackages> findAll() {
        return this.deliveryPackagesRepository.findAll();
    }

    @Override
    public List<DeliveryPackages> findDroneStatus(String droneSerialNumber) {
        Drone drone = this.droneService.getDroneBySerialNumber(droneSerialNumber);
        if (drone == null) return  null;
        return this.deliveryPackagesRepository.findDeliveryPackagesByDeliveryDroneOrderByTimePackages(drone);
    }

    @Override
    public DeliveryPackages findDroneLatestPackage(String droneSerialNumber) {
        Drone drone = this.droneService.getDroneBySerialNumber(droneSerialNumber);
        if (drone == null) return  null;
        DeliveryPackages deliveryPackages = this.deliveryPackagesRepository.findTopByDeliveryDroneOrderByTimePackagesDesc(drone);
        return deliveryPackages;
    }

    @Override
    public List<Medication> findDroneLoadedMedication(String droneSerialNumber) {
        Drone drone = this.droneService.getDroneBySerialNumber(droneSerialNumber);
        if (drone == null) return  null;
        DeliveryPackages deliveryPackages = this.deliveryPackagesRepository.findTopByDeliveryDroneOrderByTimePackagesDesc(drone);
        if (deliveryPackages.getDeliveryStatus() == OrderStatus.CONFIRMED) return null;
        List<Medication> medicationList = deliveryPackages.getOrder().getItems().stream().map(orderItem -> orderItem.getMedication()).collect(Collectors.toList());
        return medicationList;
    }

    @Override
    public boolean isDroneFreeForDelivery(String droneSerialNumber) {
        Drone drone = this.droneService.getDroneBySerialNumber(droneSerialNumber);
        if (drone == null) return  true;
        DeliveryPackages deliveryPackages = this.deliveryPackagesRepository.findTopByDeliveryDroneOrderByTimePackagesDesc(drone);
        return  (deliveryPackages.getDeliveryStatus() == OrderStatus.CONFIRMED);
    }

    @Override
    public DeliveryPackages getDeliveryPackage(Long packageId) {
        DeliveryPackages deliveryPackages = this.deliveryPackagesRepository.findFirstById(packageId);
        return deliveryPackages;
    }

    @Override
    public EntitySaveResults<DeliveryPackages> updateDeliveryStatus(Long packageId, OrderStatus orderStatus) {
        EntitySaveResults<DeliveryPackages> results = new EntitySaveResults<>();
        try {
            DeliveryPackages deliveryPackage = getDeliveryPackage(packageId);
            if (deliveryPackage == null) {
                results.setStatus("FAILED");
                results.setError("Could not find any delivery Item");
                return results;
            }

            if ((deliveryPackage.getDeliveryStatus().equals(OrderStatus.CONFIRMED) || deliveryPackage.getDeliveryStatus().equals(
                    OrderStatus.DELIVERED) && !(orderStatus.equals(OrderStatus.CONFIRMED))
            )) {
                results.setStatus("FAILED");
                results.setError("You cannot change status from 'CONFIRMED/DELIVERED' to anything else but 'CONFIRMED'");
                return results;
            }
            deliveryPackage.setDeliveryStatus(orderStatus);
            if (orderStatus.equals(OrderStatus.CONFIRMED) || orderStatus.equals(OrderStatus.DELIVERED)) {
                Order order = deliveryPackage.getOrder();
                order.setDelivered(System.currentTimeMillis());
                this.orderRepository.save(order);
            }
            results = update(deliveryPackage);
            if (results.getStatus().equalsIgnoreCase("SUCCESS")) {
                if (!deliveryPackage.getDeliveryDrone().getDroneStatus().getState().equals(DroneState.IDLE)) {
                    DroneStateLog dSL = new DroneStateLog();
                    dSL.setState(OrderStatus.mapOrderStatusToDroneStatus(orderStatus));
                    dSL.setDrone(deliveryPackage.getDeliveryDrone());
                    dSL.setDroneSerialNumber(deliveryPackage.getDeliveryDrone().getSerialNumber());
                    dSL.setBatteryPercentage(dSL.getDrone().getDroneStatus().getBatteryPercentage());
                    dSL.setLocation(dSL.getDrone().getDroneStatus().getLocation());
                    EntitySaveResults<Drone> dSLResult= this.droneService.updateDroneState(dSL);
                    if (!dSLResult.getStatus().equalsIgnoreCase("SUCCESS")){
                        System.out.println(dSLResult.getError());
                        results.setError("Couldn't update drone state. PLEASE UPDATE DRONE STATE INDEPENDENTLY \n " + dSLResult.getError());
                    }
                }

            }
            return results;

        }catch (Exception e){
            e.printStackTrace();
            results.setStatus("FAILED");
            results.setError(e.getMessage());
            return results;
        }

    }
    private Order saveOrder(Order order){
        if (order == null) return null;
        if (order.getItems().isEmpty()) return null;
        List<OrderItem> orderItems = new ArrayList<>();
        Order orderRes = this.orderRepository.save(order);
        for (OrderItem orderItem: order.getItems()){
            orderItem.setOrder(order);
           OrderItem item = this.orderItemRepository.save(orderItem);
           if (item !=null) orderItems.add(item);
        }

        if (orderItems.isEmpty()) return  null;
        order.setItems(orderItems);
        return order;
    }

    @Override
    public EntitySaveResults<DeliveryPackages> loadDrone(String droneId, Order order) {
        DeliveryPackages deliveryPackages = createDeliveryPackage(droneId, order);
        EntitySaveResults<DeliveryPackages> deliveryPackagesEntitySaveResults = new EntitySaveResults<>();
        try {
            this.deliveryPackageValidator.validateDeliveryPackage(deliveryPackages);
            Order orderRes = this.saveOrder(order);
            if(orderRes == null){
                deliveryPackagesEntitySaveResults.setStatus("FAILED");
                deliveryPackagesEntitySaveResults.setEntity(deliveryPackages);
                deliveryPackagesEntitySaveResults.setError("Failed to persist Order");
                return deliveryPackagesEntitySaveResults;
            }
            deliveryPackages.setOrder(order);
            deliveryPackagesEntitySaveResults= this.save(deliveryPackages);
            if (deliveryPackagesEntitySaveResults.getStatus().equals("SUCCESS")) {
                Drone drone = deliveryPackagesEntitySaveResults.getEntity().getDeliveryDrone();
                drone.setUpdated(deliveryPackagesEntitySaveResults.getEntity().getTimePackages());
                DroneStateLog dSL = new DroneStateLog();
                dSL.setState(DroneState.LOADED);
                dSL.setDrone(drone);
                dSL.setDroneSerialNumber(drone.getSerialNumber());
                dSL.setBatteryPercentage(drone.getDroneStatus().getBatteryPercentage());
                dSL.setLocation(drone.getDroneStatus().getLocation());
                this.droneService.updateDroneState(dSL);
            }

            deliveryPackagesEntitySaveResults.setEntity(deliveryPackages);

        }catch (Exception e){
            deliveryPackagesEntitySaveResults.setError(e.getMessage());
            deliveryPackagesEntitySaveResults.setStatus("FAILED");
            deliveryPackagesEntitySaveResults.setEntity(deliveryPackages);
            return deliveryPackagesEntitySaveResults;
        }
        return deliveryPackagesEntitySaveResults;
    }

    @Override
    public EntitySaveResults<DeliveryPackages> loadDroneWithMedicationIds(String droneId, HashMap<String, Integer> medicationList) {
        HashMap<Medication, Integer> medicationIntegerHashMap = new HashMap<>();
        for(String med : medicationList.keySet()){
            Medication medication = this.medicationService.findMedicationByCode(med);
            if (medication == null){
                    EntitySaveResults<DeliveryPackages> results = new EntitySaveResults<>();
                    results.setStatus("INVALID_MEDICATION_ID");
                    results.setError("One of the provided medication Ids is invalid, " + med);
                    return results;
            }
            medicationIntegerHashMap.put(medication, medicationList.get(med));
        };
        if (medicationIntegerHashMap.isEmpty()) {
            EntitySaveResults<DeliveryPackages> results = new EntitySaveResults<>();
            results.setStatus("INVALID_MEDICATION_ID");
            results.setError("None of the provided medication Ids is valid");
            return results;
        }
        return loadDrone(droneId, medicationIntegerHashMap);
    }

    private DeliveryPackages createDeliveryPackage(String droneId, Order order){
        Drone drone = this.droneService.getDroneBySerialNumber(droneId);
        DeliveryPackages deliveryPackages = new DeliveryPackages();
        deliveryPackages.setDeliveryDrone(drone);
        deliveryPackages.setTimePackages(System.currentTimeMillis());
        deliveryPackages.setDeliveryStatus(OrderStatus.CREATED);
        deliveryPackages.setOrder(order);
        return deliveryPackages;
    }

    public Order createOrder(HashMap<Medication, Integer> medicationList){
        if (medicationList == null || medicationList.size() < 1) return null;
        Order order = new Order();
        order.setOrderedBy(User.currentUser.getUserId().toString());
        order.setRequested(System.currentTimeMillis());
        long totalWeight =0;
        for(Medication medication : medicationList.keySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMedication(medication);
            orderItem.setQuantity(medicationList.get(medication));
            order.getItems().add(orderItem);
            if(medication!=null) totalWeight += medication.getWeight() *  orderItem.getQuantity();
        }
        order.setTotalWeight(totalWeight);
        return order;
    }


    @Override
    public EntitySaveResults<DeliveryPackages> loadDrone(String droneId, HashMap<Medication, Integer> medicationList) {
        Order order = createOrder(medicationList);
        return loadDrone(droneId, order);

    }

    @Override
    public List<Drone> getEligibleDronesForDelivery(HashMap<Medication, Integer> medicationList) {
        int totalWeight =0;
        for (Medication medication:  medicationList.keySet()){
            totalWeight += medicationList.get(medication) * medication.getWeight();
        }
        return this.droneService.getIdleDronesNonLowBatterWithWeightCapacityMore(totalWeight);
    }

    @Override
    public List<Drone> getEligibleDronesForDeliveryUsingMedicationCode(HashMap<String, Integer> medicationList) {
        HashMap<Medication, Integer> medicationIntegerHashMap = new HashMap<>();
        medicationList.keySet().stream().forEach(med ->{
            Medication medication = this.medicationService.findMedicationByCode(med);
            medicationIntegerHashMap.put(medication, medicationList.get(med));
        });

        return getEligibleDronesForDelivery(medicationIntegerHashMap);
    }

    @Override
    public EntitySaveResults<DeliveryPackages> deletePackage(Long packageId) {
        EntitySaveResults<DeliveryPackages> deliveryPackagesEntitySaveResults = new EntitySaveResults<>();
        try {
            DeliveryPackages deliveryPackages = getDeliveryPackage(packageId);
            if (deliveryPackages == null) {
                deliveryPackagesEntitySaveResults.setError("Couldn't any package for the provided Id");
                deliveryPackagesEntitySaveResults.setStatus("INVALID_ID");
                return deliveryPackagesEntitySaveResults;
            }
            Drone drone= deliveryPackages.getDeliveryDrone();
            delete(deliveryPackages);
            deliveryPackagesEntitySaveResults.setEntity(deliveryPackages);
            if (!drone.getDroneStatus().getState().equals(DroneState.IDLE)){

                drone.setUpdated(deliveryPackagesEntitySaveResults.getEntity().getTimePackages());
                DroneStateLog dSL = new DroneStateLog();
                dSL.setState(DroneState.IDLE);
                dSL.setDrone(drone);
                dSL.setDroneSerialNumber(drone.getSerialNumber());
                dSL.setBatteryPercentage(drone.getDroneStatus().getBatteryPercentage());
                dSL.setLocation(drone.getDroneStatus().getLocation());
                EntitySaveResults<Drone> droneEntitySaveResults = this.droneService.updateDroneState(dSL);

                if (!droneEntitySaveResults.getStatus().equalsIgnoreCase("SUCCESS")){
                    deliveryPackagesEntitySaveResults.setError("FAILED TO UPDATE DRONE. PLEASE UPDATE DRONE STATUS INDEPENDENTLY \n "+
                            droneEntitySaveResults.getError());
                }
            }
            return deliveryPackagesEntitySaveResults;

        }catch (Exception e){
            deliveryPackagesEntitySaveResults.setError(e.getMessage());
            deliveryPackagesEntitySaveResults.setStatus("FAILED");
            return deliveryPackagesEntitySaveResults;
        }
    }
}
