package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UpdateTransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public UpdateTransactionDto() {
		super();
	}

	@ApiModelProperty(value = "Transaction id")
	private Long id;

	@ApiModelProperty(value = "Transaction date")
	private String transactionDate;

	@ApiModelProperty(value = "Transaction amount")
	private double amount;

	@ApiModelProperty(value = "Category")
	private Long category;

	@ApiModelProperty(value = "Is Income")
	private Boolean isIncome;

	@ApiModelProperty(value = "User")
	private Long user;

	public Long getId() {
		return id;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Boolean getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Boolean isIncome) {
		this.isIncome = isIncome;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
