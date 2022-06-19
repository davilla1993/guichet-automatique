package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.ClientService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CompteController {

    private CompteService compteService;

    private ClientService clientService;
    @Autowired
    public CompteController(CompteService compteService, ClientService clientService) {

        this.compteService = compteService;
        this.clientService = clientService;
    }
    @GetMapping("/comptes")
    public String listComptes(Model model, @RequestParam(name="page", defaultValue="0")int page,
                              @RequestParam(name="size", defaultValue ="10")int size){

        Page<Compte> listComptes = compteService.listCompteByPage(page, size);
        int[] pages = new int[listComptes.getTotalPages()];

        model.addAttribute("title", "Ajouter un nouveau compte");
        model.addAttribute("pages", pages);
        model.addAttribute("listComptes", listComptes.getContent());


        return "admin/listComptes";
    }

    @GetMapping("/compte/new")
    public String newCompte(Model model){

        Compte compte = new Compte();
        List<Client> lastClients = clientService.getLastClient();

        model.addAttribute("title", "Ajouter un nouveau compte");
        model.addAttribute("compte", compte);
        model.addAttribute("lastClients", lastClients);

        return "/admin/compte_form";
    }

    @PostMapping("/compte/save")
    public String saveCompte(@Valid @ModelAttribute("compte") Compte compte, BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "admin/compte_form";
        }

            compteService.create(compte);
            redirectAttributes.addFlashAttribute("message",
                    "Le compte a été créé avec succès");

            return "redirect:/comptes";
    }

}
