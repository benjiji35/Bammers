package com.bam.GESTIBANKBAM.dao;

import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.util.BAMException;

public interface CompteDao {
	Compte findByNum(Long cpt);
	public void setVirement(Compte cpt1, Compte cpt2, double mont) throws BAMException;
}
