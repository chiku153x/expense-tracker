package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.dao.TransactionDao;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateTransactionDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.ResponseCategoryDto;
import com.iit.asdcw2.expensetracker.dto.ResponseTransactionDto;
import com.iit.asdcw2.expensetracker.dto.ResponseTransactionSummaryDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
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
	private BudgetService budgetService;

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
			AppDate appDateInstance = AppDate.getInstance();
			Transaction transaction = new Transaction();
			User user = userService.find(createTransactionDto.getUser());
			Category category = categoryService.find(createTransactionDto.getCategory());
			Date date = appDateInstance.getDatefromString(createTransactionDto.getTransactionDate());
			transaction.setUser(user);
			transaction.setAmount(createTransactionDto.getAmount());
			transaction.setIsIncome(createTransactionDto.getIsIncome());
			transaction.setCategory(category);
			transaction.setTransactionDate(date);
			transaction.setNote(createTransactionDto.getNote());
			transaction.setIsRecurrent(createTransactionDto.getIsRecurrent());
			transaction.setDescription(createTransactionDto.getDescription());
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
			AppDate appDateInstance = AppDate.getInstance();
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
					Date date = appDateInstance.getDatefromString(updateTransactionDto.getTransactionDate());
					currentTransaction.setTransactionDate(date);
				}

				if (updateTransactionDto.getAmount() != currentTransaction.getAmount()) {
					currentTransaction.setAmount(updateTransactionDto.getAmount());
				}

				if (updateTransactionDto.getIsIncome() != currentTransaction.getIsIncome()) {
					currentTransaction.setIsIncome(updateTransactionDto.getIsIncome());
				}

				if (updateTransactionDto.getNote() != currentTransaction.getNote()) {
					currentTransaction.setNote(updateTransactionDto.getNote());
				}

				if (updateTransactionDto.getIsRecurrent() != currentTransaction.getIsRecurrent()) {
					currentTransaction.setIsRecurrent(updateTransactionDto.getIsRecurrent());
				}

				if (updateTransactionDto.getDescription() != currentTransaction.getDescription()) {
					currentTransaction.setDescription(updateTransactionDto.getDescription());
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
				responseTransactionDto.setDescription(transaction.getDescription());
				result.add(responseTransactionDto);
			}
		}

		return result;
	}

	@Override
	public Transaction getTransactionById(Long id) {
		return find(id);
	}

	@Override
	public List<ResponseTransactionSummaryDto> getTransactionSummary(Long id, Long year, Long month) {
		List<Transaction> transactions = findAll().stream()
				.filter(f -> (f.getUser().getId().compareTo(id) == 0 && !f.getIsIncome()
						))
				.collect(Collectors.toList());
		List<Budget> budgets = budgetService.findAll();
		List<Budget> filteredBudgets = budgets.stream().filter(f -> (f.getUser().getId().intValue() == id.intValue() ))
		.collect(Collectors.toList());

		List<ResponseCategoryDto> allCategoriesByUser = categoryService.getAllCategoriesByUser(id);

		List<ResponseTransactionSummaryDto> rtsd = new ArrayList<>();
		int count = 1;
		for (ResponseCategoryDto responseCategoryDto : allCategoriesByUser) {
			ResponseTransactionSummaryDto obj = new ResponseTransactionSummaryDto();

			Double totalBudget = getTotalBudget(filteredBudgets, responseCategoryDto.getId(),year,month);
			Double totalExpenses = getTotalExpenses(transactions, responseCategoryDto.getId(),year,month);

			obj.setNo(Long.valueOf(count));
			obj.setCategory(responseCategoryDto.getId());
			obj.setBudget(totalBudget);
			obj.setTotalExpenses(totalExpenses);

			rtsd.add(obj);
			count++;

		}
		return rtsd;
	}

	private Double getTotalBudget(List<Budget> budgets, Long categoryId, Long year, Long month) {
		double total = 0d;
		for (Budget budget : budgets) {
			if (budget.getCategory().getId().intValue() == categoryId.intValue()) {
				Long ye = budget.getYear().longValue();
				Long mo = budget.getMonth().longValue()	;			
				if(ye.compareTo(year) == 0 && mo.compareTo(month) == 0) {
					total = total + budget.getAmount().doubleValue();
				}
				
			}
		}
		return total;
	}

	private Double getTotalExpenses(List<Transaction> transactions, Long categoryId, Long year, Long month) {
		double total = 0d;
		for (Transaction transaction : transactions) {
			if (transaction.getCategory().getId().intValue() == categoryId.intValue()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(transaction.getTransactionDate());
				if((year.intValue() == cal.get(Calendar.YEAR) && month.intValue()== cal.get(Calendar.MONTH) + 1) || transaction.getIsRecurrent().booleanValue()) {
					total = total + transaction.getAmount();
				}
			}
		}
		return total;
	}
}
