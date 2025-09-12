package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.Models.Wardrobe;
import com.Project.WardrobeBuddy.Services.WardrobeServices;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/{username}")
public class ProfileController {

    @Autowired
    TokenService tokenService;

    @Autowired
    WardrobeServices wardrobeServices;


    @PostMapping("/create_wardrobe")
    public ResponseEntity<String> createWardrobe(@RequestBody Wardrobe wardrobe,
                                                 @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (wardrobeServices.createWardrobe(wardrobe))
            return ResponseEntity.status(HttpStatus.CREATED).body("Wardrobe saved");

        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Wardrobe already exists");
    }

    @GetMapping("/check_wardrobe/{wardrobeName}")
    public ResponseEntity<String> checkWardrobe(@PathVariable String username,
                                                @PathVariable String wardrobeName,
                                                @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (wardrobeServices.checkWardrobe(username,wardrobeName))
            return ResponseEntity.status(HttpStatus.FOUND).body("Exists");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @DeleteMapping("/delete_wardrobe/{wardrobeName}")
    public ResponseEntity<String> deleteWardrobe(@PathVariable String username,
                                                 @PathVariable String wardrobeName,
                                                 @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (wardrobeServices.deleteWardrobe(username,wardrobeName))
            return ResponseEntity.status(HttpStatus.GONE).body("Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @GetMapping("/show_wardrobe/{wardrobeName}")
    public ResponseEntity<Wardrobe> showWardrobe(@PathVariable String username,
                                                 @PathVariable String wardrobeName,
                                                 @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Wardrobe());

        Wardrobe wardrobe = wardrobeServices.getWardrobe(username,wardrobeName);
        if (wardrobe==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Wardrobe());

        return ResponseEntity.status(HttpStatus.FOUND).body(wardrobe);
    }

    @GetMapping("/show_all_wardrobes")
    public ResponseEntity<List<Wardrobe>> showWardrobe(@PathVariable String username,
                                             @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());

        List<Wardrobe> list_of_wardrobes = wardrobeServices.getAllWardrobe(username);
        if (list_of_wardrobes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());

        return ResponseEntity.status(HttpStatus.FOUND).body(list_of_wardrobes);
    }
}



