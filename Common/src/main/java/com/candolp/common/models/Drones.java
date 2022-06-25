package com.candolp.common.models;

import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneState;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity

@Table
public class Drones {
    @Id
    @Column(name = "serial_number", nullable = false)
    private UUID serialNumber;

    @Column(nullable = false, columnDefinition = "0")
    private int weightCapacity;
    @Column
    private Timestamp created;
    @Column
    private  Timestamp updated;

    @OneToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(nullable = false)
    @Fetch(FetchMode.JOIN)
    private User createdBy;
    @Enumerated
    @Column(nullable = false)
    private DroneModel model;
    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private DroneStatus status;

    public DroneStatus getStatus() {
        return status;
    }

    public DroneModel getModel() {
        return model;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }


}
