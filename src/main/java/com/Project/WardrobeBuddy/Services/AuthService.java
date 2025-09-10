package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.User;
import com.Project.WardrobeBuddy.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    public boolean register(User u) {
        if (userRepo.findById(u.getUsername()).orElse(null)==null) {
            userRepo.save(u);
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {

        User db_user = userRepo.findByUsernameAndPassword(username,password).orElse(null);
        return db_user != null;
    }
}
