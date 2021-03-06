package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CreateTransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public CreateTransactionDto() {
		super();
	}

	@ApiModelProperty(value = "Transaction date")
	private String transactionDate;

	@ApiModelProperty(value = "description")
	private String description;

	@ApiModelProperty(value = "Transaction amount")
	private double amount;

	@ApiModelProperty(value = "Category")
	private Long category;

	@ApiModelProperty(value = "Is Income")
	private Boolean isIncome;

	@ApiModelProperty(value = "User")
	private Long user;

	@ApiModelProperty(value = "note")
	private String note;

	@ApiModelProperty(value = "isRecurrent")
	private Boolean isRecurrent;

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsRecurrent() {
		return isRecurrent;
	}

	public void setIsRecurrent(Boolean isRecurrent) {
		this.isRecurrent = isRecurrent;
	}

	public CreateTransactionDto(String transactionDate, String description, double amount, Long category,
			Boolean isIncome, Long user, String note, Boolean isRecurrent) {
		super();
		this.transactionDate = transactionDate;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.isIncome = isIncome;
		this.user = user;
		this.note = note;
		this.isRecurrent = isRecurrent;
	}

	@Override
	public String toString() {
		return "CreateTransactionDto [transactionDate=" + transactionDate + ", description=" + description + ", amount="
				+ amount + ", category=" + category + ", isIncome=" + isIncome + ", user=" + user + ", note=" + note
				+ ", isRecurrent=" + isRecurrent + "]";
	}

}
