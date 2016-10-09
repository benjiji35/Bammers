package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.data.BAMData;
import com.bam.GESTIBANKBAM.data.BAMDataFactory;
import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Compte;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	private static final AtomicLong counter = new AtomicLong();

	private static BAMData bd;
	private static List<Client> clients;
	
	static{
		bd = BAMDataFactory.getBAMData();
		clients = bd.getClients();
	};

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
}
