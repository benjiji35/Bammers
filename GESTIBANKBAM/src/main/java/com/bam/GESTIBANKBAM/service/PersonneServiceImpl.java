package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.data.BAMData;
import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

@Service("personneService")

public class PersonneServiceImpl implements PersonneService {
	
	private static List<Personne> personnes;
	
	static{
		try {
			personnes = BAMData.getPersonnes();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	@Override
	public Personne findById(String id) {
		for(Personne pers : personnes){
			if(pers.getId().equalsIgnoreCase(id)){
				return pers;
			}
		}
		return null;
	}

	@Override
	public List<Personne> findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByNomAndPrenom(String nom, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByPrenomAndVille(String prenom, String ville) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePersonne(Personne prs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersonne(Personne prs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonneById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Personne> findAllPersonnes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllPersonnes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPersonneExist(Personne prs) {
		// TODO Auto-generated method stub
		return false;
	}


}
