package com.bam.GESTIBANKBAM.model;

public class CommandeChequier {
	public enum STATE {
		INIT, 
		CANCELED, 
		REQUEST, 
		PENDING, 
		GRANTED, 
		REJECTED
	};

	private String numCpt;
	private int nombre;
	private String numCheque;

	private STATE state;

	public CommandeChequier() {
		state = STATE.INIT;
		nombre = -1;
	}

	public CommandeChequier(Compte c, int n, String nc) {
		numCpt    = c.getNumCpt();
		nombre    = n;
		numCheque = nc;
		state     = STATE.INIT;
	}

	public void commander() {
		if (state == STATE.INIT) {
			state = STATE.REQUEST;
		}
	}

	public String getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(String numCpt) {
		if (this.numCpt == null) {
			this.numCpt = numCpt;
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

	public String getNumCheque() {
		return new String(numCheque);
	}

	public void setNumCheque(String numCheque) {
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
		builder.append("CommandeChequier [getNumCpt()=");
		builder.append(getNumCpt());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getNumCheque()=");
		builder.append(getNumCheque());
		builder.append(", getState()=");
		builder.append(getState());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nombre;
		result = prime * result + ((numCheque == null) ? 0 : numCheque.hashCode());
		result = prime * result + ((numCpt == null) ? 0 : numCpt.hashCode());
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
		if (nombre != other.nombre)
			return false;
		if (numCheque == null) {
			if (other.numCheque != null)
				return false;
		} else if (!numCheque.equals(other.numCheque))
			return false;
		if (numCpt == null) {
			if (other.numCpt != null)
				return false;
		} else if (!numCpt.equals(other.numCpt))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
}
