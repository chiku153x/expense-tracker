package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.CategoryDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.CreateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;

@Service("categoryService")
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BudgetService budgetService;

	private Random random = null;

	public CategoryServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public CategoryServiceImpl(GenericDao<Category, Long> genericDao) {
		super(genericDao);
	}

	@Override
	public Boolean addCategory(CreateCategoryDto createCategoryDto) {
		
		try {
			Category cat = new Category();
			cat.setName(createCategoryDto.getName());
			cat.setDescription(createCategoryDto.getDescription());
			User user = userService.find(createCategoryDto.getUserId());
			cat.setUser(user);
			Category savedCategory = save(cat);
			
			CreateBudgetDto createBudgetDto = new CreateBudgetDto();
			createBudgetDto.setCategory(savedCategory);
			createBudgetDto.setDescription(createCategoryDto.getDescription());
			budgetService.addBudget(createBudgetDto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Boolean removeCategory(DeleteTransactionDto deleteTransactionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateCategory(UpdateTransactionDto updateTransactionDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
