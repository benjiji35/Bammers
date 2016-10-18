package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

public interface PersonneDAO {
	// CRUD methods
	Personne findById(Long id);
	void save(Personne pers);
	void deleteById(Long id);
	List<Personne> findAll(Object ref);
	void deleteAll();
	boolean isExists(Personne prs);

	// business methods
	List<Personne> findByNom(String nom);
	List<Personne> findByPrenom(String prenom);
	List<Personne> findByAdresse(Adresse adr);
	List<Personne> findByNomAndPrenom(String nom, String prenom);
	List<Personne> findByNomAndAdresse(String nom, Adresse adr);
	List<Personne> findByPrenomAndAdresse(String prenom, Adresse adr);
	List<Personne> findByNomAndPrenomAndAdresse(String name, String prenom, Adresse adr);
}
