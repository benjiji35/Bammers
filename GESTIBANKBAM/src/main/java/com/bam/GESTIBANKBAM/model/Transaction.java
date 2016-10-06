package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.util.BAMException;

public interface Transaction {
	public double getMontant();
	public Date getDateDebut();
	public Date getDateFin();
	public void update(BAMEvent e);
	public void sealTransaction(Date dateFin) throws BAMException;
	public boolean isSealed();
}
