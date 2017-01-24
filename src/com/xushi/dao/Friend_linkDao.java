package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Friend_link;

@Repository
public class Friend_linkDao extends BaseDaoWithQuery<Friend_link, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected Friend_linkDao() {
		super(Friend_link.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
