package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.dao.TransactionDao;
import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.service.TransactionService;

@Service("transactionService")
public class TransactionServiceImpl extends GenericServiceImpl<Transaction, Long> implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	private Random random = null;

	public TransactionServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public TransactionServiceImpl(GenericDao<Transaction, Long> genericDao) {
		super(genericDao);
	}
}
