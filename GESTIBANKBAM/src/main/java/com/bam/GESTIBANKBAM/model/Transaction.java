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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

//@MappedSuperclass
@Entity
@Table (name="Transaction")
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
	@Temporal (TemporalType.DATE)
	private Date dateDebut;

	@Temporal (TemporalType.DATE)
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

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
