package com.bam.GESTIBANKBAM.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.CompteDao;
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Credit;
import com.bam.GESTIBANKBAM.model.Debit;
import com.bam.GESTIBANKBAM.util.BAMException;
@Service("compteService")
@Transactional
public class CompteServiceImpl implements CompteService {
	@Autowired
	private CompteDao compteDAO;

	@Override
	public Compte findByNum(Long cpt) {
		return compteDAO.findByNum(cpt);
	}

	@Override
	public void setVirement(Compte cpt1, Compte cpt2, double mont) throws BAMException {
		compteDAO.setVirement(cpt1, cpt2, mont);
//		compteDAO.save(cpt1);
//		compteDAO.save(cpt2);
	}

}
