package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.BudgetDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;

@Service("budgetService")
public class BudgetServiceImpl extends GenericServiceImpl<Budget, Long> implements BudgetService {

	@Autowired
	private BudgetDao budgetDao;

	private Random random = null;

	public BudgetServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public BudgetServiceImpl(GenericDao<Budget, Long> genericDao) {
		super(genericDao);
	}

	@Override
	public Boolean addBudget(CreateBudgetDto createBudgetDto) {
		try {
			Budget budget = new Budget();
			budget.setDescription(createBudgetDto.getDescription());
			budget.setCategory(createBudgetDto.getCategory());
			save(budget);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
