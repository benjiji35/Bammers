package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Employe;


public interface EmployeService {
	Employe findById(Long id);
	
	List<Employe> findByNom(String nom);
	List<Employe> findByPrenom(String prenom);

	List<Employe> findByNomAndPrenom(String nom, String prenom);
	
	void save(Employe emp);
	
	void update(Employe emp);
	
	void deleteById(Long id);

	List<Employe> findAll(); 
	
	void deleteAll();
	
	public boolean isExists(Employe emp);

	void modifyConseiller(Long id, Employe e);

}
