package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Adresse;

public interface PersonneDAO<T> {
	T findById(Long id);
	List<T> findByNom(String nom);
	List<T> findByPrenom(String prenom);
	List<T> findByAdresse(Adresse adr);
	List<T> findByNomAndPrenom(String nom, String prenom);
	List<T> findByNomAndAdresse(String nom, Adresse adr);
	List<T> findByPrenomAndAdresse(String prenom, Adresse adr);
	List<T> findByNomAndPrenomAndAdresse(String name, String prenom, Adresse adr);
	void save(T pers);
	void deleteById(Long id);
	List<T> findAll(Object ref);
	void deleteAll();
	boolean isExists(T prs);
}
