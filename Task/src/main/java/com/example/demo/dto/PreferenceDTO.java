package com.example.demo.dto;



public class PreferenceDTO {
	private Long prefId;
	
	private String typeOfPref;
	
	private String category;
	
	private Boolean status;

	@Override
	public String toString() {
		return "PreferenceDTO [id=" + prefId + ", typeOfPref=" + typeOfPref + ", category=" + category + ", status="
				+ status + "]";
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

	
}
