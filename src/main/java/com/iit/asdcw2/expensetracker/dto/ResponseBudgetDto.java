package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ResponseBudgetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResponseBudgetDto() {
		super();
	}

	@ApiModelProperty(value = "Budget id")
	private Long id;

	@ApiModelProperty(value = "Budget description")
	private String description;

	@ApiModelProperty(value = "Budget Category")
	private Long category;

	@ApiModelProperty(value = "Amount")
	private Double amount;

	@ApiModelProperty(value = "year")
	private Integer year;

	@ApiModelProperty(value = "month")
	private Integer month;

	@ApiModelProperty(value = "user")
	private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ResponseBudgetDto(Long id, String description, Long category, Double amount, Integer year, Integer month,
			Long userId) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.year = year;
		this.month = month;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ResponseBudgetDto [id=" + id + ", description=" + description + ", category=" + category + ", amount="
				+ amount + ", year=" + year + ", month=" + month + ", userId=" + userId + "]";
	}

}
