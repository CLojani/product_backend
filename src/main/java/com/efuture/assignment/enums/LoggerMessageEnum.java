package com.efuture.assignment.enums;

public enum LoggerMessageEnum {

	METHOD_EXECUTED("METHOD EXECUTED"),
	ERROR_OCCURED("ERROR OCCURED"),
	CREATE_PRODUCT("CREATE PRODUCT"),
	CREATE("CREATE"),
	UPDATE("UPDATE"),
	GET("GET"),
	USER_LOGIN("USER LOGIN"),	
	DELETE("Delete Product");
	
	private String value;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private LoggerMessageEnum(String value) {
		this.value = value;
	}
}
