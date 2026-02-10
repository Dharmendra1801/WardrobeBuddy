package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.DTOs.UserDTO;
import com.Project.WardrobeBuddy.Services.UserService;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user) {

        if (userService.register(user))
            return ResponseEntity.ok("Registration Completed");

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {

        int x = userService.login(user);

        if (x==3)
            return ResponseEntity.ok("Login Successful");
        else if (x==2) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong Password");
        }
        else if (x==1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username");
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


    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody UserDTO user) {

        int x = userService.delete(user);
        if (x==1) {
            return ResponseEntity.ok("Account deleted successfully");
        }
        else if (x==0) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong Password");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not logged in");
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(@RequestBody String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        String name = userService.getName(username);

        if (name==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User doesn't exist");

        else return ResponseEntity.status(HttpStatus.FOUND).body(name);
    }

    @PutMapping("/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody String username, @RequestHeader String oldPass, @RequestHeader String newPass) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        int x = userService.updatePassword(username,oldPass,newPass);

        if (x==1)
            return ResponseEntity.ok("Updated Successfully");
        else if (x==-1) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Wrong Password");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username");

    }

    @PutMapping("/update/name")
    public ResponseEntity<String> updateName(@RequestBody String username, @RequestParam String name) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        if (userService.updateName(username,name))
            return ResponseEntity.ok("Updated Successfully");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Username");

    }


}
