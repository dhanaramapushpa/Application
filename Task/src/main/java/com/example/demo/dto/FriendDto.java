package com.example.demo.dto;

public class FriendDto {
	
	private Long userProfileId;
	
	private Long friendProfileId;
	
	private String userProfileEmail;
	
	private String friendProfileEmail;

	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Long getFriendProfileId() {
		return friendProfileId;
	}

	public void setFriendProfileId(Long friendProfileId) {
		this.friendProfileId = friendProfileId;
	}

	public String getUserProfileEmail() {
		return userProfileEmail;
	}

	public void setUserProfileEmail(String userProfileEmail) {
		this.userProfileEmail = userProfileEmail;
	}

	public String getFriendProfileEmail() {
		return friendProfileEmail;
	}

	public void setFriendProfileEmail(String friendProfileEmail) {
		this.friendProfileEmail = friendProfileEmail;
	}

	@Override
	public String toString() {
		return "FriendDto [userProfileId=" + userProfileId + ", friendProfileId=" + friendProfileId
				+ ", userProfileEmail=" + userProfileEmail + ", friendProfileEmail=" + friendProfileEmail + "]";
	}
	
	

}
