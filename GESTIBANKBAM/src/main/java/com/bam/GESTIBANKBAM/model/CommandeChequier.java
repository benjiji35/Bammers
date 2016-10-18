package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="CommandeChequier")
public class CommandeChequier implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum STATE {
		INIT, 
		CANCELED, 
		REQUEST, 
		PENDING, 
		GRANTED, 
		REJECTED
	};


	@NotNull
	@Column (nullable=false)
	private int nombre;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long numCheque;

	@NotNull
	@Column (nullable=false)
	private STATE state;

	public CommandeChequier() {
		state = STATE.INIT;
		nombre = -1;
	}

	public CommandeChequier(int n, Long nc) {
		nombre      = n;
		numCheque   = nc;
		state       = STATE.INIT;
	}

	public void commander() {
		if (state == STATE.INIT) {
			state = STATE.REQUEST;
		}
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		if (this.nombre == -1) {
			this.nombre = nombre;
		}
	}

	public Long getNumCheque() {
		return new Long(numCheque);
	}

	public void setNumCheque(Long numCheque) {
		if (this.numCheque == null) {
			this.numCheque = numCheque;
		}
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}
}
