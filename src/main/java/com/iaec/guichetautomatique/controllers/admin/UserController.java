package com.iaec.guichetautomatique.controllers.admin;

import com.iaec.guichetautomatique.entities.Role;
import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.services.RoleService;
import com.iaec.guichetautomatique.services.UserService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class UserController {

    private UserService userService;

    private CompteService compteService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, CompteService compteService,
                          RoleService roleService) {

        this.userService = userService;
        this.compteService = compteService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String listClients(Model model) {

        try {
            List<User> listUsers = userService.findAllUsers();

            model.addAttribute("listUsers", listUsers);

        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }

        return "admin/listUsers";
    }
    @GetMapping("/user/new")
    public String newClient(Model model){

        List<Compte> listComptes = compteService.findAll();
        List<Role>  listRoles = roleService.getAllRoles();

        model.addAttribute("listComptes", listComptes);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", new User());
        model.addAttribute("title", "Ajouter un nouveau client");

        return "admin/user_form";
    }
    @PostMapping("/user/save")
    public String saveClient(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "admin/user_form";
        }

        userService.create(user);
        redirectAttributes.addFlashAttribute("message",
                "Les informations du client ont été ajoutées avec succès");

        return "redirect:/admin/users";
    }
    @GetMapping("/user/edit/{id}")
    public String editClient(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes){

            List<Role> listRoles = roleService.getAllRoles();
        try {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("title", "Editer un client");

            return "admin/user_form";

        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.println(ex.getMessage());

            return "return:/admin/users";

        }
    }
    @GetMapping("/user/delete/{id}")
    public String deleteClient(@PathVariable("id")Integer id, RedirectAttributes redirectAttributes){

        try{
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "Le client a été supprimé avec succès");

        }catch(Exception ex){
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            System.out.print(ex.getMessage());
        }

            return "redirect:/admin/users";
    }
}
