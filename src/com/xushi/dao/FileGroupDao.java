package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.File_group;

@Repository
public class FileGroupDao extends BaseDaoWithQuery<File_group, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected FileGroupDao() {
		super(File_group.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
