package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.Services.UserService;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String username, @RequestBody String password) {

        if (userService.register(username, password))
            return ResponseEntity.ok("Registration Completed");

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String username, @RequestBody String password) {

        int x = userService.login(username,password);

        if (x==3)
            return ResponseEntity.ok("Login Successful");
        else if (x==2) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong Password");
        } else if (x==1) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Already logged in");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String username) {
        if (userService.logout(username)) {
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in");
    }

    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String username, @RequestBody String password) {

        int x = userService.delete(username,password);
        if (x==1) {
            return ResponseEntity.ok("Account deleted successfully");
        }
        else if (x==0) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong Password");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in");
    }















}
