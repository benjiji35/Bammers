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
	
	void deleteAll();
	
	boolean isExists(Client clt);

	Employe getConseiller(Client clt);

	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte);
}
