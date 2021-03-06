package com.bam.GESTIBANKBAM.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.EmployeDAO;
import com.bam.GESTIBANKBAM.model.Adresse;
import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;

@Service("employeService")
@Transactional
public class EmployeServiceImpl implements EmployeService {
	@Autowired
	private EmployeDAO employeDAO;

	@Override
	public Employe findById(Long id) {
		return employeDAO.findById(id);
	}

	@Override
	public List<Employe> findByNom(String nom) {
		return employeDAO.findByNom(nom);
	}

	@Override
	public List<Employe> findByPrenom(String prenom) {
		return employeDAO.findByPrenom(prenom);
	}

	@Override
	public List<Employe> findByNomAndPrenom(String nom, String prenom) {
		return employeDAO.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public void save(Employe prs) {
		employeDAO.save(prs);
	}

	@Override
	public void update(Employe prs) {
		// TODO VERIF
		employeDAO.save(prs);
	}

	@Override
	public void deleteById(Long id) {
		employeDAO.deleteById(id);
	}

	@Override
	public List<Employe> findAll() {
		return employeDAO.findAll();
	}

	@Override
	public void deleteAll() {
		employeDAO.deleteAll();
	}

	@Override
	public boolean isExists(Employe prs) {
		return employeDAO.isExists(prs);
	}

	@Override
	public void modifyConseiller(Long id, Employe e) {
		Employe currentEmp = findById(id);
		
		if (e.getNom() == null || e.getNom().equals("undefined")) {
			;// to get rid of undefined in javascript
		} else if (e.getNom() != null) {
			currentEmp.setNom(e.getNom());
		}
		if (e.getPrenom() == null || e.getPrenom().equals("undefined")) {
			;// to get rid of undefined in javascript
		} else if (e.getPrenom() != null) {
			currentEmp.setPrenom(e.getPrenom());
		}
		if (e.getVille() == null || e.getVille().equals("undefined")) {
			;// to get rid of undefined in javascript
		} else if (e.getVille() != null) {
			currentEmp.setVille(e.getVille());
		}
		if (e.getCodePostal() == null || e.getCodePostal().equals("undefined")) {
			;// do nothing to get rid of undefined in javascript
		} else if (e.getCodePostal() != null) {
			currentEmp.setCodePostal(e.getCodePostal());
		}
		if (e.getNbEnfants() == 0) {
			;// to get rid of undefined in javascript
		} else if (e.getNbEnfants() != 0) {
			currentEmp.setNom(e.getNom());
		}
		if (e.getAdresse() == null || e.getAdresse().equals("undefined")) {
			;// to get rid of undefined in javascript
		} else if (e.getAdresse() != null) {
			currentEmp.setAdresse(e.getAdresse());
		}

		save(currentEmp);

	}

	@Override
	public void commanderChequier(Client clt, Long numCpt, Employe cons) {
		employeDAO.commanderChequier(clt, numCpt, cons);
		
	}
}

//
// #########################################################################
// #########################################################################
// #########################################################################
//
//
// private static final AtomicLong counter = new AtomicLong();
//
// private static BAMData bd;
// private static List<Employe> conseillers;
// private static List<Employe> admins;
//
// static{
// bd = BAMDataFactory.getBAMData();
// conseillers = bd.getConseillers();
// admins = bd.getAdmins();
// };
//
//
// @Override
// public Employe findById(String id) {
// if (id == null) {
// return null;
// }
// for(Employe employe : conseillers){
// if(id.equals(employe.getId())) {
// return employe;
// }
// }
//
// return null;
// }
//
// private List<Employe> findByOneAttribute(String attribute, String
// attributeName) {
// ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
// String target;
//
// if (attribute == null) {
// return findAllEmployes();
// }
// attribute = attribute.trim().toLowerCase();
// for(Employe employe : conseillers){
// if ("nom".equalsIgnoreCase(attributeName)) {
// target = employe.getNom().toLowerCase();
// if (target.startsWith(attribute)) {
// lesEmployes.add(employe);
// }
// } else if ("prenom".equalsIgnoreCase(attributeName)) {
// target = employe.getPrenom().toLowerCase();
// if (target.startsWith(attribute)) {
// lesEmployes.add(employe);
// }
// } else if ("id".equalsIgnoreCase(attributeName)) {
// target = employe.getId().toLowerCase();
// if (target.startsWith(attribute)) {
// lesEmployes.add(employe);
// }
// }
// }
// return lesEmployes;
// }
//
//
//
// @Override
// public List<Employe> findByNom(String nom) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public List<Employe> findByPrenom(String prenom) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public List<Employe> findByNomAndPrenom(String nom, String prenom) {
// ArrayList<Employe> lesEmployes = new ArrayList<Employe>();
// String target_nom;
// String target_prenom;
//
// if (BAMTools.isUndefined(nom)) {
// if (BAMTools.isUndefined(prenom)) {
// return findAllEmployes();
// } else {
// return findByPrenom(prenom);
// }
// } else {
// if (BAMTools.isUndefined(prenom)) {
// return findByNom(nom);
// }
// }
// nom = nom.trim().toLowerCase();
// prenom = prenom.trim().toLowerCase();
// for(Employe employe : conseillers){
// target_nom = employe.getNom().toLowerCase();
// target_prenom = employe.getPrenom().toLowerCase();
// if (target_nom.startsWith(nom) && target_prenom.startsWith(prenom)) {
// lesEmployes.add(employe);
// }
// }
// return lesEmployes;
// }
//
// @Override
// public List<Employe> findByPrenomAndID(String prenom, String id) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public void saveEmploye(Employe emp) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void updateEmploye(Employe emp) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public void deleteEmployeById(String id) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public List<Employe> findAllEmployes() {
// List<Employe> emps = new ArrayList<Employe>();
//
// for (Employe e : conseillers) {
// emps.add(e);
// }
// for (Employe e : admins) {
// emps.add(e);
// }
// return emps;
// }
//
// @Override
// public void deleteAllEmployes() {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public boolean isEmployeExist(Employe emp) {
// // TODO Auto-generated method stub
// return false;
// }
//
