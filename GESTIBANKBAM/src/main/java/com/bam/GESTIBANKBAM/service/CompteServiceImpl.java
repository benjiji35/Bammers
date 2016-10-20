package com.bam.GESTIBANKBAM.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.CompteDao;
import com.bam.GESTIBANKBAM.model.Compte;
@Service("compteService")
@Transactional
public class CompteServiceImpl implements CompteService {
	@Autowired
	private CompteDao compteDAO;

	@Override
	public Compte findByNum(Long cpt) {
		return compteDAO.findByNum(cpt);
	}

}
