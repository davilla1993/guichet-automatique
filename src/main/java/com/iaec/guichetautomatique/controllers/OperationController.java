package com.iaec.guichetautomatique.controllers;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import com.iaec.guichetautomatique.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OperationController {

    private OperationService operationService;
    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }
    @RequestMapping("/operations")
    public String index() {
        return "admin/home";
    }
    @RequestMapping("/consulterCompte")
    public String consulter(Model model, int numCompte){
        model.addAttribute("numCompte", numCompte);

        try{
            Compte compte = operationService.consulterCompte(numCompte);
            Page<Operation> listOperations = operationService.listOperation(numCompte, 0, 6);

            model.addAttribute("compte", compte);
            model.addAttribute("listOperations", listOperations.getContent());

        } catch(Exception ex){
            String message = "Aucun compte n'a été trouvé avec ce numéro";
            model.addAttribute("message", message);
        }
        return "admin/home";
    }

}
