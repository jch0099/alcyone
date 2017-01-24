package com.xushi.video.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.entity.Pay_config;
import com.xushi.service.ConfigService;
import com.xushi.service.OrderService;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/video/pay/*")
public class PayController extends BaseController {
	
	@Autowired VideoService videoService;
	@Autowired UserService userService;
	@Autowired OrderService orderService;
	@Autowired ConfigService configService;
	
	/**    支付      **/
	@RequestMapping("/index")
	public void index(HttpServletRequest request) throws IOException{
		List<Pay_config> configs = configService.findPay_config();
		request.setAttribute("configs", configs);
	}
	/**    支付      **/
}
