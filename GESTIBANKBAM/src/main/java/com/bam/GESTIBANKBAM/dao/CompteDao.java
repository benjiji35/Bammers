package com.bam.GESTIBANKBAM.dao;

import com.bam.GESTIBANKBAM.model.Compte;

public interface CompteDao {
	Compte findByNum(Long cpt);
}
