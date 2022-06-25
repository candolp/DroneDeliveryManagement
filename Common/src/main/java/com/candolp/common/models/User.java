package com.candolp.common.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, columnDefinition = "default")
    private String passWord;

    private String fullName;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
