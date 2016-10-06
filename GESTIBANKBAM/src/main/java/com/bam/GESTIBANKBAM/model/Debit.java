package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

public class Debit implements Transaction {
	private double montant;
	private Date dateDebut;

	public Debit (double montant, Date dateDebut) throws BAMException {
		this.montant = montant;
		if (montant >= 0) {
			throw new BAMException("a Debit operation must be negative: montant="+montant);
		}
		this.dateDebut = new Date(dateDebut.getTime());
	}

	@Override
	public double getMontant() {
		return montant;
	}

	@Override
	public Date getDateDebut() {
		return new Date(dateDebut.getTime());
	}

	@Override
	public Date getDateFin() {
		return getDateDebut();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Debit [getMontant()=");
		builder.append(getMontant());
		builder.append(", getDateDebut()=");
		builder.append(getDateDebut());
		builder.append(", getDateFin()=");
		builder.append(getDateFin());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void sealTransaction(Date dateFin) {
	}

	@Override
	public boolean isSealed() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		long temp;
		temp = Double.doubleToLongBits(montant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Debit))
			return false;
		Debit other = (Debit) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (Double.doubleToLongBits(montant) != Double.doubleToLongBits(other.montant))
			return false;
		return true;
	}

	@Override
	public void update(BAMEvent e) {}
}
