package com.iit.asdcw2.expensetracker.daoimpl;

import org.springframework.stereotype.Repository;

import com.iit.asdcw2.expensetracker.dao.SessionDao;
import com.iit.asdcw2.expensetracker.domain.Session;

@Repository("sessionDao")
public class SessionDaoImpl extends GenericDaoImpl<Session, Long> implements SessionDao {

	public SessionDaoImpl() {
		super(Session.class);
	}

}
