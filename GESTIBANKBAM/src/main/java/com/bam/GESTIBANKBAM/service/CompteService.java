package com.bam.GESTIBANKBAM.service;

import com.bam.GESTIBANKBAM.model.Compte;

public interface CompteService {
	Compte findByNum(Long cpt);
}
