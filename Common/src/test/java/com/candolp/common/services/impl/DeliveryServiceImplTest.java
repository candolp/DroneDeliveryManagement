package com.candolp.common.services.impl;

import com.candolp.common.services.DeliveryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application.properties"}
)
public class DeliveryServiceImplTest {
    @Autowired
    DeliveryService deliveryService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
    }

    @Test
    public void loadDrone() {
    }

    @Test
    public void createOrder() {
    }

    @Test
    public void testLoadDrone() {
    }

    @Test
    public void getEligibleDronesForDelivery() {
    }

    @Test
    public void getEligibleDronesForDeliveryUsingMedicationCode() {
    }

    @Test
    public void testSave() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testDelete1() {
    }

    @Test
    public void deleteInBatch() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findDroneStatus() {
    }

    @Test
    public void findDroneLatestPackage() {
    }

    @Test
    public void findDroneLoadedMedication() {
    }

    @Test
    public void isDroneFreeForDelivery() {
    }

    @Test
    public void testLoadDrone1() {
    }

    @Test
    public void testCreateOrder() {
    }

    @Test
    public void testLoadDrone2() {
    }

    @Test
    public void testGetEligibleDronesForDelivery() {
    }

    @Test
    public void testGetEligibleDronesForDeliveryUsingMedicationCode() {
    }
}