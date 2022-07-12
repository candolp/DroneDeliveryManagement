package com.candolp.restservices;

import com.candolp.common.config.DroneSpecificationConfig;
import com.candolp.common.models.User;
import com.candolp.common.services.impl.UserServiceImpl;
import com.candolp.restservices.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

@SpringBootApplication
//@EnableConfigurationProperties({DroneSpecificationConfig.class, SecurityProperties.class})
public class RestServicesApplication {


    public static void main(String[] args) {

        SpringApplication.run(RestServicesApplication.class, args);
    }



}
