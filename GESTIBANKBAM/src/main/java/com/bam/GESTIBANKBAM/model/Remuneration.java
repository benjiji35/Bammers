package com.bam.GESTIBANKBAM.model;

public class Remuneration extends Transaction {
	private double interets;
	private double taux;

	
	public Remuneration() {
		super();
	}

	public Remuneration(double interets, double taux) {
		super();
		this.interets = interets;
		this.taux = taux;
	}

	public double getInterets() {
		return interets;
	}

	public void setInterets(double interets) {
		this.interets = interets;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Remuneration [getInterets=");
		builder.append(getInterets());
		builder.append(", getTaux=");
		builder.append(getTaux());
		builder.append(", toString=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(interets);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taux);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Remuneration))
			return false;
		Remuneration other = (Remuneration) obj;
		if (Double.doubleToLongBits(interets) != Double.doubleToLongBits(other.interets))
			return false;
		if (Double.doubleToLongBits(taux) != Double.doubleToLongBits(other.taux))
			return false;
		return true;
	}
}
