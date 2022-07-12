package com.candolp.common.repository;

import com.candolp.common.models.DroneStateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DroneStateLogRepository extends JpaRepository<DroneStateLog, Long> {
    DroneStateLog findFirstByDroneSerialNumberOrderByLastUpdateAsc(String serialNumber);
    List<DroneStateLog> findAllByDroneSerialNumberAndLastUpdateGreaterThanAndLastUpdateLessThan(String serialNumber, long from, long to);
    List<DroneStateLog> findAllByDroneSerialNumber(String serialNumber);

//    @Query(value = "select dsl from DroneStateLog dsl where dsl.droneSerialNumber = ?1 order by dsl.lastUpdate ASC ")
//    List<DroneStateLog> findDroneStateLogBySerial(String serialNumber);
//
//    @Query(value = "select dsl from DroneStateLog dsl where dsl.droneSerialNumber = ?1 and dsl.lastUpdate > ?1 and dsl.lastUpdate < ?3 order by dsl.lastUpdate ASC ")
//    List<DroneStateLog> findDroneStateLogBySerialBetweenTime(String serialNumber, long from, long to);
}