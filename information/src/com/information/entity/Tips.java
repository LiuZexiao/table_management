package com.information.entity;

public class Tips {
	private int code;
	private String type;
	
	
	public Tips() {
		super();
	}
	public Tips(int code, String type) {
		super();
		this.code = code;
		this.type = type;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
