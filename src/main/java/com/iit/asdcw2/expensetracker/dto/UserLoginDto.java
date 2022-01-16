package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("User")
public class UserLoginDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "User Name")
	private String userName;

	@ApiModelProperty(value = "Password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserLoginDto() {
		super();
	}

	
}
