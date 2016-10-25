package com.bam.GESTIBANKBAM.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table (name="BamFileModel")
public class BamFileModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue (strategy=GenerationType.AUTO)
	private Long id;
	private String sid;
	private String pathBase;
	private String key;
	private Date date;

	public BamFileModel() {
		
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPathBase() {
		return pathBase;
	}
	public void setPathBase(String pathBase) {
		this.pathBase = pathBase;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
