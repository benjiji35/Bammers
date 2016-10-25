package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
