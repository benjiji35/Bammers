package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance (strategy=InheritanceType.JOINED)
//@DiscriminatorColumn(name="ZZTYPE", discriminatorType=DiscriminatorType.INTEGER)
@Table (name="Personne")
public class Personne implements Cloneable, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	public static final int ROLE_UNAUTHENTICATED_USER = 1;

	@Transient
	public static final int ROLE_CLIENT               = 2;

	@Transient
	public static final int ROLE_CONSEILLER           = 4;

	@Transient
	public static final int ROLE_ADMIN                = 8;

	@Transient
	public static final int TYPE_EMPLOYE              = ROLE_ADMIN | ROLE_CONSEILLER;


	public enum SITUATION {
		SINGLE,
		MARRIED,
		DIVORCED,
		WIDOWED
	};

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column (nullable=false)
	private String civilite;

	@NotNull
	@Column (nullable=false)
	private String nom;

	@NotNull
	@Column (nullable=false)
	private String prenom;

	@NotNull
	@Column (nullable=false)
	@Temporal (TemporalType.DATE)
	private Date ddn;

	@NotNull
	@Column (nullable=false)
	private int type;

	@Override
	public String toString() {
		return String.format(
				"Personne [getCivilite()=%s, getNom()=%s, getPrenom()=%s, getDdn()=%s, getType()=%s, getId()=%s, getHashMdp()=%s, getAdresse()=%s, getNumero()=%s, getRue()=%s, getVille()=%s, getCodePostal()=%s, getTelephone()=%s, getMail()=%s, getSituationMatrimoniale()=%s, getNbEnfants()=%s, getIncome()=%s, getProfession()=%s]",
				getCivilite(), getNom(), getPrenom(), getDdn(), getType(), getId(), getHashMdp(), getAdresse(),
				getNumero(), getRue(), getVille(), getCodePostal(), getTelephone(), getMail(),
				getSituationMatrimoniale(), getNbEnfants(), getIncome(), getProfession());
	}

	@Column (nullable=true)
	private String hashMdp;

	@NotNull
	@Embedded
	@Column (nullable=false)
	private Adresse adresse;

	@NotNull
	@Column (nullable=false)
	private SITUATION situationMatrimoniale;

	@NotNull
	@Column (nullable=false)
	private String profession;

	@NotNull
	@Column (nullable=false)
	private int nbEnfants;

	@NotNull
	@Column (nullable=false)
	private double income;

	public Personne() {
		this(0L);
	}

	public Personne(Long id) {
		setId(id);
	}

	public Personne(String civilite, String nom, String prenom, Date ddn, int type, Long id, String hashMdp,
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
	
    //@JsonSerialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMSerialiser.class)
	public Date getDdn() {
		return ddn;
	}
    
    //@JsonDeserialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMDeserialiser.class)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
