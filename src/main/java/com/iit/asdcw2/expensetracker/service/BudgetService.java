package com.iit.asdcw2.expensetracker.service;

import java.util.List;

import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.DeleteBudgetDto;
import com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto;
import com.iit.asdcw2.expensetracker.dto.UpdateBudgetDto;

public interface BudgetService extends GenericService<Budget, Long> {

	Boolean addBudget(CreateBudgetDto createBudgetDto);

	List<ResponseBudgetDto> getAllBudgetsByUser(Long uid);

	Boolean updateBudget(UpdateBudgetDto updateBudgetDto);

	Boolean removeBudget(DeleteBudgetDto deleteBudgetDto);

//	Boolean removeBudget(DeleteBudgetDto deleteBudgetDto);
//
//	Boolean updateBudget(UpdateBudgetDto updateBudgetDto);

}
