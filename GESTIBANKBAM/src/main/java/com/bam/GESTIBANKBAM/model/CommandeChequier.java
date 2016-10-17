package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
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

	@ManyToOne
	private Compte compte;

	@NotNull
	@Column (nullable=false)
	private STATE state;

	public CommandeChequier() {
		state = STATE.INIT;
		nombre = -1;
	}

	public CommandeChequier(Compte compte, int n, Long nc) {
		this.compte = compte;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommandeChequier [getNombre=");
		builder.append(getNombre());
		builder.append(", getNumCheque=");
		builder.append(getNumCheque());
		builder.append(", getState=");
		builder.append(getState());
		builder.append(", hashCode=");
		builder.append(hashCode());
		builder.append(", getCompte=");
		builder.append(getCompte());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compte == null) ? 0 : compte.hashCode());
		result = prime * result + nombre;
		result = prime * result + ((numCheque == null) ? 0 : numCheque.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CommandeChequier))
			return false;
		CommandeChequier other = (CommandeChequier) obj;
		if (compte == null) {
			if (other.compte != null)
				return false;
		} else if (!compte.equals(other.compte))
			return false;
		if (nombre != other.nombre)
			return false;
		if (numCheque == null) {
			if (other.numCheque != null)
				return false;
		} else if (!numCheque.equals(other.numCheque))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
}
