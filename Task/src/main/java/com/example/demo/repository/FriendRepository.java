package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Friends;
import com.example.demo.model.UserProfile;

@Repository
public interface FriendRepository  extends JpaRepository<Friends, Long>{

	Friends findByUserProfileAndFriendProfile(UserProfile userProfile, UserProfile friendProfile);
	
	Friends findByUserProfile(UserProfile userProfile);
	
}
