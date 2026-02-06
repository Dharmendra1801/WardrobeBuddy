package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.DTOs.UserDTO;
import com.Project.WardrobeBuddy.Models.User;
import com.Project.WardrobeBuddy.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    public boolean register(UserDTO user) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(hashingService.hash(user.getPassword()));
        u.setWardrobeList(new ArrayList<>());
        u.setCategoryList(new ArrayList<>());
        u.setName(user.getName());

        if (userRepo.findById(u.getUsername()).orElse(null)==null) {
            userRepo.save(u);
            return true;
        }
        return false;
    }

    private boolean checkPasswordWrong(String repoPass, String password) {
        return !repoPass.equals(hashingService.hash(password));
    }

    public User getUser(String username) {
        return userRepo.findById(username).orElse(null);
    }

    public int login(UserDTO userDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (tokenService.checkToken(username)) return 0;

        User user = getUser(username);
        if (user==null) return 1;

        if (checkPasswordWrong(user.getPassword(), password)) return 2;

        tokenService.saveToken(username);
        return 3;
    }

    public boolean logout(String username) {
        if (tokenService.checkToken(username)) {
            tokenService.revokeAuth(username);
            return true;
        }
        return false;
    }

    public int delete(UserDTO userDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (logout(username)) {
            User user = getUser(username);
            if (user==null) return Integer.MIN_VALUE;

            if (checkPasswordWrong(user.getPassword(), password)) return 0;
            userRepo.deleteById(username);
            return 1;
        }
        return -1;
    }

    public String getName(String username) {
        User user = userRepo.findById(username).orElse(null);
        if (user==null) return null;
        return user.getName();
    }

    public int updatePassword(String username, String oldPass, String newPass) {
        User user = userRepo.findById(username).orElse(null);
        if (user==null) return 0;

        if (checkPasswordWrong(user.getPassword(),oldPass)) return -1;

        user.setPassword(hashingService.hash(newPass));
        logout(username);

        return 1;
    }

    public boolean updateName(String username, String name) {
        User user = userRepo.findById(username).orElse(null);
        if (user==null) return false;
        user.setName(name);
        return true;
    }
}
