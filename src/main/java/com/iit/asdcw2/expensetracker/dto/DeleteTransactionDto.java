package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DeleteTransactionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public DeleteTransactionDto() {
		super();
	}

	@ApiModelProperty(value = "Transaction id")
	private Long id;

	public DeleteTransactionDto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeleteTransactionDto [id=" + id + "]";
	}

}
