package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

public interface PersonneService {
	Personne findById(Long id);

	List<Personne> findByNom(String nom);
	List<Personne> findByPrenom(String prenom);
	List<Personne> findByAdresse(Adresse adr);

	List<Personne> findByNomAndPrenom(String nom, String prenom);
	List<Personne> findByNomAndAdresse(String nom, Adresse adr);
	List<Personne> findByPrenomAndAdresse(String prenom, Adresse adr);
	List<Personne> findByNomAndPrenomAndAdresse(String nom, String prenom, Adresse adr);
	
	void save(Personne prs);
	
	void update(Personne prs);
	
	void deleteById(Long id);

	List<Personne> findAll(); 
	
	void deleteAll();
	
	public boolean isExists(Personne prs);
}
