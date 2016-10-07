package com.bam.GESTIBANKBAM.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.CommandeChequier;
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Notification;
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.model.Transaction;
import com.bam.GESTIBANKBAM.utils.BAMTools;


public class BAMData {
	private static List<Personne> personnes;
	private static List<Client> clients;
	private static List<Employe> conseillers;
	private static List<Employe> admins;
	private static final AtomicLong counter = new AtomicLong();

	static {
		try {
			System.out.println("BAMData:: DEBUT creation personnes, clients, conseillers et admins...");
			assignDummyClientsAndConseillersAndAdmins();
			System.out.println("BAMData:: FIN >>> creation personnes, clients, conseillers et admins...");
		} catch (ParseException pe) {
			pe.printStackTrace(System.err);
			throw new ExceptionInInitializerError(pe);
		}
	};

	private static void assignDummyClientsAndConseillersAndAdmins() throws ParseException {
		int counter = 3000;
		personnes = populateDummyPersonnes(counter);
		clients   = populateDummyClients(personnes, counter - 30);
		counter  -= clients.size(); 
		conseillers = populateDummyConseillers(personnes, counter - 29);
		counter  -= conseillers.size();
		admins = populateDummyAdmins(personnes, 1);
		counter--;
		synchronizeDummies();
	}

	private static void synchronizeDummies() {
		for (Employe a : admins) {
			for (Personne p : personnes) {
				if (a.getId().equals(p.getId())) {
					p.setType(a.getType());
					break;
				}
			}
		}

		for (Employe c : conseillers) {
			for (Personne p : personnes) {
				if (c.getId().equals(p.getId())) {
					p.setType(c.getType());
					break;
				}
			}
		}

		for (Client clt : clients) {
			for (Personne p : personnes) {
				if (clt.getId().equals(p.getId())) {
					p.setType(clt.getType());
					break;
				}
			}
		}
	}

	private static List<Personne> populateDummyPersonnes(final int limite) throws ParseException {
		if (personnes == null){
			personnes = new ArrayList<Personne>();
			String nom[] = { "Hajji", "Adnan", "Gauthier", "Mahboubi", "Zidane", "Patel", "Platini", "Pele", "Maradona",
					"Chirac", "Hollande", "Mitterand", "Obama", "Ming", "Egbo", "Schillacci", "Dropsi", "Le petit", "Leberre",
					"Patron", "PAPAZIAN", "AGOPIAN", "JAKUBOWICZ", "KAC", "CUKIERMAN", "VARTANIAN", "FUKS", "ROZENBLUM", "ROZENBAUM",
					"SARKISSIAN", "GRYNBERG", "BALIAN", "BOGHOSSIAN", "ROZENCWAJG", "ZYSMAN", "ZYLBERBERG", "GUERREIRO", "WAJNBERG", 
					"BURSZTYN", "LEWKOWICZ", "CHAHINIAN", "NAJMAN", "WOJCIK", "KEVORKIAN", "TOROSSIAN", "MATTIOCCO", "BERBERIAN", 
					"MARDIROSSIAN", "CUKIER", "DERDERIAN", "ZAKARIAN", "CARDOSO", "AKIERMAN", "GOLDSZTEJN", "SZWARC", "OHANESSIAN", 
					"ZAOUI", "BORENSZTEJN", "MARKOWICZ", "ROZENBLAT", "FRENKIEL", "ZERBIB", "KAZANDJIAN", "APRAHAMIAN", "VARGA", "HADDAD", 
					"ZYLBERMAN", "VIEGAS", "BARBOSA", "CYMBLER", "ARONOWICZ", "CHIRINIAN", "GRINBAUM", "MAGANA", "PEREDA", "SZERMAN", "MIKAELIAN", 
					"KRIKORIAN", "FRANZIL", "GRYNSZPAN", "DOUKHAN", "TOSOLINI", "MARKARIAN", "PIRES", "ROTBART", "HERCBERG", "TOFFOLON", "LUSTMAN", 
					"BASMADJIAN", "KUREK", "AFONSO", "RUBINSZTEJN", "DAWIDOWICZ", "FAJGENBAUM", "JABLONKA", "ZYLBERSZTEJN", "TOPALIAN", 
					"SZULMAN", "LAHCENE", "SEBBAH", "BENHAMOU", "AMRAM", "BIERNAT", "WOLKOWICZ", "AZOUZ", "SOCROUN", "FEDERMAN", "MASLIAH", 
					"SINANIAN", "KALAIDJIAN", "HATCHADOURIAN", "SZTERN", "MELKONIAN", "CHOUKROUN", "VOLKOFF", "ZAMOLO", "MINC", "ZEMMOUR", 
					"KUDLA", "PABLOS", "YAHIAOUI", "WOJTOWICZ", "KOGUT", "TACHDJIAN", "SEFERIAN", "DAHMANI", "ZALCMAN", "GUTERMAN", "BLUMENTAL", 
					"GOLDSZTAJN", "LENCZNER", "GUTMACHER", "ALVO", "WAJSBROT", "PAMERLON", "PICKER", "BORENSZTEIN", "STUDENY", "HAROUTUNIAN", 
					"HUBERMAN", "BIRENBAUM", "ARAMA", "YAHIA", "NACCACHE", "MENAHEM", "KARAYAN", "ARABIAN", "LIPSZYC", "DAVIDIAN", "KOUYOUMDJIAN", 
					"JAMGOTCHIAN", "ZAJDMAN", "SAYAGH", "LANCHAS", "FRYDE", "LAHOUSSINE", "VAQUERO", "KECHICHIAN", "KOVAC", "CORREIA", "MAXIMOFF", 
					"SIEMIATYCKI", "NUDELMAN", "GARNEK", "ZACCARINI", "CAMBIANICA", "MALDJIAN", "WAJEMAN", "RAJZMAN", "DIAMENT", "MOUTAFIAN", 
					"ZLOTNIK", "NAVON", "SCHWARCZ", "YERAMIAN", "VAJDA", "TERZIAN", "FISZMAN", "ABDELAZIZ", "SUISSA", "AVAKIAN", "TAKVORIAN", 
					"GEORGIOU", "MOSSERI", "ABDERRAHMANE", "OHANIAN", "WIERZBA", "GAGO", "DRISS", "SOKOL", "LIWER", "FACCA", "MOUSSAOUI",
					"DE SOUSA", "BENGUIGUI", "KUPERBERG", "GLIKSMAN", "FRAJMAN", "FAIBIS", "DJAMENT", "RADZYNSKI", "ACHDJIAN", "KANTOROWICZ",
					"TOPOR", "SZTARKMAN", "MONSZAJN", "KOPELMAN", "EJZENBERG", "WAJCMAN", "SZWARCBERG", "LEONOFF", "SZPIRGLAS", "ANDREASSIAN", 
					"PITCHO", "SZPIRO", "PACHULSKI", "ICKOWICZ", "BONALUMI", "RECHTMAN", "BOCOS", "MOULAI", "HATCHIKIAN", "DONIKIAN", "SKORUPKA", 
					"BEN LOULOU", "ORDAS", "MILOCHEVITCH", "BAGDASSARIAN", "ASSADOURIAN", "KHATCHADOURIAN", "BEN SAID", "COLACICCO", "AVEDIKIAN", 
					"SZAFRAN", "KLIMENKO", "FIKMAN", "D'OLIVEIRA", "AREZKI", "TAHAR", "LUIZ", "GREATTI", "FONSECA", "DE JESUS", "SLIMANI", 
					"RICCETTI", "PIOVESAN", "MEZIANE", "WAJNFELD", "TUCHBAND", "LIWERANT", "SQUELARD", "ARIRA", "ZALCBERG", "BERNEMAN", "FISCHMAN", 
					"TOUAZI", "PLISKINE", "WALDMAN", "JEDWAB", "JAKOBOWICZ", "GRYNBLAT", "FINKIELSZTEJN", "DERER", "NAITYCHIA", "PSZENICA", 
					"OUZOUNIAN", "NISENBAUM", "BEZDIKIAN", "MITCHOVITCH", "ALGAZI", "MANSUTTI", "CAMBOURIS", "AZADIAN", "ODDONO", "PERETZ", 
					"KASZEMACHER", "HAMMOUMA", "MAYGNAN", "KARADJIAN", "GHIRARDELLO", "TORRICE", "HAMICHE", "FASCIONE", "DI SOTTO", "CARAQUIN", 
					"TOMASSIAN", "IOLI", "ARTINIAN", "PALATCHI", "PETROPOULOS", "JELEN", "GASPARETTO", "SKRZYPEK", "VLASSOFF", "SERI", "MALOSSI", 
					"LEHOUSSINE", "KIZIRIAN", "HOVNANIAN", "BACRY", "AZIZA", "NIGOGHOSSIAN", "BABIKIAN", "SOLARZ", "SAMELE", "MADJARIAN", "KALOUSTIAN", 
					"BEDIKIAN", "SZNAJDER", "STRUGO", "NALBANDIAN", "ABITBOL", "CISINSKI", "CAPPELLESSO", "SILVENTE", "AJZENBERG", "JEDYNAK", 
					"ZOLTY", "KLAJMAN", "CZARNY", "CHETRIT", "KOZLOFF", "PONOMARENKO", "DE AZEVEDO", "AIT SAADA", "MOURGLIA", "FACCHETTI", 
					"YOUNSI", "CHUDY", "BEDNAR", "NICOLOSO", "FURMAN", "AKLI", "SZEWCZYK", "MATUSIAK", "GAMEIRO", "DEGRAEUWE", "BRONES", 
					"PAJAK", "JANIK", "CROATTO", "KOLASA", "TUREK", "ARSLANIAN", "KELECHIAN", "HAMMOU", "BACHIR", "TORDJMAN", "YANG", "SOBRADO", 
					"LEONARDUZZI", "KAVAFIAN", "BIHI", "AMARA", "AISSA", "MLYNARCZYK", "CALKA", "BOCCANFUSO", "WOLKOWSKI", "RUDOWICZ", "HONIGMAN", 
					"AUFMAN", "DAVITTI", "WINTERMAN", "GICHT", "WELGER", "SZWIMER", "HERSZLIKOWICZ", "GALISTINOS", "LAJZEROWICZ", "NORYNBERG", 
					"HORYN", "FERSZT", "LIBESKIND", "TSITOGLOU", "STOLER", "FRIDLANDER", "SOKOLSKI", "ROZENTAL", "ROSEMBLUM", "GRYN", "CALLOUSTIAN", 
					"MILATOS", "JACOBACCI", "GRAJCAR", "TOPOUZIAN", "LINDWASSER", "SWAGTEN", "PENOUEL", "CYGLER", "STRINGHETTA", "HOYOS", "DURANCER", 
					"PAGANARDI", "FARAONI", "ERNER", "BONDAREFF", "LINDENBAUM", "TOROSANI", "SZLAMOWICZ", "CANELLIS", "DUNI-GERMAIN", "CICCODICOLA", 
					"FERAZZA", "MILSZTAJN", "MARTYNOFF", "LANGMAN", "KERTESZ", "FRYDMANN", "FRANCOIS-PONCET", "CHARCOSSEY", "ANTRANIKIAN", 
					"TCHIBOUKDJIAN", "DJAD", "RAMAJO", "HAMADACHE", "FERREOL-RAGOTIN", "BORNSZTEJN", "KRAJKA", "SARMIR", "KASIMIR" 
					};
			String masculin[] = { "Wajih", "SP", "Benjamin", "Mohammed", "Vika", "Jacques", "Kong", "Papy", "Camacho", "Robert", 
					"Denis", "Toto", "Franck", "Karim", "Ahmed", "Song", "Prosper", "Abdel",
					"Adam", "Alex", "Alexandre", "Alexis", "Anthony", "Antoine", "Cédric", "Charles", "Christopher", "David", "Dylan",
					"Édouard", "Elliot", "Émile", "Étienne", "Félix", "Gabriel", "Guillaume", "Hugo", "Isaac", "Jacob", "Jérémy", "Jonathan",
					"Julien", "Justin", "Léo", "Logan", "Loïc", "Louis", "Lucas", "Ludovic", "Malik", "Mathieu", "Mathis", "Maxime",
					"Michaël", "Nathan", "Nicolas", "Noah", "Olivier", "Philippe", "Raphaël", "Samuel", "Simon", "Thomas", "Tommy", "Tristan",
					"Flavian"
					};
			String feminin[] = { "Coralie", "Stephanie", "Valeria", "Sonia", "Michelle", "Nah", "Milouda", "Camille", "Tifane", "Celine",
					"Nolwene", "Charlotte", "Claire", "Sabrina", "Kamiliya", "Jessica",
					"Alexia", "Alice", "Alicia", "Amélie", "Anaïs", "Annabelle", "Arianne", "Audrey", "Aurélie", "Camille", "Catherine",
					"Charlotte", "Chloé", "Clara", "Coralie", "Daphnée", "Delphine", "Elizabeth", "Élodie", "Émilie", "Emma", "Emy", "Ève",
					"Florence", "Gabrielle", "Jade", "Juliette", "Justine", "Laurence", "Laurie", "Léa", "Léanne", "Maélie", "Maéva", "Maika",
					"Marianne", "Marilou", "Maude", "Maya", "Mégan", "Mélodie", "Mia", "Noémie", "Océane", "Olivia", "Rosalie", "Rose",
					"Sarah", "Sofia", "Victoria", "Monika"
					};
			String mdp[] = {"k1ll3r123", "t1k3r123", "m1m3r123", "g1k3r123", "k1m3r123", "j1g3r123", "k1h3r123"};
			String titre;
			String prenom;
			int i_nom;
			int i_prenom;
			int i_mdp;
			int sexe;
			int annee;
			int mois;
			int jour;
			String ddn_str;
			Random rnd = new Random();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);

			for (int i = 0; i < limite; i++) {
				sexe = rnd.nextInt(10000) % 2;
				i_nom =  rnd.nextInt(10000) % nom.length;
				if (sexe == 0) {
					i_prenom = rnd.nextInt(10000) % masculin.length;
					prenom = masculin[i_prenom];
					titre = "Mr";
				} else {
					i_prenom = rnd.nextInt(10000) % feminin.length;
					prenom = feminin[i_prenom];
					titre = "Mme";
				}
				annee = rnd.nextInt(45) + 1950;
				mois  = 1 + rnd.nextInt(10000) % 12;
				jour  = 1 + rnd.nextInt(10000) % 28;
				ddn_str = (jour < 10? "0"+jour: ""+jour) + "-" +
						(mois < 10? "0"+mois: ""+mois) + "-" +
						(annee);
				i_mdp = rnd.nextInt(10000) % mdp.length;
				personnes.add(createNewPersonne("C"+counter.incrementAndGet(),
						titre,
						nom[i_nom],
						prenom,
						sdf.parse(ddn_str),
						mdp[i_mdp] + rnd.nextInt(100) + "" + rnd.nextInt(100) + rnd.nextInt(100))); 
						//nom[i_nom] + "." + prenom + "@acme.com"));
			}
		}
		return personnes;
	}

	private static ArrayList<Client> populateDummyClients(List<Personne> personnes, int count) {
		ArrayList<Client> clts = new ArrayList<Client>();
		int idx = 0;
		final int len = personnes.size(); 
		Personne p;

		for (int i=0; i < count && idx < len; i++) {
			p = personnes.get(idx++);
			clts.add(createNewClient(p));
		}
		return clts;
	}

	private static ArrayList<Employe> populateDummyConseillers(List<Personne> personnes, int count) {
		ArrayList<Employe> agts = new ArrayList<Employe>();
		int idx = 0;
		final int len = personnes.size();
		Personne p;

		for (int i=0; i < count && idx < len; i++) {
			p = personnes.get(idx++);
			agts.add(createNewConseiller(p));
		}
		return agts;
	}

	private static ArrayList<Employe> populateDummyAdmins(List<Personne> personnes, int count) {
		ArrayList<Employe> adms = new ArrayList<Employe>();
		int idx = 0;
		final int len = personnes.size();
		Personne p;

		for (int i=0; i < count && idx < len; i++) {
			p = personnes.get(idx++);
			adms.add(createNewAdmin(p));
		}
		return adms;
	}

	public static List<Personne> getPersonnes() throws CloneNotSupportedException {
		List<Personne> pers = new ArrayList<Personne>();

		for (Iterator<Personne> it= personnes.iterator(); it.hasNext();) {
			pers.add( new Personne (it.next()) );
		}
		return pers;
	}

	public static List<Client> getClients() throws CloneNotSupportedException {
		List<Client> clts = new ArrayList<Client>();

		for (Iterator<Client> it= clients.iterator(); it.hasNext();) {
			clts.add( new Client (it.next()) );
		}
		return clts;		
	}

	public static List<Employe> getConseillers() throws CloneNotSupportedException {
		List<Employe> agts = new ArrayList<Employe>();

		for (Iterator<Employe> it= conseillers.iterator(); it.hasNext();) {
			agts.add( new Employe (it.next()) );
		}
		return agts;		
	}

	public static List<Employe> getAdmins() throws CloneNotSupportedException {
		List<Employe> adms = new ArrayList<Employe>();

		for (Iterator<Employe> it= admins.iterator(); it.hasNext();) {
			adms.add( new Employe (it.next()) );
		}
		return adms;		
	}

	private static List<Client> populateDummyClients() throws ParseException{
		if (clients == null) {
			clients = new ArrayList<Client>();
			for (Personne p : personnes) {
				clients.add(createNewClient(p));
			}
		}
//		clients.add(createNewClient("C"+counter.incrementAndGet(), 
//				titre, 
//				nom[i_nom], 
//				prenom, 
//				sdf.parse(ddn_str), 
//				mdp[i_mdp] + Integer.parseInt(((Math.random()*1000)+"").substring(0, 4)), 
//				nom[i_nom] + "." + prenom + "@acme.com"));
		// ###############################################################
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Hajji", "Wajih", sdf.parse("26-12-1990"), "k1ll3r123", "wajih@formation.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Adnan", "SP", sdf.parse("27-11-1993"), "t1k3r123", "adn@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Gauthier", "Benjamin", sdf.parse("09-11-1995"), "b1b3r123", "gauth@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Mahboubi", "Mohammed", sdf.parse("17-02-1980"), "m1m3r123", "mahb@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Patel", "Vika", sdf.parse("13-12-1988"), "g1k3r123", "patel@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mme", "Ming", "Nah", sdf.parse("31-05-1989"), "k1m3r123", "ming@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mme", "Egbo", "Milouda", sdf.parse("24-10-1991"), "j1g3r123", "egbo@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mme", "schillacci", "Valeria", sdf.parse("13-02-1993"), "j1q3r123", "schillacci@abc.com"));
//		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mme", "Dropsi", "Sonia", sdf.parse("22-07-1997"), "k1h3r123", "dropsi@abc.com"));

		return clients;
	}

	private static List<Employe> populateDummyConseillers() throws ParseException{
		if (conseillers == null) {
			conseillers = new ArrayList<Employe>();
			for (Personne p : personnes) {
				conseillers.add(createNewConseiller(p));
			}
		}
		return conseillers;
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

	private static Personne createNewPersonne(String id, String civilite, String nom, String prenom, Date ddn, String mdp) {//, String mail) {
		Personne pers = new Personne();
		pers.setId(id);
		pers.setCivilite(civilite);
		pers.setNom(nom);
		pers.setPrenom(prenom);
		pers.setDdn(ddn);
		//pers.setAdresse(createDummyAdresse(mail));
		pers.setAdresse(createDummyAdresse(nom.trim().toLowerCase()+"."+prenom.trim().toLowerCase()));
		pers.setHashMdp(mdp);
		System.out.println("creation nouvelle personne: "+pers);

		return pers;
	}

	public static Client createNewClient(Personne p) {
		Client client = new Client(p);

		client.setType(Personne.ROLE_CLIENT);
		client.setComptes(createDummyComptes(p.getId()));
		System.out.println("creation nouveau client: "+client);
		return client;
	}

	public static Employe createNewAdmin(Personne p) {
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

	private static ArrayList<Compte> createDummyComptes(String id) {
		ArrayList<Compte> cpts = new ArrayList<Compte>();
		int h = id.hashCode();

		for (int i=0, n=1+h%10; i < n; i++) {
			Compte c = new Compte();
			c.setNumCpt(h+"-cpt-"+i);
			c.setTransactions(createDummyTransactions(h+"-tr-"+i));
			c.setChequiers(createDummyChequiers(h+"-chq-"+i));
			c.setNotifications(createDummyNotifications(h+"-ntf-"+i));
			cpts.add(c);
		}
		return cpts;
	}

	private static ArrayList<Transaction> createDummyTransactions(String id) {
		ArrayList<Transaction> trcts = new ArrayList<Transaction>();
		int h = id.hashCode();

		for (int i=0, n=1+h%150; i<n; i++) {
			//Transaction t = new Transaction();
		}

		return trcts;
	}

	private static ArrayList<CommandeChequier> createDummyChequiers(String id) {
		ArrayList<CommandeChequier> chqs = new ArrayList<CommandeChequier>();
		int h = id.hashCode();

		return chqs;
	}

	private static ArrayList<Notification> createDummyNotifications(String id) {
		ArrayList<Notification> ntfs = new ArrayList<Notification>();
		int h = id.hashCode();

		return ntfs;
	}

	public static Adresse createDummyAdresse(String mail) {
		Random rnd = new Random();
		int numero = rnd.nextInt(10000) % 1000;
		int rue_idx;
		int ville_idx;
		int phone_idx;
		int email_idx;
		
		String[] emails_suffixes = {
				"@acme.com",
				"@abc.com",
				"@tiko.com",
				"@toto.com",
				"@tito.com",
				"@bito.com"
		};

		String [] rues = new String[] {
			"rue des sardines",
			"rue des mouettes",
			"rue des tulipes",
			"route de bayonne",
			"avenue du docteur long",
			"avenue foch",
			"avenue lacassagne",
			"alles jules guesdes",
			"place jean jaures",
			"cours gambetta",
			"cours laplace"
			};
		HashMap<String, String> codes_postaux = new HashMap<String, String>();
		String[] phones = new String[100];

		codes_postaux.put("paris", "75000");
		codes_postaux.put("toulouse", "31000");
		codes_postaux.put("rennes", "35000");
		codes_postaux.put("caen", "14000");
		codes_postaux.put("lyon", "69000");
		codes_postaux.put("nantes", "44000");
		codes_postaux.put("marseille", "13000");
		codes_postaux.put("laval", "53000");
		codes_postaux.put("rouen", "79000");
		codes_postaux.put("lille", "59000");
		codes_postaux.put("le mans", "72000");
		codes_postaux.put("montpellier", "34000");
		codes_postaux.put("grenoble", "38000");
		codes_postaux.put("perpignan", "66000");
		codes_postaux.put("carcasonne", "11000");
		codes_postaux.put("nimes", "32000");
		codes_postaux.put("montauban", "82000");
		codes_postaux.put("amiens", "80000");
		
		String[] villes = new String[codes_postaux.size()];
		for (int i=0; i<villes.length; i++) {
			villes[i] = codes_postaux.keySet().toArray()[i].toString();
		}
		for (int i=0; i<phones.length; i++) {
			phones[i] = gen_phone(9);
		}

		email_idx = rnd.nextInt(10000) % emails_suffixes.length;
		rue_idx = rnd.nextInt(10000) % rues.length;
		ville_idx = rnd.nextInt(10000) % villes.length;
		phone_idx = rnd.nextInt(10000) % rues.length;

		return new Adresse(numero, 
				rues[rue_idx], 
				villes[ville_idx], 
				codes_postaux.get(villes[ville_idx]), 
				phones[phone_idx],
				mail + emails_suffixes[email_idx]);
	}

	public static String gen_phone(int limit) {
		String p = "+33";
		int multiplier = 1000;

		if (((int)Math.round((Math.random()*multiplier))) % 2 == 0) {
			p += '6';
		} else {
			p += '7';
		}
		for (int i=1; i<limit; i++) {
			p += (Math.round((Math.random()*multiplier))+"").charAt(0);
			if (i%2 == 0) {
				multiplier /= 10;
			} else {
				multiplier *= 10;
			}
		}
		return p;
	}
}
