package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Employe;

public interface ConseillerService {
	Employe findById(String id);
	
	List<Employe> findByNom(String nom);
	List<Employe> findByPrenom(String prenom);

	List<Employe> findByNomAndPrenom(String nom, String prenom);
	
	void saveConseiller(Employe agent);
	
	void updateConseiler(Employe agent);
	
	void deleteConseillerById(String id);

	List<Employe> findAllConseillers(); 
	
	void deleteAllConseillers();
	
	public boolean isConseillerExist(Employe agent);

}
