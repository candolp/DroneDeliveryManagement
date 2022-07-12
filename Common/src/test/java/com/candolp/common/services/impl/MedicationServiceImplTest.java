package com.candolp.common.services.impl;

import com.candolp.common.models.Medication;
import com.candolp.common.services.MedicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application.properties"}
)
public class MedicationServiceImplTest {
    @Autowired
    MedicationService medicationService;

    Medication testMedicine;
    Medication currentMedication;

    @Before
    public void init(){
        testMedicine = new Medication();
        String name = UUID.randomUUID().toString();
        testMedicine.setName(name);
        testMedicine.setCode(name.substring(0,7));
        testMedicine.setImage(name);
        testMedicine.setWeight(234);
        this.currentMedication = medicationService.save(testMedicine).getEntity();
    }

    @Test
    public void save() {
        testMedicine = new Medication();
        String name = UUID.randomUUID().toString();
        testMedicine.setName(name);
        testMedicine.setCode(name.substring(0,7));
        testMedicine.setImage(name);
        testMedicine.setWeight(127);
        Medication medication = this.medicationService.save(testMedicine).getEntity();
        assertNotNull(medication);
    }

    @Test
    public void update() {
        this.currentMedication.setWeight(201);
        Medication medication = this.medicationService.update(currentMedication).getEntity();
        assertEquals(medication.getWeight(), 201);
    }



//    @Test
//    public void findAll() {
//        List<Medication> medicationList = this.medicationService.findAll();
//        assertTrue(medicationList.size() > 1);
//    }

    @Test
    public void findMedicationsByLighterWeight() {
        List<Medication> medicationList = this.medicationService.findMedicationsByLighterWeight(200);
        assertTrue(medicationList.size() > 0);
    }

    @Test
    public void findMedicationByContainsCode() {
        List<Medication> medicationList = this.medicationService.findMedicationsByHeavierWeight(200);
        assertTrue(medicationList.size() > 0);
    }
    @Test
    public void delete() {
        this.medicationService.deleteInBatch(medicationService.findAll());
        assertEquals(0, medicationService.findAll().size());
    }
}