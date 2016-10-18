package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="EmployeNotification")
public class EmployeNotification extends Notification {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeNotification() {
		super();
	}

	public EmployeNotification(String message, Date date) {
		super(message, date);
	}
}
