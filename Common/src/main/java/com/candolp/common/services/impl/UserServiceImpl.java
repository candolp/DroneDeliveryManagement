package com.candolp.common.services.impl;

import com.candolp.common.generic.EntitySaveResults;
import com.candolp.common.models.User;
import com.candolp.common.repository.UserRepository;
import com.candolp.common.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public EntitySaveResults<User> save(User entity) {
        return  this.checkAndSave(entity);
    }

    public EntitySaveResults checkAndSave(User entity) {
        EntitySaveResults entitySaveResults = new EntitySaveResults();

        try {
            User user = this.findByName(entity.getUsername());
            if (user != null) {
                user.setEmail(entity.getEmail());
                user.setFullName(user.getFullName());
                entitySaveResults.setStatus("EXIST");
                entitySaveResults.setEntity(user);
                return entitySaveResults;
            } else {

                    entity.setPassWord(BCrypt.hashpw(entity.getPassWord(), BCrypt.gensalt()));
                    entitySaveResults.setEntity((User)this.userRepository.save(entity));
                    entitySaveResults.setStatus("SUCCESS");
                    return entitySaveResults;

            }
        } catch (Exception var4) {
            var4.printStackTrace();
            entitySaveResults.setStatus("FAILED");
            entitySaveResults.setEntity((User)null);
            return entitySaveResults;
        }
    }

    @Override
    public EntitySaveResults<User> update(User entity) {
        EntitySaveResults entitySaveResults = new EntitySaveResults();
        try{
            entitySaveResults.setEntity(this.userRepository.save(entity));
            return entitySaveResults;
            } catch (Exception e) {
             e.printStackTrace();
             entitySaveResults.setStatus("FAILED");
                entitySaveResults.setError(e.getMessage());
                return entitySaveResults;
            }

    }

    @Override
    public void delete(User entity) {
        this.userRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(String id) {
        this.userRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void deleteInBatch(List<User> entities) {
        this.userRepository.deleteInBatch(entities);
    }


    public User find(String id) {
        return this.userRepository.findUsersByUserId(UUID.fromString(id));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = this.findByName(username);
        if (user == null) {
            return false;
        } else {
            boolean res = BCrypt.checkpw(password, user.getPassWord());
            if (res){
                User.currentUser = user;
            }
            return res;
        }
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findUserByUsername(name);
    }

    @Override
    public User getDefaultUserDetails(String userID) {
        return this.userRepository.findUsersByUserId(UUID.fromString(userID));
    }

    @Override
    public User updatePassword(User user) {
        user.setPassWord(BCrypt.hashpw(user.getPassWord(),  BCrypt.gensalt()));
//        user.setLastPasswordChangedDate(Date.valueOf(LocalDate.now()));
        return (User)this.userRepository.save(user);
    }
}
