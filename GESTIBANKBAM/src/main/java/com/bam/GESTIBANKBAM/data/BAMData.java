package com.bam.GESTIBANKBAM.data;

import java.util.List;
import java.util.Properties;

import com.bam.GESTIBANKBAM.model.Client;
import com.bam.GESTIBANKBAM.model.Employe;
import com.bam.GESTIBANKBAM.model.Personne;

public interface BAMData {

	public boolean connect(Properties p);
	public boolean close();

	// methodes utilitaires pour recuperer des listes d'objets 
	public List<Personne> getPersonnes();
	public List<Client> getClients();
	public List<Employe> getConseillers();
	public List<Employe> getAdmins();

	// les operations C.R.U.D basiques pour Personne
	public Personne findPersonne(String id);
	public boolean updatePersonne(Personne p);
	public boolean createPersonne(Personne p);
	public boolean deletePersonne(Personne p);

	// les operations C.R.U.D basiques pour Client
	public boolean createClient(Client c);
	public Client findClient(Client c);
	public boolean updateClient(Client c);
	public boolean deleteClient(Client c);

	// les operations C.R.U.D basiques pour Employe
	public boolean createEmploye(Employe e);
	public Employe findEmploye(Employe e);
	public boolean updateEmploye(Employe e);
	public boolean deleteEmploye(Employe e);
}
