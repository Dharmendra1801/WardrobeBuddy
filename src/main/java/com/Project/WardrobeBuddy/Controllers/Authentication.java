package com.Project.WardrobeBuddy.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Authentication {

    @GetMapping("/")
    public String demo() {
        return "works";
    }

    @PostMapping("/")
    public String demoo(@RequestBody String stuff) {
        System.out.println(stuff);
        return stuff.toUpperCase();
    }
}
