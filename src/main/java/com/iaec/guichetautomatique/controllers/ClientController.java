package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.ClientService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

        model.addAttribute("listComptes", listComptes);
        model.addAttribute("client", new Client());

        return "admin/client_form";
    }

    @PostMapping("/client/save")
    public String saveClient(@Valid @ModelAttribute("client") Client client,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "admin/client_form";
        }

        clientService.create(client);
        redirectAttributes.addFlashAttribute("message", "Le client a été créé avec succès");
        return "redirect:/clients";
    }

    @GetMapping("/client/edit/{id}")
    public String editClient(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes){
        try {
            Client client = clientService.getById(id);
            model.addAttribute("client", client);

            return "admin/client_form";

        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());

            return "return:/clients";

        }
    }

    @GetMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){

        try{
            clientService.deleteClient(id);
            redirectAttributes.addFlashAttribute("message", "Le client a été supprimé avec succès");

        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.print(ex.getMessage());
        }

            return "redirect:/clients";
    }
}
