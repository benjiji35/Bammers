package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

@Entity
@Table (name="Credit")
//@DiscriminatorValue (value=Credit.TYPE+"")
public class Credit extends Transaction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	public static final int TYPE = 1;

	public Credit() {
		setType(TYPE);		
	}

	public Credit(Date dateDebut, double montant) throws BAMException {
		super(TYPE, dateDebut, dateDebut, montant, false);
		if (montant < 0) {
			throw new BAMException("a Credit operation must be positive: montant="+montant);
		}
		super.sealTransaction(dateDebut);
	}

	@Override
	public void update(BAMEvent e) {}

	@Override
	public void setDateDebut(Date dateDebut) {
		super.setDateDebut(dateDebut);
		super.setDateFin(dateDebut);
	}

	@Override
	public void setDateFin(Date dateFin) {
		setDateDebut(dateFin);
	}
}
