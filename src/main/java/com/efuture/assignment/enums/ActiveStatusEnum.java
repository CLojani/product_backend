package com.efuture.assignment.enums;

public enum ActiveStatusEnum {

	ACTIVE("A","Active"),
	DEACTIVATE("D","Deactivate");
	
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private ActiveStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
}
