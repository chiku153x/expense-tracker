package com.iit.asdcw2.expensetracker.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iit.asdcw2.expensetracker.dao.GenericDao;
import com.iit.asdcw2.expensetracker.service.GenericService;

@Service("genericService")
public class GenericServiceImpl<E, I> implements GenericService<E, I> {

	protected GenericDao<E, I> genericDao;

	public GenericServiceImpl() {

	}

	public GenericServiceImpl(GenericDao<E, I> genericDao) {
		super();
		this.genericDao = genericDao;
	}

	@Override
	@Transactional(transactionManager = "transactionManager", readOnly = true)
	public E find(I id) {
		return genericDao.find(id);
	}

	@Override
	@Transactional("transactionManager")
	public E save(E e) {
		return genericDao.create(e);
	}

	@Override
	@Transactional("transactionManager")
	public Iterable<E> saveAll(Iterable<E> e) {
		return genericDao.saveAll(e);
	}

	@Override
	@Transactional("transactionManager")
	public void saveOrUpdate(E e) {
		genericDao.saveOrUpdate(e);
	}

	@Override
	@Transactional("transactionManager")
	public void delete(E e) {
		genericDao.delete(e);
	}

	@Override
	@Transactional(transactionManager = "transactionManager", readOnly = true)
	public List<E> findAll() {
		return genericDao.findAll();
	}

	@Override
	@Transactional("transactionManager")
	public void update(E e) {
		genericDao.update(e);
	}

	@Override
	@Transactional("transactionManager")
	public void flush() {
		genericDao.flush();
	}

}
