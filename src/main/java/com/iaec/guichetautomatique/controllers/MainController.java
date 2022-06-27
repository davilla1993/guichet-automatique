package com.iaec.guichetautomatique.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String homePage(){
        return "login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
