package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;

@Entity
public class CompteNotification extends Notification {
	@NotNull
	@ManyToOne
	private Compte compte;

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompteNotification [getCompte()=");
		builder.append(getCompte());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", getMessage()=");
		builder.append(getMessage());
		builder.append(", getDate()=");
		builder.append(getDate());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof CompteNotification)) {
			return false;
		}
		CompteNotification other = (CompteNotification) obj;
		if (compte == null) {
			if (other.compte != null) {
				return false;
			}
		} else if (!compte.equals(other.compte)) {
			return false;
		}
		return true;
	}

	public CompteNotification() {
		super();
	}

	public CompteNotification(String message, Date date) {
		super(message, date);
	}
}
