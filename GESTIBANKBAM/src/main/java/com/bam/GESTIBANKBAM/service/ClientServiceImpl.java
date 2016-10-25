package com.bam.GESTIBANKBAM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.ClientDAO;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Transaction;
import com.bam.GESTIBANKBAM.utils.BAMTools;
import com.bam.GESTIBANKBAM.utils.MailSender;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;
	

	@Override
	public Client findById(Long id) {
		return clientDAO.findById(id);
	}

	@Override
	public List<Client> findByNom(String nom) {
		return clientDAO.findByNom(nom);
	}

	@Override
	public List<Client> findByPrenom(String prenom) {
		return clientDAO.findByPrenom(prenom);
	}

	@Override
	public List<Client> findByNomAndPrenom(String nom, String prenom) {
		return clientDAO.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public void save(Client client) {
		if (client != null) {
			clientDAO.save(client);
		}
	}

	@Override
	public void update(Client prs) {
		// TODO VERIF
		clientDAO.save(prs);
	}

	@Override
	public void deleteById(Long id) {
		clientDAO.deleteById(id);
	}

	@Override
	public List<Client> findAll() {
		return clientDAO.findAll();
	}

	@Override
	public void deleteAll() {
		clientDAO.deleteAll();
	}

	@Override
	public boolean isExists(Client prs) {
		return clientDAO.isExists(prs);
	}

	@Override
	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
		return clientDAO.findByNomAndPrenomAndCompte(nom, prenom, cpte);
	}

	@Override
	public Employe getConseiller(Client clt) {
		return clientDAO.getConseiller(clt);
	}

	

	@Override
	public void addConseillerToClient(Long idClient, Employe c) {
		Client currentCli = findById(idClient);
		c.addClient(currentCli);
		save(currentCli);
		 
	}

	@Override
	public boolean sendMail(Client clt) {
		return MailSender.sendMail(clt);
	}
	
	@Override
	public void openNewCompte(Client clt, Employe cons, double mont) {
		clientDAO.openNewCompte(clt, cons, mont);		
	}
	
	@Override
	public List<Client> findClients(Long id) {
		return clientDAO.findClients(id); 
	}
	
	@Override
	public void addMdpToClient(Long id) {
		Client currentCli = findById(id);
		
		if (currentCli.getHashMdp() == null) {
			String password = BAMTools.genPassword(20);
			currentCli.setHashMdp(password);
		}
		save(currentCli);
		MailSender.sendMail(currentCli);
	}

	@Override
	public void modifyClient(Long id, Client c) {
		Client currentCli = findById(id);
	
//		currentCli.setSituationMatrimoniale(c.getSituationMatrimoniale());
//		if (c.getNom()==null || c.getNom().equals("undefined")) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getNom()!=null){	
//			currentCli.setNom(c.getNom());	
//		}
//		if (c.getPrenom()==null || c.getPrenom().equals("undefined")) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getPrenom()!=null){	
//			currentCli.setPrenom(c.getPrenom());	
//		}
//		if (c.getAdresse()==null || c.getAdresse().equals("undefined")) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getAdresse()!=null){	
//			currentCli.setAdresse(c.getAdresse());	
//		}
		if (c.getVille()==null || c.getVille().equals("undefined")) {
			;//to get rid of undefined in javascript
		}
		else if (c.getVille()!=null){	
			currentCli.setVille(c.getVille());	
		}
		if (c.getCodePostal()==null || c.getCodePostal().equals("undefined")) {
			;//do nothing to get rid of undefined in javascript
		}
		else if (c.getCodePostal()!=null){	
			currentCli.setCodePostal(c.getCodePostal());	
		}
//		if (c.getMail()==null || c.getMail().equals("undefined")) {
//			;//do nothing to get rid of undefined in javascript
//		}
//		else if (c.getMail()!=null){	
//			currentCli.setMail(c.getMail());	
//		}
//		if (c.getNbEnfants()== 0) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getNbEnfants()!=0){	
//			currentCli.setNom(c.getNom());	
//		}
//		if (c.getProfession()== null || c.getProfession().equals("undefined")) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getProfession()!=null){	
//			currentCli.setProfession(c.getProfession());	
//		}
//		if (c.getIncome()== 0) {
//			;//to get rid of undefined in javascript
//		}
//		else if (c.getIncome()!=0){	
//			currentCli.setIncome(c.getIncome());	
//		}
		
		update(currentCli);
		
	}

	@Override
	public List<Client> findClient(Long id) {
		return clientDAO.findClient(id);
	}

	@Override
	public void commanderChequier(Client clt, Long numCpt, Employe cons) {
		clientDAO.commanderChequier(clt, numCpt,cons);
		
	}
	
	
	
//
// #########################################################################
// #########################################################################
// #########################################################################
//
//	private static final AtomicLong counter = new AtomicLong();
//
//	private static BAMData bd;
//	private static List<Client> clients;
//	
//	static{
//		bd = BAMDataFactory.getBAMData();
//		clients = bd.getClients();
//	};
//
//	public List<Client> findAllClients() {
//		return clients;
//	}
//	
//	public Client findById(String id) {
//		if (id == null) {
//			return null;
//		}
//		for(Client client : clients){
//			if(id.equals(client.getId())) {
//				return client;
//			}
//		}
//		
//		return null;
//	}
//
//	private List<Client> findByOneAttribute(String attribute, String attributeName) {
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		String target;
//
//		if (attribute == null) {
//			return findAllClients();
//		}
//		attribute = attribute.trim().toLowerCase();
//		for(Client client : clients){
//			if ("nom".equalsIgnoreCase(attributeName)) {
//				target = client.getNom().toLowerCase();
//				if (target.startsWith(attribute)) { 
//					lesClients.add(client);
//				}
//			} else if ("prenom".equalsIgnoreCase(attributeName)) {
//				target = client.getPrenom().toLowerCase();
//				if (target.startsWith(attribute)) { 
//					lesClients.add(client);
//				}
//			} else if ("compte".equalsIgnoreCase(attributeName)) {
//				List<Compte> lesComptes = client.getComptes();
//				for (int i=0, n=lesComptes.size(); i < n; i++) {
//					target = lesComptes.get(i).getNumCpt().toLowerCase();
//					if (target.startsWith(attribute)) { 
//						lesClients.add(client);
//						// we already found a Client so we break from the inner loop
//						break;
//					}
//				}
//			}
//		}
//		return lesClients;
//	}
//
//	public List<Client> findByNom(String nom) {
//		return findByOneAttribute(nom, "nom");
//	}
//
//	public List<Client> findByPrenom(String prenom) {
//		return findByOneAttribute(prenom, "prenom");
//	}
//
//	public List<Client> findByCompte(String cpte) {
//		return findByOneAttribute(cpte, "prenom");
//	}
//
//
//	public List<Client> findByNomAndPrenom(String nom, String prenom) {
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		String target_nom;
//		String target_prenom;
//
//		if (BAMTools.isUndefined(nom)) {
//			if (BAMTools.isUndefined(prenom)) {
//				return findAllClients();
//			} else {
//				return findByPrenom(prenom);
//			} 
//		} else {
//			if (BAMTools.isUndefined(prenom)) {
//				return findByNom(nom);
//			}
//		}
//		nom = nom.trim().toLowerCase();
//		prenom = prenom.trim().toLowerCase();
//		for(Client client : clients){
//			target_nom = client.getNom().toLowerCase();
//			target_prenom = client.getPrenom().toLowerCase();
//			if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
//				lesClients.add(client);
//			}
//		}
//		return lesClients;
//	}
//
//	
//	public List<Client> findByNomAndCompte(String nom, String cpte) {
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		List<Compte> lesComptes;
//		String target_nom;
//		String target_cpte;
//
//		if (BAMTools.isUndefined(nom)) {
//			if (BAMTools.isUndefined(cpte)) {
//				return findAllClients();
//			} else {
//				return findByCompte(cpte);
//			}
//		} else if (BAMTools.isUndefined(cpte)) {
//			return findByNom(nom);
//		}
//		nom = nom.trim().toLowerCase();
//		cpte = cpte.trim().toLowerCase();
//		for(Client client : clients){
//			target_nom = client.getNom().toLowerCase();
//			if (target_nom.startsWith(nom)) {
//				lesComptes = client.getComptes();
//				for (int i=0, n=lesComptes.size(); i < n; i++) {
//					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
//					if (target_cpte.startsWith(cpte)) {
//						lesClients.add(client);
//						// we found a client, so we break from the inner loop
//						break;
//					}
//				}
//			}
//		}
//		return lesClients;
//	}
//
//	public List<Client> findByPrenomAndCompte(String prenom, String cpte) {
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		List<Compte> lesComptes;
//		String target_prenom;
//		String target_cpte;
//
//		if (BAMTools.isUndefined(prenom)) {
//			if (BAMTools.isUndefined(cpte)) {
//				return findAllClients();
//			} else {
//				return findByCompte(cpte);
//			}
//		} else if (BAMTools.isUndefined(cpte)) {
//			return findByNom(prenom);
//		}
//		prenom = prenom.trim().toLowerCase();
//		cpte = cpte.trim().toLowerCase();
//		for(Client client : clients){
//			target_prenom = client.getNom().toLowerCase();
//			if (target_prenom.startsWith(prenom)) {
//				lesComptes = client.getComptes();
//				for (int i=0, n=lesComptes.size(); i < n; i++) {
//					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
//					if (target_cpte.startsWith(cpte)) {
//						lesClients.add(client);
//						// we found a client, so we break from the inner loop
//						break;
//					}
//				}
//			}
//		}
//		return lesClients;
//	}
//
//	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte) {
//		ArrayList<Client> lesClients = new ArrayList<Client>();
//		String target_nom;
//		String target_prenom;
//		String target_cpte;
//		List<Compte> lesComptes;
//
//		if (BAMTools.isUndefined(nom)) {
//			if (BAMTools.isUndefined(prenom)) {
//				if (BAMTools.isUndefined(cpte)) {
//					return findAllClients();
//				} else {
//					return findByCompte(cpte);
//				}
//			} else {
//				if (BAMTools.isUndefined(cpte)) {
//					return findByPrenom(prenom);
//				} else {
//					return findByPrenomAndCompte(prenom, cpte);
//				}
//			}
//		} else {
//			if (BAMTools.isUndefined(prenom)) {
//				if (BAMTools.isUndefined(cpte)) {
//					return findByNom(nom);
//				} else {
//					return findByNomAndCompte(nom, cpte);
//				}
//			} else {
//				if (BAMTools.isUndefined(cpte)) {
//					return findByNomAndPrenom(nom, prenom);
//				}
//			}
//		}
//		nom = nom.trim().toLowerCase();
//		prenom = prenom.trim().toLowerCase();
//		cpte = cpte.trim().toLowerCase();
//		for (Client client : clients) {
//			target_nom = client.getNom().toLowerCase();
//			target_prenom = client.getPrenom().toLowerCase();
//			if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
//				lesComptes = client.getComptes();
//				for (int i=0, n=lesComptes.size(); i < n; i++) {
//					target_cpte = lesComptes.get(i).getNumCpt().toLowerCase();
//					if (target_cpte.startsWith(cpte)) {
//						// we found a client, so we break out from the inner loop
//						lesClients.add(client);
//						break;
//					}
//				}
//			}
//		}
//		return lesClients;
//	}
//
//	public void saveClient(Client client) {
//		client.setId("C"+counter.incrementAndGet());
//		//clients.add(client);
//		bd.createClient(client);
//	}
//
//	public void updateClient(Client client) {
//		int index = clients.indexOf(client);
//
//		if (index != -1) {
//			clients.set(index, client);
//		}
//	}
//
//	public void deleteClientById(String id) {
//		if (id == null) {
//			return;
//		}
//		for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); ) {
//		    Client client = iterator.next();
//		    if (id.equals(client.getId())) {
//		        iterator.remove();
//		    }
//		}
//	}
//
//	public boolean isClientExist(Client client) {
//		//return findByNomAndPrenom(client.getNom(), client.getPrenom())!=null;
//		return findById(client.getId()) != null;
//	}
//	
//	public void deleteAllClients(){
//		clients.clear();
//	}
//
////	@Override
////	public List<Client> findAllNewClients() {
////		List<Client> client = null;
////			for (Client cli : clients) {
////				client.add(cli);
////			}
////		return client;
////	}
////	
//	
}
