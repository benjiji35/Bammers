package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

@Entity
public class Remuneration extends Transaction {

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


	public Remuneration(Compte compte, double delta, double seuil, double taux, Date dateDebut) throws BAMException {
		super(compte, dateDebut, null, 0);
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
		double s = getCompte().getSolde() - seuilMin;
		Date date = (isSealed()? getDateFin(): new Date());

		return delta * BAMTools.getDiffInDays(date, getDateDebut()) * taux / 365;
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
	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
	}

	@Override
	public void update(BAMEvent e) {
		double s = getCompte().getSolde() - seuilMin;

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
}
