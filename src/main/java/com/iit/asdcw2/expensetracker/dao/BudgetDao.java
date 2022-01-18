package com.iit.asdcw2.expensetracker.dao;

import java.util.List;

import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto;

public interface BudgetDao extends GenericDao<Budget, Long> {

	List<ResponseBudgetDto> findAllByUser(Long uid);

}
