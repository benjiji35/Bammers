package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.event.BAMListener;
import com.bam.GESTIBANKBAM.util.BAMException;
import com.bam.GESTIBANKBAM.utils.BAMTools;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="Compte")
public class Compte implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum CompteType {
		SANS_AUTORISATION,
		AVEC_AUTORISATION,
		REMUNERATEUR
	};

	@NotNull
	@Column (nullable=false)
	private double tauxRemuneration;

	@NotNull
	@Column (nullable=false)
	private double tauxDecouvert;

	@NotNull
	@Column (nullable=false)
	// montantAutorisationDecouvert
	private double montantAutorisationDecouvert;

	@NotNull
	@Column (nullable=false)
	private double montantSeuilMinRemuneration;

	@NotNull
	@Column (nullable=false)
	private CompteType type;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long numCpt;

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<CompteNotification> notifications;

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<CommandeChequier> chequiers;

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Transaction> transactions;

	@Transient
	private List<BAMListener> listeners;


	private void setTaux() {
		setTauxDecouvert(18/100);
		setTauxRemuneration(2/100);
	}

	public Compte() {
		this(CompteType.SANS_AUTORISATION);
		setTaux();
	}

	public Compte(CompteType type) {
		this(0, type, null);
	}

	public Compte(double debit, CompteType type, Long numCpt) {
		this(debit, type, numCpt, null, null, null);
		notifications = new ArrayList<CompteNotification>();
		chequiers     = new ArrayList<CommandeChequier>();
		transactions  = new ArrayList<Transaction>();
	}

	public Compte(double debit, CompteType type, Long numCpt, List<CompteNotification> notifications, ArrayList<CommandeChequier> chequiers,
			ArrayList<Transaction> transactions) {
		this.montantAutorisationDecouvert = debit;
		this.type = type;
		this.numCpt = numCpt;
		this.notifications = notifications;
		this.chequiers = chequiers;
		this.transactions = transactions;
		this.listeners = new ArrayList<BAMListener>();
		setTaux();
		addBAMListener(new BAMListener() {
			@Override
			public void update(BAMEvent e) {
				e.getTransaction().update(e);
			}});
	}

	private void addBAMListener(BAMListener bamListener) {
		listeners.add(bamListener);
	}

	public boolean virer() {
		return false;
	}

	public boolean addTransaction(Transaction t) throws Throwable {
		boolean success = false;
		double balance;
		double mar     = getMontantSeuilMinRemuneration(); 

		if (checkIt(t)) {
			processTransaction(t);
			balance = getBalance();
			if (balance < 0) {
				processTransaction(new Decouvert(balance, getTauxDecouvert(), new Date()));
			} else if (balance >= mar) {
				processTransaction(new Remuneration(balance, mar, getTauxRemuneration(), new Date()));
			}
			success = true;
		}
		return success;
	}

	private void processTransaction(Transaction t) {
		transactions.add(t);
		fireBAMEvent(new BAMEvent(this, t, BAMEvent.TYPE.NEW_TRANSACTION));
	}

	public void commanderChequier() {
		// TODO
	}

	private boolean checkIt(Transaction t) {
		double m = t.getMontant();
		boolean ok = true;

		if (m < 0) {
			ok = getBalance() + m > getMontantAutorisationDecouvert();
		}
		return ok;
	}

	public double getMontantAutorisationDecouvert() {
		return montantAutorisationDecouvert;
	}

	protected void fireBAMEvent(BAMEvent bamEvent) {
		Iterator<BAMListener> it = listeners.iterator();
        while( it.hasNext() ) {
            ( (BAMListener) it.next() ).update( bamEvent );
        }
	}

	//@JsonIgnore
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public double getBalance() {
		double solde = 0;

		for (Transaction t : getTransactions()) {
			if (t.isWaitForMonthEnd()) {
				if (BAMTools.isLastDayOfMonth() == false) {
					continue;
				}
			}
			solde += t.getMontant();
		}
		return solde;
	}

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	//@JsonGetter
	//@JsonIgnore
	public List<CompteNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<CompteNotification> notifications) {
		this.notifications = notifications;
	}

	public void addNotification(CompteNotification ntf) {
		notifications.add(ntf);
	}

	public void removeNotification(CompteNotification ntf) {
		notifications.remove(ntf);
	}

	//@JsonIgnore
	public List<CommandeChequier> getChequiers() {
		return chequiers;
	}

	public void setChequiers(ArrayList<CommandeChequier> chequiers) {
		this.chequiers = chequiers;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public CompteType getType() {
		return type;
	}

	public void setType(CompteType type) {
		this.type = type;
	}

	public void setMontantAutorisationDecouvert(double debit) {
		if (getType() != CompteType.SANS_AUTORISATION) {
			this.montantAutorisationDecouvert = debit;
		}
	}

	public double getMontantSeuilMinRemuneration() {
		return montantSeuilMinRemuneration;
	}

	public void setMontantSeuilMinRemuneration(double montantSeuilMinRemuneration) {
		this.montantSeuilMinRemuneration = montantSeuilMinRemuneration;
	}

	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	public double getTauxDecouvert() {
		return tauxDecouvert;
	}

	public void setTauxDecouvert(double tauxDecouvert) {
		this.tauxDecouvert = tauxDecouvert;
	}
}
