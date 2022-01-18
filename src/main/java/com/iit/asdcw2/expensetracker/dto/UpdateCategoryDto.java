package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class UpdateCategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public UpdateCategoryDto() {
		super();
	}
	
	@ApiModelProperty(value = "category is")
	private Long id;

	@ApiModelProperty(value = "category name")
	private String name;

	@ApiModelProperty(value = "category description")
	private String description;

	@ApiModelProperty(value = "user")
	private Long userId;

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

	public UpdateCategoryDto(Long id, String name, String description, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UpdateCategoryDto [id=" + id + ", name=" + name + ", description=" + description + ", userId=" + userId
				+ "]";
	}

	
}
