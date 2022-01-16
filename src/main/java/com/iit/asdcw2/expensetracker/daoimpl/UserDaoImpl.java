package com.iit.asdcw2.expensetracker.daoimpl;

import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.UserDao;
import com.iit.asdcw2.expensetracker.domain.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

}
