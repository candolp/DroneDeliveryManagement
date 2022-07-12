package com.candolp.common.validators;

import com.candolp.common.config.DroneSpecificationConfig;
import com.candolp.common.models.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DroneValidator {
    @Autowired
    DroneSpecificationConfig droneSpecificationConfig;

    public List<String> validateDrone(Drone drone){
        List<String> violations = new ArrayList<>();

        if (drone.getSerialNumber() == null){
            violations.add("SERIAL NUMBER IS EMPTY");
        }
        if (drone.getSerialNumber().trim().length() > droneSpecificationConfig.getMaxSerialNumber()){
            violations.add("SERIAL NUMBER EXCEEDS " + droneSpecificationConfig.getMaxSerialNumber());
        }
        if (drone.getWeightCapacity() > droneSpecificationConfig.getWeightLimit() ||
                drone.getWeightCapacity() < 1){
            violations.add("DRONE WEIGHT CAPACITY MUST BE BETWEEN 1 AND " + droneSpecificationConfig.getWeightLimit() + " grams");
        }

        return violations;
    }
}
