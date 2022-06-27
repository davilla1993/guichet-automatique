package com.iaec.guichetautomatique.controllers.users;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.security.iGuichetUserDetails;
import com.iaec.guichetautomatique.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profil")
    public String profilDetails(@AuthenticationPrincipal iGuichetUserDetails loggedUser, Model model){

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);

        model.addAttribute("user", user);

        return "user/profil_page";

    }

    @PostMapping("/profil/update")
    public String updateProfil(@Valid @ModelAttribute("user") User user, BindingResult result,
                               @AuthenticationPrincipal iGuichetUserDetails loggedUser,
                               RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "/user/profil_page";
        }

        User savedUser = userService.updateAccount(user);
        loggedUser.setFirstName(user.getPrenom());
        loggedUser.setLastName(user.getNom());

        redirectAttributes.addFlashAttribute("message", "Votre profil a été mis à jour avec succès");

        return "redirect:/user/profil?loggedUser="+loggedUser;
    }
}
