package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Preference;
import com.example.demo.model.UserAddress;
import com.example.demo.model.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile,Long>{

	UserProfile findByEmail(String email);



	
	/*

	UserProfile findByName(String name);

	

	
	

	List<UserProfile> findByUserAddresses(UserAddress userAddress);

	UserProfile findByPreferences(Preference preference);
*/
	
}
