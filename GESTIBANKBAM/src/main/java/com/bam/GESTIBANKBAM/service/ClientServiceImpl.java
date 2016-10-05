package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Compte;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Client> clients;
	
	static{
		clients= populateDummyClients();
	}

	public List<Client> findAllClients() {
		return clients;
	}
	
	public Client findById(String id) {
		if (id == null) {
			return null;
		}
		for(Client client : clients){
			if(id.equals(client.getId())) {
				return client;
			}
		}
		return null;
	}

	private List<Client> findByOneAttribute(String attribute, String attributeName) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String target;

		if (attribute == null) {
			return findAllClients();
		}
		attribute = attribute.trim().toLowerCase();
		for(Client client : clients){
			if ("nom".equalsIgnoreCase(attributeName)) {
				target = client.getNom().toLowerCase();
				if (target.startsWith(attribute)) { 
					lesClients.add(client);
				}
			} else if ("prenom".equalsIgnoreCase(attributeName)) {
				target = client.getPrenom().toLowerCase();
				if (target.startsWith(attribute)) { 
					lesClients.add(client);
				}
			} else if ("compte".equalsIgnoreCase(attributeName)) {
				List<Compte> lesComptes = client.getComptes();
				for (int i=0, n=lesComptes.size(); i < n; i++) {
					target = lesComptes.get(i).getNumCpt().toLowerCase();
					if (target.startsWith(attribute)) { 
						lesClients.add(client);
						// we already found a Client so we break from the inner loop
						break;
					}
				}
			}
		}
		return lesClients;
	}

	public List<Client> findByNom(String nom) {
		return findByOneAttribute(nom, "nom");
	}

	public List<Client> findByPrenom(String prenom) {
		return findByOneAttribute(prenom, "prenom");
	}

	public List<Client> findByCompte(String cpte) {
		return findByOneAttribute(cpte, "prenom");
	}


	public List<Client> findByNomAndPrenom(String nom, String prenom) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String target_nom;
		String target_prenom;

		if (nom == null) {
			if (prenom == null) {
				return findAllClients();
			} else {
				return findByPrenom(prenom);
			} 
		} else {
			if (prenom == null) {
				return findByNom(nom);
			}
		}
		nom = nom.trim().toLowerCase();
		prenom = prenom.trim().toLowerCase();
		for(Client client : clients){
			target_nom = client.getNom().toLowerCase();
			target_prenom = client.getPrenom().toLowerCase();
			if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
				lesClients.add(client);
			}
		}
		return lesClients;
	}

	
	public List<Client> findByNomAndCompte(String nom, String cpte) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		List<Compte> lesComptes;
		String target_nom;
		String target_cpte;

		if (nom == null) {
			if (cpte == null) {
				return findAllClients();
			} else {
				return findByCompte(cpte);
			}
		} else if (cpte == null) {
			return findByNom(nom);
		}
		nom = nom.trim().toLowerCase();
		cpte = cpte.trim().toLowerCase();
		for(Client client : clients){
			target_nom = client.getNom().toLowerCase();
			if (target_nom.startsWith(nom)) {
				lesComptes = client.getComptes();
				for (int i=0, n=lesComptes.size(); i < n; i++) {
					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
					if (target_cpte.startsWith(cpte)) {
						lesClients.add(client);
						// we found a client, so we break from the inner loop
						break;
					}
				}
			}
		}
		return lesClients;
	}

	public List<Client> findByPrenomAndCompte(String prenom, String cpte) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		List<Compte> lesComptes;
		String target_prenom;
		String target_cpte;

		if (prenom == null) {
			if (cpte == null) {
				return findAllClients();
			} else {
				return findByCompte(cpte);
			}
		} else if (cpte == null) {
			return findByNom(prenom);
		}
		prenom = prenom.trim().toLowerCase();
		cpte = cpte.trim().toLowerCase();
		for(Client client : clients){
			target_prenom = client.getNom().toLowerCase();
			if (target_prenom.startsWith(prenom)) {
				lesComptes = client.getComptes();
				for (int i=0, n=lesComptes.size(); i < n; i++) {
					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
					if (target_cpte.startsWith(cpte)) {
						lesClients.add(client);
						// we found a client, so we break from the inner loop
						break;
					}
				}
			}
		}
		return lesClients;
	}

	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String target_nom;
		String target_prenom;
		String target_cpte;
		List<Compte> lesComptes;

		if (nom == null) {
			if (prenom == null) {
				if (cpte == null) {
					return findAllClients();
				} else {
					return findByCompte(cpte);
				}
			} else {
				if (cpte == null) {
					return findByPrenom(prenom);
				} else {
					return findByPrenomAndCompte(prenom, cpte);
				}
			}
		} else {
			if (prenom == null) {
				if (cpte == null) {
					return findByNom(nom);
				} else {
					return findByNomAndCompte(nom, cpte);
				}
			} else {
				if (cpte == null) {
					return findByNomAndPrenom(nom, prenom);
				}
			}
		}
		nom = nom.trim().toLowerCase();
		prenom = prenom.trim().toLowerCase();
		cpte = cpte.trim().toLowerCase();
		for (Client client : clients) {
			target_nom = client.getNom().toLowerCase();
			target_prenom = client.getPrenom().toLowerCase();
			if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
				lesComptes = client.getComptes();
				for (int i=0, n=lesComptes.size(); i < n; i++) {
					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
					if (target_cpte.startsWith(cpte)) {
						// we found a client, so we break out from the inner loop
						lesClients.add(client);
						break;
					}
				}
			}
		}
		return lesClients;
	}

	public void saveClient(Client client) {
		client.setId("C"+counter.incrementAndGet());
		clients.add(client);
	}

	public void updateClient(Client client) {
		int index = clients.indexOf(client);
		clients.set(index, client);
	}

	public void deleteClientById(String id) {
		if (id == null) {
			return;
		}
		for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); ) {
		    Client client = iterator.next();
		    if (id.equals(client.getId())) {
		        iterator.remove();
		    }
		}
	}

	public boolean isClientExist(Client client) {
		return findByNomAndPrenom(client.getNom(), client.getPrenom())!=null;
	}
	
	public void deleteAllClients(){
		clients.clear();
	}

	private static List<Client> populateDummyClients(){
		List<Client> clients = new ArrayList<Client>();
		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Hajji", "Wajih", "1990-12-26", "k1ll3r123", "wajih@formation.com"));
		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Adnan", "SP", "1993-11-27", "t1k3r123", "adn@abc.com"));
		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Gauthier", "Benjamin", "1995-11-09", "b1b3r123", "gauth@abc.com"));
		clients.add(createNewClient("C"+counter.incrementAndGet(), "Mr", "Mahboubi", "Mohammed", "1980-02-17", "m1m3r123", "mahb@abc.com"));
		return clients;
	}

	private static Client createNewClient(String id, String civilite, String nom, String prenom, String ddn, String mdp, String mail) {
		Client client = new Client();

		client.setId(id);
		client.setCivilite(civilite);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setDdn(createCalendar(ddn));
		client.setAdresse(createDummyAdresse(mail));
		client.setHashMdp(mdp);
		System.out.println("creation nouveau client: "+client);
		return client;
	}

	private static Calendar createCalendar(String date) {
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
		return cal;
	}

	private static Adresse createDummyAdresse(String mail) {
		int numero = ((int)Math.round((Math.random()*1000))) % 1000;
		int rue_idx;
		int ville_idx;
		int phone_idx;
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
		
		String[] villes = new String[codes_postaux.size()];
		for (int i=0; i<villes.length; i++) {
			villes[i] = codes_postaux.keySet().toArray()[i].toString();
		}
		for (int i=0; i<phones.length; i++) {
			phones[i] = gen_phone(9);
		}

		rue_idx = ((int)Math.round(Math.random()*1000)) % rues.length;
		ville_idx = ((int)Math.round(Math.random()*1000)) % villes.length;
		phone_idx = ((int)Math.round(Math.random()*1000)) % rues.length;

		return new Adresse(numero, 
				rues[rue_idx], 
				villes[ville_idx], 
				codes_postaux.get(villes[ville_idx]), 
				phones[phone_idx],
				mail);
	}

	private static String gen_phone(int limit) {
		String p = "+33";
		int multiplier = 1000;

		if (((int)Math.round((Math.random()*multiplier))) % 2 == 0) {
			p += '6';
		} else {
			p += '7';
		}
		for (int i=1; i<limit; i++) {
			p += ((int)Math.round((Math.random()*multiplier))) % multiplier;
			if (i%2 == 0) {
				multiplier /= 10;
			} else {
				multiplier *= 10;
			}
		}
		return p;
	}
}
