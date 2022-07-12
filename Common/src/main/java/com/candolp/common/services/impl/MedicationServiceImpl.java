package com.candolp.common.services.impl;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.Medication;
import com.candolp.common.repository.MedicationRepository;
import com.candolp.common.services.MedicationService;
import com.candolp.common.validators.MedicationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    MedicationValidation medicationValidation;

    @Override
    public EntitySaveResults<Medication> save(Medication entity) {
        EntitySaveResults<Medication> entitySaveResults = new EntitySaveResults<>();
        try{
            //validation check
            List<String> results = medicationValidation.validateMedicationCreation(entity);

            if (results != null && results.size()>0){

                entitySaveResults.setStatus("INVALID_BODY");
                entitySaveResults.setError(MedicationValidation.validationErrorBuilder(results));
                return entitySaveResults;
                //validation check over
            }
            Medication medication = this.medicationRepository.findByCode(entity.getCode());
            if (medication != null) {
                entitySaveResults.setEntity(medication);
                entitySaveResults.setStatus("EXISTS");
                return entitySaveResults;
            }
             medication = this.medicationRepository.findByName(entity.getName());
            if (medication != null) {
                entitySaveResults.setEntity(medication);
                entitySaveResults.setStatus("EXISTS");
                return entitySaveResults;
            }
            if(entity.getId() != null) {
                medication = this.medicationRepository.findById(entity.getId()).get();
                if (medication != null) {
                    entitySaveResults.setEntity(medication);
                    entitySaveResults.setStatus("EXISTS");
                    return entitySaveResults;
                }
            }

            entitySaveResults.setEntity(this.medicationRepository.save(entity));
            return entitySaveResults;

        }catch (Exception e){
            entitySaveResults.setStatus("FAILED");
            entitySaveResults.setError(e.getMessage());
            return entitySaveResults;
        }

    }

    @Override
    public EntitySaveResults<Medication> update(Medication entity) {
        EntitySaveResults<Medication> entitySaveResults = new EntitySaveResults<>();

        try{
            List<String> results = medicationValidation.validateMedicationCreation(entity);

            if (results != null && results.size()>0){

                entitySaveResults.setStatus("INVALID_BODY");
                entitySaveResults.setError(MedicationValidation.validationErrorBuilder(results));
                return entitySaveResults;
                //validation check over
            }
            Medication olMedication = this.medicationRepository.getOne(entity.getId());
            if (olMedication ==null ){
                 olMedication = this.medicationRepository.findByCode(entity.getCode());
                 if (olMedication == null){
                     entitySaveResults.setStatus("FAILED");
                     entitySaveResults.setError("THE ENTITY DOES NOT EXIST");
                 }

            }
            entitySaveResults.setEntity(this.medicationRepository.save(entity));
            return  entitySaveResults;
        }catch (Exception e){
            entitySaveResults.setStatus("FAILED");
            entitySaveResults.setError(e.getMessage());
            return entitySaveResults;
        }
    }

    @Override
    public void delete(Medication entity) {
        this.medicationRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteInBatch(List<Medication> entities) {
        medicationRepository.deleteInBatch(entities);
    }

    @Override
    public List<Medication> findAll() {
        return this.medicationRepository.findAll();
    }

    @Override
    public Medication findMedicationByCode(String code) {
        return this.medicationRepository.findByCode(code);
    }

    @Override
    public List<Medication> findMedicationsByHeavierWeight(long weight) {
        return this.medicationRepository.findAllByWeightGreaterThan(weight);
    }

    @Override
    public List<Medication> findMedicationsByLighterWeight(long weight) {
        return this.medicationRepository.findAllByWeightLessThan(weight);
    }






    @Override
    public List<Medication> findMedicationByContainsName(String name) {
        return this.medicationRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Medication> findMedicationByContainsCode(String code) {
        return this.medicationRepository.findAllByCodeContaining(code);
    }

    @Override
    public List<Medication> findByIds(List<Long> ids) {
        return medicationRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Medication> findByCodes(List<String> codes) {
        return medicationRepository.findAllByCodeIn(codes);
    }

    @Override
    public Medication findById(long id) {
        return  medicationRepository.findById(id).get();
    }
}
