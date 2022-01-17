package com.iit.asdcw2.expensetracker.dao;

import java.util.List;

import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto;

public interface CategoryDao extends GenericDao<Category, Long> {

	List<ResponseCategoryDto> findAllByUser(Long uid);

}
