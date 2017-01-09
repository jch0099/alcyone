package com.xushi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.xushi.core.dao.BaseDaoWithQuery;
import com.xushi.entity.File_log;

@Repository
public class FileLogDao extends BaseDaoWithQuery<File_log, Integer> {
		
	@PersistenceContext
	EntityManager em;

	protected FileLogDao() {
		super(File_log.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
