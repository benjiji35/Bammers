package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Employe extends Personne {
	private Calendar dateEntree;
	private ArrayList<String> fonctions;

	private ArrayList<Notification> notifications; //(Not in constructor ?)

	public Employe() {
		this.setType(ROLE_CONSEILLER | ROLE_ADMIN);
		fonctions = new ArrayList<String>();
	}

	public Employe(String matricule, Calendar dateEntree, ArrayList<String> fonctions,
			ArrayList<Notification> notifications) {
		this();
		this.dateEntree = dateEntree;
		this.fonctions = fonctions;
		this.notifications = notifications;
	}

	public Calendar getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Calendar dateEntree) {
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
		super.setType(type & ROLE_CONSEILLER & ROLE_ADMIN);
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
		builder.append("Employe [getDateEntree=");
		builder.append(getDateEntree());
		builder.append(", getFonctions=");
		builder.append(getFonctions());
		builder.append(", getNotifications=");
		builder.append(getNotifications());
		builder.append(", toString=");
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