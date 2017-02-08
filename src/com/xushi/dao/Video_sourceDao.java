package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Video_source;

@Repository
public class Video_sourceDao extends BaseDaoWithQuery<Video_source, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected Video_sourceDao() {
		super(Video_source.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
