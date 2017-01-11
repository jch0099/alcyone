package com.xushi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xushi.core.dao.DaoException;
import com.xushi.core.dao.QueryWhere;
import com.xushi.dao.Pay_configDao;
import com.xushi.dao.UserDao;
import com.xushi.dao.Video_userDao;
import com.xushi.entity.User;
import com.xushi.entity.Video_user;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;
import com.xushi.util.security.MD5MixUtil;
import com.xushi.util.system.Const;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserDao userDao;
	@Autowired VideoService videoService;
	@Autowired Video_userDao video_userDao;
	@Autowired Pay_configDao pay_configDao;
	
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
		User temp = userDao.getBy("account", user.getAccount());
		if( null != temp && NumberUtil.toInt(temp.getId()) != NumberUtil.toInt(user.getId()) ) throw new DaoException("帐号不能重复!");
		if( NumberUtil.toInt(user.getId()) > 0 ) {
			userDao.update(user);
		} else {
			user.setCreate_time(DateTimeUtil.getCurDateTime());
			userDao.save(user);
		}
	}

	@Override
	@Transactional
	public void initAdminuser() {
		User user = getUserByAccount(Const.ADMINUSER_NAME);
		if( null != user ) return;
		user = new User();
		user.setAccount(Const.ADMINUSER_NAME);
		user.setPassword(MD5MixUtil.md5Mix(Const.ADMINUSER_PWD));
		user.setType(1);
		user.setEnd_date("9999-12-31");
		user.setCreate_time(DateTimeUtil.getCurDateTime());
		userDao.save(user);
	}

	@Override
	public List<User> findUser(Integer type) {
		if( null == type ) return userDao.findAll();
		return userDao.findBy("type", type);
	}

	@Override
	@Transactional
	public void ScheduledHandle() {
		String now = DateTimeUtil.getCurDate();
		QueryWhere where = new QueryWhere();
		where.and("end_date", "<", now);
		List<User> users = userDao.findByWhere(where);
		if( null != users ) for (User user : users) {
			user.setEnd_date(null);
			user.setType(2);
			userDao.update(user);
		}
		List<Video_user> list = video_userDao.findByWhere(where);
		if( null != list ) video_userDao.delete(list);
	}
}
