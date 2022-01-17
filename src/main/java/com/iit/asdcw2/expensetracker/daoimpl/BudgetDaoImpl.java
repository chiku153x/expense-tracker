package com.iit.asdcw2.expensetracker.daoimpl;

import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.BudgetDao;
import com.iit.asdcw2.expensetracker.domain.Budget;

@Repository("budgetDao")
public class BudgetDaoImpl extends GenericDaoImpl<Budget, Long> implements BudgetDao {

	public BudgetDaoImpl() {
		super(Budget.class);
	}

}
