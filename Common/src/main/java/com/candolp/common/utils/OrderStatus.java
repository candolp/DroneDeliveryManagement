package com.candolp.common.utils;

public enum OrderStatus {
    ORDERED,
    CONFIRMED,
    DELIVERING,
    DELIVERED,
    CREATED;

    public static DroneState mapOrderStatusToDroneStatus(OrderStatus orderStatus){
        switch (orderStatus){
            case CREATED:
                return DroneState.LOADING;
            case ORDERED:
                return DroneState.LOADED;
            case DELIVERING:
                return DroneState.DELIVERING;
            case DELIVERED:
                return DroneState.RETURNING;
            case CONFIRMED:
                return DroneState.IDLE;
            default:
                return null;
        }

    }
}


