package com.bam.GESTIBANKBAM.service;

import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.util.BAMException;

public interface CompteService {
	Compte findByNum(Long cpt);
	void setVirement(Compte cpt1, Compte Cpt2, double mont) throws BAMException;
}
