package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="EmployeNotification")
public class EmployeNotification extends Notification {
	/**
	 * 
	 */
	
	@NotNull
	@Column (nullable=false)
	private Long idAuteur;
	
	public Long getIdAuteur() {
		return idAuteur;
	}

	public void setIdAuteur(Long idAuteur) {
		this.idAuteur = idAuteur;
	}

	private static final long serialVersionUID = 1L;
	
	
	public EmployeNotification() {
		super();
	}

	public EmployeNotification(String message, Date date, Long idAuteur) {
		super(message, date);
		this.idAuteur=idAuteur;
	}
}
