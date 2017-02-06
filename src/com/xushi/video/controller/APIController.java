package com.xushi.video.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.util.Global;
import com.xushi.entity.Order;
import com.xushi.entity.vo.AlipayVo;
import com.xushi.service.OrderService;
import com.xushi.util.NumberUtil;
import com.xushi.util.date.DateTimeUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.http.HttpUtil;

/**
 * 
 * @author penken
 */
@Controller
@RequestMapping("/api/*")
public class APIController extends BaseController {
	
	@Autowired OrderService orderService;
	
	@RequestMapping("/callback")
	public void callback(AlipayVo vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println(JsonUtil.toJson(vo));
		String msg = "Fail";
		try {
			int id = NumberUtil.toInt(vo.getTitle());
			if( 0 == id ) throw new DaoException("订单号错误");
			Order order = orderService.getOrder(id);
			if( null == order ) throw new DaoException("未找到订单");
			if( order.getStatus() != 2 ) throw new DaoException("订单已完成");
			if( order.getAmount() != NumberUtil.toFloat(vo.getMoney()) ) throw new DaoException("额度不对");
			if( !Global.alipay_memo.equals(vo.getMemo()) ) throw new DaoException("效验未通过");
			//处理时间
			String pay_time = DateTimeUtil.formatDateTime(DateTimeUtil.getDate(vo.getPaytime(), "yyyy-MM-dd HH:mm:ss"))  ;
			vo.setPaytime(pay_time);
			order.setPay_time(vo.getPaytime());
			order.setPay_account(vo.getAlipay_account());
			order.setAlipay_order_num(vo.getTradeNo());
			order.setStatus(1);
			orderService.payOrder(order);
			//orderService.saveOrder(order);
			msg = "Success";
		} catch (DaoException e) {
			e.printStackTrace();
			msg = "IncorrectOrder";
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(response, msg);
	}
	
	public static void main(String[] args) throws IOException {
		String url = "http://127.0.0.1/alcyone/api/callback";
		System.out.println(url);
		Map<String,String> map = new HashMap<String, String>();
		map.put("tradeNo", "20170206200040011100550036070409");
		map.put("Money", "3");
		map.put("title", "7");
		map.put("memo", "d439e0f6f8cc9c02");
		map.put("alipay_account", "jch0099@qq.com");
		map.put("Gateway", "alipay");
		map.put("Sign", "363D8AB4A9D415CFF15D1B78D93BD9FF");
		map.put("Paytime", "2017-2-6 14:38:17");
		HttpUtil.httpPost(url, map, "UTF-8");
	}
}
