package com.candolp.common.repository;

import com.candolp.common.models.DeliveryPackages;
import com.candolp.common.models.Drone;
import com.candolp.common.utils.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPackagesRepository extends JpaRepository<DeliveryPackages, Long>{
    List<DeliveryPackages> findFirstByDeliveryDrone_SerialNumberOrderByTimePackages(String serialNumber);
    List<DeliveryPackages> findDeliveryPackagesByDeliveryDroneOrderByTimePackages(Drone drone);
    DeliveryPackages findTopByDeliveryDroneOrderByTimePackagesDesc(Drone drone);
    List<DeliveryPackages> findAllByDeliveryStatus(OrderStatus orderStatus);
    DeliveryPackages findFirstById(Long id);
}