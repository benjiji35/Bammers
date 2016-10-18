package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

@Entity
@Table (name="Credit")
public class Credit extends Transaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Credit(Date dateDebut, double montant) throws BAMException {
		super(dateDebut, dateDebut, montant);
		if (montant < 0) {
			throw new BAMException("a Credit operation must be positive: montant="+montant);
		}
		super.sealTransaction(dateDebut);
	}

	@Override
	public void update(BAMEvent e) {
		// TODO Auto-generated method stub
	}

}
