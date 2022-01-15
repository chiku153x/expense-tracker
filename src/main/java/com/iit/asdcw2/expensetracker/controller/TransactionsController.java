package com.iit.asdcw2.expensetracker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iit.asdcw2.base.controller.BaseController;
import com.iit.asdcw2.expensetracker.domain.Transaction;
import com.iit.asdcw2.expensetracker.dto.TransactionDto;
import com.iit.asdcw2.expensetracker.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Transaction API" })
@Controller
@RequestMapping("/api/transaction")
public class TransactionsController extends BaseController {
	
	@Autowired
	TransactionService transactionService;

	@ApiOperation("Gets all Transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getAllTransactions", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getAllTransactions(HttpServletRequest request) throws Exception {
		List<Map<String, String>> al = new ArrayList<>();
		Map<String, String> m = new HashMap<String, String>();
		m.put("AAAA", "BB");
		al.add(m);

		Map<String, String> m2 = new HashMap<String, String>();
		m2.put("ppp", "aaa");
		al.add(m2);
		return new ResponseEntity<>(al, HttpStatus.OK);
	}

	@ApiOperation("Creates Transaction")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = List.class, message = "Cases created successfully from JSON"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/createTransaction", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<List<Object>> createMultipleCaseSeedsFromJson(HttpServletRequest request,
			@RequestBody List<TransactionDto> caseDtos) throws Exception {
        
		transactionService.saveOrUpdate(new Transaction(100L,"jhdjf"));
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

}
