package com.iit.asdcw2.expensetracker.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.AccessType;
import javax.persistence.Column;

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

	@ApiModelProperty(value = "Transaction date")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "date")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Transaction(Long id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + "]";
	}

}
