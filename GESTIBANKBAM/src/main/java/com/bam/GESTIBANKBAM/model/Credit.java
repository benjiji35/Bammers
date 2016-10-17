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

@Entity
public class Credit extends Transaction {
	public Credit(Compte compte, Date dateDebut, double montant) throws BAMException {
		super(compte, dateDebut, dateDebut, montant);
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
