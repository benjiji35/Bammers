package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

//@MappedSuperclass
@Entity
@Inheritance (strategy=InheritanceType.TABLE_PER_CLASS)
@Table (name="Notification")
public abstract class Notification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue (strategy=GenerationType.AUTO)
	@GeneratedValue (strategy=GenerationType.TABLE)
	private Long id;

	@NotNull
	@Column (nullable=false)
	private String message;

	@NotNull
	@Column (nullable=false)
	@Temporal (TemporalType.DATE)
	private Date date;

	public Notification() {
		
	}

	public Notification(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
}
