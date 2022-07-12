package com.candolp.common.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Transient
    public static User currentUser;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passWord;

    private String fullName;
    private  String email;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "userId:" + userId +
                ", username:'" + username + '\'' +
                ", passWord:'" + passWord + '\'' +
                ", fullName:'" + fullName + '\'' +
                ", email:'" + email + '\'' +
                '}';
    }
}
