package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import com.iit.asdcw2.expensetracker.domain.Category;

import io.swagger.annotations.ApiModelProperty;

public class CreateBudgetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public CreateBudgetDto() {
		super();
	}

	@ApiModelProperty(value = "Description")
	private String description;

	@ApiModelProperty(value = "Category")
	private Category category;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CreateBudgetDto(String description, Category category) {
		super();
		this.description = description;
		this.category = category;
	}

	@Override
	public String toString() {
		return "CreateBudgetDto [description=" + description + ", category=" + category + "]";
	}

	
}
