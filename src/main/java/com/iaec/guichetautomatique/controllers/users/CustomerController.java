package com.iaec.guichetautomatique.controllers.users;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.UserService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class CustomerController {

    UserService userService;

    CompteService compteService;

    @Autowired
    public CustomerController(UserService userService, CompteService compteService) {
        this.userService = userService;
        this.compteService = compteService;
    }

    @GetMapping("")
    public String showClientHome(){
        return "client/user_home";
    }

    @GetMapping("/comptes")
    public String getComptesClient(Model model, Principal principal){

        String login = principal.getName();
        User user = userService.findByLogin(login);
        List<Compte> listCompte = compteService.getComptesByUser(user.getId());

        model.addAttribute("listCompte", listCompte);

        return "user/comptes";
    }
}