package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Personne;

@Repository ("personneDAO")
public class PersonneDAOImpl extends AbstractDAO<Long, Personne> implements PersonneDAO<Personne> {

	@Override
	public Personne findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<Personne> findByNom(String nom) {
		try {
			return (List<Personne>)getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :nom ")
				.setParameter("nom", nom)
				.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public List<Personne> findByPrenom(String prenom) {
		try {
			return (List<Personne>)getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE p.prenom LIKE :prenom ")
				.setParameter("prenom", prenom).getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public List<Personne> findByAdresse(Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByNomAndPrenom(String nom, String prenom) {
		try {
			return (List<Personne>)getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :nom AND p.prenom LIKE :prenom")
				.setParameter("nom", nom)
				.setParameter("prenom", prenom).getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}


	@Override
	public List<Personne> findByNomAndAdresse(String nom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByPrenomAndAdresse(String prenom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personne> findByNomAndPrenomAndAdresse(String name, String prenom, Adresse adr) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(Personne pers) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Personne> findAll(Object ref) {
		List<Personne> ma_liste = (List<Personne>)getEntityManager()
				.createQuery("SELECT p FROM Personne p")
				.getResultList();

		return ma_liste;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExists(Personne prs) {
		// TODO Auto-generated method stub
		return false;
	}

}
