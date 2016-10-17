package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;

public interface ClientDAO extends PersonneDAO<Client> {
	public List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte);
}
