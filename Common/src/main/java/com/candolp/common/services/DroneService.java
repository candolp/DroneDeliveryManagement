package com.candolp.common.services;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.generic.GenericService;
import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStateLog;
import com.candolp.common.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DroneService extends GenericService<Drone> {
    List<Drone> getDronesByModel(String models);
    Drone getDroneBySerialNumber(String serialNumber);
    List<Drone> getDronesBySerialNumbers(List<String > serialNumbers);
    List<Drone> searchDroneByName(String serialNumbers);
    List<Drone> getDronesByWeightCapacity(int weight);
    List<Drone> getDronesWithHigherCapacity(int weight);
    List<Drone> getDronesWithLowerCapacity(int weight);
    List<Drone> getDronesWithBetween(int upperWeight, int lowerWeight);
    List<Drone> getDronesWithBatterCapacityLower(int percentage);
    List<Drone> getDronesWithBatterCapacityGreater(int percentage);
    List<Drone> getDronesWithBatterCapacityBETWEEN(int percentageLower, int percentageUpper);
    List<Drone> getDronesWithBatterCapacity(int percentage);
    List<Drone> getDronesWithState(String state);
    List<Drone> getIdleDronesWithCapacityMore(int percentage);
    List<Drone> getIdleDronesNonLowBatterWithWeightCapacityMore(int capacity);

    DroneStateLog updateDroneStatus(DroneStateLog droneStateLog) throws Exception;
    EntitySaveResults<Drone> updateDroneState(DroneStateLog droneStateLog) ;

    List<DroneStateLog> getDroneStatusLogs(String droneSerialNumber);
    List<DroneStateLog> getDroneStatusLogsAudit(String droneSerialNumber, long from, long to);

}
