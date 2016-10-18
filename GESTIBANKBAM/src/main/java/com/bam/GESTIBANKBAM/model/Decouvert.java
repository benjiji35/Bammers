package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Entity
@Table (name="Decouvert")
public class Decouvert extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column (nullable=false)
	private double agios;

	@NotNull
	@Column (nullable=false)
	private double taux;

	public Decouvert(double agios, double taux, Date dateDebut) throws BAMException {
		super(dateDebut, null, agios);
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
		Date date = (isSealed()? getDateFin(): null);
		int d = BAMTools.getDiffInDays(getDateDebut(), date);

		return (getAgios() * d * taux) / 365;
	}

	@Override
	public void update(BAMEvent evt) {
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

}
