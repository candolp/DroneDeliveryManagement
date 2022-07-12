package com.candolp.common.services.impl;

import com.candolp.common.config.DroneSpecificationConfig;
import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStateLog;
import com.candolp.common.models.DroneStatus;
import com.candolp.common.models.User;
import com.candolp.common.repository.DroneStateLogRepository;
import com.candolp.common.repository.DroneStatusRepository;
import com.candolp.common.repository.DronesRepository;
import com.candolp.common.repository.GPSLocationRepository;
import com.candolp.common.services.DroneService;
import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneState;
import com.candolp.common.validators.DroneValidator;
import com.candolp.common.validators.MedicationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {
    @Autowired
    DronesRepository dronesRepository;

    @Autowired
    DroneStatusRepository droneStatusRepository;

    @Autowired
    GPSLocationRepository gpsLocationRepository;

    @Autowired
    DroneSpecificationConfig droneSpecificationConfig;

    @Autowired
    DroneStateLogRepository droneStateLogRepository;

    @Autowired
    DroneValidator droneValidator;

    @Override
    public EntitySaveResults<Drone> save(Drone entity) {
        EntitySaveResults<Drone> entitySaveResults = new EntitySaveResults<>();
        try {
            if (entity.getSerialNumber() == null) entity.setSerialNumber(UUID.randomUUID().toString());
            Drone drone = this.dronesRepository.findDronesBySerialNumber(entity.getSerialNumber());


            if (drone != null) {
                entitySaveResults.setStatus("EXISTS");
                entitySaveResults.setEntity(drone);
                return entitySaveResults;
            }
            entity.setUpdated(System.currentTimeMillis());
            entity.setCreated(entity.getUpdated());
            User user = User.currentUser;
            entity.setCreatedBy(User.currentUser.getUserId());
            entity.getDroneStatus().setSerialNumber(entity.getSerialNumber());
            List<String> violations = droneValidator.validateDrone(entity);
            if (violations.size() > 0){
                entitySaveResults.setError(MedicationValidation.validationErrorBuilder(violations));
                entitySaveResults.setStatus("INVALID_BODY");
                return entitySaveResults;
            }
            entitySaveResults.setEntity(dronesRepository.save(entity));
            return entitySaveResults;
        }catch (Exception e){
            e.printStackTrace();
            entitySaveResults.setStatus("FAILED");
            entitySaveResults.setError(e.getMessage());
            return entitySaveResults;
        }
    }

    @Override
    public EntitySaveResults<Drone> update(Drone entity) {
        EntitySaveResults<Drone> entitySaveResults = new EntitySaveResults<>();
        try{
            Drone drone = dronesRepository.findDronesBySerialNumber(entity.getSerialNumber());
            if (drone == null){
                entitySaveResults.setEntity(entity);
                entitySaveResults.setStatus("FAILED");
                entitySaveResults.setError("The specified entity serial number does not exist");
            }
            entity.setUpdated(System.currentTimeMillis());
            List<String> violations = droneValidator.validateDrone(entity);
            if (violations.size() > 0){
                entitySaveResults.setError(MedicationValidation.validationErrorBuilder(violations));
                entitySaveResults.setStatus("INVALID_BODY");
                return entitySaveResults;
            }
            entitySaveResults.setEntity(dronesRepository.save(entity));
            return  entitySaveResults;
        }catch (Exception e){
            entitySaveResults.setStatus("FAILED");
            entitySaveResults.setEntity(null);
            return entitySaveResults;
        }
    }

    @Override
    public void delete(Drone entity) {
        this.dronesRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(String  id) {
    this.dronesRepository.deleteById(id);
    }


    @Override
    public void deleteInBatch(List<Drone> entities) {
        this.dronesRepository.deleteAll(entities);
    }



    @Override
    public List<Drone> findAll() {
        return this.dronesRepository.findAll();
    }
    /*
      * get All Drones with specified model.
      * model to search with. Model can be one of the following:
      * LightWeight,Middleweight,CruiserWeight,Heavyweight
     */
    @Override
    public List<Drone> getDronesByModel(String model) {
        return this.dronesRepository.findAllByModel(DroneModel.getValueOf(model));
    }
    /*
    * get Drones with Exact serial Number
    * param: serialNumber to filter with
     */

    @Override
    public Drone getDroneBySerialNumber(String serialNumber) {
        return this.dronesRepository.findDronesBySerialNumber(serialNumber);
    }

    @Override
    public List<Drone> getDronesBySerialNumbers(List<String> serialNumbers) {
        return this.dronesRepository.findAllBySerialNumberIn(serialNumbers);
    }

    @Override
    public List<Drone> searchDroneByName(String serialNumbers) {
        return  this.dronesRepository.findAllByNameContaining(serialNumbers);
    }

    /*
    * Get all drone with exactly the specified weight capacity
    * param: weight
     */
    @Override
    public List<Drone> getDronesByWeightCapacity(int weight) {
        return this.dronesRepository.findAllByWeightCapacity(weight);
    }

    @Override
    public List<Drone> getDronesWithHigherCapacity(int weight) {
        return this.dronesRepository.findAllByWeightCapacityGreaterThan(weight);
    }

    @Override
    public List<Drone> getDronesWithLowerCapacity(int weight) {
        return this.dronesRepository.findAllByWeightCapacityIsLessThan(weight);
    }

    @Override
    public List<Drone> getDronesWithBetween(int upperWeight, int lowerWeight) {
      return   this.dronesRepository.findAllByWeightCapacityGreaterThanAndAndWeightCapacityLessThan(upperWeight, lowerWeight);
    }


    @Override
    public List<Drone> getDronesWithBatterCapacityLower(int percentage) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository.findAllByBatteryPercentageLessThan(percentage);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public List<Drone> getDronesWithBatterCapacityGreater(int percentage) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository.findAllByBatteryPercentageGreaterThan(percentage);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }
    @Override
    public List<Drone> getDronesWithBatterCapacityBETWEEN(int percentageLower, int percentageUpper) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository.findAllByBatteryPercentageBetween(percentageLower,percentageUpper);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public List<Drone> getDronesWithBatterCapacity(int percentage) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository.findAllByBatteryPercentage(percentage);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public List<Drone> getDronesWithState(String state) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository.findAllByState(DroneState.valueOf(state));
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public List<Drone> getIdleDronesWithCapacityMore(int batterCapacity) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository
                .findDroneStatusesByBatteryPercentageGreaterThanAndState(batterCapacity,DroneState.IDLE);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public List<Drone> getIdleDronesNonLowBatterWithWeightCapacityMore(int weight) {
        List<DroneStatus> droneStatusList = this.droneStatusRepository
                .findIdleDroneByWeightBattery(this.droneSpecificationConfig.getLowBattery(),weight);
        return droneStatusList.stream().map(droneStatus -> {return droneStatus.getDrone();}).collect(Collectors.toList());
    }

    @Override
    public DroneStateLog updateDroneStatus(DroneStateLog droneStateLog) throws Exception{

            Drone drone = this.dronesRepository.findDronesBySerialNumber(droneStateLog.getDroneSerialNumber());

           if (drone == null) return  null;
           droneStateLog.setLastUpdate(System.currentTimeMillis());
           DroneStatus droneStatus = drone.getDroneStatus();
           droneStatus.setState(droneStateLog.getState());
           droneStatus.setBatteryPercentage(droneStateLog.getBatteryPercentage());
           drone.setDroneStatus(droneStatus);
           drone.setUpdated(drone.getUpdated());
           droneStateLog.setDrone(drone);
           this.dronesRepository.save(drone);
           return this.droneStateLogRepository.save(droneStateLog);

    }

    @Override
    public EntitySaveResults<Drone> updateDroneState(DroneStateLog droneStateLog){
        EntitySaveResults<Drone> droneEntitySaveResults = new EntitySaveResults<>();
        try {
            if (droneStateLog == null || droneStateLog.getDroneSerialNumber() == null || droneStateLog.getDroneSerialNumber().trim().isEmpty()) {
                droneEntitySaveResults.setError("INVALID REQUEST BODY");
                droneEntitySaveResults.setStatus("INVALID_BODY");
                return droneEntitySaveResults;
            }
            Drone drone = this.dronesRepository.findDronesBySerialNumber(droneStateLog.getDroneSerialNumber());

            if (drone == null) {
                droneEntitySaveResults.setError("The Provided Serial Number does not link to any drone");
                droneEntitySaveResults.setStatus("INVALID_DRONE_SERIAL_NUMBER");
                return droneEntitySaveResults;
            }
            droneStateLog.setLastUpdate(System.currentTimeMillis());
            DroneStatus droneStatus = drone.getDroneStatus();
//            droneStatus.setLocation(droneStateLog.getLocation());
            droneStatus.setState(droneStateLog.getState());
            droneStatus.setBatteryPercentage(droneStateLog.getBatteryPercentage());
            drone.setDroneStatus(droneStatus);
            drone.setUpdated(drone.getUpdated());
            droneStateLog.setDrone(drone);
            DroneStateLog resLog =  this.droneStateLogRepository.save(droneStateLog);
            drone.getDroneStatus().setLocation(resLog.getLocation());
            droneEntitySaveResults.setEntity(this.dronesRepository.save(drone));
//            droneStateLog.setLocation(droneEntitySaveResults.getEntity().getDroneStatus().getLocation());


        }catch (Exception e){
            droneEntitySaveResults.setError(e.getMessage());
            droneEntitySaveResults.setStatus("FAILED");
            return droneEntitySaveResults;
        }
        return droneEntitySaveResults;
    }

    @Override
    public List<DroneStateLog> getDroneStatusLogs(String droneSerialNumber) {
        return this.droneStateLogRepository.findAllByDroneSerialNumber(droneSerialNumber);
    }

    @Override
    public List<DroneStateLog> getDroneStatusLogsAudit(String droneSerialNumber, long from, long to) {
        return this.droneStateLogRepository.findAllByDroneSerialNumberAndLastUpdateGreaterThanAndLastUpdateLessThan(droneSerialNumber, from, to);
    }
}
