package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

public class Remuneration implements Transaction {

	private Compte cpte;

	// the difference between the balance and the minimum threshold
	private double delta;

	private double seuilMin;
	private Date dateDebut;
	private Date dateFin;

	private double taux;

	public Remuneration(double delta, double seuil, double taux, Date dateDebut) throws BAMException {
		super();
		this.delta = delta;
		this.seuilMin = seuil;
		this.taux = taux;
		dateDebut = new Date(dateDebut.getTime());
		dateFin   = new Date(dateFin.getTime());

		StringBuffer buf = new StringBuffer();

		if (seuil < 0) { 
			buf.append("Minimum threeshold must be positive. seuil=").append(seuil);
		}
		if (taux < 0) {
			if (buf.length() > 0) {
				buf.append("\n");
			}
			buf.append("\nRate must be positive. taux=").append(taux);
		}
		if (buf.length() > 0) {
			throw new BAMException(buf.toString());
		}
	}

	public double getTaux() {
		return taux;
	}


	@Override
	public double getMontant() {
		double s = cpte.getSolde() - seuilMin;
		Date date = (isSealed()? dateFin: new Date());

		return delta * BAMTools.getDiffInDays(date, dateDebut) * taux / 365;
//		if (s == delta) {
//			// no change, so we do not seal the transaction
//		} else if (s < delta) {
//			
//		} else {
//			
//		}
//
//		return ;
	}

	@Override
	public Date getDateDebut() {
		return new Date(dateDebut.getTime());
	}

	@Override
	public Date getDateFin() {
		if (isSealed()) {
			return new Date(dateFin.getTime());
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Remuneration [getTaux()=");
		builder.append(getTaux());
		builder.append(", getMontant()=");
		builder.append(getMontant());
		builder.append(", getDateDebut()=");
		builder.append(getDateDebut());
		builder.append(", getDateFin()=");
		builder.append(getDateFin());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", isSealed()=");
		builder.append(isSealed());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpte == null) ? 0 : cpte.hashCode());
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		long temp;
		temp = Double.doubleToLongBits(delta);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(seuilMin);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taux);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Remuneration))
			return false;
		Remuneration other = (Remuneration) obj;
		if (cpte == null) {
			if (other.cpte != null)
				return false;
		} else if (!cpte.equals(other.cpte))
			return false;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (Double.doubleToLongBits(delta) != Double.doubleToLongBits(other.delta))
			return false;
		if (Double.doubleToLongBits(seuilMin) != Double.doubleToLongBits(other.seuilMin))
			return false;
		if (Double.doubleToLongBits(taux) != Double.doubleToLongBits(other.taux))
			return false;
		return true;
	}

	@Override
	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
		if (dateDebut.getTime() <= dateFin.getTime()) {
			this.dateFin = new Date(dateFin.getTime());
		} else {
			throw new BAMException("dateFin must be after dateDebut: " +
					"dateDebut=" + dateDebut +
					" - dateFin=" + dateFin);
		}
	}

	@Override
	public boolean isSealed() {
		return dateFin != null;
	}

	@Override
	public void update(BAMEvent e) {
		double s = cpte.getSolde() - seuilMin;

		switch (e.getType()) {
		case NEW_DAY:
			break;
		case NEW_TRANSACTION:
			break;
		case BALANCE_WAS_NEGATIVE_THEN_BECOME_POSITIVE:
			break;
		case BALANCE_WAS_POSITIVE_THEN_BECOME_NEGATIVE:
			break;
		case TRANSACTION_SEALED:
			break;
		}
	}
}
