package com.candolp.common.repository;

import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStatus;
import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneStatusRepository extends JpaRepository<DroneStatus, Long> {
    List<DroneStatus> findAllByBatteryPercentage(int percentage);
    List<DroneStatus> findAllByBatteryPercentageLessThan(int percentage);
    List<DroneStatus> findAllByBatteryPercentageGreaterThan(int percentage);
    List<DroneStatus> findAllByBatteryPercentageBetween(int percentageLower,int percentageUpper);
    DroneStatus findDroneStatusBySerialNumber(String id);
    List<DroneStatus> findAllByState(DroneState droneState);
    List<DroneStatus> findDroneStatusesByBatteryPercentageGreaterThanAndState(int batterPercentage, DroneState state);

    @Query(value = "select ds from Drone d, DroneStatus ds where ds.state = 'IDLE' and ds.batteryPercentage > ?1 and d.model = ?2 and d.weightCapacity > ?3 and ds.serialNumber = d.serialNumber")
    List<DroneStatus> findIdleDroneByWeightBatteryModel(int batteryPercentage, DroneModel model, long weightCapacity);

    @Query(value = "select ds from Drone d, DroneStatus ds where ds.state = ?4 and ds.batteryPercentage > ?1 and d.model = ?2 and d.weightCapacity > ?3 and ds.serialNumber = d.serialNumber")
    List<DroneStatus> findStateDroneByWeightBatteryModel(int batteryPercentage, DroneModel model, long weightCapacity, DroneState state);

    @Query(value = "select ds from Drone d, DroneStatus ds where ds.state = 0  and ds.batteryPercentage > ?1  and d.weightCapacity > ?2 and ds.serialNumber = d.serialNumber")
    List<DroneStatus> findIdleDroneByWeightBattery(int batteryPercentage,  int weightCapacity);

}