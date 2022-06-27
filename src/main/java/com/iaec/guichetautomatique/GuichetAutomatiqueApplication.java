package com.iaec.guichetautomatique;

import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Role;
import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.repository.RoleRepository;
import com.iaec.guichetautomatique.repository.UserRepository;
import com.iaec.guichetautomatique.services.RoleService;
import com.iaec.guichetautomatique.services.UserService;
import com.iaec.guichetautomatique.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GuichetAutomatiqueApplication {

	public static void main(String[] args) {

		SpringApplication.run(GuichetAutomatiqueApplication.class, args);
<<<<<<< HEAD
	}

	@Override
	public void run(String... args) throws Exception {
		/*// Création des roles
		Role role_admin = new Role("ADMIN");
		Role role_user =  new Role("USER");
		roleService.createRole(role_admin);
		roleService.createRole(role_user);

		// Création des utilisateurs
		User user1 = new User("Administrateur", "Principal","90876543", "admin", "admin");
		User user2 = new User("Utilisateur", "Test", "70986547", "user", "user");
		User user3 = new User("GBOSSOU", "Folly", "91554874", "carlo", "1234");
		User user4 = new User("ALADE", "Ghislaine", "95126790","ghis", "1234");
		User user5 = new User("PEREKE", "Prince","93460373", "prince","1234");
		User user6 = new User("DAOUDA","Ayélé Martine", "98670924","martine", "1234");
		userService.create(user1);
		userService.create(user2);
		userService.create(user3);
		userService.create(user4);
		userService.create(user5);
		userService.create(user6);

		// Création des comptes bancaires
		compteService.create(new Compte(15000, user2));
		compteService.create(new Compte(30000, user3));
		compteService.create(new Compte(45000, user3));
		compteService.create(new Compte(90000, user4));

		final List<Compte> comptesClient = compteService.getComptesByUser(user3.getId());
			for(Compte compte : comptesClient){
				System.out.println("Titulaire : " + compte.getUser().getNom());
				System.out.println("Numéro de compte:" + compte.getNumeroCompte());
				System.out.println("Solde : " + compte.getSolde());
		}*/
	}
=======
	}		
>>>>>>> 2e6cd28ee6425d75ab77562e0e85cc633cd4e822
}
