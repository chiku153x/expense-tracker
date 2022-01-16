package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Transaction")
public class TransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "Transaction id")
	private Long id;

	@ApiModelProperty(value = "Transaction name")
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

	@Override
	public String toString() {
		return "TransactionDto [id=" + id + ", name=" + name + "]";
	}
	
	

	public TransactionDto() {
		super();
	}

	public TransactionDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

}
