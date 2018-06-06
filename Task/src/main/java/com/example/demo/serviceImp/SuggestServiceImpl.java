package com.example.demo.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SuggestionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Preference;
import com.example.demo.model.UserAddress;
import com.example.demo.model.UserPreference;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserPreferenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SuggestService;
import com.example.demo.util.Response;

@Service
public class SuggestServiceImpl implements SuggestService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserPreferenceRepository userPreferenceRepository;
	
	@Override
	public Response suggestByAddress(String email) {
		UserProfile userProfile=userRepository.findByEmail(email);//current user 
		List<UserProfile> suggestedProfiles=new ArrayList<>();
		List<UserAddress> userAddresses=userProfile.getUserAddress(); // current user addresses
		List<UserAddress> existingUserAddress=addressRepository.findAll(); //existingUserAddress 
		System.out.println("existingUserAddress"+existingUserAddress.size());
		for(UserAddress u1:userAddresses) {  // current user addresses
				if(u1.getIsPrimary()==true) {
					System.out.println(">>>>");
					for(UserAddress u:existingUserAddress) { //existingUserAddress 
						if(!u.getUserProfile().equals(userProfile) && u1.getCity().equals(u.getCity())) {
							if(u.getUserProfile().isPublicProfile()==true) {
								suggestedProfiles.add(u.getUserProfile());
							}
						}
					}
				}
		}
		return new Response("success",200,toSuggestionDto(suggestedProfiles));
	}

	@Override
	public Response suggestByPref(String email) {
		UserProfile userProfile=userRepository.findByEmail(email);
		List<UserProfile> suggestedUserProfiles=new ArrayList<>();
		List<UserPreference> userPreferences=userPreferenceRepository.findByUserProfile(userProfile);
		List<UserPreference> existingUserPreferences=userPreferenceRepository.findAll();
		System.out.println("existingUserPreferences" +existingUserPreferences.size() +"userPreferences :" +userPreferences.size());
		for(UserPreference u:existingUserPreferences) {
			for (UserPreference userPreference : userPreferences) {
				if (!userPreference.getUserProfile().equals(u.getUserProfile()) && userPreference.getPreference().equals(u.getPreference())) {
					if (!suggestedUserProfiles.contains(u.getUserProfile())) {
						suggestedUserProfiles.add(u.getUserProfile());
					}
				}
			} 
		}
		System.out.println(">>>"+suggestedUserProfiles.size());
		return new Response("success",200,toSuggestionDto(suggestedUserProfiles));
	}	
	
	public List<SuggestionDto> toSuggestionDto(List<UserProfile> suggestedUserProfiles) {
		 List<SuggestionDto> userDtos = new ArrayList<>();
		for (UserProfile user: suggestedUserProfiles) {
			SuggestionDto dto = new SuggestionDto();
			dto.setEmail(user.getEmail());
			dto.setName(user.getName());
			userDtos.add(dto);
		}
		return userDtos;
	}
}
