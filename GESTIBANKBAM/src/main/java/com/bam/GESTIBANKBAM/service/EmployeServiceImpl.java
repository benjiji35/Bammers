package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.data.BAMData;
import com.bam.GESTIBANKBAM.data.BAMDataFactory;
import com.bam.GESTIBANKBAM.model.Employe;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employe> findByPrenomAndCompte(String prenom, String cpte) {
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
