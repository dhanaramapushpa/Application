package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Preference;
import com.example.demo.model.UserPreference;
import com.example.demo.model.UserProfile;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference,Long>{

	List<UserPreference> findByUserProfile(UserProfile userProfile);


	//@Query(value="select c.preference from UserPreference c where c.typeOfPref=:typeOfPref & c.category=:category")
	//UserPreference findByPreference(@Param("typeOfPref") String typeOfPref,@Param("category") String category);


	UserPreference findByPreference(Preference preference);
	
	UserPreference findByPreferenceAndUserProfile(Preference preference,UserProfile userProfile);


	
}
