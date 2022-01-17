package com.iit.asdcw2.expensetracker.service;

import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.dto.CreateTransactionDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;

public interface TransactionService extends GenericService<Transaction, Long> {

	Boolean addTransaction(CreateTransactionDto createTransactionDto);

	Boolean updateTransaction(UpdateTransactionDto updateTransactionDto);

	Boolean removeTransaction(DeleteTransactionDto deleteTransactionDto);

}
