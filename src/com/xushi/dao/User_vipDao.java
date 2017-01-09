package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Video_user;

@Repository
public class User_vipDao extends BaseDaoWithQuery<Video_user, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected User_vipDao() {
		super(Video_user.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
