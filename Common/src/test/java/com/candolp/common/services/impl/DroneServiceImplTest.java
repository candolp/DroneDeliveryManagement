package com.candolp.common.services.impl;

import com.candolp.common.models.Drone;
import com.candolp.common.models.DroneStateLog;
import com.candolp.common.models.DroneStatus;
import com.candolp.common.models.User;
import com.candolp.common.services.DroneService;
import com.candolp.common.utils.DroneModel;
import com.candolp.common.utils.DroneState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application.properties"}
)
public class DroneServiceImplTest {
    @Autowired
    DroneService droneService;

    @Autowired
    UserServiceImpl userService;

    Drone baseDrone;
    User currentUser;
    DroneStateLog droneStateLog1;
    DroneStateLog droneStateLog2;
    DroneStateLog droneStateLog3;
    DroneStatus droneStatus;
    Drone currentDrone;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setPassWord("testPass1");
        user.setUsername("test1");
        user.setFullName("Test User 1");
        this.currentUser = (User) this.userService.checkAndSave(user).getEntity();
        this.baseDrone = new Drone();
        baseDrone.setCreated(System.currentTimeMillis());
        baseDrone.setCreatedBy(currentUser.getUserId());
        baseDrone.setModel(DroneModel.CruiserWeight);
        baseDrone.setWeightCapacity(500);
        droneStatus = new DroneStatus();
        droneStatus.setBatteryPercentage(100);
        droneStatus.setState(DroneState.IDLE);
        droneStatus.setDrone(baseDrone);
        baseDrone.setDroneStatus(droneStatus);

        //drone status setup
        droneStateLog1 = new DroneStateLog();

        droneStateLog2 = new DroneStateLog();

        droneStateLog3 = new DroneStateLog();
        this.currentDrone = this.droneService.save(baseDrone).getEntity();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() {
        assertNotNull(currentDrone);
        assertNotNull(currentDrone.getDroneStatus());
        assertNotNull(currentDrone.getDroneStatus().getSerialNumber());
    }

    @Test
    public void update() {
        this.currentDrone.setModel(DroneModel.Heavyweight);
        Drone updatedDrone = this.droneService.update(this.currentDrone).getEntity();
        assertEquals(updatedDrone.getModel(), this.currentDrone.getModel());
    }

//    @Test
//    public void delete() {
//        this.droneService.delete(this.currentDrone);
//        assertNull(this.droneService.getDroneBySerialNumber(this.currentDrone.getSerialNumber()));
//    }

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
    public void find() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void getDronesByModel() {
    }

    @Test
    public void getDroneBySerialNumber() {
    }

    @Test
    public void getDronesByWeightCapacity() {
    }

    @Test
    public void getDronesWithHigherCapacity() {
    }

    @Test
    public void getDronesWithLowerCapacity() {
    }

    @Test
    public void getDronesWithBetween() {
    }

    @Test
    public void getDronesWithBatterCapacityLower() {
    }

    @Test
    public void getDronesWithBatterCapacityGreater() {
    }

    @Test
    public void getDronesWithBatterCapacity() {
    }

    @Test
    public void getDronesWithState() {
    }

    @Test
    public void getIdleDronesWithCapacityMore() {
    }

    @Test
    public void getIdleDronesNonLowBatterWithWeightCapacityMore() {
    }

    @Test
    public void updateDroneStatus() {
//        droneStateLog1.setDrone(this.currentDrone);
//        droneStateLog2.setDrone(this.currentDrone);
//        droneStateLog3.setDrone(this.currentDrone);

        droneStateLog1.setDroneSerialNumber(this.currentDrone.getSerialNumber());
        droneStateLog2.setDroneSerialNumber(this.currentDrone.getSerialNumber());
        droneStateLog3.setDroneSerialNumber(this.currentDrone.getSerialNumber());

        droneStateLog1.setState(DroneState.DELIVERING);
        droneStateLog1.setBatteryPercentage(90);

        droneStateLog2.setBatteryPercentage(50);
        droneStateLog2.setState(DroneState.IDLE);
        try {

            //first State Update
            this.droneService.updateDroneStatus(droneStateLog1);
            Drone updteDrone1 = this.droneService.getDroneBySerialNumber(droneStateLog1.getDroneSerialNumber());

            assertEquals(updteDrone1.getDroneStatus().getBatteryPercentage(), 90);
            assertEquals(updteDrone1.getDroneStatus().getState(), DroneState.DELIVERING);


            //second State Update
            this.droneService.updateDroneStatus(droneStateLog2);
            Drone updteDrone2 = this.droneService.getDroneBySerialNumber(droneStatus.getSerialNumber());

            assertEquals(updteDrone2.getDroneStatus().getBatteryPercentage(), 50);
            assertEquals(updteDrone2.getDroneStatus().getState(), DroneState.IDLE);


            // test Drone State Logs

            List<DroneStateLog> audit = this.droneService.getDroneStatusLogs(this.currentDrone.getSerialNumber());

            assertTrue(audit.size() >1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}