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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.event.BAMListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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

	@OneToMany (mappedBy="compte", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<CompteNotification> notifications;

	@OneToMany (mappedBy="compte", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compte [virer=");
		builder.append(virer());
		builder.append(", getTransactions=");
		builder.append(getTransactions());
		builder.append(", getSolde=");
		builder.append(getSolde());
		builder.append(", getNumCpt=");
		builder.append(getNumCpt());
		builder.append(", getNotifications=");
		builder.append(getNotifications());
		builder.append(", getChequiers=");
		builder.append(getChequiers());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chequiers == null) ? 0 : chequiers.hashCode());
		long temp;
		temp = Double.doubleToLongBits(debit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listeners == null) ? 0 : listeners.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + ((numCpt == null) ? 0 : numCpt.hashCode());
		temp = Double.doubleToLongBits(solde);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Compte))
			return false;
		Compte other = (Compte) obj;
		if (chequiers == null) {
			if (other.chequiers != null)
				return false;
		} else if (!chequiers.equals(other.chequiers))
			return false;
		if (Double.doubleToLongBits(debit) != Double.doubleToLongBits(other.debit))
			return false;
		if (listeners == null) {
			if (other.listeners != null)
				return false;
		} else if (!listeners.equals(other.listeners))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		if (numCpt == null) {
			if (other.numCpt != null)
				return false;
		} else if (!numCpt.equals(other.numCpt))
			return false;
		if (Double.doubleToLongBits(solde) != Double.doubleToLongBits(other.solde))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		if (type != other.type)
			return false;
		return true;
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
