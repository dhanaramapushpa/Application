package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreType;


@Entity
@JsonIgnoreType
public class UserProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String salt;
	
	private boolean isPublicProfile;
	
	private boolean isNotificationEnabled;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="userProfile")
	private List<UserAddress> userAddress;
	
	public List<UserAddress> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddress> userAddress) {
		this.userAddress = userAddress;
	}

	public UserProfile() {
		
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}

	public UserProfile(String name, String password, String email, boolean isPublicProfile,
			boolean isNotificationEnabled) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.isPublicProfile = isPublicProfile;
		this.isNotificationEnabled = isNotificationEnabled;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", salt="
				+ salt + ", userAddress=" + userAddress + "]";
	}

	public boolean isPublicProfile() {
		return isPublicProfile;
	}

	public void setPublicProfile(boolean isPublicProfile) {
		this.isPublicProfile = isPublicProfile;
	}

	public boolean isNotificationEnabled() {
		return isNotificationEnabled;
	}

	public void setNotificationEnabled(boolean isNotificationEnabled) {
		this.isNotificationEnabled = isNotificationEnabled;
	}

}
