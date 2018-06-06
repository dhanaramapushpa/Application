package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.UserAddress;

public class UserDto {


	private Long userId;
	
	private String name;
	
	private String email;
	
	private String password;

	private boolean isPublicProfile;
	
	private boolean isNotificationEnabled;


	private List<UserAddress> userAddress;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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



	public List<UserAddress> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddress> userAddress) {
		this.userAddress = userAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", isPublicProfile=" + isPublicProfile + ", isNotificationEnabled=" + isNotificationEnabled
				+ ", userAddress=" + userAddress + "]";
	}
	
	
	
}
