package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.DTOs.WardrobeDTO;
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
public class WardrobeController {

    @Autowired
    TokenService tokenService;

    @Autowired
    WardrobeServices wardrobeServices;


    @PostMapping("/create_wardrobe")
    public ResponseEntity<String> createWardrobe(@RequestBody WardrobeDTO wardrobe,
                                                 @PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (wardrobeServices.createWardrobe(username, wardrobe))
            return ResponseEntity.status(HttpStatus.CREATED).body("Wardrobe saved");

        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Wardrobe name already exists");
    }

    @GetMapping("/show_all_wardrobes")
    public ResponseEntity<List<WardrobeDTO>> showWardrobe(@PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        List<WardrobeDTO> list_of_wardrobes = wardrobeServices.getAllWardrobe(username);
        if (list_of_wardrobes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.FOUND).body(list_of_wardrobes);
    }

    @GetMapping("/show_wardrobe/{wardrobeName}")
    public ResponseEntity<WardrobeDTO> showWardrobe(@PathVariable String username,
                                                    @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        WardrobeDTO wardrobe = wardrobeServices.getWardrobe(username,wardrobeName);
        if (wardrobe==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.FOUND).body(wardrobe);
    }

    @GetMapping("/get_id/{wardrobeName}")
    public ResponseEntity<Long> getWardrobeId(@PathVariable String username,
                                              @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        Long id = wardrobeServices.getId(username,wardrobeName);

        if (id==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.FOUND).body(id);
    }

    @GetMapping("/show_wardrobe_by_Id/{id}")
    public ResponseEntity<WardrobeDTO> showWardrobeById(@PathVariable String username,
                                                        @PathVariable Long id) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        WardrobeDTO wardrobe = wardrobeServices.getWardrobeById(id);

        if (wardrobe==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.FOUND).body(wardrobe);
    }


    @DeleteMapping("/delete_wardrobe/{wardrobeName}")
    public ResponseEntity<String> deleteWardrobe(@PathVariable String username,
                                                 @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        if (wardrobeServices.deleteWardrobe(username,wardrobeName))
            return ResponseEntity.status(HttpStatus.GONE).body("Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @PutMapping("/change_name/{wardrobeName}")
    public ResponseEntity<String> changeName(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestParam String newName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        if (wardrobeServices.changeName(username,wardrobeName,newName))
            return ResponseEntity.status(HttpStatus.GONE).body("Changed");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @PutMapping("/change_note/{wardrobeName}")
    public ResponseEntity<String> changeNote(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestParam String newNote) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        if (wardrobeServices.changeNote(username,wardrobeName,newNote))
            return ResponseEntity.status(HttpStatus.GONE).body("Changed");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

}



