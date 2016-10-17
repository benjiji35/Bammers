package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

//@MappedSuperclass
@Entity
@Inheritance (strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue (strategy=GenerationType.AUTO)
	@GeneratedValue (strategy=GenerationType.TABLE)
	private Long id;

	@NotNull
	@Column (nullable=false)
	private Date dateDebut;

	private Date dateFin;

	private double montant;


	public Transaction() {
		super();
	}

	public Transaction(Date dateDebut, Date dateFin, double montant) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.montant = montant;
	}

	public abstract void update(BAMEvent e);

	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
		if (getDateDebut().getTime() <= dateFin.getTime()) {
			setDateFin(dateFin);
		} else {
			throw new BAMException("dateFin must be after dateDebut: " +
					"dateDebut=" + dateDebut +
					" - dateFin=" + dateFin);
		}
	}

	public boolean isSealed() {
		return getDateFin() != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		if (isSealed()) {
			return;
		}
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [isSealed=");
		builder.append(isSealed());
		builder.append(", getId=");
		builder.append(getId());
		builder.append(", getDateDebut=");
		builder.append(getDateDebut());
		builder.append(", getDateFin=");
		builder.append(getDateFin());
		builder.append(", hashCode=");
		builder.append(hashCode());
		builder.append(", getMontant=");
		builder.append(getMontant());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Transaction))
			return false;
		Transaction other = (Transaction) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(montant) != Double.doubleToLongBits(other.montant))
			return false;
		return true;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
