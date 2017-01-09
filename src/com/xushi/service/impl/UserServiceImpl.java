package com.xushi.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.DaoException;
import com.xushi.dao.UserDao;
import com.xushi.entity.User;
import com.xushi.service.UserService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;
import com.xushi.util.security.MD5Util;
import com.xushi.util.system.Const;

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
		User temp = userDao.getBy("order_num", user.getAccount());
		if( null != temp && NumberUtil.toInt(temp.getId()) != NumberUtil.toInt(user.getId()) ) throw new DaoException("帐号不能重复!");
		if( NumberUtil.toInt(user.getId()) > 0 ) {
			userDao.update(user);
		}
		else {
			user.setCreate_time(DateTimeUtil.getCurDateTime());
			userDao.save(user);
		}
	}

	@Override
	public void initAdminuser() {
		User user = getUserByAccount(Const.ADMINUSER_NAME);
		if( null != user ) return;
		user = new User();
		user.setAccount(Const.ADMINUSER_NAME);
		user.setPassword(MD5Util.MD5Encode(Const.ADMINUSER_PWD, null));
		user.setType(1);
		user.setCreate_time(DateTimeUtil.getCurDateTime());
		userDao.save(user);
		
	}
}
