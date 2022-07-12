package com.candolp.common.validators;

import com.candolp.common.config.DroneSpecificationConfig;
import com.candolp.common.models.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class MedicationValidation {

    @Autowired
    DroneSpecificationConfig droneSpecificationConfig;

    public List<String> validateMedicationCreation(Medication medication)  {
        List<String> violations = new ArrayList<>();

        //checkNameViolation
        if (medication.getName() == null) {
            violations.add("MISSING NAME");
        }else if (medication.getName().trim().isEmpty()){
            violations.add("MISSING NAME");
        }else if(!Pattern.matches(droneSpecificationConfig.getMedicationNamePattern(), medication.getName())){
            violations.add("NAME CONTAINS SPECIAL CHARACTERS");
        }

        if (medication.getCode() == null) {
            violations.add("MISSING CODE");
        }else if (medication.getName().trim().isEmpty()){
            violations.add("MISSING CODE");
        }else if(!Pattern.matches(droneSpecificationConfig.getMedicationCodePattern(), medication.getCode())){
            violations.add("CODE CONTAINS LOWERCASE OR SPECIAL CHARACTER");
        }

        if (medication.getWeight() > droneSpecificationConfig.getWeightLimit()){
            violations.add("MEDICATION IS HEAVIER THAN ANY DRONE WEIGHT CAPACITY");
        }
        return violations;

    }
    public static   String validationErrorBuilder(List<String> errors){
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i=0; i< errors.size(); i++){
            if(i == 0){
                stringBuilder.append("'").append(errors.get(i)).append("'");
            }else {
                stringBuilder.append(",").append("'").append(errors.get(i)).append("'");
            }
            if (i == errors.size() -1){
                stringBuilder.append("]");
            }
        }
        return stringBuilder.toString();
    }
}
