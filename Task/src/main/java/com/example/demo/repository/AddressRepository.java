package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAddress;
import com.example.demo.model.UserProfile;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long>{

	UserAddress findByStreetName(String streetName);

	UserAddress findBydoorNo(String doorNo);

	List<UserAddress> findByUserProfile(UserProfile userProfile);

	UserProfile findByCity(String city);

}
