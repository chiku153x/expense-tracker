package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.BudgetDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.DeleteBudgetDto;
import com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto;
import com.iit.asdcw2.expensetracker.dto.UpdateBudgetDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;

@Service("budgetService")
public class BudgetServiceImpl extends GenericServiceImpl<Budget, Long> implements BudgetService {

	@Autowired
	private BudgetDao budgetDao;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

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
			Category category = categoryService.find(createBudgetDto.getCategory());
			User user = userService.find(createBudgetDto.getUser());
			Budget budget = new Budget();
			budget.setDescription(createBudgetDto.getDescription());
			budget.setCategory(category);
			budget.setAmount(createBudgetDto.getAmount());
			budget.setMonth(createBudgetDto.getMonth());
			budget.setYear(createBudgetDto.getYear());
			budget.setUser(user);
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
			User user = userService.find(updateBudgetDto.getUser());
			Budget budget = new Budget();
			budget.setAmount(updateBudgetDto.getAmount());
			budget.setCategory(cat);
			budget.setDescription(updateBudgetDto.getDescription());
			budget.setId(updateBudgetDto.getId());
			budget.setUser(user);
			budget.setYear(updateBudgetDto.getYear());
			budget.setMonth(updateBudgetDto.getMonth());
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
