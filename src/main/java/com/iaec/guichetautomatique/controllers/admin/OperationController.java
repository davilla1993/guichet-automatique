package com.iaec.guichetautomatique.controllers.admin;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import com.iaec.guichetautomatique.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class OperationController {

    private OperationService operationService;
    @Autowired
    public OperationController(OperationService operationService) {

        this.operationService = operationService;
    }
    @GetMapping("/operations")
    public String operations(){

        return "admin/operation";
    }
    @GetMapping("/consulterCompte")
    public String consulter(Model model, int numCompte, @RequestParam(name="page", defaultValue="0")int page,
                            @RequestParam(name="size", defaultValue="4")int size){
        String message = "";

        model.addAttribute("numCompte", numCompte);

        try {

            Compte compte = operationService.consulterCompte(numCompte);
            Page<Operation> listOperations = operationService.listOperation(numCompte, page, size);
            int[] pages = new int[listOperations.getTotalPages()];

            model.addAttribute("pages", pages);
            model.addAttribute("compte", compte);
            model.addAttribute("listOperations", listOperations.getContent());

        } catch (Exception ex) {
            message = "Aucun compte n'a été trouvé avec ce numéro";
        }

        model.addAttribute("message", message);

        return "admin/operation";
    }
    @PostMapping("/depot")
    public String versement(Model model, int numCompte, int montant) {

        try{
            operationService.verser(numCompte, montant);

        }catch(Exception ex){
            model.addAttribute("error", ex);
            return "redirect:/admin/consulterCompte?numCompte="+numCompte+"&error="+ex.getMessage();

        }

        return "redirect:/admin/consulterCompte?numCompte="+numCompte;
    }

}
