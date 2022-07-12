package com.candolp.common.models;

import com.candolp.common.utils.DroneState;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
public class DroneStateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(insertable = false, updatable = false)
    private String droneSerialNumber;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "droneSerialNumber")
    Drone drone;
    @JoinColumn(name = "id")
    @OneToOne(cascade = CascadeType.ALL)
    GPSLocation location;
    long lastUpdate;
    int batteryPercentage;
    DroneState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public GPSLocation getLocation() {
        return location;
    }

    public void setLocation(GPSLocation location) {
        this.location = location;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }


    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", droneSerialNumber:'" + droneSerialNumber + '\'' +
                ", drone:" + drone +
                ", location:" + location +
                ", lastUpdate:" + lastUpdate +
                ", batteryPercentage:" + batteryPercentage +
                ", state:" + state +
                '}';
    }
}
