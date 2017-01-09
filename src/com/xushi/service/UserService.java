package com.xushi.service;

import java.util.List;

import com.xushi.entity.User;

public interface UserService {
	
	User getUserById(Integer id);
	
	User getUserByAccount(String account);
	
	void saveUser(User user);
	
	List<User> findUser(Integer type);
	
	void initAdminuser();
	
	/**
	 * 调度处理用户和视频
	 */
	void ScheduledHandle();
}
