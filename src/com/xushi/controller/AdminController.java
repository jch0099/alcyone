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
import com.xushi.util.NumberUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/admin/*")
public class AdminController extends BaseController {
	
	@Autowired UserService userService;
	@Autowired FileGroupService fileGroupService;
	
	@RequestMapping("/share")
	public String share(HttpServletRequest request,HttpServletResponse response) throws Exception {
		User user = UserSessionUtil.getUser(request);
		if( null == user || null== user.getType() || user.getType() != 1 ) return "redirect:/nologin/index";
		return "/admin/share";
	}
	
	@RequestMapping("/ajax_share")
	public String ajax_share(String account,Integer count,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"分享成功");
		try {
			User sourceUser = UserSessionUtil.getUser(request);
			if( null == sourceUser || NumberUtil.toInt(sourceUser.getType()) != 1 ) throw new DaoException("沒有權限");
			if( null == count || count < 0 ||null == account ) throw new DaoException("參數錯誤");
			User user = userService.getUserByAccount(account);
			if( null == user ) throw new DaoException("非法帳號");
			//List<File_group> list = fileGroupService.findFileGroup(sourceUser,count);
			/*if( null != list ) {
				for (File_group file_group : list) {
					System.out.println(file_group.getId());
					file_group.setId(null);
					file_group.setUser(user);
					file_group.setStatus(1);
					fileGroupService.saveFileGroup(file_group);
				}
			}*/
			for (int i = 0; i < count; i++) {
				File_group item = new File_group();
				item.setUser(user);
				fileGroupService.saveFileGroup(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
		return "/admin/share";
	}
	
}
