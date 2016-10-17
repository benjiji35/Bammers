package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Entity
public class Remuneration extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// the difference between the balance and the minimum threshold
	@NotNull
	@Column (nullable=false)
	private double delta;

	@NotNull
	@Column (nullable=false)
	private double seuilMin;

	@NotNull
	@Column (nullable=false)
	private double taux;


	public Remuneration(double delta, double seuil, double taux, Date dateDebut) throws BAMException {
		super(dateDebut, null, 0);
		this.delta = delta;
		this.seuilMin = seuil;
		this.taux = taux;
		dateDebut = new Date(dateDebut.getTime());

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
		Date date = (isSealed()? getDateFin(): null);

		return getDelta() * BAMTools.getDiffInDays(date, getDateDebut()) * taux / 365;
	}

	@Override
	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
	}

	@Override
	public void update(BAMEvent e) {
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getSeuilMin() {
		return seuilMin;
	}

	public void setSeuilMin(double seuilMin) {
		this.seuilMin = seuilMin;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Remuneration [getTaux=");
		builder.append(getTaux());
		builder.append(", getMontant=");
		builder.append(getMontant());
		builder.append(", getDelta=");
		builder.append(getDelta());
		builder.append(", getSeuilMin=");
		builder.append(getSeuilMin());
		builder.append(", hashCode=");
		builder.append(hashCode());
		builder.append(", isSealed=");
		builder.append(isSealed());
		builder.append(", getId=");
		builder.append(getId());
		builder.append(", getDateDebut=");
		builder.append(getDateDebut());
		builder.append(", getDateFin=");
		builder.append(getDateFin());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Remuneration))
			return false;
		Remuneration other = (Remuneration) obj;
		if (Double.doubleToLongBits(delta) != Double.doubleToLongBits(other.delta))
			return false;
		if (Double.doubleToLongBits(seuilMin) != Double.doubleToLongBits(other.seuilMin))
			return false;
		if (Double.doubleToLongBits(taux) != Double.doubleToLongBits(other.taux))
			return false;
		return true;
	}
}
