package com.candolp.restservices.controllers;



import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.Medication;
import com.candolp.common.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;



@RestController
@RequestMapping(path = "/api/medication")
@ComponentScan("com.candolp.common")
public class MedicationController {
    Logger logger = Logger.getLogger(MedicationController.class.getName());
    @Autowired
    MedicationService medicationService;

    @PostMapping("/createMedication/v_1")
    public EntitySaveResults<Medication> createMedication(@RequestBody Medication medication){
        logger.info("CREATE MEDICATION ENDPOINT");
        logger.info("request body: " + medication.toString());
        return medicationService.save(medication);
    }

    @PostMapping("/update/v_1")
    public EntitySaveResults<Medication> update(@RequestBody Medication medication){
        logger.info("Update MEDICATION ENDPOINT");
        logger.info("request body: " + medication.toString());
        return medicationService.update(medication);
    }

    @GetMapping("getById")
    public Medication getMedication(@RequestParam long id){

        logger.info("GET MEDICATION BY IDS invoked");
        logger.info("provided ID: " + id);
        return medicationService.findById(id);
    }

    @GetMapping("getByIds")
    public List<Medication> getMedications(@RequestParam String ids){
        List<String>   idsList = Arrays.asList(ids.trim().split(","));
        logger.info("GET MEDICATION BY IDS invoked");
        logger.info("provided IDs: " + ids);
        List<Long> longIds = new ArrayList<>();
        for (String id: idsList){
            Long longId =Long.parseLong(id);
            longIds.add(longId);
        }
        return medicationService.findByIds(longIds);
    }

    @GetMapping("getByCodes")
    public List<Medication> getMedicationByCodes(@RequestParam String codes){
        List<String>   codesList = Arrays.asList(codes.trim().split(","));
        logger.info("GET MEDICATION BY Codes invoked");
        logger.info("provided Code: " + codes);
        return medicationService.findByCodes(codesList);
    }

    @GetMapping("getByCode")
    public Medication getMedicationByCode(@RequestParam String code){

        logger.info("GET MEDICATION BY Code invoked");
        logger.info("provided Codes: " + code);
        return medicationService.findMedicationByCode(code);
    }

    @GetMapping("searchByCode")
    public List<Medication> searchMedicationByCode(@RequestParam String code){
        logger.info("GET MEDICATION BY Code invoked");
        logger.info("provided Codes: " + code);
        return medicationService.findMedicationByContainsCode(code);
    }

    @GetMapping("searchByName")
    public List<Medication> searchMedicationByName(@RequestParam String name){
        logger.info("GET MEDICATION BY Code invoked");
        logger.info("provided Codes: " + name);
        return medicationService.findMedicationByContainsName(name);
    }

    @DeleteMapping("deleteById")
    public EntitySaveResults<Medication> deleteById(@RequestParam Long id){
        logger.info("GET MEDICATION BY Code invoked");
        logger.info("provided Codes: " + id);

        EntitySaveResults<Medication> res = new EntitySaveResults<>();
        try {
            Medication medication = this.medicationService.findById(id);
            if (medication == null) {
                res.setError("No Medicine found for the provided Id");
                res.setStatus("Invalid_Id");
                return res;
            }
            medicationService.delete(medication);
            res.setEntity(medication);
            return res;
        }catch (Exception e){
            res.setError(e.getMessage());
            res.setStatus("Invalid_Id");
            return res;
        }
    }

    @DeleteMapping("deleteByCode")
    public EntitySaveResults<Medication> deleteByCode(@RequestParam String code){
        logger.info("GET MEDICATION BY Code invoked");
        logger.info("provided Codes: " + code);
        EntitySaveResults<Medication> res = new EntitySaveResults<>();
        Medication medication = this.medicationService.findMedicationByCode(code);
        if (medication == null){
            res.setError("No Medicine found for the provided code");
            res.setStatus("Invalid_code");
            return  res;
        }
        medicationService.delete(medication);
        res.setEntity(medication);
        return  res;
    }


}
