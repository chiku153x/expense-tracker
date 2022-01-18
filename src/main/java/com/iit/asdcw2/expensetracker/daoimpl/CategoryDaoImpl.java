package com.iit.asdcw2.expensetracker.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.CategoryDao;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto;

@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {

	public CategoryDaoImpl() {
		super(Category.class);
	}

	@Override
	public List<ResponseCategoryDto> findAllByUser(Long uid) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"select new com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto(c.id, c.name,c.description,c.user.id) from Category c where c.user.id = :userId");
		Query<?> query = this.getCurrentSession().createQuery(builder.toString());
		query.setParameter("userId", uid);
		return (List<ResponseCategoryDto>) query.getResultList();
	}

}
