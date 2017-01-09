package com.xushi.service;

import com.xushi.entity.User;

public interface UserService {
	
	User getUserById(Integer id);
	
	User getUserByAccount(String account);
	
	void saveUser(User user);
}
