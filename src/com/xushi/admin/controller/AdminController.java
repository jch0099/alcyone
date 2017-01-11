package com.xushi.admin.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xushi.core.controller.BaseController;
import com.xushi.entity.User;
import com.xushi.service.UserService;
import com.xushi.util.StringUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.VerifyImage;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.security.MD5MixUtil;
import com.xushi.util.system.Const;
import com.xushi.util.system.PlatformMenuUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController extends BaseController {
	@Autowired
	ServletContext context;
	@Autowired UserService userService;
	
	@RequestMapping("/login")
	public void index(HttpServletRequest request){
		
	}
	/**
	 * 登陆页
	 * @throws Exception 
	 */
	@RequestMapping("/ajax_login") 
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajaxLogin(String account, String pwd, String checkcode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultVo resultVo = new ResultVo();
		
		try {
			User user = userService.getUserByAccount(account);
		
			if(null != checkcode && checkcode.equals(UserSessionUtil.getRandCode(request))){
				if (null != user && user.getPassword().equals(MD5MixUtil.md5Mix(pwd))&& 1 == user.getType()) {
					
					JsonObject menu = PlatformMenuUtil.getMenu(user, request);//得到第一个菜单url
					JsonArray ja = menu.get("item").getAsJsonArray();
					String url = "";
					if(null != ja && ja.size() > 0){
						JsonElement je = ja.get(0);
						JsonObject cmenu1 = je.getAsJsonObject();
						JsonArray menus2 = cmenu1.get("item").getAsJsonArray();
						if(null != menus2 && menus2.size() > 0){
							JsonElement cje2 = menus2.get(0);
							JsonObject cmenu2 = cje2.getAsJsonObject();
							JsonArray menus3 = cmenu2.get("item").getAsJsonArray();
							if(null != menus3 && menus3.size() > 0){
								JsonElement cje3 = menus3.get(0);
								JsonObject cmenu3 = cje3.getAsJsonObject();
								url = cmenu3.get("url").getAsString();
							}
						}
					}
					
					if(StringUtil.isEmpty(url)){
						resultVo = new ResultVo(false,"用戶沒有任何相應權限");
					}else{
						resultVo = new ResultVo(true,url);
						UserSessionUtil.setAdminUser(user, request);
					}
				
				}else{
					VerifyImage image = new VerifyImage();
					image.creatImage();
					UserSessionUtil.setRandCode(checkcode, request);
					resultVo = new ResultVo(false,"用戶名密碼錯誤");
				}
			}else{
				VerifyImage image = new VerifyImage();
				image.creatImage();
				UserSessionUtil.setRandCode(checkcode, request);
				resultVo = new ResultVo(false,"驗證碼錯誤");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
	/**
	 * 登出
	 * @throws IOException 
	 */
	@RequestMapping("/logout")  
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserSessionUtil.removeAdminUser(request);
		response.sendRedirect("login");
	}

	/**
	 * 修改密码
	 */
	@RequestMapping("/changepassword")  
	public void changepassword(HttpServletRequest request) {
	}
	/**
	 * 用户密码修改处理
	 */
	@RequestMapping("/ajax_changepassword") 
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajaxChangePass(String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			User user = UserSessionUtil.getAdminUser(request);
			if(null != user){
				try {
					user.setPassword(MD5MixUtil.md5Mix(password));
					userService.saveUser(user);
				} catch (Exception e) {
					e.printStackTrace();
					resultVo = new ResultVo(true,"更新失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
	/**
	 * 验证码
	 */
	@RequestMapping("verifyimage")
	public void verifyimage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession();
			VerifyImage image = new VerifyImage();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			ImageIO.write(image.creatImage(), "JPEG", response.getOutputStream());
			session.setAttribute(Const.SESSION_KEY_RAND, image.sRand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 菜单
	 * @throws Exception 
	 */
	@RequestMapping("/loadmenu")  
	public void loadMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = UserSessionUtil.getAdminUser(request);
		writeJson(response, PlatformMenuUtil.getSessionMenu(user, request).toString());
	}
}
