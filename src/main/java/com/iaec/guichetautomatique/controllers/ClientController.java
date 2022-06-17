package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.ClientService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class ClientController {

    private ClientService clientService;

    private CompteService compteService;

    @Autowired
    public ClientController(ClientService clientService, CompteService compteService) {

        this.clientService = clientService;
        this.compteService = compteService;
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
    @GetMapping("/client/new")
    public String newClient(Model model){

        List<Compte> listComptes = compteService.findAll();
        Client client = new Client();

        model.addAttribute("listeComptes", listComptes);
        model.addAttribute("client", client);

        return "admin/client_form";
    }
}
