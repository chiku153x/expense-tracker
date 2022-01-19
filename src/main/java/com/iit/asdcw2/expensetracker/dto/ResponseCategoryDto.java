package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ResponseCategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResponseCategoryDto() {
		super();
	}

	@ApiModelProperty(value = "category id")
	private Long id;
	
	@ApiModelProperty(value = "category name")
	private String name;

	@ApiModelProperty(value = "category description")
	private String description;

	@ApiModelProperty(value = "user")
	private Long userId;
	
	@ApiModelProperty(value = "isDeleted")
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
// arguments are:com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto(c.id, c.name,c.description,c.user.id, c.isDeleted) 
	public ResponseCategoryDto(Long id, String name, String description, Long userId, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.userId = userId;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "ResponseCategoryDto [id=" + id + ", name=" + name + ", description=" + description + ", userId="
				+ userId + ", isDeleted=" + isDeleted + "]";
	}

	
	

}
