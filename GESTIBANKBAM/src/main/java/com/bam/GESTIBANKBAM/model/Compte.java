package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	private double solde;

	@NotNull
	@Column (nullable=false)
	private double debit;

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

	
	public Compte() {
		this(CompteType.SANS_AUTORISATION);
	}

	public Compte(CompteType type) {
		this(0, 0, type, null);
	}

	public Compte(double solde, double debit, CompteType type, Long numCpt) {
		this(solde, debit, type, numCpt, null, null, null);
		notifications = new ArrayList<CompteNotification>();
		chequiers     = new ArrayList<CommandeChequier>();
		transactions  = new ArrayList<Transaction>();
	}

	public Compte(double solde, double debit, CompteType type, Long numCpt, List<CompteNotification> notifications, ArrayList<CommandeChequier> chequiers,
			ArrayList<Transaction> transactions) {
		this.solde = solde;
		this.debit = debit;
		this.type = type;
		this.numCpt = numCpt;
		this.notifications = notifications;
		this.chequiers = chequiers;
		this.transactions = transactions;
		this.listeners = new ArrayList<BAMListener>();
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

	public boolean addTransaction(Transaction t) {
		if (checkIt(t)) {
			transactions.add(t);
			solde += t.getMontant();
			fireBAMEvent(new BAMEvent(this, t, BAMEvent.TYPE.NEW_TRANSACTION));
			for (Transaction tt : transactions) {
				if (! tt.isSealed()) {
					// TODO
				}
			}
			return true;
		}
		return false;
	}

	public void commanderChequier() {
		// TODO
	}

	private boolean checkIt(Transaction t) {
		return (t.getMontant() + getSolde() <= getDebit());
	}

	public double getDebit() {
		return debit;
	}

	protected void fireBAMEvent(BAMEvent bamEvent) {
		Iterator<BAMListener> it = listeners.iterator();
        while( it.hasNext() ) {
            ( (BAMListener) it.next() ).update( bamEvent );
        }
	}

	@JsonIgnore
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public double getSolde() {
		return 0;
	}

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	//@JsonGetter
	@JsonIgnore
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

	@JsonIgnore
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

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}
}
