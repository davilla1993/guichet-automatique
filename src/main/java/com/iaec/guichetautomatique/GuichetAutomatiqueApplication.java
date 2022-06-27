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
	}		
}
