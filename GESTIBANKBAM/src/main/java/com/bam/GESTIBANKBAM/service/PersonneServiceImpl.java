package com.bam.GESTIBANKBAM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.PersonneDAO;
import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

@Service("personneService")
public class PersonneServiceImpl implements PersonneService<Personne> {
	
	@Autowired
	private PersonneDAO personneDAO;

	@Override
	public Personne findById(Long id) {
		return (Personne)personneDAO.findById(id);
	}

	@Override
	public List<Personne> findByNom(String nom) {
		return personneDAO.findByNom(nom);
	}

	@Override
	public List<Personne> findByPrenom(String prenom) {
		return personneDAO.findByPrenom(prenom);
	}

	@Override
	public List<Personne> findByAdresse(Adresse adr) {
		return personneDAO.findByAdresse(adr);
	}

	@Override
	public List<Personne> findByNomAndPrenom(String nom, String prenom) {
		return personneDAO.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public List<Personne> findByNomAndAdresse(String nom, Adresse adr) {
		return personneDAO.findByNomAndAdresse(nom, adr);
	}

	@Override
	public List<Personne> findByPrenomAndAdresse(String prenom, Adresse adr) {
		return personneDAO.findByPrenomAndAdresse(prenom, adr);
	}

	@Override
	public List<Personne> findByNomAndPrenomAndAdresse(String nom, String prenom, Adresse adr) {
		return personneDAO.findByNomAndPrenomAndAdresse(nom, prenom, adr);
	}

	@Override
	public void deleteById(Long id) {
		personneDAO.deleteById(id);
	}

	@Override
	public List<Personne> findAll() {
		return personneDAO.findAll(null);
	}

	@Override
	public void deleteAll() {
		personneDAO.deleteAll();
	}

	@Override
	public void save(Personne prs) {
		personneDAO.save(prs);
	}

	@Override
	public void update(Personne prs) {
		// TODO VERIF
		personneDAO.save(prs);
	}

	@Override
	public boolean isExists(Personne prs) {
		return personneDAO.isExists(prs);
	}
}
