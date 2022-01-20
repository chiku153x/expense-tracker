package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ResponseTransactionSummaryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResponseTransactionSummaryDto() {
		super();
	}

	@ApiModelProperty(value = "No")
	private Long no;

	@ApiModelProperty(value = "Category")
	private Long category;

	@ApiModelProperty(value = "Total Expenses")
	private Double totalExpenses;

	@ApiModelProperty(value = "Budget")
	private Double budget;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public ResponseTransactionSummaryDto(Long no, Long category, Double totalExpenses, Double budget) {
		super();
		this.no = no;
		this.category = category;
		this.totalExpenses = totalExpenses;
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "ResponseTransactionSummaryDto [no=" + no + ", category=" + category + ", totalExpenses=" + totalExpenses
				+ ", budget=" + budget + "]";
	}

}
