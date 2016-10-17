package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import javax.persistence.NoResultException;

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
		List <Client> clients = getEntityManager().createQuery("SELECT p FROM Personne p WHERE nom LIKE  :name AND prenom like :prenom").setParameter("name", name).setParameter("prenom", prenom).getResultList();
		return null;
	}

	@Override
	public void save(Client pers) {
		persist(pers);
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findAll(Long idCons) {
		List <Client> clients = getEntityManager().createQuery("SELECT c FROM Client c WHERE conseiller_id LIKE  :id").setParameter("id", idCons).getResultList();
		return clients;

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
		return false;
		
	}

//	@Override
//	public List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte, String idCons) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Client> findAll(Object ref) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte) {
		// TODO Auto-generated method stub
		return null;
	}
}
