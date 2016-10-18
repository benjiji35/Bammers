package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.EmployeNotification;
import com.bam.GESTIBANKBAM.model.Personne;

@Repository("employeDAO")
public class EmployeDAOImpl extends AbstractDAO<Long, Employe> 
	implements EmployeDAO {

	@Override
	public Employe findById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public List<Employe> findByNom(String name) {
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
		try {
			return (List<Employe>)getEntityManager()
				.createQuery("SELECT e FROM Employe e WHERE e.nom LIKE :nom AND e.prenom LIKE :prenom ")
				.setParameter("nom", nom)
				.setParameter("prenom", prenom).getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public void save(Employe pers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employe> findAll() {
		try {
			return (List<Employe>)getEntityManager()
				.createQuery("SELECT e FROM Employe e ")
				.getResultList();
		} catch (NoResultException nre) {
			nre.printStackTrace(System.err);
			return null;
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isExists(Employe prs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Client> findClients(Employe emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeNotification> findNotifications(Employe emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignClient(Employe emp, Client clt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employe> findConseillers() {
		// TODO Auto-generated method stub
		return null;
	}

}
