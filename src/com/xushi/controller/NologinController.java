package com.xushi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.entity.File_group;
import com.xushi.entity.User;
import com.xushi.service.FileGroupService;
import com.xushi.service.UserService;
import com.xushi.util.StringUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.security.MD5Util;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/nologin/*")
public class NologinController extends BaseController {

	@Autowired UserService userService;
	@Autowired FileGroupService fileGroupService;

	@RequestMapping("/test")
	public void test() {
	}
	@RequestMapping("/index")
	public void index() {
	}
	@RequestMapping("/login")
	public void login() throws Exception {
	}
	@RequestMapping("/register")
	public void register() throws Exception {
	}
	@RequestMapping("/contact")
	public void contact() throws Exception {
	}
	
	@RequestMapping("/ajax_register")
	public void ajax_register(String account,String password,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"操作成功");
		try {
			User user = userService.getUserByAccount(account);
			_Log.info(account);
			if( null != user ) throw new DaoException("该帐号已被注册");
			if( StringUtil.toString(password).length() < 4 ) throw new DaoException("密码长度小于4");
			password = MD5Util.MD5Encode(password, null);
			user = new User();
			user.setAccount(account);
			user.setPassword(password);
			user.setType(2);
			userService.saveUser(user);
			UserSessionUtil.setUser(user,request);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/ajax_login")
	public void ajax_login(String account,String password,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"操作成功");
		try {
			User user = userService.getUserByAccount(account);
			if( null == user ) throw new DaoException("帐号不存在");
			String ck = MD5Util.MD5Encode(password, null);
			if( null != ck && ck.equals(user.getPassword()) ) {
				UserSessionUtil.setUser(user,request);
			}else {
				throw new DaoException("密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			UserSessionUtil.removeUser(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/nologin/index");
	}
	
	@RequestMapping("/see")
	public String see(String uuid,HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			if( null != uuid ) {
				File_group item = fileGroupService.getFileGroupByUUid(uuid);
				if( null == item ) response.sendRedirect(request.getContextPath()+"/nologin/wsee");
				request.setAttribute("item", item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/nologin/see";
	}
	@RequestMapping("/wsee")
	public void wsee(String uuid,HttpServletRequest request) throws Exception {
	}
}
