package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.CategoryDao;
import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	private Random random = null;

	public CategoryServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public CategoryServiceImpl(GenericDao<Category, Long> genericDao) {
		super(genericDao);
	}

}
