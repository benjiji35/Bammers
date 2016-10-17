package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;

public interface ClientService extends PersonneService<Client> {
	public List<Client> findByNomAndPrenomAndCompte(String nom, String prenom, String cpte);
}
