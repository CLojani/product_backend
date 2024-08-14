package com.efuture.assignment.enums;

public enum ResponseEnum {

	SUCCESS(200, "Success !"),
	USER_NOT_FOUND(414, "User not found !"),
	USER_ALREADY_EXITS(416, "Already exits !"),
	ERROR(400, "Error !"),
	NOT_FOUND(401, "Not Found!"),
	NO_DATA(402, "No Data!"),
	INCORRECT_PASSWORD(406,"Incorrect password or username!"),
	INACTIVE_USER(409,"Inactive user!");
	
	private int code;
	private String message;
	
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
	
	private ResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
