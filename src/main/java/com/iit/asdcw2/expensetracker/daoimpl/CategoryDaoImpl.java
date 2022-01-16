package com.iit.asdcw2.expensetracker.daoimpl;

import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.CategoryDao;
import com.iit.asdcw2.expensetracker.domain.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {

	public CategoryDaoImpl() {
		super(Category.class);
	}

}
