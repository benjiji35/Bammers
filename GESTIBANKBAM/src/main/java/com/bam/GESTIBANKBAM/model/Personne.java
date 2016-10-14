package com.bam.GESTIBANKBAM.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Personne implements Cloneable {
	public static final int ROLE_UNAUTHENTICATED_USER = 1;
	public static final int ROLE_CLIENT = 2;
	public static final int ROLE_CONSEILLER = 4;
	public static final int ROLE_ADMIN = 8;

	public enum SITUATION {
		SINGLE,
		MARRIED,
		DIVORCED,
		WIDOWED
	};

	private String civilite;
	private String nom;
	private String prenom;
	private Date ddn;
	private int type;
	private String id;
	private String hashMdp;
	private Adresse adresse;
	private SITUATION situationMatrimoniale;
	private String profession;
	private int nbEnfants;
	private double income;

	public Personne() {
		this("0");
	}

	public Personne(String id) {
		setId(id);
	}

	public Personne(String civilite, String nom, String prenom, Date ddn, int type, String id, String hashMdp,
			Adresse adresse) {
		this();
		this.civilite = civilite;
		this.nom      = nom;
		this.prenom   = prenom;
		this.ddn      = ddn;
		this.id       = id;
		this.hashMdp  = hashMdp;
		this.adresse  = adresse;
		this.setType(type);
	}

	public Personne(Personne p) throws CloneNotSupportedException {
		Personne np = copyFrom(p);
		this.civilite = np.getCivilite();
		this.nom      = np.getNom();
		this.prenom   = np.getPrenom();
		this.ddn      = np.getDdn();
		this.type     = np.getType();
		this.id       = np.getId();
		this.hashMdp  = np.getHashMdp();
		this.adresse  = np.getAdresse();
	}

	protected Personne copyFrom(Personne p) {
		Personne np = new Personne();
		Adresse a = p.getAdresse();

		np.setCivilite(p.getCivilite());
		np.setNom(p.getNom());
		np.setPrenom(p.getPrenom());
		np.setDdn(new Date(p.getDdn().getTime()));
		np.setId(p.getId());
		np.setHashMdp(p.getHashMdp());
		np.setType(p.getType());
		np.setAdresse(new Adresse(a.getNumero(), a.getRue(), a.getVille(), a.getCodePostal(), a.getTelephone(), a.getMail()));
		return np;
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
	
    @JsonSerialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMSerialiser.class)
	public Date getDdn() {
		return ddn;
	}
    
    @JsonDeserialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMDeserialiser.class)
	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		int t = type & (ROLE_UNAUTHENTICATED_USER | ROLE_CLIENT | ROLE_CONSEILLER | ROLE_ADMIN);

		if (t == (ROLE_UNAUTHENTICATED_USER | ROLE_CLIENT | ROLE_CONSEILLER | ROLE_ADMIN)) {
			t = ROLE_UNAUTHENTICATED_USER;
		}
		this.type = t; 
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

	public int getNumero() {
		return getAdresse().getNumero();
	}
	public void setNumero(int numero) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setNumero(numero);
		} else {
			a = new Adresse(numero, a.getRue(), a.getVille(), a.getCodePostal(), a.getTelephone(), a.getMail());
		}
		setAdresse(a);
	}
	public String getRue() {
		return getAdresse().getRue();
	}
	public void setRue(String rue) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setRue(rue);
		} else {
			a = new Adresse(a.getNumero(), rue, a.getVille(), a.getCodePostal(), a.getTelephone(), a.getMail());
		}
		setAdresse(a);
	}
	public String getVille() {
		return getAdresse().getVille();
	}
	public void setVille(String ville) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setVille(ville);
		} else {
			a = new Adresse(a.getNumero(), a.getRue(), ville, a.getCodePostal(), a.getTelephone(), a.getMail());
		}
		setAdresse(a);
	}
	public String getCodePostal() {
		return getAdresse().getCodePostal();
	}
	public void setCodePostal(String codePostal) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setCodePostal(codePostal);
		} else {
			a = new Adresse(a.getNumero(), a.getRue(), a.getVille(), codePostal, a.getTelephone(), a.getMail());
		}
		setAdresse(a);
	}
	public String getTelephone() {
		return getAdresse().getTelephone();
	}
	public void setTelephone(String telephone) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setTelephone(telephone);
		} else {
			a = new Adresse(a.getNumero(), a.getRue(), a.getVille(), a.getCodePostal(), telephone, a.getMail());
		}
		setAdresse(a);
	}
	public String getMail() {
		return getAdresse().getMail();
	}
	public void setMail(String mail) {
		Adresse a = getAdresse();

		if (a == null) {
			a = new Adresse();
			a.setMail(mail);
		} else {
			a = new Adresse(a.getNumero(), a.getRue(), a.getVille(), a.getCodePostal(), a.getTelephone(), mail);
		}
		setAdresse(a);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Personne [getCivilite()=");
		builder.append(getCivilite());
		builder.append(", getNom()=");
		builder.append(getNom());
		builder.append(", getPrenom()=");
		builder.append(getPrenom());
		builder.append(", getDdn()=");
		builder.append(getDdn());
		builder.append(", getType()=");
		builder.append(getType());
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getHashMdp()=");
		builder.append(getHashMdp());
		builder.append(", getAdresse()=");
		builder.append(getAdresse());
		builder.append(", getNumero()=");
		builder.append(getNumero());
		builder.append(", getRue()=");
		builder.append(getRue());
		builder.append(", getVille()=");
		builder.append(getVille());
		builder.append(", getCodePostal()=");
		builder.append(getCodePostal());
		builder.append(", getTelephone()=");
		builder.append(getTelephone());
		builder.append(", getMail()=");
		builder.append(getMail());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", getSituationMatrimoniale()=");
		builder.append(getSituationMatrimoniale());
		builder.append(", getNbEnfants()=");
		builder.append(getNbEnfants());
		builder.append(", getIncome()=");
		builder.append(getIncome());
		builder.append(", getProfession()=");
		builder.append(getProfession());
		builder.append("]");
		return builder.toString();
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
		long temp;
		temp = Double.doubleToLongBits(income);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nbEnfants;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((situationMatrimoniale == null) ? 0 : situationMatrimoniale.hashCode());
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
		if (Double.doubleToLongBits(income) != Double.doubleToLongBits(other.income))
			return false;
		if (nbEnfants != other.nbEnfants)
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
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		if (situationMatrimoniale != other.situationMatrimoniale)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Personne p = (Personne) super.clone();
		Personne np = copyFrom(p);

		return np;
	}

	public SITUATION getSituationMatrimoniale() {
		return situationMatrimoniale;
	}

	public void setSituationMatrimoniale(SITUATION situationMatrimoniale) {
		this.situationMatrimoniale = situationMatrimoniale;
	}

	public int getNbEnfants() {
		return nbEnfants;
	}

	public void setNbEnfants(int nbEnfants) {
		this.nbEnfants = nbEnfants;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
}
