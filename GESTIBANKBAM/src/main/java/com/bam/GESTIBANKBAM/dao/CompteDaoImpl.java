package com.bam.GESTIBANKBAM.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;


import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.CompteNotification;
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
		String messageD = "Votre virement de " + mont + "€ vers le compte n° " + cpt2.getNumCpt() + " a bien été effectué." ;
		String messageC = "Vous avez reçu un virement de " + mont + "€ du compte n°" + cpt1.getNumCpt();
		CompteNotification messageDebit = new CompteNotification(messageD, new Date());
		CompteNotification messageCredit = new CompteNotification(messageC, new Date());
		Compte compteDebiteur = findByNum(cpt1.getNumCpt());
		Compte compteCredite = findByNum(cpt2.getNumCpt());
		try {
			success = compteDebiteur.addTransaction(debit) && compteCredite.addTransaction(credit);
			if (success) {
				update(compteCredite);
				update(compteDebiteur);
				compteCredite.addNotification(messageCredit);
				compteDebiteur.addNotification(messageDebit);
			}
		} catch (Throwable e) {
			e.printStackTrace(System.err);
			
		}		

//		double solde = compteDebiteur.getBalance();
//		double decauto = compteDebiteur.getMontantAutorisationDecouvert();
//		if((solde-mont)>decauto){
//			compteDebiteur.getTransactions().add(debit);
//			compteCredite.getTransactions().add(credit);
//			compteDebiteur.getNotifications().add(messageDebit);
//			compteCredite.getNotifications().add(messageCredit);		}
	}

}
