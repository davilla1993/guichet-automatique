package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String listClients(Model model, @RequestParam(name="page", defaultValue="1")int page,
                              @RequestParam(name="size", defaultValue="6")int size) {
        Page<Client> listClient = clientService.findAllClients(page, size);
        int[] pages = new int[listClient.getTotalPages()];

        model.addAttribute("pages", pages);
        model.addAttribute("listClients", listClient.getContent());

        return "admin/listClients";
    }
}
