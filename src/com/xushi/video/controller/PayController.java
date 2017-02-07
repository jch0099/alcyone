package com.xushi.video.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.util.Global;
import com.xushi.entity.Order;
import com.xushi.entity.Pay_config;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.service.ConfigService;
import com.xushi.service.OrderService;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.NumberUtil;
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
	public String pay_vip(Integer length,Integer count,HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			if( null == length || null == count ) throw new DaoException("参数错误");
			User user = UserSessionUtil.getVideoUser(request);
			Order order = new Order();
			Pay_config pay_config = configService.getPay_configByLength(length);
			Float payAmount = pay_config.getAmount()*count;
			order.setAmount(payAmount);
			UUID uuid = UUID.randomUUID();
			order.setOrder_num(uuid.toString());
			order.setMonth_length(length*count);
			order.setType(2);
			order.setUser_id(user.getId());
			orderService.saveOrder(order);
			request.setAttribute("optEmail", Global.alipay_account);
			request.setAttribute("payAmount", payAmount);
			request.setAttribute("title", order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/video/pay/pay";
	}
	
	@RequestMapping("/pay_video")
	public String pay_video(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		try {
			if( null == id ) throw new DaoException("参数错误");
			User user = UserSessionUtil.getVideoUser(request);
			Video video = videoService.getVideo(id);
			if( video.getIs_free() == 1 || NumberUtil.toFloat(video.getAmount()) <= 0 ) throw new DaoException("该视频是免费的");
			Float payAmount = video.getAmount();
			Order order = new Order();
			order.setAmount(payAmount);
			order.setVideo_id(video.getId());
			UUID uuid = UUID.randomUUID();
			order.setOrder_num(uuid.toString());
			order.setType(1);
			order.setUser_id(user.getId());
			orderService.saveOrder(order);
			request.setAttribute("optEmail", Global.alipay_account);
			request.setAttribute("payAmount", payAmount);
			request.setAttribute("title", order.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/video/pay/pay";
	}
	//支付
	@RequestMapping("/pay")
	public void pay(HttpServletRequest request) throws IOException{
		request.setAttribute("optEmail", Global.alipay_account);
		request.setAttribute("payAmount", "0.01");
		request.setAttribute("title", "打赏");
	}
	//打赏
	@RequestMapping("/pay_")
	public void pay_(HttpServletRequest request) throws IOException{
	}
	/**    支付      **/
}
