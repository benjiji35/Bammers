package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Personne;

@Repository ("clientDAO")
public class ClientDAOImpl extends AbstractDAO<Long, Client> implements ClientDAO {

	@Override
	public Client findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<Client> findByNom(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByAdresse(Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByNomAndPrenom(String name, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Client pers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findAll(Object ref) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findByNomAndAdresse(String nom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByPrenomAndAdresse(String prenom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByNomAndPrenomAndAdresse(String name, String prenom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExists(Client prs) {
		// TODO Auto-generated method stub
		return false;
	}
}
