package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Preference;
import com.example.demo.model.UserProfile;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long>{

	Preference findBytypeOfPref(String typeOfPref);

	//List<Preference> findByUserProfile(UserProfile userProfile);//
	
	List<Preference> findByCategoryAndTypeOfPref(String category,String typeOfPref);

}
