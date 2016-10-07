package com.bam.GESTIBANKBAM.event;

import java.util.EventObject;

import com.bam.GESTIBANKBAM.model.Transaction;

public class BAMEvent extends EventObject {
	public enum TYPE {
		//
		// Client side events
		//
		NEW_TRANSACTION, 
		TRANSACTION_SEALED, 
		BALANCE_WAS_POSITIVE_THEN_BECOME_NEGATIVE,
		BALANCE_WAS_NEGATIVE_THEN_BECOME_POSITIVE,
		NEW_DAY,
		CLIENT_NOTIFICATION,

		//
		// Agent (Conseiller) side events
		//
		CHECKBOOK_REQUEST,
		NEW_ACCOUNT_REQUEST,
		NEW_ACCOUNT_UPDATE,
		AGENT_NOTIFICATION,
		

		//
		// Admin side events
		//
		NEW_CLIENT,
		NEW_CLIENT_UPDATE,
		AGENT_ON_LEAVE,
		ADMIN_NOTIFICATION
	};

	private Transaction transaction;
	private TYPE type;

	public BAMEvent(Object source, TYPE type) {
		this(source, null, type);
	}

	public BAMEvent(Object source, Transaction t) {
		this(source, t, TYPE.NEW_TRANSACTION);
	}

	public BAMEvent(Object source, Transaction t, TYPE type) {
		super(source);
		this.transaction = t;
		this.type = type;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public TYPE getType() {
		return type;
	}
}
