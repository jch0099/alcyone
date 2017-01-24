package com.xushi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Friend_link;
import com.xushi.service.Friend_linkService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/friend_link/*")
public class AdminFriendLinkController extends BaseController{
	
	@Autowired Friend_linkService friend_linkService;
	
	@RequestMapping("/list")
	public void list(Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		Page<Friend_link> page = friend_linkService.findFriend_linkPage(pr);
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Friend_link fl = friend_linkService.getFriend_link(id);
			request.setAttribute("item", fl);
		}
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_edit(Friend_link friend_link,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			friend_linkService.saveFriend_link(friend_link);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
	
	@RequestMapping("/ajax_updateStatus")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_updateStatus(Integer _action_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			if( null != _action_id ) {
				Friend_link friend_link = friend_linkService.getFriend_link(_action_id);
				friend_link.setStatus(3-friend_link.getStatus());
				friend_linkService.saveFriend_link(friend_link);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
}
