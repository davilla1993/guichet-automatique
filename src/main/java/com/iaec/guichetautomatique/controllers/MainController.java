package com.iaec.guichetautomatique.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String showHome(Model model) {
        return "admin/home";
    }

    @GetMapping("/operations")
    public String showOperations(Model model) {
        return "admin/operation";
    }

    /*@GetMapping("/clients")
    public String showClients(Model model) {
        return "admin/listClients";
    }*/

    @GetMapping("/comptes")
    public String showComptes(Model model) {
        return "admin/listComptes";
    }
}
