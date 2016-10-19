package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
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

import com.bam.GESTIBANKBAM.event.BAMEvent;
import com.bam.GESTIBANKBAM.event.BAMListener;
import com.bam.GESTIBANKBAM.util.BAMException;

//@MappedSuperclass
@Entity
@Table (name="Transaction")
//@Inheritance (strategy=InheritanceType.TABLE_PER_CLASS)

//@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn (name="type", discriminatorType=DiscriminatorType.INTEGER)
@Inheritance (strategy=InheritanceType.JOINED)
public abstract class Transaction implements Serializable, BAMListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue (strategy=GenerationType.TABLE)
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column (nullable=false)
	@Temporal (TemporalType.DATE)
	private Date dateDebut;

	@Temporal (TemporalType.DATE)
	private Date dateFin;

	private double montant;

	@NotNull
	@Column (nullable=false)
	private int type;

	private boolean waitForMonthEnd;

	@Transient
	private static final HashMap<Class, Integer> registeredTypes = new HashMap<Class, Integer>();


	protected static void register(Transaction t) {
		registeredTypes.put(t.getClass(), new Integer(t.getType()));
	}

	@SuppressWarnings("rawtypes")
	protected static HashMap<Class, Integer> getRegisteredTypes() {
		return registeredTypes;
	}

	protected static Integer getRegisteredType(Transaction t) {
		return registeredTypes.get(t);
	}

	protected static boolean removeRegisteredType(Transaction t) {
		return registeredTypes.remove(t.getClass()) != null;
	}

	public Transaction() {
		super();
	}

	public Transaction(int type, Date dateDebut, Date dateFin, double montant, boolean waitForMonthEnd) {
		this();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.montant = montant;
		this.waitForMonthEnd = waitForMonthEnd;
		setType(type);
	}

	public abstract void update(BAMEvent e);

	public void sealTransaction(Date dateFin) throws BAMException {
		if (isSealed()) {
			return;
		}
		if (getDateDebut().getTime() <= dateFin.getTime()) {
			setDateFin(dateFin);
		} else {
			throw new BAMException("dateFin must be after dateDebut: " +
					"dateDebut=" + dateDebut +
					" - dateFin=" + dateFin);
		}
	}

	public boolean isSealed() {
		return getDateFin() != null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		if (isSealed()) {
			return;
		}
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		Transaction.removeRegisteredType(this);
		this.type = type;
		Transaction.register(this);
	}

	public boolean isWaitForMonthEnd() {
		return waitForMonthEnd;
	}

	public void setWaitForMonthEnd(boolean waitForMonthEnd) {
		this.waitForMonthEnd = waitForMonthEnd;
	}
}
