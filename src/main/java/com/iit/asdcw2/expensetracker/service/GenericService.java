package com.iit.asdcw2.expensetracker.service;

import java.util.List;

public interface GenericService<E, I> {
	public List<E> findAll();

	public E find(I id);

	public E save(E e);

	public Iterable<E> saveAll(Iterable<E> e);

	public void update(E e);

	public void saveOrUpdate(E e);

	public void delete(E e);

	public void flush();
}
