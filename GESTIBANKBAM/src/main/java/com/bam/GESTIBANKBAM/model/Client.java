package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Personne {
	private ArrayList<Compte> comptes;

	public Client() {
		super();
		this.setType(ROLE_CLIENT);
		comptes = new ArrayList<Compte>();
	}

	public Client(String idClient) {
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

	
	public ArrayList<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(ArrayList<Compte> comptes) {
		System.out.println("Client.setCompte(comptes)="+comptes.size());
		this.comptes = comptes;
	}

	@Override
	public void setType(int type) {
		super.setType(ROLE_CLIENT);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<Compte> lc = getComptes();
		final int len = lc.size();
		builder.append("Client [getComptes=[");
		for (int i = 0; i < len-1; i++) {
			builder.append(lc.get(i)).append(',');
		}
		if (len > 0) {
			builder.append(lc.get(len-1));
		}
		builder.append(']');
		builder.append(", toString=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comptes == null) ? 0 : comptes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (comptes == null) {
			if (other.comptes != null)
				return false;
		} else if (!comptes.equals(other.comptes))
			return false;
		return true;
	}


}
