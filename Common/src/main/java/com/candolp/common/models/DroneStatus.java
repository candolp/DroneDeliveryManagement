package com.candolp.common.models;

import com.candolp.common.utils.DroneState;

import javax.persistence.*;
import java.util.Date;

@Entity

@Table
public class DroneStatus {
    private Long id;

    @OneToOne(mappedBy = "droneStatus", cascade = CascadeType.DETACH)
    Drones drone;
    @OneToOne(mappedBy = "droneStatus", cascade = CascadeType.REMOVE)
    GPSLocation location;
    Date lastUpdate;
    int batteryPercentage;
    DroneState state;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
