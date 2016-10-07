package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Personne;

public interface PersonneService {
	Personne findById(String id);

	List<Personne> findByNom(String nom);
	List<Personne> findByPrenom(String prenom);

	List<Personne> findByNomAndPrenom(String nom, String prenom);
	List<Personne> findByPrenomAndVille(String prenom, String ville);
	
	void savePersonne(Personne prs);
	
	void updatePersonne(Personne prs);
	
	void deletePersonneById(String id);

	List<Personne> findAllPersonnes(); 
	
	void deleteAllPersonnes();
	
	public boolean isPersonneExist(Personne prs);
}
