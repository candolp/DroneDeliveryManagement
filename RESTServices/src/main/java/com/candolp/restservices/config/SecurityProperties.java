package com.candolp.restservices.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "sec")
public class SecurityProperties {
    private String testPassword;
    private String testUser;
    private String securityEndpoint;
    private String authenticationType;

    @PostConstruct
    public void init(){
        System.out.println(toString());
    }


    public String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }

    public String getTestUser() {
        return testUser;
    }

    public void setTestUser(String testUser) {
        this.testUser = testUser;
    }

    public String getSecurityEndpoint() {
        return securityEndpoint;
    }

    public void setSecurityEndpoint(String securityEndpoint) {
        this.securityEndpoint = securityEndpoint;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    @Override
    public String toString() {
        return "{" +
                "testPassword:'" + testPassword + '\'' +
                ", testUser:'" + testUser + '\'' +
                ", securityEndpoint:'" + securityEndpoint + '\'' +
                ", authenticationType:'" + authenticationType + '\'' +
                '}';
    }
}
