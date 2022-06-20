package com.iaec.guichetautomatique.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class CustomerController {

    @GetMapping("")
    public String showClientHome(){
        return "client/home";
    }
}
