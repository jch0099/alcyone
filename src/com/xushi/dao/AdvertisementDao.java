package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.Advertisement;

@Repository
public class AdvertisementDao extends BaseDaoWithQuery<Advertisement, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected AdvertisementDao() {
		super(Advertisement.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
