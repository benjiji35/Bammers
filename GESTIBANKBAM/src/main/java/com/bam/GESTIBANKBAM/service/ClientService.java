package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;

public interface ClientService {
	Client findById(Long id);
	
	List<Client> findByNom(String nom);
	List<Client> findByPrenom(String prenom);

	List<Client> findByNomAndPrenom(String nom, String prenom);
	
	void save(Client clt);
	
	void update(Client clt);
	
	void deleteById(Long id);

	List<Client> findAll(); 
	
	void addConseillerToClient(Long idClient, Employe c);
	
	void deleteAll();
	
	boolean isExists(Client clt);

	Employe getConseiller(Client clt);

	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte);

	boolean sendMail(Client clt);
	
	List<Client> findClients(Long id);

	void addMdpToClient(Long idClient);
	
	void modifyClient(Long idClient, Client c);

	List<Client> findClient(Long id);
	
	void openNewCompte(Client clt,Employe cons, double mont);
	
	void commanderChequier(Client clt, Long numCpt, Employe cons);
}
