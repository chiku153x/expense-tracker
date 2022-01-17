package com.iit.asdcw2.expensetracker.service;

import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;

public interface BudgetService extends GenericService<Budget, Long> {

	Boolean addBudget(CreateBudgetDto createBudgetDto);

//	Boolean removeBudget(DeleteBudgetDto deleteBudgetDto);
//
//	Boolean updateBudget(UpdateBudgetDto updateBudgetDto);

}
