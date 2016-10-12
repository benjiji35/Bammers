package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;

public interface ClientService {
	Client findById(String id);
	
	List<Client> findByNom(String nom);
	List<Client> findByPrenom(String prenom);
	List<Client> findByCompte(String cpte);

	List<Client> findByNomAndPrenom(String nom, String prenom);
	List<Client> findByNomAndCompte(String nom, String cpte);
	List<Client> findByPrenomAndCompte(String prenom, String cpte);
	List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte);
	
	void saveClient(Client client);
	
	void updateClient(Client client);
	
	void deleteClientById(String id);
	
	List<Client> findAllNewClients(); 

	List<Client> findAllClients(); 
	
	void deleteAllClients();
	
	public boolean isClientExist(Client client);
}
