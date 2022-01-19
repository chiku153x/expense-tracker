package com.iit.asdcw2.expensetracker.domain;

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

@ApiModel("budget")
@Entity
@Table(name = "budget", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class Budget implements Serializable {

	private static final long serialVersionUID = 1L;

	public Budget() {
		super();
	}

	@ApiModelProperty(value = "Budget id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "Description")
	@Column(name = "description")
	private String description;

	@ApiModelProperty(value = "Category")
	@ManyToOne
	@JoinColumn(name = "category")
	private Category category;

	@ApiModelProperty(value = "Amount")
	@Column(name = "amount")
	private Double amount;

	@ApiModelProperty(value = "Year")
	@Column(name = "year")
	private Integer year;

	@ApiModelProperty(value = "Month")
	@Column(name = "month")
	private Integer month;
	
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

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Budget(Long id, String description, Category category, Double amount, Integer year, Integer month,
			User user) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.amount = amount;
		this.year = year;
		this.month = month;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", description=" + description + ", category=" + category + ", amount=" + amount
				+ ", year=" + year + ", month=" + month + ", user=" + user + "]";
	}

	

}
