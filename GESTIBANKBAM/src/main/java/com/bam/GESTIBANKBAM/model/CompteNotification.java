package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="CompteNotification")
public class CompteNotification extends Notification {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompteNotification() {
		super();
	}

	public CompteNotification(String message, Date date) {
		super(message, date);
	}
}
