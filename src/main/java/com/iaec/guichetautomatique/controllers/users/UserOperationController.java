package com.iaec.guichetautomatique.controllers.users;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.services.CompteService;
import com.iaec.guichetautomatique.services.OperationService;
import com.iaec.guichetautomatique.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserOperationController {

    private OperationService operationService;
    private UserService userService;

    private CompteService compteService;

    @Autowired
    public UserOperationController(OperationService operationService, UserService userService,
                                   CompteService compteService) {

        this.operationService = operationService;
        this.userService  = userService;
        this.compteService = compteService;
    }

    @GetMapping("/operations")
    public String operations(Model model, Principal principal){

        String loggedUser = principal.getName();
        User user = userService.findByLogin(loggedUser);
        List<Compte> listUserComptes = compteService.getComptesByUser(user.getId());

        model.addAttribute("listUserComptes", listUserComptes);

        return "/user/user_operation";
    }

    @GetMapping("/consulterCompte")
    public String consulter(Model model,int numCompte, @RequestParam(name="page", defaultValue="0")int page,
                            @RequestParam(name="size", defaultValue="4")int size){

        try {

            Compte compte = operationService.consulterCompte(numCompte);
            Page<Operation> listOperations = operationService.listOperation(numCompte, page, size);
            int[] pages = new int[listOperations.getTotalPages()];

            model.addAttribute("pages", pages);
            model.addAttribute("compte", compte);
            model.addAttribute("numCompte", numCompte);
            model.addAttribute("listOperations", listOperations.getContent());

        } catch (Exception ex) {
            model.addAttribute("error", ex);
        }

        return "user/user_operation";
    }

    @PostMapping("/retrait")
    public String versement(Model model, int numCompte, int montant) {

        try{
            operationService.retirer(numCompte, montant);

        }catch(Exception ex){
            model.addAttribute("error", ex);
            return "redirect:/user/consulterCompte?numCompte="+numCompte+"&error="+ex.getMessage();

        }

        return "redirect:/user/consulterCompte?numCompte="+numCompte;
    }

}
