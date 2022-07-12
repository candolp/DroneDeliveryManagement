package com.candolp.common.validators;

import com.candolp.common.config.DroneSpecificationConfig;
import com.candolp.common.models.*;
import com.candolp.common.services.DroneService;
import com.candolp.common.services.MedicationService;
import com.candolp.common.utils.DroneState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryPackageValidator {
    @Autowired
    DroneSpecificationConfig droneSpecificationConfig;
    @Autowired
    MedicationService medicationService;
    @Autowired
    DroneService droneService;

    public void validateDeliveryPackage(DeliveryPackages entity) throws Exception {

        if (entity.getDeliveryDrone().getSerialNumber() == null){
            throw new Exception("NO DELIVERY DRONE SPECIFIED");
        }
        Drone drone = droneService.getDroneBySerialNumber(entity.getDeliveryDrone().getSerialNumber());
        if (drone == null) throw  new Exception("INVALID DELIVERY DRONE SPECIFIED");
        if (drone.getDroneStatus().getState() != DroneState.IDLE) throw  new Exception("SPECIFIED DRONE IS NOT IDLE");
        if (drone.getDroneStatus().getBatteryPercentage() < droneSpecificationConfig.getLowBattery()) throw  new Exception("SPECIFIED DRONE IS HAS LOW BATTERY");
        if (entity.getOrder() == null )throw  new Exception("ORDER CANNOT BE NULL");
        //check for medication list
        List<OrderItem> orderItems = entity.getOrder().getItems();
        if (orderItems == null || orderItems.size() < 1) throw  new Exception("Medication items cannot be empty");
        long totalWeight =0;
        for (OrderItem orderItem : orderItems){
            if (orderItem == null) throw  new Exception("AN INVALID MEDICATION WAS ADDED");
            Medication medication = medicationService.findMedicationByCode(orderItem.getMedication().getCode());
            if (medication == null) throw  new Exception("AN INVALID MEDICATION WAS ADDED");
            totalWeight += medication.getWeight() * orderItem.getQuantity();
        }
        if (totalWeight > drone.getWeightCapacity()) throw  new Exception("SPECIFIED DRONE CANNOT CARRY MEDICATION");
    }
}
