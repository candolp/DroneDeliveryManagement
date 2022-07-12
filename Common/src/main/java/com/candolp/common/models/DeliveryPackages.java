package com.candolp.common.models;

import com.candolp.common.utils.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class DeliveryPackages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_drone_serial_number")
    private Drone deliveryDrone;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus deliveryStatus;

    long timePackages;

    public long getTimePackages() {
        return timePackages;
    }

    public void setTimePackages(long timePackages) {
        this.timePackages = timePackages;
    }

    public OrderStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public Order getOrder() {
        return order;
    }

    public Drone getDeliveryDrone() {
        return deliveryDrone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeliveryDrone(Drone deliveryDrone) {
        this.deliveryDrone = deliveryDrone;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDeliveryStatus(OrderStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
