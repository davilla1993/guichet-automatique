package com.iaec.guichetautomatique.controllers.admin;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.UserService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CompteController {

    private CompteService compteService;

    private UserService userService;
    @Autowired
    public CompteController(CompteService compteService, UserService userService) {

        this.compteService = compteService;
        this.userService = userService;
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
    /*@GetMapping("/comptes/{id}")
    public String listCompteByClient(@PathVariable("id") Integer id, Model model){

        Client user = userService.getById(id);
        Integer idClient = user.getId();

        List<Compte> compte = compteService.getCompteByClient(idClient);

        return "user/comptes";
    }*/

    @GetMapping("/compte/new")
    public String newCompte(Model model){

        Compte compte = new Compte();
        List<User> lastUsers = userService.getLastUser();

        model.addAttribute("title", "Ajouter un nouveau compte");
        model.addAttribute("compte", compte);
        model.addAttribute("lastUsers", lastUsers);

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
                    "Les informations de compte ont ??t?? ajout??es avec succ??s");

            return "redirect:/admin/comptes";
    }

    @GetMapping("/compte/edit/{id}")
    public String editCompte(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes){

        try {
            Compte compte = compteService.findById(id);
            List<User> lastUsers = userService.getLastUser();

            model.addAttribute("title", "Mettre ?? jour les informations");
            model.addAttribute("compte", compte);
            model.addAttribute("lastUsers", lastUsers);

            return "admin/compte_form";

        } catch(Exception ex){
            redirectAttributes.addFlashAttribute("message",ex.getMessage());

            System.out.println(ex.getMessage());

            return "redirect:/admin/comptes";
        }
    }

    @GetMapping("/compte/delete/{id}")
    public String deleteCompte(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        try{
            compteService.delete(id);
            redirectAttributes.addFlashAttribute("message","Le compte a ??t?? supprim?? avec succ??s");

        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());
        }

        return "redirect:/admin/comptes";
    }

}
