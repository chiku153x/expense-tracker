package com.iit.asdcw2.expensetracker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iit.asdcw2.base.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(tags = { "Transaction API" })
@Controller
@RequestMapping("/api/transaction")
public class TransactionsController extends BaseController {

	@ApiOperation("Gets all Transactions")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getAllTransactions", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getAllTransactions( HttpServletRequest request) throws Exception {
		List<Map<String, String>> al = new ArrayList<>();
		Map<String,String> m = new HashMap<String,String>();
		m.put("AAAA", "BB");
		al.add(m);
		
		Map<String,String> m2 = new HashMap<String,String>();
		m2.put("ppp", "aaa");
		al.add(m)2;
		return new ResponseEntity<>(al, HttpStatus.OK);
	}

}
