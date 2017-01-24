package com.xushi.data.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.entity.Friend_link;
import com.xushi.service.Friend_linkService;
import com.xushi.util.gson.JsonUtil;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/data/json/*")
public class JsonController extends BaseController {
	
	/*@Autowired VideoService videoService;
	@Autowired UserService userService;
	@Autowired OrderService orderService;
	@Autowired ConfigService configService;*/
	@Autowired Friend_linkService friend_linkService;
	
	@RequestMapping("/getFriend_link")
	public void getFriend_link(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<Friend_link> links = friend_linkService.findFriend_link(1);
			if( null != links ) for (Friend_link link : links) {
				Map<String, Object> map = new HashMap<String, Object>();
				list.add(map);
				map.put("name", link.getName());
				map.put("url", link.getUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(response, JsonUtil.toJson(list));
	}
}
