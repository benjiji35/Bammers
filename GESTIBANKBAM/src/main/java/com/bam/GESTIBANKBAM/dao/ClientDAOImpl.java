package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Personne;


@Repository ("clientDAO")
public class ClientDAOImpl extends AbstractDAO<Long, Client> 
	implements ClientDAO {

	@Override
	public Client findById(Long id) {
		return getByKey(id);
	}

	@Override
	public List<Client> findByNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings ("unchecked")
	public List<Client> findByNomAndPrenom(String nom, String prenom) {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE nom LIKE  :name AND prenom like :prenom")
				.setParameter("name", nom)
				.setParameter("prenom", prenom)
				.getResultList();
		return clients;
	}

	@Override
	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE nom LIKE  :name AND prenom like :prenom " +
						"comptes like :compte")
				.setParameter("name", nom)
				.setParameter("prenom", prenom)
				.setParameter("compte", cpte)
				.getResultList();
		return clients;
	}


	@Override
	public void save(Client clt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findAll() {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT c FROM Client c ")
				.getResultList();
		return clients;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> findByPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExists(Client clt) {
		return findById(clt.getId()) != null;
	}

	@Override
	public Employe getConseiller(Client clt) {
		List<Employe> conseillers = (List<Employe>) getEntityManager()
				.createQuery("SELECT e FROM Employe e ")
				.getResultList();
		Employe agt = null;
		List<Client> clts = null;
		for (Employe e : conseillers) {
			if (e.getType() == Personne.ROLE_CONSEILLER) {
				clts = e.getClients();
				for (Client c : clts) {
					if (c.getId() == clt.getId()) {
						agt = e;
						return agt;
					}
				}
			}
		}
		System.out.println(">>> OK");
		return agt;
	}
}
