package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;

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

	
	public ArrayList<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(ArrayList<Compte> comptes) {
		this.comptes = comptes;
	}

	@Override
	public void setType(int type) {
		super.setType(ROLE_CLIENT);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [getComptes=");
		builder.append(getComptes());
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
