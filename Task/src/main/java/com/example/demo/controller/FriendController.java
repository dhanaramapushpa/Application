package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FriendDto;
import com.example.demo.service.FriendService;
import com.example.demo.util.Response;

@RestController
public class FriendController {

	@Autowired FriendService friendService; 
	
	@RequestMapping(value="/add-friend",method=RequestMethod.POST)
	public Response addFriend(@RequestBody FriendDto friendDto) {
		System.out.println(friendDto.toString());
		return friendService.addFriend(friendDto);
	}
	
	@RequestMapping(value="/remove-friend",method=RequestMethod.POST)
	public Response removeFriend(@RequestBody FriendDto friendDto) {
		return friendService.removeFriend(friendDto);
	}
	
}
