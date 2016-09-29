package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;

public class Compte {
	private String numCpt;
	private ArrayList<Notification> notifications;
	private ArrayList<CommandeChequier> chequiers;
	private ArrayList<Transaction> transactions;

	
	public Compte() {
	}

	public Compte(String numCpt, ArrayList<Notification> notifications, ArrayList<CommandeChequier> chequiers,
			ArrayList<Transaction> transactions) {
		this.numCpt = numCpt;
		this.notifications = notifications;
		this.chequiers = chequiers;
		this.transactions = transactions;
	}

	public boolean virer() {
		return false;
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
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + ((numCpt == null) ? 0 : numCpt.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}
}
