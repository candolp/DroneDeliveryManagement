package com.candolp.common.models;

import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneState;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity

@Table
public class Drone {
    @Id
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private int weightCapacity;
    @Column
    private long created;
    @Column
    private  long updated;
    @Column(nullable = false)
    private UUID createdBy;
    @Enumerated
    @Column(nullable = false)
    private DroneModel model;

    private String name;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "serial_number")
    @Fetch(FetchMode.JOIN)
    private DroneStatus droneStatus;

    public DroneStatus getDroneStatus() {
        return droneStatus;
    }

    public void setDroneStatus(DroneStatus droneStatus) {
        this.droneStatus = droneStatus;
    }


    public DroneModel getModel() {
        return model;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }


    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "serialNumber:'" + serialNumber + '\'' +
                ", name:'" + name + '\''+
                ", weightCapacity:" + weightCapacity +
                ", created:" + created +
                ", updated:" + updated +
                ", createdBy:" + createdBy +
                ", model:" + model +
                ", droneStatus" + droneStatus +
                '}';
    }
}
