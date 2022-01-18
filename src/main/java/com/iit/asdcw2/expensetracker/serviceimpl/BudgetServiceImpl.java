package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.BudgetDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.DeleteBudgetDto;
import com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto;
import com.iit.asdcw2.expensetracker.dto.UpdateBudgetDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
import com.iit.asdcw2.expensetracker.service.CategoryService;

@Service("budgetService")
public class BudgetServiceImpl extends GenericServiceImpl<Budget, Long> implements BudgetService {

	@Autowired
	private BudgetDao budgetDao;

	@Autowired
	private CategoryService categoryService;

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

	@Override
	public List<ResponseBudgetDto> getAllBudgetsByUser(Long uid) {
		return budgetDao.findAllByUser(uid);
	}

	@Override
	public Boolean updateBudget(UpdateBudgetDto updateBudgetDto) {
		try {
			Category cat = categoryService.find(updateBudgetDto.getCategory());
			Budget budget = new Budget();
			budget.setAmount(updateBudgetDto.getAmount());
			budget.setCategory(cat);
			budget.setDescription(updateBudgetDto.getDescription());
			budget.setId(updateBudgetDto.getId());
			saveOrUpdate(budget);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Boolean removeBudget(DeleteBudgetDto deleteBudgetDto) {
		try {
			Budget budget = find(deleteBudgetDto.getId());
			delete(budget);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
