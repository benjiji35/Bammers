package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;

public class Decouvert implements Transaction {
	private Compte cpte;
	private double montant;
	private double agios;
	private double taux;
	private Date dateDebut;
	private Date dateFin;

	public Decouvert(Compte cpte, double agios, double taux, Date dateDebut) throws BAMException {
		this.cpte = cpte;
		this.agios = agios;
		this.taux = taux;
		this.dateDebut = new Date(dateDebut.getTime());
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
		double s = Math.abs(cpte.getSolde());
		int d = BAMTools.getDiffInDays(getDateDebut(), new Date());

		return (s * d * taux) / 365;
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
	public void update(BAMEvent e) {
		Compte cpte = (Compte)e.getSource();
		if (this.cpte == null) {
			this.cpte = cpte;
		}
		switch (e.getType()) {
		case NEW_TRANSACTION:
			if (cpte.getSolde() >= 0) {
				sealTransaction(new Date());
				cpte.fireBAMEvent(e);
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

	@Override
	public void sealTransaction(Date dateFin) {
		if (! isSealed()) {
			this.dateFin = new Date(dateFin.getTime());
		}
	}

	@Override
	public boolean isSealed() {
		return dateFin != null;
	}

}
