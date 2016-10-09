package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.Date;

public class Employe extends Personne {
	private Date dateEntree;
	private ArrayList<String> fonctions;

	private ArrayList<Notification> notifications; //(Not in constructor ?)

	public Employe() {
		this.setType(ROLE_CONSEILLER | ROLE_ADMIN);
		fonctions = new ArrayList<String>();
	}

	public Employe(String matricule, Date dateEntree, ArrayList<String> fonctions,
			ArrayList<Notification> notifications) {
		this();
		this.dateEntree = dateEntree;
		this.fonctions = fonctions;
		this.notifications = notifications;
	}

	public Employe(Personne p) {
		this();
		if (p == null) {
			return;
		}
		super.setId(p.getId());
		super.setCivilite(p.getCivilite());
		super.setNom(p.getNom());
		super.setPrenom(p.getPrenom());
		super.setType(p.getType());
		super.setHashMdp(p.getHashMdp());
		super.setAdresse(p.getAdresse());
		super.setDdn(p.getDdn());
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public ArrayList<String> getFonctions() {
		return fonctions;
	}

	public void setFonctions(ArrayList<String> fonctions) {
		this.fonctions = fonctions;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public void setType(int type) {
		int t = type & (ROLE_CONSEILLER | ROLE_ADMIN);

		if (t == (ROLE_CONSEILLER | ROLE_ADMIN)) {
			t = ROLE_CONSEILLER;
		}
		super.setType(t);
	}

	public void setConseiller() {
		this.setType(ROLE_CONSEILLER);
	}

	public void setAdmin() {
		this.setType(ROLE_ADMIN);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employe [getDateEntree()=");
		builder.append(getDateEntree());
		builder.append(", getFonctions()=");
		builder.append(getFonctions());
		builder.append(", getNotifications()=");
		builder.append(getNotifications());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateEntree == null) ? 0 : dateEntree.hashCode());
		result = prime * result + ((fonctions == null) ? 0 : fonctions.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Employe))
			return false;
		Employe other = (Employe) obj;
		if (dateEntree == null) {
			if (other.dateEntree != null)
				return false;
		} else if (!dateEntree.equals(other.dateEntree))
			return false;
		if (fonctions == null) {
			if (other.fonctions != null)
				return false;
		} else if (!fonctions.equals(other.fonctions))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		return true;
	}
}
