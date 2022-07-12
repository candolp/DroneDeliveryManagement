package com.candolp.restservices.controllers;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStateLog;
import com.candolp.common.services.impl.DroneServiceImpl;
import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneQueryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/drone")
public class DroneController {
    Logger logger = Logger.getLogger(DroneController.class.getName());

    @Autowired
    DroneServiceImpl droneService;

    @PostMapping("/create/v_1")
    public EntitySaveResults<Drone> createDrone(@RequestBody Drone drone){
        logger.info("CREATE DRONE has been invoked");
        logger.info("provided drone: " + drone);
        return droneService.save(drone);
    }

    @PostMapping("/update/v_1")
    public EntitySaveResults<Drone> updateDrone(@RequestBody Drone drone){
        logger.info("UPDATE DRONE has been invoked");
        logger.info("provided drone: " + drone);
        return droneService.update(drone);
    }

    @GetMapping("getDroneById")
    public Drone getDroneBySerialNumber(@RequestParam String serialNumber){
        logger.info("GET DRONE BY SERIAL NUMBER has been invoked");
        logger.info("provided serial number: " + serialNumber);
        return droneService.getDroneBySerialNumber(serialNumber);
    }

    @GetMapping("getDronesByIds")
    public List<Drone> getDronesBySerialNumbers(@RequestParam String serialNumbers){
        logger.info("GET DRONE BY SERIAL NUMBER has been invoked");
        logger.info("provided serial number: " + serialNumbers);
        List<String> drones = Arrays.asList(serialNumbers.trim().split(","));
        return droneService.getDronesBySerialNumbers(drones);
    }

    @GetMapping("searchDronesByName")
    public List<Drone> searchDronesByName(@RequestParam String name){
        logger.info("SEARCH DRONES BY NAME has been invoked");
        logger.info("provided serial number: " + name);

        return droneService.searchDroneByName(name);
    }

    @GetMapping("getDronesByModel")
    public List<Drone> getDronesByModel(@RequestParam String model){
        logger.info("GET DRONE BY Model has been invoked");
        logger.info("provided model: " + model);
        return droneService.getDronesByModel(model);
    }
    @GetMapping("getDronesByState")
    public List<Drone> getDronesByState(@RequestParam String state){
        logger.info("GET DRONE BY State has been invoked");
        logger.info("provided model: " + state);
        return droneService.getDronesWithState(state);
    }

    @GetMapping("getDronesWithStateWithBetterBattery")
    public List<Drone> getDronesWithStateWithBetterBattery(@RequestParam int percentage){
        logger.info("GET IDLE Drones with betterBattery has been invoked");
        logger.info("provided percentage: " + percentage);
        return droneService.getIdleDronesWithCapacityMore(percentage);
    }

    @GetMapping("getDroneByWeight")
    public List<Drone> getDronesByWeight(@Nullable @RequestParam String queryCriteria,@Nullable @RequestParam Integer weight, @Nullable @RequestParam Integer weightLower,@Nullable @RequestParam Integer weightUpper){
        logger.info("GET DRONE BY Weight has been invoked");

        if (queryCriteria == null || queryCriteria.equalsIgnoreCase(DroneQueryConstant.EXACT)){
            if(weight ==null) return new ArrayList<Drone>();
            return droneService.getDronesByWeightCapacity(weight);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.HIGHER)){
            if(weight ==null) return new ArrayList<Drone>();
            return droneService.getDronesWithHigherCapacity(weight);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.LOWER)){
            if(weight ==null) return new ArrayList<Drone>();
            return droneService.getDronesWithLowerCapacity(weight);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.BETWEEN)){
            if(weightUpper ==null || weightLower == null ) return new ArrayList<Drone>();
            return droneService.getDronesWithBetween( weightLower,weightUpper);
        }

        if(weight ==null) return new ArrayList<Drone>();
        return droneService.getDronesByWeightCapacity(weight);
    }


    @GetMapping("getDroneByBatteryPercentage")
    public List<Drone> getDroneByBatteryPercentage(@Nullable @RequestParam String queryCriteria,@Nullable @RequestParam Integer percentage, @Nullable @RequestParam Integer percentageLower,@Nullable @RequestParam Integer percentageUpper){
        logger.info("GET DRONE BY Weight has been invoked");

        if (queryCriteria == null || queryCriteria.equalsIgnoreCase(DroneQueryConstant.EXACT)){
            if(percentage ==null) return new ArrayList<Drone>();
            return droneService.getDronesWithBatterCapacity(percentage);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.HIGHER)){
            if(percentage ==null) return new ArrayList<Drone>();
            return droneService.getDronesWithBatterCapacityGreater(percentage);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.LOWER)){
            if(percentage ==null) return new ArrayList<Drone>();
            return droneService.getDronesWithBatterCapacityLower(percentage);
        }

        if (queryCriteria.equalsIgnoreCase(DroneQueryConstant.BETWEEN)){
            if(percentageUpper ==null || percentageLower == null ) return new ArrayList<Drone>();
            return droneService.getDronesWithBatterCapacityBETWEEN( percentageLower,percentageUpper);
        }

        if(percentage ==null) return new ArrayList<Drone>();
        return droneService.getDronesWithBatterCapacity(percentage);
    }

    @PostMapping("updateDroneStatus/v_1")
    public EntitySaveResults<Drone> updateDroneStatus(@RequestBody DroneStateLog droneStateLog){
        logger.info("GET IDLE Drones with betterBattery has been invoked");
        logger.info("provided percentage: " + droneStateLog.toString());
        return droneService.updateDroneState(droneStateLog);
    }

    @GetMapping("getDroneStatusLogs")
    public List<DroneStateLog> getDroneStatusLogs(@RequestParam String serialNumber){
        logger.info("GET Drone Audit Log BY SERIAL NUMBER has been invoked");
        logger.info("provided serial number: " + serialNumber);
        return droneService.getDroneStatusLogs(serialNumber);
    }


}
