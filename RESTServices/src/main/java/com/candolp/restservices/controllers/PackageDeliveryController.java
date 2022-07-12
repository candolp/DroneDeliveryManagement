package com.candolp.restservices.controllers;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.Drone;
import com.candolp.common.models.DeliveryPackages;
import com.candolp.common.requests.CreateDeliveryWithDroneRequest;
import com.candolp.common.services.DeliveryService;
import com.candolp.common.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/delivery")
public class PackageDeliveryController {
    Logger logger = Logger.getLogger(PackageDeliveryController.class.getName());
    @Autowired
    DeliveryService deliveryService;

    @PostMapping("loadDrone/v_1")
    public EntitySaveResults<DeliveryPackages> createWithDelivery(@RequestBody CreateDeliveryWithDroneRequest orderRequest){
        logger.info("LOAD DELIVER has been invoked");
        logger.info("provided drone: " + orderRequest.toString());
        return  deliveryService.loadDroneWithMedicationIds(orderRequest.getDroneSerialNumber(), orderRequest.getOrderItems());
    }

    @PostMapping("getDeliverableDrones")
    public List<Drone> getDeliverableDrones(@RequestBody HashMap<String, Integer> medicationList){
        logger.info("LOAD DELIVER has been invoked");
        return  deliveryService.getEligibleDronesForDeliveryUsingMedicationCode(medicationList);
    }

    @GetMapping("updateOrderStatus")
    public EntitySaveResults<DeliveryPackages> updateOrderStatus(@RequestParam Long packageId, OrderStatus orderStatus){
        logger.info("UPDATE DELIVERY STATUS been invoked");
        logger.info("provided packageId: " + packageId.toString() + ", status: " + orderStatus.toString());
        return this.deliveryService.updateDeliveryStatus(packageId, orderStatus);
    }

    @GetMapping("getDeliveryPackage")
    public DeliveryPackages updateOrderStatus(@RequestParam Long packageId){
        logger.info("GET DELIVERY PACKAGE has been invoked");
        logger.info("provided packageId: " + packageId.toString());
        DeliveryPackages res =  deliveryService.getDeliveryPackage(packageId);
        return res;
    }

    @GetMapping("getDroneLastPackage")
    public DeliveryPackages updateOrderStatus(@RequestParam String serialNumber){
        logger.info("GET DELIVERY PACKAGE has been invoked");
        logger.info("provided packageId: " + serialNumber);
        return  deliveryService.findDroneLatestPackage(serialNumber);
    }

    @DeleteMapping("delete")
    public EntitySaveResults<DeliveryPackages> deletePackage(@RequestParam Long packageId){
        logger.info("GET DELIVERY PACKAGE has been invoked");
        logger.info("provided packageId: " + packageId.toString());
        return this.deliveryService.deletePackage(packageId);
    }
}
