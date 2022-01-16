package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("User")
public class UserLoginResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "token")
	private String token;
	
	@ApiModelProperty(value = "userName")
	private String userName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserLoginResponseDto(String token, String userName) {
		super();
		this.token = token;
		this.userName = userName;
	}

	public UserLoginResponseDto() {
		super();
	}

	
}
