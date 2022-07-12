package com.candolp.common.models;

import com.candolp.common.utils.DroneState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity

@Table()
public class DroneStatus {
    @Id
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "droneStatus", fetch = FetchType.LAZY)
    Drone drone;

    @JoinColumn(name = "id" )
    @OneToOne(cascade = CascadeType.ALL)
    GPSLocation location;
    int batteryPercentage;
    DroneState state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    @Override
    public String toString() {
        return "{" +
                "serialNumber:'" + serialNumber + '\'' +
                ", location:" + location +
                ", batteryPercentage:" + batteryPercentage +
                ", state:" + state +
                '}';
    }
}
