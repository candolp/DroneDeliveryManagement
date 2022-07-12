package com.candolp.common.utils;

public enum DroneState {
    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING;
    public static DroneState getValueOf(String droneState){
        for(DroneState _droneState : values()){
            if (_droneState.toString().equalsIgnoreCase(droneState)) return _droneState;
        }

        return null;
    }
}
