package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.DTOs.WardrobeDTO;
import com.Project.WardrobeBuddy.Services.WardrobeService;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/{username}/wardrobe")
public class WardrobeController {

    @Autowired
    TokenService tokenService;

    @Autowired
    WardrobeService wardrobeService;


    @PostMapping("/new")
    public ResponseEntity<String> createWardrobe(@RequestBody WardrobeDTO wardrobe,
                                                 @PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (wardrobeService.createWardrobe(username, wardrobe))
            return ResponseEntity.status(HttpStatus.CREATED).body("Wardrobe saved");

        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Wardrobe name already exists");
    }

    @GetMapping("/all")
    public ResponseEntity<List<WardrobeDTO>> showWardrobe(@PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());

        List<WardrobeDTO> list_of_wardrobes = wardrobeService.getAllWardrobe(username);

        return ResponseEntity.status(HttpStatus.FOUND).body(list_of_wardrobes);
    }

    @GetMapping("/name/{wardrobeName}")
    public ResponseEntity<WardrobeDTO> showWardrobe(@PathVariable String username,
                                                    @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new WardrobeDTO());

        WardrobeDTO wardrobe = wardrobeService.getWardrobe(username,wardrobeName);
        if (wardrobe==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WardrobeDTO());

        return ResponseEntity.status(HttpStatus.FOUND).body(wardrobe);
    }


    @GetMapping("/id/{wardrobeName}")
    public ResponseEntity<Long> getWardrobeId(@PathVariable String username,
                                              @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1L);

        Long id = wardrobeService.getId(username,wardrobeName);

        if (id==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);

        return ResponseEntity.status(HttpStatus.FOUND).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WardrobeDTO> showWardrobeById(@PathVariable String username,
                                                        @PathVariable Long id) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new WardrobeDTO());

        WardrobeDTO wardrobe = wardrobeService.getWardrobeById(id);

        if (wardrobe==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WardrobeDTO());

        return ResponseEntity.status(HttpStatus.FOUND).body(wardrobe);
    }


    @DeleteMapping("/delete/{wardrobeName}")
    public ResponseEntity<String> deleteWardrobe(@PathVariable String username,
                                                 @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");

        if (wardrobeService.deleteWardrobe(username,wardrobeName))
            return ResponseEntity.status(HttpStatus.GONE).body("Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @PutMapping("/change_name/{wardrobeName}")
    public ResponseEntity<String> changeName(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestParam String newName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");

        if (wardrobeService.changeName(username,wardrobeName,newName))
            return ResponseEntity.status(HttpStatus.OK).body("Changed");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

    @PutMapping("/change_note/{wardrobeName}")
    public ResponseEntity<String> changeNote(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestParam String newNote) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");

        if (wardrobeService.changeNote(username,wardrobeName,newNote))
            return ResponseEntity.status(HttpStatus.OK).body("Changed");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wardrobe doesn't exist");
    }

}



