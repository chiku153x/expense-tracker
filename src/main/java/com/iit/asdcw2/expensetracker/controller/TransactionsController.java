package com.iit.asdcw2.expensetracker.controller;

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
import com.iit.asdcw2.expensetracker.dto.CreateTransactionDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
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
		return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
	}

	@ApiOperation("Gets Transaction by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getTransactionById", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getTransactionById(HttpServletRequest request) throws Exception {
		return new ResponseEntity<>(transactionService.find(1L), HttpStatus.OK);
	}

	@ApiOperation("Creates Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Transaction is created"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/createTransaction", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> createTransactions(HttpServletRequest request,
			@RequestBody CreateTransactionDto createTransactionDto) throws Exception {

		Boolean isCreated = transactionService.addTransaction(createTransactionDto);

		if (isCreated) {
			return new ResponseEntity<>(ResponseMessage.message("Transaction is Saved"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Transaction is not Saved"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Update Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Transactions are updated"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/updateTransaction", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> updateTransactions(HttpServletRequest request,
			@RequestBody UpdateTransactionDto updateTransactionDto) throws Exception {

		Boolean isUpdated = transactionService.updateTransaction(updateTransactionDto);
		if (isUpdated) {
			return new ResponseEntity<>(ResponseMessage.message("Transaction is updated"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Transaction is not updated"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Delete Transaction")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Transaction is deleted"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/deleteTransaction", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> deleteTransactions(HttpServletRequest request,
			@RequestBody DeleteTransactionDto deleteTransactionDto) throws Exception {

		Boolean isDeleted = transactionService.removeTransaction(deleteTransactionDto);
		if (isDeleted) {
			return new ResponseEntity<>(ResponseMessage.message("Transactions are deleted"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Transactions are deleted"), HttpStatus.BAD_REQUEST);

	}

}
