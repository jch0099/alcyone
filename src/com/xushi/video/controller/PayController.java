package com.xushi.video.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.entity.Order;
import com.xushi.entity.Pay_config;
import com.xushi.entity.User;
import com.xushi.service.ConfigService;
import com.xushi.service.OrderService;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.UserSessionUtil;

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
	
	@RequestMapping("/pay_vip")
	public void pay_vip(Integer length,Integer count,HttpServletRequest request) throws IOException{
		try {
			if( null == length || null == count ) throw new DaoException("参数错误");
			User user = UserSessionUtil.getVideoUser(request);
			Order order = new Order();
			order.setAmount(30.0f);
			UUID uuid = UUID.randomUUID();
			order.setOrder_num(uuid.toString());
			order.setTime_type(length*count);
			order.setType(2);
			order.setUser_id(user.getId());
			orderService.saveOrder(order);
			request.setAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**    支付      **/
}
