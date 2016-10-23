package com.bam.GESTIBANKBAM.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.EmployeNotification;
import com.bam.GESTIBANKBAM.model.Notification;
import com.bam.GESTIBANKBAM.model.Personne;
import com.bam.GESTIBANKBAM.service.EmployeService;


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

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE nom LIKE  :name AND prenom like :prenom " +
						"compte like :compte")
				.setParameter("name", nom)
				.setParameter("prenom", prenom)
				.setParameter("compte", cpte)
				.getResultList();
		return clients;
	}


	@Override
	public void save(Client client) {
		System.out.println(">>> persisting client::"+client);
		persist(client);
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



	@Override
	public void openNewCompte(Client client, Employe cons, double montant) {
		Employe conseiller = getConseiller(client);
		EmployeNotification ntf = new EmployeNotification("Votre client "+client.getCivilite()+" "+ client.getNom()+" "+client.getPrenom()+"souhaite ouvrir un compte avec", new Date());
		conseiller.addNotification(ntf);
	}}
