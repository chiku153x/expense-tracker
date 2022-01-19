package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CreateBudgetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public CreateBudgetDto() {
		super();
	}

	@ApiModelProperty(value = "Description")
	private String description;

	@ApiModelProperty(value = "Category")
	private Long category;

	@ApiModelProperty(value = "Amount")
	private Double amount;

	@ApiModelProperty(value = "Year")
	private Integer year;

	@ApiModelProperty(value = "Month")
	private Integer month;
	
	@ApiModelProperty(value = "User")
	private Long user;

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public CreateBudgetDto(String description, Long category, Double amount, Integer year, Integer month, Long user) {
		super();
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.year = year;
		this.month = month;
		this.user = user;
	}

	@Override
	public String toString() {
		return "CreateBudgetDto [description=" + description + ", category=" + category + ", amount=" + amount
				+ ", year=" + year + ", month=" + month + ", user=" + user + "]";
	}

	

}
