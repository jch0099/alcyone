package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Video_user;

@Repository
public class Video_userDao extends BaseDaoWithQuery<Video_user, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected Video_userDao() {
		super(Video_user.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
