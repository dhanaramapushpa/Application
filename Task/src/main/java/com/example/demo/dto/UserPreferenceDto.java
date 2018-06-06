package com.example.demo.dto;

import com.example.demo.model.Preference;
import com.example.demo.model.UserProfile;

public class UserPreferenceDto {

	private Long id;
	
	private UserProfile userProfile;
	
	private Preference preference;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	
	
	
}
