package com.candolp.common.repository;

import com.candolp.common.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDeliveredGreaterThan(long delivered);
    List<Order> findAllByRequestedGreaterThan(long requested);
    List<Order> findAllByDeliveredBetween(long from, long to);
    List<Order> findAllByRequestedBetween(long from, long to);
    Order findOrderById(long id);

}