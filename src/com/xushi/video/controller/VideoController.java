package com.xushi.video.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.core.page.Sort;
import com.xushi.core.util.ResponseUtil;
import com.xushi.entity.Advertisement;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.service.AdvertisementService;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.NumberUtil;
import com.xushi.util.StringUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.file.FileUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.security.MD5MixUtil;
import com.xushi.util.system.Const;
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
	@Autowired AdvertisementService adService;
	
	/**    视频      **/
	@RequestMapping("/index")
	public void index(Integer pageno,Integer sorttype, HttpServletRequest request){
		try {
			List<Advertisement> ads = adService.findAdvertisement(1, 1);
			request.setAttribute("ads", ads);
			
			PageRequest pr = new PageRequest(pageno, 30);
			Sort sort = new Sort(false, "create_time");
			if( NumberUtil.toInt(sorttype) == 1 ) {
				sort = new Sort(false, "read_num","create_time");
			}
			pr.setSort(sort);
			Page<Video> page = videoService.findVideoPage(null,null,1,pr);
			/*List<Video> list = page.getResults();
			if( null != list ) for (Video video : list) {
				//String img = StringUtil.toString(video.getImg());
				//if( !img.startsWith("http") ) video.setImg(Global.WebPath+"images/404pic.png");
				//if( !img.startsWith("http") ) video.setImg(Global.WebPath+"uploads/"+video.getImg());
			}*/
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/play")
	public String index(Integer id, HttpServletRequest request){
		try {
			List<Advertisement> ads = adService.findAdvertisement(2, 1);
			List<Advertisement> adtop = new ArrayList<Advertisement>();
			List<Advertisement> adbottom = new ArrayList<Advertisement>();
			if( null != ads ) for (int i = 0; i < ads.size(); i++) {
				Advertisement ad = ads.get(i);
				if( i <= ads.size()/2 ) adtop.add(ad);
				else adbottom.add(ad);
			}
			request.setAttribute("adtop", adtop);
			request.setAttribute("adbottom", adbottom);
			
			User user = UserSessionUtil.getVideoUser(request);
			if( null != id ) {
				Video video = videoService.getVideo(id);
				boolean c = videoService.checkVideo(user,video);
				video.setRead_num(NumberUtil.toInt(video.getRead_num())+1);
				videoService.saveVideo(video);
				//handleVideoUrl(video);
				if( !c ) video.setUrl("-no-auth");
				request.setAttribute("video", video);
			}
			request.getSession().setAttribute("play_id", NumberUtil.toInt(id) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/video/play";
	}
	@RequestMapping("/get_video/{id}.{ext}")
	public void get_video(@PathVariable Integer id,@PathVariable String ext, HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			if( null == id || null == ext ) throw new DaoException("参数错误");
			Integer temp_id = (Integer) request.getSession().getAttribute("play_id");
			if( NumberUtil.toInt(id) != NumberUtil.toInt(temp_id) ) throw new DaoException("不可下载");
			request.getSession().removeAttribute("play_id");
			Video v = videoService.getVideo(id);
			if( null == v ) throw new DaoException("未找到视频");
			String filepath = Const.VIDEO_FLODER_ROOT + v.getUrl();
			if ( !FileUtil.exists(Const.VIDEO_FLODER_ROOT+v.getUrl()) ) throw new DaoException("站内视频文件不存在");
			User user = UserSessionUtil.getVideoUser(request);
			boolean c = videoService.checkVideo(user,v);
			if( !c ) throw new DaoException("无权限观看该视频");
			File file = new File(filepath);
			ResponseUtil.outMp4(response, file, "alcyone.mp4");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/video/index");
	}
	private void handleVideoUrl(Video video) {
		String url = StringUtil.toString(video.getUrl());
		if( url.startsWith("<iframe") && url.endsWith("</iframe>") ) return;
		if( url.startsWith("<embed") && url.endsWith("</embed>") ) return;
		video.setUrl("");
	}
	/**    视频      **/
	/**    用户      **/
	@RequestMapping("/reg")
	public void reg(HttpServletRequest request){
	}
	@RequestMapping("/ajax_reg")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_reg(String account,String password,String brithday,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			if( StringUtil.isEmpty(account) || StringUtil.isEmpty(password) ) throw new DaoException("帐号密码必须填写");
			if( StringUtil.isEmpty(brithday) ) throw new DaoException("生日必须填写");
			User user = userService.getUserByAccount(account);
			if( null != user ) throw new DaoException("帐号已存在");
			user = new User();
			user.setAccount(account);
			user.setPassword(MD5MixUtil.md5Mix(password));
			user.setBrithday(brithday);
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
	@RequestMapping("/forgetpwd")
	public void forgetpwd(HttpServletRequest request){
	}
	@RequestMapping("/ajax_forgetpwd")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_forgetpwd(String account,String password,String brithday,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			if( StringUtil.isEmpty(account) || StringUtil.isEmpty(password) ) throw new DaoException("帐号密码必须填写");
			if( StringUtil.isEmpty(brithday) ) throw new DaoException("生日必须填写");
			User user = userService.getUserByAccount(account);
			if( null == user ) throw new DaoException("帐号不存在");
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
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserSessionUtil.removeVideoUser(request);
		response.sendRedirect("index");
	}
	/**    用户     **/
}
