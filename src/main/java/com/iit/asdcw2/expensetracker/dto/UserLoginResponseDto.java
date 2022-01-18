package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("User")
public class UserLoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "name")
	private String name;

	@ApiModelProperty(value = "id")
	private Long id;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserLoginResponseDto(String token, String name, Long id) {
		super();
		this.token = token;
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserLoginResponseDto [token=" + token + ", name=" + name + ", id=" + id + "]";
	}

}
