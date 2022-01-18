package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import com.iit.asdcw2.expensetracker.domain.Category;

import io.swagger.annotations.ApiModelProperty;

public class UpdateBudgetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public UpdateBudgetDto() {
		super();
	}

	@ApiModelProperty(value = "Id")
	private Long id;
	
	@ApiModelProperty(value = "Description")
	private String description;

	@ApiModelProperty(value = "Category")
	private Long category;


	@ApiModelProperty(value = "Amount")
	private Double amount;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getCategory() {
		return category;
	}


	public void setCategory(Long category) {
		this.category = category;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public UpdateBudgetDto(Long id, String description, Long category, Double amount) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "UpdateBudgetDto [id=" + id + ", description=" + description + ", category=" + category + ", amount="
				+ amount + "]";
	}

	
}
