package com.iit.asdcw2.expensetracker.service;

import com.iit.asdcw2.expensetracker.domain.User;

public interface UserService extends GenericService<User, Long> {
	
	public User login(String userName, String password);

}
