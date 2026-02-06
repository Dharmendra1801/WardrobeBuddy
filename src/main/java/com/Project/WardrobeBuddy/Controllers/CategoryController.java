package com.Project.WardrobeBuddy.Controllers;

import com.Project.WardrobeBuddy.Services.CategoryService;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/{username}/category")
public class CategoryController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add/{categoryName}")
    public ResponseEntity<String> addCategory(@PathVariable String username,
                                              @PathVariable String categoryName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        categoryService.add(username,categoryName);

        return ResponseEntity.ok("Added");
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAll(@PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());

        return ResponseEntity.ok(categoryService.getAll(username));
    }

    @DeleteMapping("/delete/{categoryName}")
    public ResponseEntity<String> deleteCategory(@PathVariable String username,
                                              @PathVariable String categoryName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required!!!");

        if (categoryService.deleteCategory(categoryName))
            return ResponseEntity.ok("Added");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category doesn't exists");
    }
}
