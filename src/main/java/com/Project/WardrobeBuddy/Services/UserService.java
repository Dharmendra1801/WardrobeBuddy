package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.User;
import com.Project.WardrobeBuddy.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    HashingService hashingService;

    @Autowired
    TokenService tokenService;

    public boolean register(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(hashingService.hash(password));
        u.setWardrobeList(new ArrayList<>());

        if (userRepo.findById(u.getUsername()).orElse(null)==null) {
            userRepo.save(u);
            return true;
        }
        return false;
    }

    private boolean checkPassword(User user, String password) {
        return !user.getPassword().equals(hashingService.hash(password));
    }

    public User getUser(String username) {
        return userRepo.findById(username).orElse(null);
    }

    public int login(String username, String password) {
        if (tokenService.checkToken(username)) return 0;

        User user = getUser(username);
        if (user==null) return 1;

        if (checkPassword(user, password)) return 2;

        return 3;
    }

    public boolean logout(String username) {
        if (tokenService.checkToken(username)) {
            tokenService.revokeAuth(tokenService.getToken(username));
            return true;
        }
        return false;
    }

    public int delete(String username, String password) {
        if (logout(username)) {
            User user = getUser(username);
            if (user==null) return Integer.MIN_VALUE;

            if (checkPassword(user, password)) return 0;
            userRepo.deleteById(username);
            return 1;
        }
        return -1;
    }
}
