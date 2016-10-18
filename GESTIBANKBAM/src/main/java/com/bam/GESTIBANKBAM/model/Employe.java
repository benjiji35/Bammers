package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
//@DiscriminatorValue (Personne.TYPE_EMPLOYE+"")
@Table (name="Employe")
public class Employe extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column (nullable=false)
	@Temporal (TemporalType.DATE)
	private Date dateEntree;

	private String fonctions;

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Client> clients; 

	@OneToMany (cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<EmployeNotification> notifications; //(Not in constructor ?)

	public Employe() {
		this.setType(ROLE_CONSEILLER | ROLE_ADMIN);
		configure();
	}

	public Employe(String matricule, Date dateEntree, String fonctions,
			ArrayList<EmployeNotification> notifications) {
		this();
		this.dateEntree = dateEntree;
		this.fonctions = fonctions;
		this.notifications = notifications;
	}

	public Employe(Personne p) {
		this();
		if (p == null) {
			return;
		}
		super.setId(p.getId());
		super.setCivilite(p.getCivilite());
		super.setNom(p.getNom());
		super.setPrenom(p.getPrenom());
		super.setType(p.getType());
		super.setHashMdp(p.getHashMdp());
		super.setAdresse(p.getAdresse());
		super.setDdn(p.getDdn());
	}

	private void configure() {
		if (getType() == Personne.ROLE_CONSEILLER) {
			clients = new ArrayList<Client>();
		}
		fonctions = "";
	}

	public boolean addClient(Client c) {
		if (getType() == Personne.ROLE_CONSEILLER) {
			clients.add(c);
			return true;
		}
		return false;
	}
	
	//@JsonSerialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMSerialiser.class)
	public Date getDateEntree() {
		return dateEntree;
	}
    
	//@JsonDeserialize(using=com.bam.GESTIBANKBAM.utils.JsonBAMDeserialiser.class)
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getFonctions() {
		return fonctions;
	}

	public void setFonctions(String fonctions) {
		this.fonctions = fonctions;
	}

	public List<EmployeNotification> getNotifications() {
		return notifications;
	}

	public void addNotification(EmployeNotification ntf) {
		notifications.add(ntf);
	}

	public void removeNotification(Notification ntf) {
		notifications.remove(ntf);
	}

	public void setNotifications(List<EmployeNotification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public void setType(int type) {
		int t = type & (ROLE_CONSEILLER | ROLE_ADMIN);

		if (t == (ROLE_CONSEILLER | ROLE_ADMIN)) {
			t = ROLE_CONSEILLER;
		}
		super.setType(t);
	}

	public void setConseiller() {
		this.setType(ROLE_CONSEILLER);
	}

	public void setAdmin() {
		this.setType(ROLE_ADMIN);
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
