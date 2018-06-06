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
		Response response=null;
		UserProfile userProfile=userRepository.findByEmail(email);
		List<UserProfile> suggestedProfiles=new ArrayList<>();
		List<UserAddress> userAddresses=userProfile.getUserAddress();
		List<UserAddress> existingUserAddress=addressRepository.findAll();
		for(UserAddress u1:userAddresses) {
				if(u1.getIsPrimary()==true) {
					for(UserAddress u:existingUserAddress) {
						if(u1.getCity().equals(u.getCity())) {
							if(u.getUserProfile().isPublicProfile()==true) {
								suggestedProfiles.add(u.getUserProfile());
							}
						}
					}
				}
		}
		List<SuggestionDto> suggestionDtos=new ArrayList<>();
	for(UserProfile e:suggestedProfiles) {
		if(userProfile.equals(e)) {
			suggestedProfiles.remove(e);
		}
		else {
			SuggestionDto suggestionDto=new SuggestionDto(e.getName(), e.getEmail());
			suggestionDtos.add(suggestionDto);
		}
	}
		return new Response("suggested Profiles",200,suggestionDtos);
	}

	@Override
	public Response suggestByPref(String email) {
		UserProfile userProfile=userRepository.findByEmail(email);
		Response response=new Response();
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
		for(UserProfile u:suggestedUserProfiles) {
			if(userProfile.equals(u)) {
				suggestedUserProfiles.remove(u);
			}
		}
		response=new Response("success",500,toUserDto(suggestedUserProfiles));
		return response;
	}	
	
	public List<UserDto> toUserDto(List<UserProfile> suggestedUserProfiles) {
		 List<UserDto> userDtos = new ArrayList<>();
		for (UserProfile user: suggestedUserProfiles) {
			UserDto dto = new UserDto();
			dto.setEmail(user.getEmail());
			dto.setName(user.getName());
			userDtos.add(dto);
		}
		return userDtos;
	}
}
