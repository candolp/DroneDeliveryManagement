package com.candolp.common.repository;

import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStatus;
import com.candolp.common.utils.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Repository
public interface DronesRepository extends JpaRepository<Drone, String> {
    Drone findDronesBySerialNumber(String serialNumber);
    List<Drone> findAllBySerialNumberIn(List<String> strings);
    List<Drone> findAllByModel(DroneModel model);
    List<Drone> findAllByNameContaining(String name);
    List<Drone> findAllByCreated(Time created);
    List<Drone> findAllByWeightCapacityGreaterThan(int weight);
    List<Drone> findAllByWeightCapacity(int weight);
    List<Drone> findAllByWeightCapacityGreaterThanAndAndWeightCapacityLessThan(int upperWeight, int lowerWeight);
    List<Drone> findAllByWeightCapacityIsLessThan(int weight);
    List<Drone> findDronesByCreatedBy(String userId);

}