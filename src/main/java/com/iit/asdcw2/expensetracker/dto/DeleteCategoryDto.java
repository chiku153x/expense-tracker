package com.iit.asdcw2.expensetracker.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DeleteCategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public DeleteCategoryDto() {
		super();
	}

	@ApiModelProperty(value = "Category id")
	private Long id;

	public DeleteCategoryDto(Long id) {
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
		return "DeleteCategoryDto [id=" + id + "]";
	}

}
