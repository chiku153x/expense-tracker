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

@ApiModel("category")
@Entity
@Table(name = "category", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}

	@ApiModelProperty(value = "Category id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "Name")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(value = "Description")
	@Column(name = "description")
	private String description;

	@ApiModelProperty(value = "User")
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ApiModelProperty(value = "isDeleted")
	@Column(name = "isDeleted")
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Category(Long id, String name, String description, User user, Boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", user=" + user
				+ ", isDeleted=" + isDeleted + "]";
	}

	

}
