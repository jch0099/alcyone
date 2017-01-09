package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.QueryWhere;
import com.xushi.dao.FileLogDao;
import com.xushi.entity.File_log;
import com.xushi.entity.User;
import com.xushi.service.FileLogService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class FileLogServiceImpl implements FileLogService{
	@Autowired FileLogDao fileLogDao;
	
	@Override
	@Transactional
	public boolean check(User user) {
		String date = DateTimeUtil.getCurDate();
		delete(user, date);
		QueryWhere where = new QueryWhere();
		where.and("user", user);
		where.and("date", date);
		File_log log = fileLogDao.getByWhere(where);
		if( null == log ) {
			log = new File_log();
			log.setUser(user);
			log.setDate(date);
			log.setFile_num(1);
			fileLogDao.save(log);
			return true;
		}else {
			if( log.getFile_num() >= 10 ) return false;
			log.setFile_num(NumberUtil.toInt(log.getFile_num()) + 1);
			fileLogDao.update(log);
			return true;
		}
	}
	
	private void delete(User user,String date) {
		QueryWhere where = new QueryWhere();
		where.and("user", user);
		where.andNotLikeAll("date", date);
		List<File_log> list = fileLogDao.findByWhere(where);
		fileLogDao.defaultSort(list);
	}
}
