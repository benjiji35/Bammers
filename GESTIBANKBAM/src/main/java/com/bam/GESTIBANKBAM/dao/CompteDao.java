package com.bam.GESTIBANKBAM.dao;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.util.BAMException;

public interface CompteDao {
	Compte findByNum(Long cpt);
	public void setVirement(Compte cpt1, Compte cpt2, double mont) throws BAMException;
	List<Compte> findAll();
	void commanderChequier(Compte cpt);
}
