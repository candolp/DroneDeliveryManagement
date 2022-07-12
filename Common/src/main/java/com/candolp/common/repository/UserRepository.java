package com.candolp.common.repository;

import com.candolp.common.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByFullNameContaining(String fullName);
    User findUserByUsername(String username);
    User findUsersByUserId(UUID userId);
    User findUsersByEmail(String email);
}