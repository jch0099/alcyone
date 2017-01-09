package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.User;

@Repository
public class UserDao extends BaseDaoWithQuery<User, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected UserDao() {
		super(User.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
