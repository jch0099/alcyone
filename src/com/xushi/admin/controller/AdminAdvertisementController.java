package com.xushi.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;


@Controller
@RequestMapping("/admin/advertisement/*")
public class AdminAdvertisementController extends BaseController{
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request){
		
	}
}
