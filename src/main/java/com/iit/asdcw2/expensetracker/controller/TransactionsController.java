package com.iit.asdcw2.expensetracker.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iit.asdcw2.base.controller.BaseController;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateTransactionDto;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.TransactionService;
import com.iit.asdcw2.expensetracker.service.UserService;
import com.iit.asdcw2.util.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Transaction API" })
@RestController
@RequestMapping("/api/transaction")
public class TransactionsController extends BaseController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@ApiOperation("Gets all Transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getAllTransactions", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getAllTransactions(HttpServletRequest request) throws Exception {
		List<Transaction> allTransactions = transactionService.findAll();
		return new ResponseEntity<>(allTransactions, HttpStatus.OK);
	}

	@ApiOperation("Creates Transaction")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = List.class, message = "Cases created successfully from JSON"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/createTransaction", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> creatTransactions(HttpServletRequest request,
			@RequestBody List<CreateTransactionDto> createTransactionDto) throws Exception {

		createTransactionDto.forEach(f -> {
			Transaction tr = new Transaction();
			User user = userService.find(f.getUser());
			Category category = categoryService.find(f.getCategory());
			Date date = AppDate.getDatefromString(f.getTransactionDate());
			tr.setUser(user);
			tr.setAmount(f.getAmount());
			tr.setIsIncome(f.getIsIncome());
			tr.setCategory(category);
			tr.setTransactionDate(date);
			transactionService.saveOrUpdate(tr);
		});

		return new ResponseEntity<>(ResponseMessage.message("Transactions are Saved"), HttpStatus.OK);

	}

}
