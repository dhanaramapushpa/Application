package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FriendDto;
import com.example.demo.model.Friends;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendService;
import com.example.demo.util.Response;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired UserRepository userRepo;
	
	@Autowired FriendRepository friendrepo;
	
	@Override
	public Response addFriend(FriendDto frinedDto) {
		Response response = new Response();
		try {
			System.out.println("1");
			String friendProfileEmail = frinedDto.getFriendProfileEmail();
			String userProfileEmail = frinedDto.getUserProfileEmail();
			Long friendProfileId = frinedDto.getFriendProfileId();
			Long userProfileId = frinedDto.getUserProfileId();
			UserProfile userProfile = null;
			UserProfile friendProfile = null;
			if (friendProfileEmail != null && userProfileEmail != null) {
				userProfile = userRepo.findByEmail(userProfileEmail);
				friendProfile = userRepo.findByEmail(userProfileEmail);
			} else {
				if (friendProfileId != null && userProfileId != null) {
					userProfile = userRepo.getOne(userProfileId);
					friendProfile = userRepo.getOne(friendProfileId);
				}
			}
			if (userProfile != null && friendProfile != null) {
				System.out.println("2");
				Friends findByUserProfileAndFriendProfile = friendrepo.findByUserProfileAndFriendProfile(userProfile, friendProfile);
				if (findByUserProfileAndFriendProfile != null && findByUserProfileAndFriendProfile.getIsActive()) {
					response = new Response("Already added", 200, frinedDto);
				} else {
					System.out.println("3");
					Friends friends = new Friends();
					friends.setFriendProfile(friendProfile);
					friends.setUserProfile(userProfile);
					friends.setIsActive(Boolean.TRUE);
					friendrepo.save(friends);
					response = new Response("success", 200, frinedDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return response;
	}


	@Override
	public Response removeFriend(FriendDto frinedDto) {
		Response response = new Response();
		try {
			String friendProfileEmail = frinedDto.getFriendProfileEmail();
			String userProfileEmail = frinedDto.getUserProfileEmail();
			Long friendProfileId = frinedDto.getFriendProfileId();
			Long userProfileId = frinedDto.getUserProfileId();
			UserProfile userProfile = null;
			UserProfile friendProfile = null;
			if (friendProfileEmail != null && userProfileEmail != null) {
				userProfile = userRepo.findByEmail(userProfileEmail);
				friendProfile = userRepo.findByEmail(userProfileEmail);
			} else {
				if (friendProfileId != null && userProfileId != null) {
					userProfile = userRepo.getOne(userProfileId);
					friendProfile = userRepo.getOne(friendProfileId);
				}
			}
			if (userProfile != null && friendProfile != null) {
				Friends findByUserProfileAndFriendProfile = friendrepo.findByUserProfileAndFriendProfile(userProfile, friendProfile);
				if (findByUserProfileAndFriendProfile != null) {
					findByUserProfileAndFriendProfile.setIsActive(Boolean.FALSE);
					response = new Response("success", 200, frinedDto);
				} else {
					response = new Response("No record found", 200, frinedDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}



}
