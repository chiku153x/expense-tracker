package com.iit.asdcw2.expensetracker.daoimpl;

import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.TransactionDao;
import com.iit.asdcw2.expensetracker.domain.Transaction;

@Repository("transactionDao")
public class TransactionDaoImpl extends GenericDaoImpl<Transaction, Long> implements TransactionDao {

	public TransactionDaoImpl() {
		super(Transaction.class);
	}

}
