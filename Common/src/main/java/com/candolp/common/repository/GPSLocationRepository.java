package com.candolp.common.repository;

import com.candolp.common.models.Drone;
import com.candolp.common.models.GPSLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GPSLocationRepository extends JpaRepository<GPSLocation, Drone> {

    List<GPSLocation> findAll();
    List<GPSLocation> findAllByDroneStateLog_DroneSerialNumber(String droneId);
}