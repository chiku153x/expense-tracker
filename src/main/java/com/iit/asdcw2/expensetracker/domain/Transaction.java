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
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("transaction")
@Entity
@Table(name = "transaction", indexes = @Index(name = "idx_id", columnList = "id"))
@Access(AccessType.FIELD)
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Transaction id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "Transaction name")
	@Column(name = "name")
	private String name;

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

	public Transaction(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Transaction() {
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", name=" + name + "]";
	}

}
