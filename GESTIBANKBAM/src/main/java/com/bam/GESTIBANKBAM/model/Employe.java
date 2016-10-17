package com.bam.GESTIBANKBAM.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
//@DiscriminatorValue (Personne.TYPE_EMPLOYE+"")
//@Table (name="Employe")
public class Employe extends Personne {
	@NotNull
	@Column (nullable=false)
	private Date dateEntree;

	@NotNull
	@Column (nullable=false)
	private ArrayList<String> fonctions;

	@OneToMany (mappedBy="conseiller")
	private List<Client> clients; 

	@OneToMany (mappedBy="employe")
	private List<EmployeNotification> notifications; //(Not in constructor ?)

	public Employe() {
		this.setType(ROLE_CONSEILLER | ROLE_ADMIN);
		configure();
	}

	public Employe(String matricule, Date dateEntree, ArrayList<String> fonctions,
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
		fonctions = new ArrayList<String>();
	}

	public boolean addClient(Client c) {
		if (getType() == Personne.ROLE_CONSEILLER) {
			clients.add(c);
			return true;
		}
		return false;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public ArrayList<String> getFonctions() {
		return fonctions;
	}

	public void setFonctions(ArrayList<String> fonctions) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employe [getDateEntree()=");
		builder.append(getDateEntree());
		builder.append(", getFonctions()=");
		builder.append(getFonctions());
		builder.append(", getNotifications()=");
		builder.append(getNotifications());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", getClients()=");
		builder.append(getClients());
		builder.append(", getCivilite()=");
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
		builder.append(", toString()=");
		builder.append(super.toString());
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
		int result = super.hashCode();
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime * result + ((dateEntree == null) ? 0 : dateEntree.hashCode());
		result = prime * result + ((fonctions == null) ? 0 : fonctions.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Employe)) {
			return false;
		}
		Employe other = (Employe) obj;
		if (clients == null) {
			if (other.clients != null) {
				return false;
			}
		} else if (!clients.equals(other.clients)) {
			return false;
		}
		if (dateEntree == null) {
			if (other.dateEntree != null) {
				return false;
			}
		} else if (!dateEntree.equals(other.dateEntree)) {
			return false;
		}
		if (fonctions == null) {
			if (other.fonctions != null) {
				return false;
			}
		} else if (!fonctions.equals(other.fonctions)) {
			return false;
		}
		if (notifications == null) {
			if (other.notifications != null) {
				return false;
			}
		} else if (!notifications.equals(other.notifications)) {
			return false;
		}
		return true;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
