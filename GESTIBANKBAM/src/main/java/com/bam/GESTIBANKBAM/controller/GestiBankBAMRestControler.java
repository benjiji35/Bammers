package com.bam.GESTIBANKBAM.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.service.EmployeService;
import com.bam.GESTIBANKBAM.service.PersonneService;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@RestController
public class GestiBankBAMRestControler {

	@Autowired
	ClientService clientService; 

	@Autowired
	PersonneService personneService; 

	@Autowired
	EmployeService employeService; 

	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listAllClients() {
		List<Client> clients = clientService.findAllClients();
		if (clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/mdpClient/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listMdpClient() {
		List<Client> clients = clientService.findAllClients();
		if (clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}

		List<Client> clientsSansMdp = new ArrayList<>();
		for (Client c : clients) {
			if (c.getHashMdp() == null) {
				if (c.getConseiller() != null)
					// System.out.println(c);
					clientsSansMdp.add(c);
			}
		}

		return new ResponseEntity<List<Client>>(clientsSansMdp, HttpStatus.OK);
	}
	// -------------------Retrieve All New Clients--------------------------------------------------------

	@RequestMapping(value = "/newClient/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listNewClient() {
		System.out.println("test");
		List<Client> client = clientService.findAllClients();

		if (client.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}
		List<Client> clientsSansConseiller = new ArrayList<>();
		for (Client c : client) {
			if (c.getConseiller() == null) {
				// System.out.println(c);
				clientsSansConseiller.add(c);
			}
		}

		return new ResponseEntity<List<Client>>(clientsSansConseiller, HttpStatus.OK);
	}

	// -------------------Retrieve Single Client--------------------------------------------------------

	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> getClient(@PathVariable("id") String id) {
		System.out.println("Fetching User with id " + id);
		Client client = clientService.findById(id);
		if (client == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Client founded :" + client.toString());
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}


	// -------------------Retrieve Single Client By nom and prenom and cpte-------------------------------

	@RequestMapping(value = "/client/nom-{nom}/prenom-{prenom}/cpte-{cpte}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Client>> getUser3(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("cpte") String cpte) {
		
		System.out.println("Fetching Client with nom" + " " + nom+" "+"Fetching Client with prenom" + " " +prenom+" "+"Fetching Client with cpte" + " " +cpte);
		
		List<Client> cl = clientService.findByNomAndPrenomAndCompte(nom,prenom,cpte);
		if (cl == null) {
			return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Client found :" + cl.toString());
		return new ResponseEntity<List<Client>>(cl, HttpStatus.OK);
	}

	// -------------------Create a Client--------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.POST)
	public ResponseEntity<Void> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Client " + client.getNom() + " - " + client.getPrenom());

		if (clientService.isClientExist(client)) {
			System.out.println("A Client with name " + client.getNom() + " " + client.getPrenom() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		if (client.getConseiller() == null) {
			clientService.saveClient(client);
			System.out.println(">>> " + client);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Client --------------------------------------------------------

	@RequestMapping(value = "/newClient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
		System.out.println("Updating User " + id);

		Client currentClient = clientService.findById(id);
		System.out.println("on tient le bon bout");
		if (currentClient == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		currentClient.setCivilite(client.getCivilite());
		currentClient.setNom(client.getNom());
		currentClient.setPrenom(client.getPrenom());
		System.out.println("client DDN::" + client.getDdn());
		currentClient.setDdn(client.getDdn());
		currentClient.setHashMdp(client.getHashMdp());
		currentClient.setAdresse(client.getAdresse());
		currentClient.setConseiller(client.getConseiller());
		System.out.println("client::" + currentClient);

		clientService.updateClient(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	// ------------------- Validate a Client --------------------------------------------------------

	@RequestMapping(value = "/affClient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> validClient(@PathVariable("id") String id, @RequestBody Client client) {
		System.out.println("Validating User " + id);

		Client currentClient = clientService.findById(id);
		System.out.println("on tient le bon bout");
		if (currentClient == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		currentClient.setCivilite(client.getCivilite());
		currentClient.setNom(client.getNom());
		currentClient.setPrenom(client.getPrenom());
		System.out.println("client DDN::" + client.getDdn());
		currentClient.setDdn(client.getDdn());
		if (client.getHashMdp() == null) {
			String password = BAMTools.genPassword(1).get(0);
			currentClient.setHashMdp(password);
		}
		currentClient.setAdresse(client.getAdresse());
		currentClient.setConseiller(client.getConseiller());
		System.out.println("client::" + currentClient);

		clientService.updateClient(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	// ------------------- Delete a Client --------------------------------------------------------

	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteClient(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting Client with id " + id);

		Client client = clientService.findById(id);
		if (client == null) {
			System.out.println("Unable to delete. Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		clientService.deleteClientById(id);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Clients --------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteAllClients() {
		System.out.println("Deleting All Clients");

		clientService.deleteAllClients();
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}
	// -------------------Retrieve All Personnes--------------------------------------------------------

	@RequestMapping(value = "/personne/", method = RequestMethod.GET)
	public ResponseEntity<List<Personne>> listAllPersonnes() {
		List<Personne> personnes = personneService.findAllPersonnes();
		if (personnes.isEmpty()) {
			return new ResponseEntity<List<Personne>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Personne>>(personnes, HttpStatus.OK);
	}
	// ----------------------------------Authenticate Personne---------------------------------------

	@RequestMapping(value = "/personne/id-{id}/mdp-{mdp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Personne> getPersonne(@PathVariable("id") String id, @PathVariable("mdp") String mdp) {
		System.out.println("Fetching personne with id" + " " + id);
		Personne pers = personneService.findById(id);
		if (pers == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Bienvenue chez BamBank");
		}
		if (pers.getHashMdp().equals(mdp)) {
			return new ResponseEntity<Personne>(pers, HttpStatus.OK);
		} else {
			System.out.println("The mdp " + mdp + " " + "is invalid");
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		}
		//i have to create a session at BACK END TOO
	}

	// -------------------Retrieve All Conseiller--------------------------------------------------------

	@RequestMapping(value = "/conseiller/", method = RequestMethod.GET)
	public ResponseEntity<List<Employe>> listAllConseiller() {
		List<Employe> emp = employeService.findAllEmployes();
		if (emp.isEmpty()) {
			return new ResponseEntity<List<Employe>>(HttpStatus.NO_CONTENT);
		}
		List<Employe> Conseiller = new ArrayList<>();
		for (Employe e : emp) {
			if (e.getType() == 4) {
				System.out.println(e);
				Conseiller.add(e);
			}
		}
		return new ResponseEntity<List<Employe>>(Conseiller, HttpStatus.OK);
	}
	
	// -------------------Retrieve All Conseiller by ID--------------------------------------------------------

	@RequestMapping(value = "/conseiller/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employe> getConseiller(@PathVariable("id") String id) {
		System.out.println("Fetching Conseiller with id " + id);
		Employe employe = employeService.findById(id);
		if (employe == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Employe>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Client founded :" + employe.toString());
		return new ResponseEntity<Employe>(employe, HttpStatus.OK);
	}
}
