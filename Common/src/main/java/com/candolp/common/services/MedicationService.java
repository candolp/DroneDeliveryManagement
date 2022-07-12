package com.candolp.common.services;

import com.candolp.common.generic.GenericService;
import com.candolp.common.models.Medication;

import java.util.List;


public interface MedicationService extends GenericService<Medication> {
    Medication findMedicationByCode(String code);
    List<Medication> findMedicationsByHeavierWeight(long weight);
    List<Medication> findMedicationsByLighterWeight(long weight);
    List<Medication> findMedicationByContainsName(String name);
    List<Medication> findMedicationByContainsCode(String name);
    List<Medication> findByIds(List<Long> Ids);
    List<Medication> findByCodes(List<String> Ids);
    Medication findById(long id);

}
