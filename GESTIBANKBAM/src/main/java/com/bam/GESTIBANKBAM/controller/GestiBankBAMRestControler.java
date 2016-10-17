package com.bam.GESTIBANKBAM.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.model.Transaction;
import com.bam.GESTIBANKBAM.service.ClientService;
import com.bam.GESTIBANKBAM.service.EmployeService;
import com.bam.GESTIBANKBAM.service.PersonneService;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@RestController
public class GestiBankBAMRestControler {

	private static List<Personne>personnes;
	private static List<Client>clients;
	private static List<Employe>conseillers;
	private static List<Employe>admins;

	static {
//		personnes   = new ArrayList<Personne>();
//		clients     = new ArrayList<Client>();
//		conseillers = new ArrayList<Employe>();
//		admins      = new ArrayList<Employe>();
//
//		initDB();
//		persistDB();
	};

	private static void initDB() {

		personnes.add(new Personne(
				"Mr", "AKLI", "Abdel", BAMTools.parseDate("25-03-1968"), Personne.ROLE_CLIENT, 1L, "m1m3r123908652",
				new Adresse(859, "place jean jaures", "nantes", "44000", "+33656961528", "akli.abdel@tiko.com")));

		personnes.add(new Personne(
				"Mme", "JANIK", "Émilie", BAMTools.parseDate("04-08-1970"), Personne.ROLE_CLIENT, 2L, "k1h3r12356571",
				new Adresse(1, "cours gambetta", "nimes", "32000", "+33784283626", "janik.émilie@tiko.com")));

		personnes.add(new Personne(
				"Mr", "ZALCBERG", "Mathis", BAMTools.parseDate("17-01-1951"), Personne.ROLE_CLIENT, 5L, "g1k3r12303480",
				new Adresse(172, "avenue foch", "lille", "59000", "+33619357656", "zalcberg.mathis@bito.com")));

		personnes.add(new Personne(
				"Mr", "Mitterand", "Lucas", BAMTools.parseDate("15-02-1950"), Personne.ROLE_CLIENT, 7L, "t1k3r12392556",
				new Adresse(390, "avenue du docteur long", "carcasonne", "11000", "+33744587413", "mitterand.lucas@abc.com")));
		
		personnes.add(new Personne(
				"Mr", "BEDIKIAN", "Tristan", BAMTools.parseDate("13-08-1969"), Personne.ROLE_CLIENT, 9L, "g1k3r123468070",
				new Adresse(479, "route de bayonne", "lyon", "69000", "+33781554541", "bedikian.tristan@tito.com")));

		personnes.add(new Personne(
				"Mme", "Platini", "Sofia", BAMTools.parseDate("26-12-1990"), Personne.ROLE_CLIENT, 11L, "k1h3r123799726",
				new Adresse(264, "cours gambetta", "nimes", "32000", "+33775476831", "platini.sofia@bito.com")));

		personnes.add(new Personne(
				"Mr", "Patel", "Tommy", BAMTools.parseDate("06-11-1955"), Personne.ROLE_CLIENT, 13L, "j1g3r123341539",
				new Adresse(422, "alles jules guesdes", "montpellier", "34000", "+33642697233", "patel.tommy@toto.com")));

		personnes.add(new Personne(
				"Mme", "AFONSO", "Stephanie", BAMTools.parseDate("20-11-1958"), Personne.ROLE_CLIENT, 15L, "m1m3r12337584",
				new Adresse(169, "cours laplace", "lyon", "69000", "+33757691287", "afonso.stephanie@toto.com")));

		personnes.add(new Personne(
				"Mme", "WINTERMAN", "Alicia", BAMTools.parseDate("20-01-1980"), Personne.ROLE_CLIENT, 17L, "j1g3r123161922",
				new Adresse(766, "rue des tulipes", "grenoble", "38000", "+33666326755", "winterman.alicia@bito.com")));

		personnes.add(new Personne(
				"Mr", "Chirac", "Toto", BAMTools.parseDate("16-02-1957"), Personne.ROLE_CLIENT, 19L, "g1k3r123142832",
				new Adresse(51, "avenue foch", "carcasonne", "11000", "+33688223746", "chirac.toto@tiko.com")));

		// Conseillers
		personnes.add(new Personne(
				"Mr", "BEZDIKIAN", "Édouard", BAMTools.parseDate("12-08-1960"), Personne.ROLE_CONSEILLER, 21L, "m1m3r123912558",
				new Adresse(185, "route de bayonne", "montauban", "82000", "+33771317258", "bezdikian.édouard@bito.com")));

		personnes.add(new Personne(
				"Mme", "KEVORKIAN", "Arianne", BAMTools.parseDate("27-01-1990"), Personne.ROLE_CONSEILLER, 23L, "m1m3r123526496",
				new Adresse(524, "alles jules guesdes", "rennes", "35000", "+33728753383", "kevorkian.arianne@tito.com")));

		personnes.add(new Personne(
				"Mr", "MALOSSI", "Simon", BAMTools.parseDate("24-12-1951"), Personne.ROLE_CONSEILLER, 27L, "k1h3r123722356", 
				new Adresse(328, "rue des sardines", "montauban", "82000", "+33789295523", "malossi.simon@bito.com")));

		// Admins
		personnes.add(new Personne(
				"Mme", "SOLARZ", "Alicia", BAMTools.parseDate("18-12-1979"), Personne.ROLE_ADMIN, 25L, "j1g3r123305972", 
				new Adresse(658, "place jean jaures", "le mans", "72000", "+33728744142", "solarz.alicia@tito.com")));

		for (Personne p : personnes) {
			switch (p.getType()) {
				case Personne.ROLE_CLIENT:
					clients.add(createNewClient(p));
					System.out.println("FAKE::"+clients.get(clients.size()-1));
					break;
				case Personne.ROLE_CONSEILLER:
					conseillers.add(createNewConseiller(p));
					break;
				case Personne.ROLE_ADMIN:
					admins.add(createNewAdmin(p));
					break;
				default:
					System.out.println("init Personnes::"+p);
			}
		}
		// assigning Clients to Conseillers
		Random rnd = new Random();
		int idx;
		for (Client clt : clients) {
			for (Personne p : personnes) {
				if (clt.getId().equals(p.getId())) {
					p.setType(clt.getType());
					break;
				}
			}
			idx = rnd.nextInt(conseillers.size());
			Employe emp = conseillers.get(idx);
			emp.addClient(clt);
			clt.setConseiller(emp);
		}
	}

	private static void persistDB() {
		PersonneDAO daoP     = new PersonneDAOImpl();
		EmployeDAO  daoAgent = new EmployeDAOImpl();
		EmployeDAO  daoAdmin = new EmployeDAOImpl();

		for (Personne p : personnes) {
			daoP.save(p);
		}
		for (Employe e : conseillers) {
			daoAgent.save(e);
		}
		for (Employe a : admins) {
			daoAdmin.save(a);
		}
	}

	private static Employe createNewConseiller(Personne p) {
		Employe c = new Employe(p);
		Random rnd = new Random();
		int year = 20 + rnd.nextInt(20);
		int month =  1 + rnd.nextInt(12);
		int days = 1 + rnd.nextInt(28);
		ArrayList<String> fcts = new ArrayList<String>(); 

		fcts.add("conseiller");
		fcts.add("banquier");
		c.setType(Personne.ROLE_CONSEILLER);
		c.setDateEntree(BAMTools.addToCalendar(c.getDdn(), year, month, days));
		c.setFonctions(fcts);
		System.out.println("creation conseiller="+c);
		return c;
	}

	private static Employe createNewAdmin(Personne p) {
		Employe adm = new Employe(p);
		ArrayList<String> fcts = new ArrayList<String>();
		Random rnd = new Random();
		int year   = 30 + rnd.nextInt(20);
		int month  = 1  + rnd.nextInt(12);
		int day    = 1 + rnd.nextInt(29);

		adm.setType(Personne.ROLE_ADMIN); 

		fcts.add("administrateur");
		fcts.add("banquier");
		adm.setDateEntree(BAMTools.addToCalendar(adm.getDdn(), year, month, day));
		adm.setFonctions(fcts);
		System.out.println("creation admin="+adm);
		return adm;
	}



	private static Client createNewClient(Personne p) {
		Client client = new Client(p);

		client.setType(Personne.ROLE_CLIENT);
		try {
			client.setComptes(createDummyComptes(p.getId()));
		} catch (BAMException e) {
			e.printStackTrace(System.err);
		}
		System.out.println("creation nouveau client: "+client);
		return client;
	}

	private static ArrayList<Compte> createDummyComptes(Long id) throws BAMException {
		ArrayList<Compte> cpts = new ArrayList<Compte>();
		Random rnd = new Random();
		final int n = 1 + rnd.nextInt(5);

		for (int i=0; i < n; i++) {
			Compte c = new Compte(3000+rnd.nextInt(10000),
					1200,
					CompteType.AVEC_AUTORISATION,
					Long.parseLong(id+""+(i+1)));
			c.setTransactions(createDummyTransactions(id+""+i));
			c.setChequiers(createDummyChequiers(c, id+""+i));
			c.setNotifications(createDummyNotifications(id+""+i));
			cpts.add(c);
		}
		return cpts;
	}

	private static ArrayList<Transaction> createDummyTransactions(String id) throws BAMException {
		ArrayList<Transaction> trcts = new ArrayList<Transaction>();
		Random rnd = new Random();
		final int n = 1 + rnd.nextInt(100);
		int step = 1 + rnd.nextInt(9);

		for (int i=0; i<n; i++) {
			Transaction t;
			if (i % step == 0) {
				step = 1 + rnd.nextInt(9);
				t = new Debit(BAMTools.addToCalendar(new Date(), 
						-rnd.nextInt(2), -rnd.nextInt(13), -rnd.nextInt(31)),
						-1-rnd.nextInt(100));
			} else {
				t = new Credit(BAMTools.addToCalendar(new Date(), 
						-rnd.nextInt(2), -rnd.nextInt(13), -rnd.nextInt(31)),
						100+rnd.nextInt(200));
			}
			trcts.add(t);
		}
		return trcts;
	}

	private static List<CompteNotification> createDummyNotifications(String id) {
		List<CompteNotification> ntfs = new ArrayList<CompteNotification>();
		Random rnd = new Random();
		final int n = 1 + rnd.nextInt(10); 
		String[] strs = new String[] {
				"Commande de chequier - status - acceptee",
				"Commande de chequier - status - en attente",
				"Commande de chequier - status - rejettee",
				"Offre promotionnelle",
				"Offre de parrainage",
				"Changement de conseiller",
				"Votre nouvelle carte est disponible",
				"Votre nouveau carnet de cheque est disponible",
				"Echeance contrat assurance habitation",
				"Echeance contrat assurance automobile",
				"Echeance contrat assurance moto"
		};

		for (int i=0; i < n; i++) {
			CompteNotification notif = new CompteNotification(strs[rnd.nextInt(strs.length)], 
					BAMTools.addToCalendar(new Date(), 0, 0, -rnd.nextInt(131)));
			ntfs.add(notif);
		}
		return ntfs;
	}


	private static ArrayList<CommandeChequier> createDummyChequiers(Compte c, String id) {
		ArrayList<CommandeChequier> chqs = new ArrayList<CommandeChequier>();
		Random rnd = new Random();
		final int n = 1 + rnd.nextInt(10);

		for (int i=0; i < n; i++) {
			CommandeChequier cc = new CommandeChequier(c, 
					1+rnd.nextInt(3),
					Long.parseLong(c.getNumCpt()+""+id+""+(i+1)));
			cc.commander();
			chqs.add(cc);
		}
		return chqs;
	}





	@Autowired
	ClientService clientService; 

	@Autowired
	PersonneService personneService; 

	@Autowired
	EmployeService employeService; 
	
	private static final Logger logger = Logger.getLogger(GestiBankBAMRestControler.class);

	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listAllClients() {
		List<Client> clients = clientService.findAll();
		if (clients.isEmpty()) {
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	// -------------------Retrieve All Clients--------------------------------------------------------

	@RequestMapping(value = "/mdpClient/", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> listMdpClient() {
		List<Client> clients = clientService.findAll();
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
		List<Client> client = clientService.findAll();

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
		System.out.println("Client found :" + cl.toString());
		return new ResponseEntity<List<Client>>(cl, HttpStatus.OK);
	}

	// -------------------Create a Client--------------------------------------------------------

	@RequestMapping(value = "/client/", method = RequestMethod.POST)
	public ResponseEntity<Void> createClient(@RequestBody Client client, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Client " + client.getNom() + " - " + client.getPrenom());

		if (clientService.isExists(client)) {
			System.out.println("A Client with name " + client.getNom() + " " + client.getPrenom() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		if (client.getConseiller() == null) {
			clientService.save(client);
			System.out.println(">>> " + client);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Client --------------------------------------------------------

	@RequestMapping(value = "/newClient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
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

		clientService.update(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	// ------------------- Validate a Client --------------------------------------------------------

	@RequestMapping(value = "/affClient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Client> validClient(@PathVariable("id") Long id, @RequestBody Client client) {
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

		clientService.update(currentClient);
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
}
