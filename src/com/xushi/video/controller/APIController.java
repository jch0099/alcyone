package com.xushi.video.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.service.OrderService;

/**
 * 
 * @author penken
 */
@Controller
@RequestMapping("/api/*")
public class APIController extends BaseController {
	
	@Autowired OrderService orderService;
	
	@RequestMapping("/callback")
	public void callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("kkkkkkkkkkkk");
		writeJson(response, "[]");
	}
}
