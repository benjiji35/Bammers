package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;

public interface ClientDAO {
	// CRUD methods
	void save(Client clt);
	void deleteById(Long id);
	List<Client> findAll();
	void deleteAll();

	// business methods
	Client findById(Long id);
	boolean isExists(Client clt);
	Employe getConseiller(Client clt);
	List<Client> findByNom(String nom);
	List<Client> findByPrenom(String prenom);
	List<Client> findByNomAndPrenom(String nom, String prenom);
	List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte);
}
