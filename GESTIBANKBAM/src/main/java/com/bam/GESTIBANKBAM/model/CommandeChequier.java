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


	@Id
	//@GeneratedValue (strategy=GenerationType.AUTO)
	@GeneratedValue (strategy=GenerationType.TABLE)
	private Long numCheque;

	public CommandeChequier() {

	}

	public CommandeChequier( Long nc) {
		numCheque   = nc;

	}


	public Long getNumCheque() {
		return new Long(numCheque);
	}

	public void setNumCheque(Long numCheque) {
		if (this.numCheque == null) {
			this.numCheque = numCheque;
		}
	}
}
