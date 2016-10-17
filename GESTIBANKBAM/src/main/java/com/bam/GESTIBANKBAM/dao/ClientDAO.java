package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Client;

public interface ClientDAO extends PersonneDAO<Client> {
	public List<Client> findByNomAndPrenomAndCompte(String name, String prenom, String cpte);
	public Client findById(Long id);
	public void save(Client pers);
	public void deleteById(Long id);
	public List<Client> findAll(Long idCons);
}
