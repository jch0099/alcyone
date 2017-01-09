package com.xushi.util;

import javax.servlet.http.HttpServletRequest;

import com.xushi.entity.User;
import com.xushi.util.system.Const;


/**
 * 关于用户session操作
 * 
 * @author root
 * 
 */
public class UserSessionUtil {

	public static void setAdminUser(User user, HttpServletRequest request) {
		request.getSession().setAttribute(Const.SESSION_KEY_ADMIN_USER, user);
	}

	public static User getAdminUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute(Const.SESSION_KEY_ADMIN_USER);
	}

	public static void removeAdminUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_ADMIN_USER);
	}
	
	public static void setVideoUser(User user, HttpServletRequest request) {
		request.getSession().setAttribute(Const.SESSION_KEY_VIDEO_USER, user);
	}

	public static User getVideoUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute(Const.SESSION_KEY_VIDEO_USER);
	}

	public static void removeVideoUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Const.SESSION_KEY_VIDEO_USER);
	}
}