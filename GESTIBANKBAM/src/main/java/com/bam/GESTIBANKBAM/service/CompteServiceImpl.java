package com.bam.GESTIBANKBAM.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bam.GESTIBANKBAM.dao.CompteDao;
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Transaction;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;
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
	public boolean setVirement(Compte cpt1, Compte cpt2, double mont) throws BAMException {
		return compteDAO.setVirement(cpt1, cpt2, mont);
//		compteDAO.save(cpt1);
//		compteDAO.save(cpt2);
	}

	@Override
	public List<Compte> findAll() {
		return compteDAO.findAll();
	}

	@Override
	public void commanderChequier(Compte cpt) {
		compteDAO.commanderChequier(cpt);
		
	}

	@Override
	public List<Double> findOngoingTransactionAmounts(Long cpt) {
		Compte compte = findByNum(cpt);
		List <Double> ops = new ArrayList<Double>();
		double d = 0, c = 0;
		double m;
		Date today = new Date();

		if (compte == null) {
			return null;
		}
		for (Transaction t : compte.getTransactions()) {
			if ((BAMTools.isLastDayOfMonth() == false) && (t.isSealed() == false)) {
				m = t.getMontant(today);
				if (m < 0) {
					d += m;
				} else {
					c += m;
				}
			}
		}
		ops.add(c);
		ops.add(d);
		return ops;
	}
}
