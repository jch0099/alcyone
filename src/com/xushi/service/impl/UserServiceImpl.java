package com.xushi.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.dao.UserDao;
import com.xushi.entity.User;
import com.xushi.service.UserService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserDao userDao;
	
	@Override
	public User getUserById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public User getUserByAccount(String account) {
		return userDao.getBy("account", account);
	}
	
	@Override
	@Transactional
	public void saveUser(User user) {
		if( NumberUtil.toInt(user.getId()) > 0 ) {
			userDao.update(user);
		}
		else {
			user.setCreate_date(DateTimeUtil.getCurDate());
			userDao.save(user);
		}
	}
}
