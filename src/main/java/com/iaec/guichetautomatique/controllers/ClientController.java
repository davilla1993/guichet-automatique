package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {

        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String listClients(Model model) {
        String message="";

        try {
            List<Client> listClients = clientService.findAllClients();

            model.addAttribute("listClients", listClients);

        } catch (Exception e) {
            message = e.getMessage();
        }

        model.addAttribute("message", message);
        return "admin/listClients";

    }
}
