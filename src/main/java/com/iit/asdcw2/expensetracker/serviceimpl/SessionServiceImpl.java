package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.dao.SessionDao;
import com.iit.asdcw2.expensetracker.domain.Session;
import com.iit.asdcw2.expensetracker.service.SessionService;

@Service("sessionService")
public class SessionServiceImpl extends GenericServiceImpl<Session, Long> implements SessionService {

	@Autowired
	private SessionDao sessionDao;

	private Random random = null;

	public SessionServiceImpl() {
		this.random = new Random();
	}

	@Autowired
	public SessionServiceImpl(GenericDao<Session, Long> genericDao) {
		super(genericDao);
	}

}
