package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.event.BAMListener;

public class Compte implements Cloneable {
	public enum CompteType {
		SANS_AUTORISATION,
		AVEC_AUTORISATION,
		REMUNERATEUR
	};

	private double solde;
	private double debit;
	private CompteType type;
	private String numCpt;
	private ArrayList<Notification> notifications;
	private ArrayList<CommandeChequier> chequiers;
	private ArrayList<Transaction> transactions;

	private ArrayList<BAMListener> listeners;

	
	public Compte() {
		this(CompteType.SANS_AUTORISATION);
	}

	public Compte(CompteType type) {
		this(0, 0, type, null);
	}

	public Compte(double solde, double debit, CompteType type, String numCpt) {
		this(solde, debit, type, numCpt, null, null, null);
		notifications = new ArrayList<Notification>();
		chequiers     = new ArrayList<CommandeChequier>();
		transactions  = new ArrayList<Transaction>();
	}

	public Compte(double solde, double debit, CompteType type, String numCpt, ArrayList<Notification> notifications, ArrayList<CommandeChequier> chequiers,
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
					
				}
			}
			return true;
		}
		return false;
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

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public double getSolde() {
		return 0;
	}

	public String getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(String numCpt) {
		this.numCpt = numCpt;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	public ArrayList<CommandeChequier> getChequiers() {
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
