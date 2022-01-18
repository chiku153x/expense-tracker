package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ResponseTransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResponseTransactionDto() {
		super();
	}

	@ApiModelProperty(value = "Transaction Id")
	private Long id;

	@ApiModelProperty(value = "Transaction date")
	private Date transactionDate;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
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

	public ResponseTransactionDto(Long id, Date transactionDate, double amount, Long category, Boolean isIncome,
			Long user, String note, Boolean isRecurrent) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.category = category;
		this.isIncome = isIncome;
		this.user = user;
		this.note = note;
		this.isRecurrent = isRecurrent;
	}

	@Override
	public String toString() {
		return "ResponseTransactionDto [id=" + id + ", transactionDate=" + transactionDate + ", amount=" + amount
				+ ", category=" + category + ", isIncome=" + isIncome + ", user=" + user + ", note=" + note
				+ ", isRecurrent=" + isRecurrent + "]";
	}


}
