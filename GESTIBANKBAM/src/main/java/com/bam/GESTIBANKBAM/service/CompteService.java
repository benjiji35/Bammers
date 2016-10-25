package com.bam.GESTIBANKBAM.service;

import java.util.List;

import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.util.BAMException;

public interface CompteService {
	Compte findByNum(Long cpt);
	void setVirement(Compte cpt1, Compte Cpt2, double mont) throws BAMException;
	List<Compte> findAll();
	void commanderChequier(Compte cpt);
}
