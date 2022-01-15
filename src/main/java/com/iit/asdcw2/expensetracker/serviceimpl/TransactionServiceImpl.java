package com.iit.asdcw2.expensetracker.serviceimpl;
import org.springframework.stereotype.Service;

import  com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.service.TransactionService;
import com.iit.asdcw2.generic.serviceimpl.GenericServiceImpl;

@Service("transactionService")
public class TransactionServiceImpl extends GenericServiceImpl<Transaction,Long> implements TransactionService{

}
