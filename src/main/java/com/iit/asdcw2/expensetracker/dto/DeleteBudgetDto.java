package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DeleteBudgetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public DeleteBudgetDto() {
		super();
	}

	@ApiModelProperty(value = "Budget id")
	private Long id;

	public DeleteBudgetDto(Long id) {
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
		return "DeleteBudgetDto [id=" + id + "]";
	}

}
