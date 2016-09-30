package com.bam.GESTIBANKBAM.model;

import java.util.Calendar;

public class Personne {
	public static final int ROLE_UNAUTHENTICATED_USER = 1;
	public static final int ROLE_CLIENT = 2;
	public static final int ROLE_CONSEILLER = 4;
	public static final int ROLE_ADMIN = 8;

	private String civilite;
	private String nom;
	private String prenom;
	private Calendar ddn;
	private int type;
	private String id;
	private String hashMdp;
	private Adresse adresse;

	public Personne() {
		this("0");
	}

	public Personne(String id) {
		setId(id);
	}

	public Personne(String civilite, String nom, String prenom, Calendar ddn, int type, String id, String hashMdp,
			Adresse adresse) {
		this();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
		this.id = id;
		this.hashMdp = hashMdp;
		this.adresse = adresse;
		this.setType(type);
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Calendar getDdn() {
		return ddn;
	}

	public void setDdn(Calendar ddn) {
		this.ddn = ddn;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type & ROLE_UNAUTHENTICATED_USER & ROLE_CLIENT & ROLE_CONSEILLER & ROLE_ADMIN;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHashMdp() {
		return hashMdp;
	}

	public void setHashMdp(String hashMdp) {
		this.hashMdp = hashMdp;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Personne civilite=" + getCivilite() + ", nom=" + getNom() + ", prenom=" + getPrenom()
				+ ", ddn=" + getDdn() + ", type=" + getType() + ", id=" + getId()
				+ ", hashMdp=" + getHashMdp() + ", adresse=" + getAdresse() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((ddn == null) ? 0 : ddn.hashCode());
		result = prime * result + ((hashMdp == null) ? 0 : hashMdp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Personne))
			return false;
		Personne other = (Personne) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (civilite == null) {
			if (other.civilite != null)
				return false;
		} else if (!civilite.equals(other.civilite))
			return false;
		if (ddn == null) {
			if (other.ddn != null)
				return false;
		} else if (!ddn.equals(other.ddn))
			return false;
		if (hashMdp == null) {
			if (other.hashMdp != null)
				return false;
		} else if (!hashMdp.equals(other.hashMdp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}