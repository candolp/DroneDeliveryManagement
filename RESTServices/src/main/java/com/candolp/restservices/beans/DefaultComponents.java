package com.candolp.restservices.beans;


import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.User;
import com.candolp.common.services.impl.UserServiceImpl;
import com.candolp.restservices.config.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class DefaultComponents {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityProperties securityProperties;
    private Logger logger = Logger.getLogger(DefaultComponents.class.getName());


    @PostConstruct
    public void init(){
        logger.info("Creating default application");
        User defaultUser = new User();
        defaultUser.setUsername("testuser");
        defaultUser.setFullName("test user");
        defaultUser.setPassWord("testPass");
        defaultUser.setEmail("test@mail.com");
        EntitySaveResults<User> results = userService.checkAndSave(defaultUser);
        if (results.getStatus().contains("SUCCESS")){
            logger.info("a new default user has been created : \n" + results.getEntity());
        }else if (results.getStatus().contains("FAILED")){
            logger.warning("Failed to create default user");
        }
    }


}
