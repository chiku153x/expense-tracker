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

	public Budget(Long id, String description, Category category) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Budget [id=" + id + ", description=" + description + ", category=" + category + "]";
	}

	

}
