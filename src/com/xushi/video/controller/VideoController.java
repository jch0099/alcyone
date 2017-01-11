package com.xushi.video.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.core.page.Sort;
import com.xushi.core.util.Global;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.StringUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.security.MD5MixUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/video/*")
public class VideoController extends BaseController {
	
	@Autowired VideoService videoService;
	@Autowired UserService userService;
	
	/**    视频      **/
	@RequestMapping("/index")
	public void index(Integer pageno,String sortstr, HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		Sort sort = new Sort(false, "update_time");
		if( !StringUtil.isEmpty(sortstr) ) {
			sort = new Sort(false, sortstr,"update_time");
		}
		pr.setSort(sort);
		Page<Video> page = videoService.findVideoPage(null, null, pr);
		List<Video> list = page.getResults();
		if( null != list ) for (Video video : list) {
			String img = StringUtil.toString(video.getImg());
			if( !img.startsWith("http") ) video.setImg(Global.WebPath+"images/404pic.png");
		}
		request.setAttribute("page", page);
	}
	@RequestMapping("/play")
	public void index(Integer id, HttpServletRequest request){
		System.out.println(id);
	}
	/**    视频      **/
	/**    用户      **/
	@RequestMapping("/reg")
	public void reg(HttpServletRequest request){
		
	}
	@RequestMapping("/ajax_reg")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_reg(String account,String password,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			if( StringUtil.isEmpty(account) || StringUtil.isEmpty(password) ) throw new DaoException("帐号密码必须填写");
			User user = userService.getUserByAccount(account);
			if( null != user ) throw new DaoException("帐号已存在");
			user = new User();
			user.setAccount(account);
			user.setPassword(MD5MixUtil.md5Mix(password));
			userService.saveUser(user);
			UserSessionUtil.setVideoUser(user, request);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request){
		
	}
	@RequestMapping("/ajax_login")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_login(String account,String password,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			if( StringUtil.isEmpty(account) || StringUtil.isEmpty(password) ) throw new DaoException("帐号密码必须填写");
			User user = userService.getUserByAccount(account);
			if( null == user ) throw new DaoException("帐号不存在");
			if ( !user.getPassword().equals(MD5MixUtil.md5Mix(password)) ) throw new DaoException("密码错误");
			UserSessionUtil.setVideoUser(user, request);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	@RequestMapping("/changepwd")
	public void changepwd(HttpServletRequest request){
		
	}
	@RequestMapping("/ajax_changepwd")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_changepwd(String oldpassword,String newpassword,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			if( StringUtil.isEmpty(oldpassword) || StringUtil.isEmpty(newpassword) ) throw new DaoException("新旧密码必须填写");
			User user = UserSessionUtil.getVideoUser(request);
			if( null == user ) throw new DaoException("当前没有帐号");
			if ( !user.getPassword().equals(MD5MixUtil.md5Mix(oldpassword)) ) throw new DaoException("旧密码错误");
			user.setPassword(MD5MixUtil.md5Mix(newpassword));
			userService.saveUser(user);
			UserSessionUtil.setVideoUser(user, request);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserSessionUtil.removeVideoUser(request);
		response.sendRedirect("index");
	}
	/**    用户     **/
	/**    支付      **/
	/**    支付      **/
}
