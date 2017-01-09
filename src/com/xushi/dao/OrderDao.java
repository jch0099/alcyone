package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Order;

@Repository
public class OrderDao extends BaseDaoWithQuery<Order, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected OrderDao() {
		super(Order.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
