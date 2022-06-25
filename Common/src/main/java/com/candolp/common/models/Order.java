package com.candolp.common.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Timestamp requested;

    @Column
    private Timestamp delivered;

    @Column
    private long totalWeight;

    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private User orderedBy;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderItem> items = new java.util.ArrayList<>();

    public User getOrderedBy() {
        return orderedBy;
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
