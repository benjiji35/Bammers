package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.data.BAMData;
import com.bam.GESTIBANKBAM.data.BAMDataFactory;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Service("employeService")
public class EmployeServiceImpl implements EmployeService {
	private static final AtomicLong counter = new AtomicLong();

	private static BAMData bd;
	private static List<Employe> conseillers;
	private static List<Employe> admins;
	
	static{
		bd = BAMDataFactory.getBAMData();
		conseillers = bd.getConseillers();
		admins = bd.getAdmins();
	};


	@Override
	public Employe findById(String id) {
		if (id == null) {
			return null;
		}
		for(Employe employe : conseillers){
			if(id.equals(employe.getId())) {
				return employe;
			}
		}
		
		return null;
	}
	
	private List<Employe> findByOneAttribute(String attribute, String attributeName) {
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		String target;

		if (attribute == null) {
			return findAllEmployes();
		}
		attribute = attribute.trim().toLowerCase();
		for(Employe employe : conseillers){
			if ("nom".equalsIgnoreCase(attributeName)) {
				target = employe.getNom().toLowerCase();
				if (target.startsWith(attribute)) { 
					lesEmployes.add(employe);
				}
			} else if ("prenom".equalsIgnoreCase(attributeName)) {
				target = employe.getPrenom().toLowerCase();
				if (target.startsWith(attribute)) { 
					lesEmployes.add(employe);
				}
			} else if ("id".equalsIgnoreCase(attributeName)) {
				target = employe.getId().toLowerCase();
				if (target.startsWith(attribute)) { 
					lesEmployes.add(employe);
					}
				}
			}
		return lesEmployes;	
		}
		


	@Override
	public List<Employe> findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employe> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employe> findByNomAndPrenom(String nom, String prenom) {
		ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
		String target_nom;
		String target_prenom;

		if (BAMTools.isUndefined(nom)) {
			if (BAMTools.isUndefined(prenom)) {
				return findAllEmployes();
			} else {
				return findByPrenom(prenom);
			} 
		} else {
			if (BAMTools.isUndefined(prenom)) {
				return findByNom(nom);
			}
		}
		nom = nom.trim().toLowerCase();
		prenom = prenom.trim().toLowerCase();
		for(Employe employe : conseillers){
			target_nom = employe.getNom().toLowerCase();
			target_prenom = employe.getPrenom().toLowerCase();
			if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
				lesEmployes.add(employe);
			}
		}
		return lesEmployes;
	}
	
	@Override
	public List<Employe> findByPrenomAndID(String prenom, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveEmploye(Employe emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmploye(Employe emp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployeById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employe> findAllEmployes() {
		List<Employe> emps = new ArrayList<Employe>();

		for (Employe e : conseillers) {
			emps.add(e);
		}
		for (Employe e : admins) {
			emps.add(e);
		}
		return emps;
	}

	@Override
	public void deleteAllEmployes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmployeExist(Employe emp) {
		// TODO Auto-generated method stub
		return false;
	}

}
