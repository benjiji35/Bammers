package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Embeddable
@Table (name="Adresse")
public class Adresse implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column (nullable=false)
	private int numero;

	@NotNull
	@Column (nullable=false)
	private String rue;

	@NotNull
	@Column (nullable=false)
	private String ville;

	@NotNull
	@Column (nullable=false, length=5)
	private String codePostal;

	@NotNull
	@Column (nullable=false)
	private String telephone;

	@NotNull
	@Email
	@Column (nullable=false)
	private String mail;

	public Adresse() {
		
	}

	public Adresse(int numero, String rue, String ville, String codePostal, String telephone, String mail) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.mail = mail;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
