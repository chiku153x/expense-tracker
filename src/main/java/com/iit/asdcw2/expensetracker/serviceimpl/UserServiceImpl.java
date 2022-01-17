package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.dao.UserDao;
import com.iit.asdcw2.expensetracker.domain.User;
import com.iit.asdcw2.expensetracker.service.UserService;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserDao userDao;

	private Random random = null;

	public UserServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public UserServiceImpl(GenericDao<User, Long> genericDao) {
		super(genericDao);
	}

	@Override
	public User login(String userName, String password) {
		List<User> allUsers = userDao.findAll();
		List<User> matchingUsers = allUsers.stream()
				.filter(f -> f.getUsername().equals(userName) && f.getPassword().equals(password))
				.collect(Collectors.toList());
		if (matchingUsers.size() != 0) {
			return matchingUsers.get(0);
		}
		return null;
	}

}
