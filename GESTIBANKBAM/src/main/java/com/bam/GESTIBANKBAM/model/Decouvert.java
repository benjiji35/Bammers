package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Entity
@Table (name="Decouvert")
//@DiscriminatorValue (value=Decouvert.TYPE+"")
public class Decouvert extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	public static final int TYPE = 4;

	@NotNull
	@Column (nullable=false)
	private double taux;

	public Decouvert() {
		setType(TYPE);
	}

	public Decouvert(double balance, double taux, Date dateDebut) throws BAMException {
		super(TYPE, dateDebut, null, balance, true);
		this.taux = taux;
		if (taux < 0) {
			throw new BAMException("rate must be positive. taux=" + taux);
		}
	}

//	public void calculAgios() {
//		
//	}

	@Override
	public double getMontant() {
		double m = 0;
		int d;
		Date date;

		if (isSealed()) {
			date = getDateFin();
			d    = BAMTools.getDiffInDays(getDateDebut(), date);
			m    = -1 * Math.abs((super.getMontant() * d * getTaux()) / 365);
		}

		return m;
	}

	@Override
	public void update(BAMEvent evt) {
		Object source = evt.getSource();
		Compte cpte   = (source instanceof Compte? (Compte)source: null);
		double balance;

		if (cpte != null) {
			balance = cpte.getBalance();
			if (balance >= 0) {
				try {
					sealTransaction(new Date());
				} catch (BAMException e1) {
					e1.printStackTrace(System.err);
				}
			}
		}
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

}
