package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Employe;


public interface EmployeService {
	Employe findById(String id);
	
	List<Employe> findByNom(String nom);
	List<Employe> findByPrenom(String prenom);

	List<Employe> findByNomAndPrenom(String nom, String prenom);
	List<Employe> findByPrenomAndCompte(String prenom, String cpte);
	
	void saveEmploye(Employe emp);
	
	void updateEmploye(Employe emp);
	
	void deleteEmployeById(String id);

	List<Employe> findAllEmployes(); 
	
	void deleteAllEmployes();
	
	public boolean isEmployeExist(Employe emp);

}
