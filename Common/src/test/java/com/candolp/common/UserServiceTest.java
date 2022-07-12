package com.candolp.common;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.User;
import com.candolp.common.services.impl.UserServiceImpl;
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
public class UserServiceTest {
    @Autowired
    UserServiceImpl userService;
    private EntitySaveResults<User> entitySaveResults;



    @Before
    public  void initTest(){
        User user = new User();
        user.setPassWord("testPass1");
        user.setUsername("test1");
        user.setFullName("Test User 1");
        this.entitySaveResults = this.userService.checkAndSave(user);
    }

    @Test
    public void createUserTest(){
        User user = new User();
        user.setPassWord("testPass");
        user.setUsername("test");
        user.setFullName("Test User");
        user.setEmail("test@mail.com");
//        this.userService.save(user);
//        assertEquals(user.getFullName(), this.userService.findByName(user.getUsername()).getFullName());
        EntitySaveResults<User> entitySaveResults1 = this.userService.checkAndSave(user);
        assertNotNull(entitySaveResults1.getEntity());
    }
    @Test
    public void updateUserTest(){
        User user2 = (User) this.entitySaveResults.getEntity();
        user2.setEmail("updated@test.com");
        EntitySaveResults entitySaveResults1 = this.userService.checkAndSave(user2);
        assertEquals("updated@test.com", ((User) entitySaveResults1.getEntity()).getEmail());
    }

    @Test
    public void getUserTest(){
        User user = this.userService.findByName("test1");
        assertEquals((this.entitySaveResults.getEntity().getFullName()), user.getFullName());
    }

    @Test
    public void authenticationTest(){
        assertTrue(this.userService.authenticate("test1", "testPass1"));
    }

    @Test
    public void deleteUserTest(){
        this.userService.delete(this.entitySaveResults.getEntity());
        assertNull(this.userService.getDefaultUserDetails(this.entitySaveResults.getEntity().getUserId().toString()));
    }

//
//    @After
//    public void destroy(){
//        this.userService.delete(this.entitySaveResults.getUser());
//    }

}
