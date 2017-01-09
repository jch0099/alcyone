package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Pay_config;

@Repository
public class Pay_configDao extends BaseDaoWithQuery<Pay_config, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected Pay_configDao() {
		super(Pay_config.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
