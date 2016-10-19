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
@Table (name="Remuneration")
//@DiscriminatorValue (value=Remuneration.TYPE+"")
public class Remuneration extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	public static final int TYPE = 8;

	@NotNull
	@Column (nullable=false)
	private double seuilMin;

	@NotNull
	@Column (nullable=false)
	private double taux;

	public Remuneration() {
		setType(TYPE);
	}

	public Remuneration(double solde, double seuil, double taux, Date dateDebut) throws BAMException {
		super(TYPE, dateDebut, null, solde, true);
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
		double m = 0;

		if (isSealed()) {
			Date date = getDateFin();
			m = Math.abs((super.getMontant() - getSeuilMin()) * 
					BAMTools.getDiffInDays(date, getDateDebut()) * taux / 365);
		}

		return m;
	}

	@Override
	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
		setDateFin(dateFin);
	}

	@Override
	public void update(BAMEvent e) {
		Object source = e.getSource();
		Compte cpte   = (source instanceof Compte? (Compte)source: null);
		double balance;

		if (cpte != null) {
			balance = cpte.getBalance();
			if (balance < getSeuilMin()) {
				try {
					sealTransaction(new Date());
				} catch (BAMException e1) {
					e1.printStackTrace(System.err);
				}
			}
		}
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
}
