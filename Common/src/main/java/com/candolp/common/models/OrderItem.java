package com.candolp.common.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(nullable = false)
    @Fetch(FetchMode.SELECT)
    private Medication medication;

    @Column(nullable = false, columnDefinition = "1")
    private int quantity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public Medication getMedication() {
        return medication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
