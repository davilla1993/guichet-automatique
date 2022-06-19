package com.iaec.guichetautomatique;

import com.iaec.guichetautomatique.entities.Client;
import com.iaec.guichetautomatique.entities.Compte;
import com.iaec.guichetautomatique.entities.Operation;
import com.iaec.guichetautomatique.entities.Versement;
import com.iaec.guichetautomatique.repository.ClientRepository;
import com.iaec.guichetautomatique.repository.CompteRepository;
import com.iaec.guichetautomatique.repository.OperationRepository;
import com.iaec.guichetautomatique.services.ClientService;
import com.iaec.guichetautomatique.services.CompteService;
import com.iaec.guichetautomatique.services.OperationService;
import com.iaec.guichetautomatique.services.UserService;
import com.iaec.guichetautomatique.services.impl.CompteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GuichetAutomatiqueApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private CompteService compteService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private UserService userService;

	@Autowired
	private ClientService clientService;

	public static void main(String[] args) {

		SpringApplication.run(GuichetAutomatiqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Client c3 = clientService.create(new Client("GBOSSOU", "Folly", "91554874", "carlo", "carlo"));
		Compte cp3 = compteService.create(new Compte(15000, c3));
}
}
