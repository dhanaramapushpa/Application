package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FriendDto;
import com.example.demo.util.Response;

@Service
public interface FriendService {
	
	Response addFriend(FriendDto frinedDto);
	
	Response removeFriend(FriendDto frinedDto);
}
