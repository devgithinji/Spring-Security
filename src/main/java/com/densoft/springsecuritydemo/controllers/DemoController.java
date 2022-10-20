package com.densoft.springsecuritydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome(){

        return "home";
    }

    @GetMapping("/leaders")
    public String showManager(){

        return "leaders";
    }

    @GetMapping("/systems")
    public String showAdmin() {

        return "systems";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {

        return "access-denied";
    }
}
