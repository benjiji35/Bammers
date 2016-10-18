package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.EmployeNotification;	

public interface EmployeDAO {
	// CRUD methods
	void save(Employe emp);
	void deleteById(Long id);
	List<Employe> findAll();
	void deleteAll();

	// business methods
	Employe findById(Long id);
	boolean isExists(Employe emp);
	List<Employe> findByNom(String nom);
	List<Employe> findByPrenom(String prenom);
	List<Employe> findByNomAndPrenom(String name, String prenom);
	List<Client> findClients(Employe emp);
	List<EmployeNotification> findNotifications(Employe emp);
	void assignClient(Employe emp, Client clt);
	List<Employe> findConseillers();
	
}
