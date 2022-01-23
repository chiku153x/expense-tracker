package com.iit.asdcw2.expensetracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iit.asdcw2.base.controller.BaseController;
import com.iit.asdcw2.expensetracker.domain.Session;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.dto.UserLoginDto;
import com.iit.asdcw2.expensetracker.dto.UserLoginResponseDto;
import com.iit.asdcw2.expensetracker.service.SessionService;
import com.iit.asdcw2.expensetracker.service.UserService;
import com.iit.asdcw2.util.AppDate;
import com.iit.asdcw2.util.AppSecurity;
import com.iit.asdcw2.util.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "User API" })
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;

	@ApiOperation("Login User")
	@ApiResponses(value = { @ApiResponse(code = 200, response = List.class, message = "User logged in successgully"),
			@ApiResponse(code = 401, response = String.class, message = "Something went worng") })
	@PostMapping(value = "/login", consumes = { "application/json; charset=UTF-8" }, produces = {
			"application/json; charset=UTF-8" })
	public ResponseEntity<Object> userLogin(HttpServletRequest request, @RequestBody UserLoginDto userloginDto)
			throws Exception {
        AppDate appDateInstance = AppDate.getInstance();
		String userName = userloginDto.getUserName();
		String password = userloginDto.getPassword();
		if (userName.trim().equals("") || password.trim().equals("")) {
			return new ResponseEntity<>("Username or password is invalid", HttpStatus.BAD_REQUEST);
		}

		String hashPassword = AppSecurity.getHash(password);
		User user = userService.login(userName, hashPassword);
		if (user != null) {
			String token = AppSecurity.getHash(userName + password);
			UserLoginResponseDto userloginResponseDto = new UserLoginResponseDto(token,
					user.getFirstName() + " " + user.getLastName(), user.getId());
			sessionService.save(new Session(user, token, appDateInstance.now()));
			return new ResponseEntity<>(userloginResponseDto, HttpStatus.OK);
		}

		return new ResponseEntity<>(ResponseMessage.message("Login failed due to invalid username or password"),
				HttpStatus.UNAUTHORIZED);
	}

}
