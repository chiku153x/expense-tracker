package com.iit.asdcw2.expensetracker.daoimpl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.BudgetDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto;

@Repository("budgetDao")
public class BudgetDaoImpl extends GenericDaoImpl<Budget, Long> implements BudgetDao {

	public BudgetDaoImpl() {
		super(Budget.class);
	}

	@Override
	public List<ResponseBudgetDto> findAllByUser(Long uid) {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"select new com.iit.asdcw2.expensetracker.dto.ResponseBudgetDto( b.id,b.description,b.category.id, b.amount, b.year , b.month , b.user.id) from Budget b where b.user.id = :userId");
		Query<?> query = this.getCurrentSession().createQuery(builder.toString());
		query.setParameter("userId", uid);
		return (List<ResponseBudgetDto>) query.getResultList();
	}

}
