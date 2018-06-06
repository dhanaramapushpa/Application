package com.example.demo.dto;

import java.util.List;

import com.example.demo.dto.PreferenceDTO;
import com.example.demo.dto.UserDto;

public class IncomingRequestBody {

	private UserDto userDto;
	
	private List<PreferenceDTO> preferenceDto;
	
	
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	public List<PreferenceDTO> getPreferenceDto() {
		return preferenceDto;
	}
	public void setPreferenceDto(List<PreferenceDTO> preferenceDto) {
		this.preferenceDto = preferenceDto;
	}
}
