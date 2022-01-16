package com.iit.asdcw2.expensetracker.dao;

import java.util.List;

import org.hibernate.Session;

public interface GenericDao<E, I> {
	public Session getCurrentSession();

	public List<E> findAll();

	public List<E> findAll(Class<E> entityObjectClass);

	public E find(I id);

	public E find(I id, Class<E> entityObjectClass);

	public E create(E e);

	public E create(E e, Class<E> entityObjectClass);

	public List<E> saveAll(Iterable<E> e);

	public void update(E e);

	public void update(E e, Class<E> entityObjectClass);

	public void saveOrUpdate(E e);

	public void saveOrUpdate(E e, Class<E> entityObjectClass);

	public void delete(E e);

	public void flush();
}
