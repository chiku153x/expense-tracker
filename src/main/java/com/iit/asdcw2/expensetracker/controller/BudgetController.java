package com.iit.asdcw2.expensetracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iit.asdcw2.base.controller.BaseController;
import com.iit.asdcw2.expensetracker.domain.Budget;
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.CreateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteBudgetDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateBudgetDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
import com.iit.asdcw2.expensetracker.service.BudgetService;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;
import com.iit.asdcw2.util.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Budget API" })
@RestController
@RequestMapping("/api/budget")
public class BudgetController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	BudgetService budgetService;

	@ApiOperation("Gets all Budgets")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getAllBudgets/{uid}", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getAllBudgets(HttpServletRequest request, @PathVariable Long uid,
			@RequestHeader(value = "X-Auth-Token") String authToken) throws Exception {
		User user = userService.find(uid);
		if (user != null) {
			return new ResponseEntity<>(budgetService.getAllBudgetsByUser(uid), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Invalid request"), HttpStatus.BAD_REQUEST);
	}

	@ApiOperation("Gets budget by id")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getBudgetById/{id}", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getBudgetById(@PathVariable Long id, HttpServletRequest request) throws Exception {
		if (id != null && id != 0) {
			Budget budget = budgetService.find(id);
			return new ResponseEntity<>(budget, HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Invalid query"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Creates Budget")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Budget is created"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/createBudget", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> createBudget(HttpServletRequest request,
			@RequestBody CreateBudgetDto createBudgetDto) throws Exception {
		Boolean isSuccessfull = budgetService.addBudget(createBudgetDto);
		if (isSuccessfull) {
			return new ResponseEntity<>(ResponseMessage.message("Budget is saved"), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseMessage.message("Budget is not Saved"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Update Budget")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Budget updated"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/updateBudget", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> updateBudget(HttpServletRequest request,
			@RequestBody UpdateBudgetDto updateBudgetDto) throws Exception {

		Boolean isUpdated = budgetService.updateBudget(updateBudgetDto);
		if (isUpdated) {
			return new ResponseEntity<>(ResponseMessage.message("Budget is updated"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Budget is not updated"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Delete Budget")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Budget is deleted"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/deleteBudget", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> deleteBudget(HttpServletRequest request,
			@RequestBody DeleteBudgetDto deleteBudgetDto) throws Exception {

		Boolean isDeleted = budgetService.removeBudget(deleteBudgetDto);
		if (isDeleted) {
			return new ResponseEntity<>(ResponseMessage.message("Budget is deleted"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Budget is not deleted"), HttpStatus.BAD_REQUEST);

	}

}
