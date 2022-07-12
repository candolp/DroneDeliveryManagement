package com.candolp.restservices.controllers;

import com.candolp.common.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {
    @GetMapping("login")
    public User login(@RequestParam String username , @RequestParam String password){
        System.out.println(username + "   pass  " + password);
        User user = new User();
        user.setUsername(username);
        user.setPassWord(password);
        user.setFullName(username);
        user.setUserId(UUID.randomUUID());

        return  user;
    }
}
