package com.candolp.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "droneLocation")
public class GPSLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

//    String drone_status_id;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "drone_status_id")
    private DroneStateLog droneStateLog;
    @Column(nullable = true)
    String longitude;
    @Column(nullable = true)
    String latitude;
    @Column(nullable = true)
    String altitude;
    @Column(nullable = true)
    String googleGPSlink;
    Date lastUpdated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GPSLocation() {
    }

    public GPSLocation(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GPSLocation(String longitude, String latitude, String altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public DroneStateLog getDroneStateLog() {
        return droneStateLog;
    }

    public void setDroneStateLog(DroneStateLog droneStateLog) {
        this.droneStateLog = droneStateLog;
    }

    public GPSLocation(String googleGPSlink) {
        this.googleGPSlink = googleGPSlink;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getGoogleGPSlink() {
        return googleGPSlink;
    }

    public void setGoogleGPSlink(String googleGPSlink) {
        this.googleGPSlink = googleGPSlink;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

//    public String getDrone_status_id() {
//        return drone_status_id;
//    }
//
//    public void setDrone_status_id(String drone_status_id) {
//        this.drone_status_id = drone_status_id;
//    }

    @Override
    public String toString() {
        return "GPSLocation{" +
                "id=" + id +
                ", droneStateLog=" + droneStateLog +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", altitude='" + altitude + '\'' +
                ", googleGPSlink='" + googleGPSlink + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
