package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Video;

@Repository
public class VideoDao extends BaseDaoWithQuery<Video, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected VideoDao() {
		super(Video.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
