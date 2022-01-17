package com.iit.asdcw2.expensetracker.service;

import java.util.List;

import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.dto.CreateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;

public interface CategoryService extends GenericService<Category, Long> {

	Boolean addCategory(CreateCategoryDto createCategoryDto);

	Boolean removeCategory(DeleteTransactionDto deleteTransactionDto);

	Boolean updateCategory(UpdateTransactionDto updateTransactionDto);

	List<ResponseCategoryDto> getAllCategoriesByUser(Long uid);

}
