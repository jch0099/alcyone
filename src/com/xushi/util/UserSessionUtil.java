package com.xushi.util;

import javax.servlet.http.HttpServletRequest;

import com.xushi.entity.User;


/**
 * 关于用户session操作
 * 
 * @author root
 * 
 */
public class UserSessionUtil {

	public static void setUser(User user, HttpServletRequest request) {
		request.getSession().setAttribute("_user", user);
	}

	public static User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("_user");
	}

	public static void removeUser(HttpServletRequest request) {
		request.getSession().removeAttribute("_user");
	}
}