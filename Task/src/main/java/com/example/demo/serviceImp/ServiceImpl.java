package com.example.demo.serviceImp;



import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PreferenceDTO;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Preference;
import com.example.demo.model.UserAddress;
import com.example.demo.model.UserPreference;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.PreferenceRepository;
import com.example.demo.repository.UserPreferenceRepository;
import com.example.demo.repository.UserRepository;

import com.example.demo.service.UserService;
import com.example.demo.util.PasswordGenerationUtil;
import com.example.demo.util.Response;
import com.example.demo.validator.UserValidator;

@Service
public class ServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PreferenceRepository preferenceRepository;
	
	
	@Autowired
	private UserPreferenceRepository userPreferenceRepository;
	
	@Autowired
	private UserValidator userValidator;
	
	
	@Override
	public Response login(String email, String password) {
		Response response=new Response("success login",200,"success");
		UserProfile userProfile=userRepository.findByEmail(email);
		try {
		 
		if(!PasswordGenerationUtil.checkPassword(password, userProfile.getSalt(), userProfile.getPassword())) {
			return new Response("Password not Matched",500,"Failure");
		}
		else {
			System.out.println("Login successful");
			List<String> emailIds=new ArrayList<>();
			List<UserAddress> userAddresses=userProfile.getUserAddress();
			List<UserAddress> existingUserAddress=addressRepository.findAll();
			for(UserAddress u1:userAddresses) {
					if(u1.getIsPrimary()==true) {
						for(UserAddress u:existingUserAddress) {
							if(u1.getCity().equals(u.getCity())) {
								emailIds.add(u1.getUserProfile().getEmail());
							}
						}
					}
			}
			return new Response("Login successful",200,emailIds);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}


	
	@Override
	public Response saveUser(UserDto userDto, List<PreferenceDTO> preferenceDto) {
		Response response = new Response();
		UserProfile userProfile = new UserProfile();
		response=userValidator.signUpValidator(userDto,preferenceDto);
		if(response==null) {
		if (userDto.getUserId() != null) {
			userProfile = userRepository.getOne(userDto.getUserId());
		} 
		
		try {
			userProfile.setName(userDto.getName());
			userProfile.setEmail(userDto.getEmail());
			Map<String, String> map = null;
			try {
			map = PasswordGenerationUtil.maskPassword(userDto.getPassword());
			} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			}
		
			if(map != null) {
				userProfile.setSalt(map.get("salt"));
				userProfile.setPassword(map.get("password"));
			} else {
				userProfile.setPassword(userDto.getPassword());
			}
			
			
			List<UserAddress> userAddress = new ArrayList<>();
			for (UserAddress u : userDto.getUserAddress()) {
				u.setUserProfile(userProfile);
				userAddress.add(u);
			}
			
			userProfile.setUserAddress(userAddress);
			userRepository.save(userProfile);
			for (PreferenceDTO p :preferenceDto) {
				if (p.getPrefId() != null && preferenceRepository.getOne(p.getPrefId()) != null) {
					Preference preference = preferenceRepository.getOne(p.getPrefId());
					UserPreference userPreference = userPreferenceRepository.findByPreferenceAndUserProfile(preference,userProfile);
					if (userPreference == null) {
						userPreference = new UserPreference();
						userPreference.setUserProfile(userProfile);
						userPreference.setPreference(preference);
						userPreferenceRepository.save(userPreference);
					}
				}
			}
				
			response = new Response("success", 200, userProfile);
		}
	      catch (Exception e) {
			e.printStackTrace();
		}
		}
		return response;
	}


	@Override
	public Response retrieveByEmail(String email){
		UserProfile userProfile=userRepository.findByEmail(email);
		Response response=new Response("Success",200,userProfile);
		return response;
	}


	@Override
	public Response retrieveByEmailAndPassword(String email, String password) {
			Response response=new Response();
			UserProfile userProfile=userRepository.findByEmail(email);
			if(userProfile.getPassword().equals(password)) {
				response=new Response("success",200,userProfile);
			}
			return response;
	}


	@Override
	public String updateUser(String email, UserDto userDto) {
		UserProfile userProfile=userRepository.findByEmail(email);
		userProfile.setPassword(userDto.getPassword());
		userProfile.setName(userDto.getName());
		userProfile.setUserAddress(userDto.getUserAddress());
		userRepository.save(userProfile);
		return "updated successfully";
	}
	

	@Override
	public Response deleteUser(String email) {
		    UserProfile userProfile=new UserProfile();
			userProfile=userRepository.findByEmail(email);
		List<UserAddress> userAddress=addressRepository.findByUserProfile(userProfile);
		for(UserAddress u:userAddress) {
			addressRepository.delete(u);
		}
		
		List<UserPreference> userPreferences=userPreferenceRepository.findByUserProfile(userProfile);
		for(UserPreference u:userPreferences) {
		userPreferenceRepository.delete(u);
		}
		userRepository.delete(userProfile);
		
		Response response=new Response("success",200,userProfile);
		return response;
	}



	@Override
	public Response suggestUser(/*List<Preference> preferences, */String city) {
		List<String> emailIds=new ArrayList<>();
		List<UserAddress> userAddress=addressRepository.findAll();
		for(UserAddress u:userAddress) {
			if(u.getCity().equals(city)) {
				emailIds.add(u.getUserProfile().getEmail());
			}
		}
		return new Response("Users living in same city",200,emailIds);
	}


	

	

	//@Override
	//public Response retrieve(String streetName, String doorNo, String city) {
	//	UserAddress userAddress=addressRepository.findByStreetName(streetName);
	//	List<UserProfile> userProfile=new ArrayList<UserProfile>();
	//	if(userAddress.getDoorNo().equals(doorNo)& userAddress.getCity().equals(city)) {
	//		userProfile=userRepository.findByUserAddresses(userAddress);
	//	}
	//		Response response=new Response("success",200,userProfile);
	//	return response;
	//}



	//@Override
	//public UserProfile retrieveByPreference(String typeOfPref, String category) {
	//	Preference preference=preferenceRepository.findBytypeOfPref(typeOfPref);
	//	UserProfile userProfile=new UserProfile();
	//	if(preference.getCategory().equals(category)) {
	//		userProfile=userRepository.findByPreferences(preference);
	//	}
	//	Response response = new Response();
	//	return userProfile;
	//}
}
