package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="Client")
public class Client extends Personne {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Compte> comptes;

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

	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		System.out.println("Client.setCompte(comptes)="+comptes.size());
		this.comptes = comptes;
	}

	@Override
	public void setType(int type) {
		super.setType(ROLE_CLIENT);
	}


}
