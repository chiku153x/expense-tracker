package com.iit.asdcw2.expensetracker.domain;

import java.io.Serializable;
import java.util.Date;

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

@ApiModel("transaction")
@Entity
@Table(name = "transaction", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	public Transaction() {
	}

	@ApiModelProperty(value = "Transaction id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "Transaction date")
	@Column(name = "transactionDate")
	private Date transactionDate;

	@ApiModelProperty(value = "Transaction amount")
	@Column(name = "amount")
	private double amount;

	@ApiModelProperty(value = "Category")
	@ManyToOne
    @JoinColumn(name = "category")
	private Category category;

	@ApiModelProperty(value = "Is Income")
	@Column(name = "isIncome")
	private Boolean isIncome;
	
	@ApiModelProperty(value = "User")
	@ManyToOne
    @JoinColumn(name = "user")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date date) {
		this.transactionDate = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Boolean getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Boolean isIncome) {
		this.isIncome = isIncome;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Transaction(Long id, Date transactionDate, double amount, Category category, Boolean isIncome, User user) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.category = category;
		this.isIncome = isIncome;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", amount=" + amount + ", category="
				+ category + ", isIncome=" + isIncome + ", user=" + user + "]";
	}

	
	
	
}
