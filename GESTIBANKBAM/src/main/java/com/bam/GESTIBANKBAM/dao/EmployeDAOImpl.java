package com.bam.GESTIBANKBAM.dao;

import java.util.ArrayList;
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
		List<Employe> employe = getEntityManager()
				//.createQuery("SELECT p FROM Personne p,Employe e WHERE p.id = e.id  WHERE p.nom LIKE :nom OR p.prenom LIKE :prenom ")
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :name OR p.prenom LIKE :prenom ")
				.setParameter("name","%"+ nom+"%")
				.setParameter("prenom","%"+ prenom+"%")
				.getResultList();
		
		List<Employe> emp = new ArrayList<>();
			for (Employe e : employe){
				if (e.getType()== Personne.ROLE_CONSEILLER){
				emp.add(e);
				}
			}
			return emp;
	}

	@Override
	public void save(Employe pers) {
		System.out.println(">>> persisting client::"+pers);
		persist(pers);
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
