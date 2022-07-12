package com.candolp.restservices.services;


import com.candolp.common.services.impl.UserServiceImpl;
import com.candolp.common.utils.SecConstants;
import com.candolp.restservices.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    UserServiceImpl userServiceImpl;

    private String username;
    private String password;
    private List<String> roles;


    public boolean authenticate(){
        switch (securityProperties.getAuthenticationType()){
            case SecConstants.TEST:
                return authenticWithTest();
            case SecConstants.ANY:
                return noAuthentication();
            case SecConstants.DB:
                return authenticateWithDB();
            default:
                return authenticateWithREST();
        }

    }


    boolean noAuthentication(){
        return true;
    }

    boolean authenticWithTest(){
        return  (username.equalsIgnoreCase(securityProperties.getTestUser()) &&
                password.equalsIgnoreCase(securityProperties.getTestPassword()));
    }

    private boolean authenticateWithDB(){
        return userServiceImpl.authenticate(username, password);
    }

    private boolean authenticateWithREST(){
        return true;
    }





    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
