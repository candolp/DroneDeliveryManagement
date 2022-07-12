package com.candolp.common.utils;

public enum DroneModel {
    LightWeight,
    Middleweight,
    CruiserWeight,
    Heavyweight;

    public static DroneModel getValueOf(String droneModel){
        for(DroneModel _droneModel : values()){
            if (_droneModel.toString().equalsIgnoreCase(droneModel)) return _droneModel;
        }

        return null;
    }
}

