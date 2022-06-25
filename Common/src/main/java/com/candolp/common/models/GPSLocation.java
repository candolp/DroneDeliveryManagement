package com.candolp.common.models;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "droneLocation")
public class GPSLocation {

    @OneToOne(mappedBy = "droneLocation")
    @Id
    Drones drones;
    long longitude;
    long latitude;
    long altitude;
    String googleGPSlink;
    Date lastUpdated;

    public GPSLocation() {
    }

    public GPSLocation(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public GPSLocation(long longitude, long latitude, long altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public GPSLocation(String googleGPSlink) {
        this.googleGPSlink = googleGPSlink;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getAltitude() {
        return altitude;
    }

    public void setAltitude(long altitude) {
        this.altitude = altitude;
    }

    public String getGoogleGPSlink() {
        return googleGPSlink;
    }

    public void setGoogleGPSlink(String googleGPSlink) {
        this.googleGPSlink = googleGPSlink;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
