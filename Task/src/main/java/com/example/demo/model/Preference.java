package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Preference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prefId;
	
	private String typeOfPref;
	
	private String category;
	
	private Boolean status;
	
	

	
	public Preference(Long prefId,String typeOfPref, String category, Boolean status) {
		super();
		this.prefId=prefId;
		this.typeOfPref = typeOfPref;
		this.category = category;
		this.status = status;
	}
	
	public Preference() {
		
	}

	
	public Long getPrefId() {
		return prefId;
	}

	public void setPrefId(Long prefId) {
		this.prefId = prefId;
	}

	public String getTypeOfPref() {
		return typeOfPref;
	}

	public void setTypeOfPref(String typeOfPref) {
		this.typeOfPref = typeOfPref;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Preference [id=" + prefId + ", typeOfPref=" + typeOfPref + ", category=" + category + ", status=" + status
				+ "]";
	}

	
}
