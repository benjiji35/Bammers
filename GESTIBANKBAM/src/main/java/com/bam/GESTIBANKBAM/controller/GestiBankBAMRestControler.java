package com.bam.GESTIBANKBAM.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
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

import com.bam.GESTIBANKBAM.dao.EmployeDAO;
import com.bam.GESTIBANKBAM.dao.EmployeDAOImpl;
import com.bam.GESTIBANKBAM.dao.PersonneDAO;
import com.bam.GESTIBANKBAM.dao.PersonneDAOImpl;
import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.CommandeChequier;
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Compte.CompteType;
import com.bam.GESTIBANKBAM.model.CompteNotification;
import com.bam.GESTIBANKBAM.model.Credit;
import com.bam.GESTIBANKBAM.model.Debit;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Notification;
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.model.Transaction;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.service.CompteService;
import com.bam.GESTIBANKBAM.service.EmployeService;
import com.bam.GESTIBANKBAM.service.PersonneService;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;
import com.bam.GESTIBANKBAM.utils.MailSender;

@RestController
public class GestiBankBAMRestControler {

	
	@Autowired
	ClientService clientService; 

	@Autowired
	PersonneService personneService; 

	@Autowired
	EmployeService employeService; 
	
	@Autowired
	CompteService compteService;

	private Thread bamBank;
	
	private static final Logger logger = Logger.getLogger(GestiBankBAMRestControler.class);

	{
		bamBank = new Thread(new Runnable() {
			private boolean work = true;

			@SuppressWarnings("unused")
			private void stopWork() {
				work = false;
			}

			private boolean mustWork() {
				return work;
			}

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				List<Compte> les_comptes = null;

				while (mustWork()) {
					if (BAMTools.isLastDayOfMonth()) {
						les_comptes = compteService.findAll();
						for (Compte c : les_comptes) {
							c.sealTransactions();
						}
					}
					try {
						Thread.currentThread().sleep(24*3600*1000L);
					} catch (InterruptedException e) {
						e.printStackTrace(System.err);
					}
				}
			}});
		bamBank.start();
	};

	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listAllClients() {
		List<Client> clients = clientService.findAll();
		if (clients == null || clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	
	// -------------------Retrieve Filtered Clients--------------------------------------------------------

		@RequestMapping(value = "/filteredClient/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Client>> listClients(@PathVariable("id") Long id) {
			List<Client> clients = clientService.findClients(id);
			if (clients == null || clients.isEmpty()) {
				return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
		}
	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/mdpClient/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listMdpClient(@PathVariable("id") Long id) {
		List<Client> clients = clientService.findClient(id);
		if (clients == null || clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}

		List<Client> clientsSansMdp = new ArrayList<>();
		for (Client c : clients) {
			if (c.getHashMdp() == null) {
				//if (c.getConseiller() != null)
				if (c.getConseillerId() != null) {
					// System.out.println(c);
					clientsSansMdp.add(c);
				}
			}
		}

		return new ResponseEntity<List<Client>>(clientsSansMdp, HttpStatus.OK);
	}
	// -------------------Retrieve All New Clients--------------------------------------------------------

	@RequestMapping(value = "/newClient/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listNewClient() {
		System.out.println("test");
		List<Client> clients = clientService.findAll();

		if (clients == null || clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}
		List<Client> clientsSansMdp = new ArrayList<>();
		for (Client c : clients) {
			//if (c.getConseiller() == null) {
			if (c.getHashMdp() == null) {
				// System.out.println(c);
				clientsSansMdp.add(c);
			}
		}

		return new ResponseEntity<List<Client>>(clientsSansMdp, HttpStatus.OK);
	}

	// -------------------Retrieve Single Client--------------------------------------------------------

	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
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
		System.out.println("Client found :" + cl);
		return new ResponseEntity<List<Client>>(cl, HttpStatus.OK);
	}
	
	// -------------------Retrieve Single Client By nom and prenom-------------------------------

		@RequestMapping(value = "/client/nom-{nom}/prenom-{prenom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Client>> getUsers(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom) {
			
			System.out.println("Fetching Client with nom" + " " + nom+" "+"Fetching Client with prenom" + " " +prenom);
			
			List<Client> cl = clientService.findByNomAndPrenom(nom,prenom);
			if (cl == null) {
				return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
			}
			System.out.println("Client found :" + cl);
			return new ResponseEntity<List<Client>>(cl, HttpStatus.OK);
		}
		
	// -------------------Retrieve Single Client By nom ------------------------------------------------

		@RequestMapping(value = "/client/nom-{nom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Client>> getUser4(@PathVariable("nom") String nom) {
			
			System.out.println("Fetching Client with nom" + " " + nom);
			
			List<Client> cl = clientService.findByNom(nom);
			if (cl == null) {
				return new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
			}
			System.out.println("Client found :" + cl);
			return new ResponseEntity<List<Client>>(cl, HttpStatus.OK);
		}


	// -------------------Create a Client--------------------------------------------------------

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/client/", method = RequestMethod.POST)
//	public ResponseEntity<Void> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
	public ResponseEntity<Void> createClient(@RequestBody HashMap<String, Object> client, UriComponentsBuilder ucBuilder) {	
		String nom = (String)client.get("nom");
		String prenom = (String)client.get("prenom");
		String profession = (String)client.get("profession");
		Date ddn        = BAMTools.parseDate((String)client.get("ddn"));

		LinkedHashMap<String,Object> adresse    = (LinkedHashMap<String,Object>)client.get("adresse");

		int situationMatrimoniale = Integer.parseInt(((String)client.get("situationMatrimoniale")));
		int enfants = (Integer)client.get("enfants");
		double salaire = Double.parseDouble((client.get("salaire")+""));
		String civilite = (String)client.get("civilite");
		LinkedHashMap<String, Object> comptes = (LinkedHashMap<String, Object>)client.get("comptes");


		LinkedHashMap<String,Object> unCompte = (LinkedHashMap<String,Object>)comptes.values().iterator().next();
		LinkedHashMap<String, Object> transactions = (LinkedHashMap<String, Object>)unCompte.get("transactions");
		LinkedHashMap<String, Object> uneTransaction =  (LinkedHashMap<String, Object>)transactions.get("0");
		double montant = Double.parseDouble(uneTransaction.get("montant")+"");
		Date dateDebut = new Date();
		
		System.out.println("createClient()::");
		System.out.println("nom="+nom);
		Personne.SITUATION sm;
		switch (situationMatrimoniale) {
			case 0:
				sm = Personne.SITUATION.SINGLE;
				break;
			case 1:
				sm = Personne.SITUATION.MARRIED;
				break;
			case 2:
				sm = Personne.SITUATION.DIVORCED;
				break;
			case 3:
				default:
				sm = Personne.SITUATION.WIDOWED;
		};
		Compte cpt = new Compte();
		Credit credit = null;
		Client clt = new Client();
		Adresse adr = new Adresse();
		adr.setNumero(Integer.parseInt((String) adresse.get("numero")));
		adr.setRue((String) adresse.get("rue"));
		adr.setCodePostal(adresse.get("codePostal")+"");
		adr.setVille((String) adresse.get("ville"));
		adr.setTelephone((String) adresse.get("telephone"));
		adr.setMail((String) adresse.get("mail"));
		clt.setType(Personne.ROLE_CLIENT);
		clt.setCivilite(civilite);
		clt.setNom(nom);
		clt.setPrenom(prenom);
		clt.setProfession(profession);
		clt.setDdn(ddn);
		clt.setIncome(salaire);
		clt.setSituationMatrimoniale(sm);
		clt.setNbEnfants(enfants);
		clt.setAdresse(adr);
		try {
			credit = new Credit(dateDebut, montant);
			cpt.addTransaction(credit);
		} catch (Throwable t) {
			t.printStackTrace(System.err);
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} 
		clt.addCompte(cpt);

		System.out.println("Creating Client " + clt.getNom() + " - " + clt.getPrenom());

		if (clientService.isExists(clt)) {
			System.out.println("A Client with name " + clt.getNom() + " " + clt.getPrenom() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			clientService.save(clt);
			System.out.println(">>> " + clt);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/client/{id}").buildAndExpand(clt.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Client --------------------------------------------------------

	@RequestMapping(value = "/newClient/idcl-{idcl}/idcons-{idcons}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable("idcl") Long idcl, @PathVariable("idcons") Long idcons) {
	
		System.out.println("Ajout du conseiller :"+idcons+" au client :"+idcl);
		Employe emp = employeService.findById(idcons);
		clientService.addConseillerToClient(idcl, emp);
		Client currentClient = clientService.findById(idcl);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
		
	}
	
	// ------------------- Modify a Client --------------------------------------------------------

		@RequestMapping(value = "/UpdateClient/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Client> modifyClients(@PathVariable("id") Long id, @RequestBody Client client) {
		
			System.out.println("Ajout du client :"+client);
			
			
			if (client == null) {
				System.out.println("Client with id " + id + " not found");
				return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
			}
			clientService.modifyClient(id, client);
			System.out.println("after the function"+client.toString());
			return new ResponseEntity<Client>(client, HttpStatus.OK);
			
		}
		
// --------------------------------- Modify a Conseiller ---------------------------------------------------

		@RequestMapping(value = "/UpdateConseiller/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Employe> modifyConseillers(@PathVariable("id") Long id, @RequestBody Employe conseiller) {
		
			System.out.println("Ajout du conseiller :"+conseiller);
			
			
			if (conseiller == null) {
				System.out.println("conseiller with id " + id + " not found");
				return new ResponseEntity<Employe>(HttpStatus.NOT_FOUND);
			}
			employeService.modifyConseiller(id, conseiller);
			System.out.println("after the function"+conseiller.toString());
			return new ResponseEntity<Employe>(conseiller, HttpStatus.OK);
			
		}

	// ------------------- Validate a Client --------------------------------------------------------

	@RequestMapping(value = "/affClient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> validClient(@PathVariable("id") Long id) {
		System.out.println("Validating User " + id);

		Client currentClient = clientService.findById(id);
		System.out.println("on tient le bon bout"+currentClient);

		if (currentClient == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}
		clientService.addMdpToClient(id);
		//MailSender.sendMail(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}
	
	// ------------------- Delete a Client --------------------------------------------------------

	@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
		System.out.println("Fetching & Deleting Client with id " + id);

		Client client = clientService.findById(id);
		if (client == null) {
			System.out.println("Unable to delete. Client with id " + id + " not found");
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		clientService.deleteById(id);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Clients --------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteAllClients() {
		System.out.println("Deleting All Clients");

		clientService.deleteAll();
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}
	// -------------------Retrieve All Personnes--------------------------------------------------------

	@RequestMapping(value = "/personne/", method = RequestMethod.GET)
	public ResponseEntity<List<Personne>> listAllPersonnes() {
		List<Personne> personnes = personneService.findAll();
		if (personnes.isEmpty()) {
			return new ResponseEntity<List<Personne>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Personne>>(personnes, HttpStatus.OK);
	}
	// ----------------------------------Authenticate Personne---------------------------------------

	@RequestMapping(value = "/personne/id-{id}/mdp-{mdp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Personne> getPersonne(@PathVariable("id") Long id, @PathVariable("mdp") String mdp) {
		System.out.println("Fetching personne with id" + " " + id);
		Personne pers = (Personne) personneService.findById(id);
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
		List<Employe> emp = employeService.findAll();
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
	public ResponseEntity<Employe> getConseiller(@PathVariable("id") Long id) {
		System.out.println("Fetching Conseiller with id " + id);
		Employe employe = (Employe) employeService.findById(id);
		if (employe == null) {
			System.out.println("Client with id " + id + " not found");
			return new ResponseEntity<Employe>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Client founded :" + employe.toString());
		return new ResponseEntity<Employe>(employe, HttpStatus.OK);
	}
	
	// -------------------Retrieve All Conseiller by nom, prenom and id--------------------------------------------------------

	@RequestMapping(value = "/searchConseiller/nom-{nom}/prenom-{prenom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Employe>> getConseillers(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom) {
		System.out.println("Fetching Conseiller with nom " + nom + "with prenom"+prenom);
		
		List<Employe> employe = employeService.findByNomAndPrenom(nom, prenom);
		if (employe == null) {
			System.out.println("Employe not found");
			return new ResponseEntity <List<Employe>>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Employe founded :" + employe.toString());
		return new ResponseEntity <List<Employe>>(employe, HttpStatus.OK);
	}
	
// -------------------Create a Conseiller--------------------------------------------------------

@RequestMapping(value = "/conseiller/", method = RequestMethod.POST)
public ResponseEntity<Void> createCons(@RequestBody Employe cons, UriComponentsBuilder ucBuilder) {
	System.out.println("Creating Cons " + cons.getNom() + " - " + cons.getPrenom());
	String password = BAMTools.genPassword(20);
	cons.setHashMdp(password);

	if (employeService.isExists(cons)) {
		System.out.println("A Client with name " + cons.getNom() + " " + cons.getPrenom() + " already exist");
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	else {
		employeService.save(cons);
		System.out.println(">>> " + cons);
	}

	HttpHeaders headers = new HttpHeaders();
	headers.setLocation(ucBuilder.path("/conseiller/{id}").buildAndExpand(cons.getId()).toUri());
	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}

//----------Notification--------------------------------------

@RequestMapping(value = "/notif/", method = RequestMethod.POST)
public ResponseEntity<Void> createNotif(@RequestBody Notification pub , UriComponentsBuilder ucBuilder) {
System.out.println("Creating notification " + pub.getMessage() + " - " + pub.getDate());

//if (clientService.isClientExist(client)) {
//	System.out.println("A Client with name " + client.getNom() + " " + client.getPrenom() + " already exist");
//	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//}
//
//if (client.getConseiller() == null) {
//	clientService.saveClient(client);
//	System.out.println(">>> " + client);
//}

HttpHeaders headers = new HttpHeaders();
headers.setLocation(ucBuilder.path("/notif/").buildAndExpand(pub.getMessage()).toUri());
return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
}


//-------------------Retrieve Single compte--------------------------------------------------------

	@RequestMapping(value = "/compte/{cpt}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Compte> getCompte(@PathVariable("cpt") Long cpt) {
		System.out.println("Fetching User with id " + cpt);
		Compte compte =  compteService.findByNum(cpt);
		if (compte == null) {
			System.out.println("Compte with number " + cpt + " not found");
			return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Compte founded :" + compte.toString());
		return new ResponseEntity<Compte>(compte, HttpStatus.OK);
	}
	//-------------------Faire un virement--------------------------------------------------------

		@RequestMapping(value = "/compte/{cpt1}/{cpt2}/{mont}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public void setVirement(@PathVariable("cpt1") Long cpt1, @PathVariable("cpt2") Long cpt2, @PathVariable("mont") double mont) throws Throwable {
			System.out.println("Fetching User with id " + cpt1);
			Compte compte1 = compteService.findByNum(cpt1);
			Compte compte2 = compteService.findByNum(cpt2);
			compteService.setVirement(compte1, compte2, mont);
//			if (compte == null) {
//				System.out.println("Compte with number " + cpt + " not found");
//				return new ResponseEntity<Compte>(HttpStatus.NOT_FOUND);
//			}
//			System.out.println("Compte founded :" + compte.toString());
//			return new ResponseEntity<Compte>(compte, HttpStatus.OK);
	}
}
