package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Entity
public class Decouvert extends Transaction {

	@NotNull
	@Column (nullable=false)
	private double agios;

	@NotNull
	@Column (nullable=false)
	private double taux;

	public Decouvert(Compte compte, double agios, double taux, Date dateDebut) throws BAMException {
		super(compte, dateDebut, null, 0);
		this.agios = agios;
		this.taux = taux;
		if (agios < 0 || taux < 0) {
			throw new BAMException("agios and taux both must be positive. agios=" + 
					agios +
					" - taux=" + 
					taux);
		}
	}

	public void calculAgios() {
		
	}

	@Override
	public double getMontant() {
		double s = Math.abs(getCompte().getSolde());
		int d = BAMTools.getDiffInDays(getDateDebut(), new Date());

		return (s * d * taux) / 365;
	}

	@Override
	public void update(BAMEvent evt) {
		Compte cpte = (Compte)evt.getSource();
		if (getCompte() == null) {
			setCompte(cpte);
		}
		switch (evt.getType()) {
		case NEW_TRANSACTION:
			if (cpte.getSolde() >= 0) {
				try {
					sealTransaction(new Date());
				} catch (BAMException e1) {
					e1.printStackTrace(System.err);
				}
				cpte.fireBAMEvent(evt);
			}
			break;
		case NEW_DAY:
			break;
		case BALANCE_WAS_NEGATIVE_THEN_BECOME_POSITIVE:
			break;
		case BALANCE_WAS_POSITIVE_THEN_BECOME_NEGATIVE:
			break;
		case TRANSACTION_SEALED:
			break;
			default:
				;
		}
	}

	public double getAgios() {
		return agios;
	}

	public void setAgios(double agios) {
		this.agios = agios;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Decouvert [getMontant()=");
		builder.append(getMontant());
		builder.append(", getAgios()=");
		builder.append(getAgios());
		builder.append(", getTaux()=");
		builder.append(getTaux());
		builder.append(", isSealed()=");
		builder.append(isSealed());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getCompte()=");
		builder.append(getCompte());
		builder.append(", getDateDebut()=");
		builder.append(getDateDebut());
		builder.append(", getDateFin()=");
		builder.append(getDateFin());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(agios);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taux);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof Decouvert)) {
			return false;
		}
		Decouvert other = (Decouvert) obj;
		if (Double.doubleToLongBits(agios) != Double.doubleToLongBits(other.agios)) {
			return false;
		}
		if (Double.doubleToLongBits(taux) != Double.doubleToLongBits(other.taux)) {
			return false;
		}
		return true;
	}

}
