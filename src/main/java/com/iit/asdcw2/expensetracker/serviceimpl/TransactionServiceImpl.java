package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.dao.TransactionDao;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateTransactionDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.ResponseTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.TransactionService;
import com.iit.asdcw2.expensetracker.service.UserService;
import com.iit.asdcw2.util.AppDate;

@Service("transactionService")
public class TransactionServiceImpl extends GenericServiceImpl<Transaction, Long> implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	private Random random = null;

	public TransactionServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public TransactionServiceImpl(GenericDao<Transaction, Long> genericDao) {
		super(genericDao);
	}

	@Override
	public Boolean addTransaction(CreateTransactionDto createTransactionDto) {
		try {
			Transaction transaction = new Transaction();
			User user = userService.find(createTransactionDto.getUser());
			Category category = categoryService.find(createTransactionDto.getCategory());
			Date date = AppDate.getDatefromString(createTransactionDto.getTransactionDate());
			transaction.setUser(user);
			transaction.setAmount(createTransactionDto.getAmount());
			transaction.setIsIncome(createTransactionDto.getIsIncome());
			transaction.setCategory(category);
			transaction.setTransactionDate(date);
			transaction.setNote(createTransactionDto.getNote());
			transaction.setIsRecurrent(createTransactionDto.getIsRecurrent());
			save(transaction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateTransaction(UpdateTransactionDto updateTransactionDto) {
		try {
			Transaction currentTransaction = find(updateTransactionDto.getId());
			if (currentTransaction != null) {
				if (updateTransactionDto.getUser() != null) {
					User user = userService.find(updateTransactionDto.getUser());
					currentTransaction.setUser(user);
				}

				if (updateTransactionDto.getCategory() != null) {
					Category category = categoryService.find(updateTransactionDto.getCategory());
					currentTransaction.setCategory(category);
				}

				if (updateTransactionDto.getTransactionDate() != null) {
					Date date = AppDate.getDatefromString(updateTransactionDto.getTransactionDate());
					currentTransaction.setTransactionDate(date);
				}

				if (updateTransactionDto.getAmount() != currentTransaction.getAmount()) {
					currentTransaction.setAmount(updateTransactionDto.getAmount());
				}

				if (updateTransactionDto.getIsIncome() != currentTransaction.getIsIncome()) {
					currentTransaction.setIsIncome(updateTransactionDto.getIsIncome());
				}

				saveOrUpdate(currentTransaction);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean removeTransaction(DeleteTransactionDto deleteTransactionDto) {

		try {
			Transaction transaction = find(deleteTransactionDto.getId());
			delete(transaction);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ResponseTransactionDto> getAllTransactionsByUser(User user) {
		List<Transaction> allTransactions = findAll();

		List<ResponseTransactionDto> result = new ArrayList<>();
		for (Transaction transaction : allTransactions) {
			if (transaction.getUser().getId().intValue() == user.getId().intValue()) {
				ResponseTransactionDto responseTransactionDto = new ResponseTransactionDto();
				responseTransactionDto.setId(transaction.getId());
				responseTransactionDto.setAmount(transaction.getAmount());
				responseTransactionDto.setCategory(transaction.getCategory().getId());
				responseTransactionDto.setIsIncome(transaction.getIsIncome());
				responseTransactionDto.setTransactionDate(transaction.getTransactionDate());
				responseTransactionDto.setUser(transaction.getUser().getId());
				responseTransactionDto.setIsRecurrent(transaction.getIsRecurrent());
				responseTransactionDto.setNote(transaction.getNote());
				result.add(responseTransactionDto);
			}
		}

		return result;
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return find(id);
	}
}
