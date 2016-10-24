package com.bam.GESTIBANKBAM.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Compte;
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
		List<Client> nomClient = getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :name")
				.setParameter("name", nom)
				.getResultList();
		return nomClient;
	}

	@Override
	@SuppressWarnings ("unchecked")
	public List<Client> findByNomAndPrenom(String nom, String prenom) {
		List <Client> clients = getEntityManager()
				//.createQuery("SELECT p FROM Personne p INNER JOIN p.Client c WHERE c.nom LIKE :name OR c.prenom LIKE :prenom ")
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :name OR p.prenom LIKE :prenom ")
				.setParameter("name","%"+nom+"%")
				.setParameter("prenom", "%"+prenom+"%")
				.getResultList();
		List<Client> Clts = new ArrayList<>();
		for (Client c : clients) {
			if (c.getType() == Personne.ROLE_CLIENT) {
				Clts.add(c);
			}
		}
		return Clts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
		List <Client> clts = getEntityManager()
				.createQuery("SELECT p FROM Personne p WHERE p.nom LIKE :name OR p.prenom LIKE :prenom ")
				.setParameter("name", nom)
				.setParameter("prenom", prenom)
				.getResultList();
		List <Client> clients = new ArrayList<Client>();
		String cpt_pattern;

		for (Client c : clts) {
			for (Compte cpt : c.getComptes()) {
				cpt_pattern = cpt.getNumCpt() + "";
				if (cpt_pattern.indexOf(cpte) != -1) {
					clients.add(c);
					break;
				}
			}
		}
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
	public List<Client> findClients(Long id) {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT c FROM Client c WHERE c.conseillerId = :idc")
				.setParameter("idc", id.longValue())
				.getResultList();
		List<Client> Clts = new ArrayList<>();
		for (Client c : clients) {
			if (c.getHashMdp()!= null) {
				Clts.add(c);
			}
		}
		return Clts;
		
	}

	@Override
	public List<Client> findClient(Long id) {
		List <Client> clients = getEntityManager()
				.createQuery("SELECT c FROM Client c WHERE c.conseillerId = :idc")
				.setParameter("idc", id.longValue())
				.getResultList();
		return clients;
	}



	@Override
	public void openNewCompte(Client client, Employe cons, double montant) {
		Employe conseiller = getConseiller(client);
		EmployeNotification ntf = new EmployeNotification("Votre client "+client.getCivilite()+" "+ client.getNom()+" "+client.getPrenom()+"souhaite ouvrir un compte avec", new Date());
		conseiller.addNotification(ntf);
	}}
