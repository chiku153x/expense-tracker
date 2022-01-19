package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.CategoryDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.CreateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteCategoryDto;
import com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto;
import com.iit.asdcw2.expensetracker.dto.UpdateCategoryDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;

@Service("categoryService")
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserService userService;

	@Lazy
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

//			CreateBudgetDto createBudgetDto = new CreateBudgetDto();
//			createBudgetDto.setCategory(savedCategory.getId());
//			createBudgetDto.setDescription(createCategoryDto.getDescription());
//			budgetService.addBudget(createBudgetDto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Boolean removeCategory(DeleteCategoryDto deleteCategoryDto) {
		try {
			Category category = find(deleteCategoryDto.getId());
			delete(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateCategory(UpdateCategoryDto updateCategoryDto) {
		try {
			Category category = find(updateCategoryDto.getId());
			category.setDescription(updateCategoryDto.getDescription());
			category.setName(updateCategoryDto.getName());
			category.setUser(userService.find(updateCategoryDto.getUserId()));
			saveOrUpdate(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ResponseCategoryDto> getAllCategoriesByUser(Long uid) {
		List<ResponseCategoryDto> findAllCategoriesByAdmin = new ArrayList<>();
		List<User> adminUsers = userService.findAll().stream().filter(f -> f.getUsername().equals("admin"))
				.collect(Collectors.toList());
		if (adminUsers != null && adminUsers.size() > 0) {
			User admin = adminUsers.get(0);
			if (admin.getId().intValue() != uid.intValue()) {
				findAllCategoriesByAdmin = categoryDao.findAllByUser(admin.getId());
			}
		}

		List<ResponseCategoryDto> findAllCategories = categoryDao.findAllByUser(uid);
		List<ResponseCategoryDto> filtered = findAllCategories.stream().filter(f-> !f.getIsDeleted()).collect(Collectors.toList());
		if (!filtered.isEmpty()) {
			findAllCategories.addAll(findAllCategoriesByAdmin);
		}

		return findAllCategories;
	}

}
