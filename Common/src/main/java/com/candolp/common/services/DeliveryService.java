package com.candolp.common.services;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.generic.GenericService;
import com.candolp.common.models.DeliveryPackages;
import com.candolp.common.models.Drone;
import com.candolp.common.models.Medication;
import com.candolp.common.models.Order;
import com.candolp.common.utils.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface DeliveryService extends GenericService<DeliveryPackages> {
    List<DeliveryPackages> findDroneStatus(String droneSerialNumber);
    DeliveryPackages findDroneLatestPackage(String droneSerialNumber);
    List<Medication> findDroneLoadedMedication(String droneSerialNumber);
    boolean isDroneFreeForDelivery(String droneSerialNumber);
    DeliveryPackages getDeliveryPackage(Long packageId);

    EntitySaveResults<DeliveryPackages> updateDeliveryStatus(Long packageId, OrderStatus orderStatus);

    EntitySaveResults<DeliveryPackages> loadDrone(String droneId, Order order);

    EntitySaveResults<DeliveryPackages> loadDrone(String droneId, HashMap<Medication, Integer> medicationList);
    EntitySaveResults<DeliveryPackages> loadDroneWithMedicationIds(String droneId, HashMap<String, Integer> medicationList);

    List<Drone> getEligibleDronesForDelivery(HashMap<Medication, Integer> medicationList);

    List<Drone> getEligibleDronesForDeliveryUsingMedicationCode(HashMap<String, Integer> medicationList);
    EntitySaveResults<DeliveryPackages> deletePackage(Long packageId);

}
