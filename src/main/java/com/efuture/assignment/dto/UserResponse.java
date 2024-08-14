package com.efuture.assignment.dto;

import com.efuture.assignment.model.User;

public class UserResponse {

	private int code;
	private String message;
	private UserDTO userDto;
	private String token;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
