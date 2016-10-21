package com.bam.GESTIBANKBAM.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;


import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Credit;
import com.bam.GESTIBANKBAM.model.Debit;
import com.bam.GESTIBANKBAM.util.BAMException;
@Repository ("compteDAO")
public class CompteDaoImpl extends AbstractDAO<Long, Compte> implements CompteDao {

	@Override
	public Compte findByNum(Long cpt) {
		return getByKey(cpt);
	}

	@Override
	public void setVirement(Compte cpt1, Compte cpt2, double mont) throws BAMException {
		Debit debit = new Debit(new Date(), -mont);
		Credit credit = new Credit(new Date(), mont);
		boolean success = false;
		
			
		Compte compteDebiteur = findByNum(cpt1.getNumCpt());
		Compte compteCredite = findByNum(cpt2.getNumCpt());
		try {
			success = compteDebiteur.addTransaction(debit) && compteCredite.addTransaction(credit);
			if (success) {
				update(compteCredite);
				update(compteDebiteur);
			}
		} catch (Throwable e) {
			e.printStackTrace(System.err);
			
		}		
	}

}
