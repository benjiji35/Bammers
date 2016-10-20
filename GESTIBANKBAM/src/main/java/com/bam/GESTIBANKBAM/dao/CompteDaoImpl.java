package com.bam.GESTIBANKBAM.dao;

import org.springframework.stereotype.Repository;

import com.bam.GESTIBANKBAM.model.Compte;
@Repository ("compteDAO")
public class CompteDaoImpl extends AbstractDAO<Long, Compte> implements CompteDao {

	@Override
	public Compte findByNum(Long cpt) {
		return getByKey(cpt);
	}

}
