package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Image;

@Repository
public class ImageDao extends BaseDaoWithQuery<Image, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected ImageDao() {
		super(Image.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
