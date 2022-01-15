package com.iit.asdcw2.generic.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iit.asdcw2.generic.dao.GenericDao;

@Repository("genericDao")
@Transactional("transactionManager")
public class GenericDaoImpl<E, I extends Serializable> implements GenericDao<E, I> {

	private Class<E> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<E> findAll() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityClass);
		Root<E> root = query.from(entityClass);
		query.select(root);
		return getCurrentSession().createQuery(query).getResultList();
	}

	@Override
	public List<E> findAll(Class<E> entityObjectClass) {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityObjectClass);
		Root<E> root = query.from(entityObjectClass);
		query.select(root);
		return getCurrentSession().createQuery(query).getResultList();
	}

	@Override
	public E find(I id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	@Override
	public E find(I id, Class<E> entityObjectClass) {
		return (E) getCurrentSession().get(entityObjectClass, id);
	}

	@Override
	public E create(E e) {
		Serializable id = getCurrentSession().save(e);
		return (E) getCurrentSession().get(entityClass, id);
	}

	@Override
	public E create(E e, Class<E> entityObjectClass) {
		Serializable id = getCurrentSession().save(e);
		return (E) getCurrentSession().get(entityObjectClass, id);
	}

	@Override
	public List<E> saveAll(Iterable<E> e) {
		List<E> result = new ArrayList<E>();
		for (E entity : e) {
			Serializable id = getCurrentSession().save(entity);
			result.add((E) getCurrentSession().get(entityClass, id));
		}
		return result;
	}

	@Override
	public void update(E e) {
		getCurrentSession().update(e);
	}

	@Override
	public void update(E e, Class<E> entityObjectClass) {
		getCurrentSession().update(entityObjectClass.getName(), e);
	}

	@Override
	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	@Override
	public void saveOrUpdate(E e, Class<E> entityObjectClass) {
		getCurrentSession().saveOrUpdate(entityObjectClass.getName(), e);
	}

	@Override
	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}

}
