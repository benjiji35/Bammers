package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EmployeNotification extends Notification {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@ManyToOne
	private Employe employe;

	public EmployeNotification() {
		super();
	}

	public EmployeNotification(String message, Date date) {
		super(message, date);
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeNotification [getEmploye()=");
		builder.append(getEmploye());
		builder.append(", getMessage()=");
		builder.append(getMessage());
		builder.append(", hashCode()=");
		builder.append(hashCode());
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
		result = prime * result + ((employe == null) ? 0 : employe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof EmployeNotification))
			return false;
		EmployeNotification other = (EmployeNotification) obj;
		if (employe == null) {
			if (other.employe != null)
				return false;
		} else if (!employe.equals(other.employe))
			return false;
		return true;
	}
}
