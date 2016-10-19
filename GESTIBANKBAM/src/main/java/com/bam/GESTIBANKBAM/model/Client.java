package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Client")
public class Client extends Personne {

	private static final long serialVersionUID = 1L;

	/* Constructeurs */
	public Client() {
		super();
		this.setType(ROLE_CLIENT);
		comptes = new ArrayList<Compte>();
	}

	public Client(Long idClient) {
		this();
		setId(idClient);
	}

	public Client(Personne p) {
		this();
		if (p == null) {
			return;
		}
		super.setId(p.getId());
		super.setCivilite(p.getCivilite());
		super.setNom(p.getNom());
		super.setPrenom(p.getPrenom());
		super.setType(ROLE_CLIENT);
		super.setHashMdp(p.getHashMdp());
		super.setAdresse(p.getAdresse());
		super.setDdn(p.getDdn());
	}

	/**
	 * Attributs
	 */
	@ManyToOne
	private Employe conseiller;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Compte> comptes;

	/* getters and setters */
	@JsonIgnore
	public Employe getConseiller() {
		return conseiller;
	}

	public void setConseiller(Employe conseiller) {
		this.conseiller = conseiller;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		System.out.println("Client.setCompte(comptes)=" + comptes.size());
		this.comptes = comptes;
	}

	@Override
	public void setType(int type) {
		super.setType(ROLE_CLIENT);
	}

	@Override
	public String toString() {
		return String.format("Client [getComptes()=%s, toString()=%s]", getComptes(), super.toString());
	}

}
