package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

@Entity
public class Debit extends Transaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Debit (Date dateDebut, double montant) throws BAMException {
		super(dateDebut, dateDebut, montant);
		if (montant >= 0) {
			throw new BAMException("a Debit operation must be negative: montant="+montant);
		}
		super.sealTransaction(dateDebut);
	}

	@Override
	public void update(BAMEvent e) {
		// TODO Auto-generated method stub
		
	}
}
