package com.candolp.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "specifications")
public class DroneSpecificationConfig {
    private int weightLimit;
    private int maxSerialNumber;
    private int lowBattery;
    private  String medicationCodePattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
    private  String medicationNamePattern = "^([A-Za-z][0-9A-Za-z])([_\\-]?([0-9A-Za-z])+)*";



    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getMaxSerialNumber() {
        return maxSerialNumber;
    }

    public void setMaxSerialNumber(int maxSerialNumber) {
        this.maxSerialNumber = maxSerialNumber;
    }

    public int getLowBattery() {
        return lowBattery;
    }

    public void setLowBattery(int lowBattery) {
        this.lowBattery = lowBattery;
    }

    public String getMedicationCodePattern() {
        return medicationCodePattern;
    }

    public void setMedicationCodePattern(String medicationCodePattern) {
        this.medicationCodePattern = medicationCodePattern;
    }

    public String getMedicationNamePattern() {
        return medicationNamePattern;
    }

    public void setMedicationNamePattern(String medicationNamePattern) {
        this.medicationNamePattern = medicationNamePattern;
    }

    @Override
    public String toString() {
        return "{" +
                "weightLimit=" + weightLimit +
                ", maxSerialNumber=" + maxSerialNumber +
                ", lowBattery=" + lowBattery +
                '}';
    }
}
