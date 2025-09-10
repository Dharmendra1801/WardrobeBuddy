package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.Models.User;
import com.Project.WardrobeBuddy.Services.AuthService;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User u) {
        if (service.register(u)) return ResponseEntity.ok("Registration Completed");
        else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader String username, @RequestHeader String password) {
        if (service.login(username,password))
            return ResponseEntity.ok().header("auth_token",tokenService.getToken(username)).body("Login Successful");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth_token") String token) {
        if (tokenService.tokenCheck(token)) {
            tokenService.revokeAuth(token);
            return ResponseEntity.ok("Logout successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in");
    }















}
