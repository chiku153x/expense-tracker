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
import com.iit.asdcw2.expensetracker.domain.Category;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.CreateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteCategoryDto;
import com.iit.asdcw2.expensetracker.dto.DeleteTransactionDto;
import com.iit.asdcw2.expensetracker.dto.UpdateCategoryDto;
import com.iit.asdcw2.expensetracker.dto.UpdateTransactionDto;
import com.iit.asdcw2.expensetracker.service.CategoryService;
import com.iit.asdcw2.expensetracker.service.UserService;
import com.iit.asdcw2.util.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Category API" })
@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@ApiOperation("Gets all Categories")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getAllCategories/{uid}", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getAllCategories(HttpServletRequest request, @PathVariable Long uid,
			@RequestHeader(value = "X-Auth-Token") String authToken) throws Exception {
		User user = userService.find(uid);
		if (user != null) {
			return new ResponseEntity<>(categoryService.getAllCategoriesByUser(uid), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Invalid request"), HttpStatus.BAD_REQUEST);
	}

	@ApiOperation("Gets category by id")
	@ApiResponses(value = { @ApiResponse(code = 200, response = String.class, message = "Operation successful"),
			@ApiResponse(code = 500, response = String.class, message = "TODO") })
	@GetMapping(value = "/getCategoryById/{id}", produces = { "application/json; charset=UTF-8" })
	public ResponseEntity<Object> getCategoryById(@PathVariable Long id, HttpServletRequest request) throws Exception {
		if (id != null && id != 0) {
			Category category = categoryService.find(id);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Invalid query"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Creates Category")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Category is created"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/createCategory", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> createCategories(HttpServletRequest request,
			@RequestBody CreateCategoryDto createCategoryDto) throws Exception {
		Boolean isSuccessfull = categoryService.addCategory(createCategoryDto);
		if (isSuccessfull) {
			return new ResponseEntity<>(ResponseMessage.message("Category is Saved"), HttpStatus.OK);
		}
		return new ResponseEntity<>(ResponseMessage.message("Category is not Saved"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Update Category")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Category updated"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/updateCategory", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> updateTransactions(HttpServletRequest request,
			@RequestBody UpdateCategoryDto updateCategoryDto) throws Exception {

		Boolean isUpdated = categoryService.updateCategory(updateCategoryDto);
		if (isUpdated) {
			return new ResponseEntity<>(ResponseMessage.message("Category is updated"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Category is not updated"), HttpStatus.BAD_REQUEST);

	}

	@ApiOperation("Delete Category")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "Category is deleted"),
			@ApiResponse(code = 401, response = String.class, message = "TODO") })
	@PostMapping(value = "/deleteCategory", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> deletecategory(HttpServletRequest request,
			@RequestBody DeleteCategoryDto deleteCategoryDto) throws Exception {

		Boolean isDeleted = categoryService.removeCategory(deleteCategoryDto);
		if (isDeleted) {
			return new ResponseEntity<>(ResponseMessage.message("Category is deleted"), HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Category is not deleted"), HttpStatus.BAD_REQUEST);

	}

}
