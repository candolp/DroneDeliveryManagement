package com.candolp.restservices.controllers;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.User;
import com.candolp.common.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/api/user")
public class UserController {
    Logger logger = Logger.getLogger(UserController.class.getName());
    @Autowired
    UserServiceImpl userService;


    @PostMapping("/createUser/v_10")
    public EntitySaveResults<User> createUser(@RequestBody User user){
        logger.info(user.toString());
//        user.setUserId(UUID.randomUUID());
        return  userService.checkAndSave(user);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
//        logger.info(user.toString());
//        user.setUserId(UUID.randomUUID());
        return  userService.findAll();
    }


}
