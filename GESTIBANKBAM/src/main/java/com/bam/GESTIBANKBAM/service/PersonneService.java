package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Adresse;

public interface PersonneService<T> {
	T findById(Long id);

	List<T> findByNom(String nom);
	List<T> findByPrenom(String prenom);
	List<T> findByAdresse(Adresse adr);

	List<T> findByNomAndPrenom(String nom, String prenom);
	List<T> findByNomAndAdresse(String nom, Adresse adr);
	List<T> findByPrenomAndAdresse(String prenom, Adresse adr);
	List<T> findByNomAndPrenomAndAdresse(String nom, String prenom, Adresse adr);
	
	void save(T prs);
	
	void update(T prs);
	
	void deleteById(Long id);

	List<T> findAll(); 
	
	void deleteAll();
	
	public boolean isExists(T prs);
}
