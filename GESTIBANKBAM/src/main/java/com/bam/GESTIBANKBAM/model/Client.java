package com.bam.GESTIBANKBAM.model;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client extends Personne {

	private static final long serialVersionUID = 1L;

	/* Constructeurs */
	public Client() {
		super();
		this.setType(ROLE_CLIENT);
		comptes = new HashSet<Compte>();
//		documents = new HashSet<BamFileModel>();
	}

	public Client(Long idClient) {
		this();
		setId(idClient);
	}

	public Client(Personne p) {
		this();
		if (p == null) {
			return;
		}
		super.setId(p.getId());
		super.setCivilite(p.getCivilite());
		super.setNom(p.getNom());
		super.setPrenom(p.getPrenom());
		super.setType(ROLE_CLIENT);
		super.setHashMdp(p.getHashMdp());
		super.setAdresse(p.getAdresse());
		super.setDdn(p.getDdn());
	}

	/**
	 * Attributs
	 */
//	@ManyToOne
//	private Employe conseiller;
	private Long conseillerId;

	// TODO
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Compte> comptes;

//	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
//	private Set<BamFileModel> documents;

	private Hashtable<String, String> pathsToAttachedFiles;

	/* getters and setters */
//	@JsonIgnore
//	public Employe getConseiller() {
//		return conseiller;
//	}
//
//	public void setConseiller(Employe conseiller) {
//		this.conseiller = conseiller;
//	}

	public void addCompte(Compte cpt) {
		comptes.add(cpt);
	}
	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		System.out.println("Client.setCompte(comptes)=" + comptes.size());
		this.comptes = comptes;
	}

	@Override
	public void setType(int type) {
		super.setType(ROLE_CLIENT);
	}

	@Override
	public String toString() {
		return String.format("Client [getComptes()=%s, toString()=%s]", getComptes(), super.toString());
	}

	public Long getConseillerId() {
		return conseillerId;
	}

	public void setConseillerId(Long conseillerId) {
		this.conseillerId = conseillerId;
	}

	public Hashtable<String, String> getPathsToAttachedFiles() {
		return pathsToAttachedFiles;
	}

	public void setPathsToAttachedFiles(Hashtable<String, String> pathsToAttachedFiles) {
		this.pathsToAttachedFiles = pathsToAttachedFiles;
	}

	public boolean addFile(String key, String fileName) {
		return addFile(key, fileName, false);
	}

	public boolean addFile(String key, String fileName, boolean overwrite) {
		String f = pathsToAttachedFiles.get(key);
		boolean added = false;

		if (f != null && overwrite) {
			pathsToAttachedFiles.put(key, fileName);
			added = true;
		} else {
			pathsToAttachedFiles.put(key, fileName);
			added = true;
		}
		return added;
	}

//	public Set<BamFileModel> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(Set<BamFileModel> documents) {
//		this.documents = documents;
//	}
//
//	public void addDocument(BamFileModel doc) {
//		documents.add(doc);
//	}
//
//	public void removeDocument(BamFileModel doc) {
//		documents.remove(doc);
//	}
}
