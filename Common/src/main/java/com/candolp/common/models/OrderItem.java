package com.candolp.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "orders")
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

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", medication:" + medication +
                ", quantity:" + quantity +
//                ", order:" + order +
                '}';
    }
}
