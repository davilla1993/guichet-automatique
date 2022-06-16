package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompteController {

    private CompteService compteService;
    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }
    @GetMapping("/comptes")
    public String listComptes(Model model, @RequestParam(name="page", defaultValue="0")int page,
                              @RequestParam(name="size", defaultValue ="10")int size){

        Page<Compte> listComptes = compteService.listCompteByPage(page, size);
        int[] pages = new int[listComptes.getTotalPages()];

        model.addAttribute("pages", pages);
        model.addAttribute("listComptes", listComptes.getContent());

        return "admin/listComptes";
    }
}
