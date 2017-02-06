package com.xushi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.core.page.Sort;
import com.xushi.entity.Order;
import com.xushi.entity.User;
import com.xushi.entity.Video;
import com.xushi.service.OrderService;
import com.xushi.service.UserService;
import com.xushi.service.VideoService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/pay_order/*")
public class AdminPayOrderController extends BaseController{
	
	@Autowired OrderService orderService;
	@Autowired UserService userService;
	@Autowired VideoService videoService;
	
	@RequestMapping("/list")
	public void list(Integer status,String start_date,String end_date,Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		pr.setSort(new Sort(false, "id"));
		Page<Order> page = orderService.findOrder(status, start_date, end_date, pr);
		if( null != page.getResults() ) for (Order o : page.getResults()) {
			if( null == o.getUser_id() ) o.setUser_account("-打赏-");
			else {
				User u = userService.getUserById(o.getUser_id());
				o.setUser_account(u.getAccount());
			}
		}
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Order order = orderService.getOrder(id);
			request.setAttribute("item", order);
		}
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_edit(Order order,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			Order o = orderService.getOrder(order.getId());
			if( o.getStatus() != 2 ) throw new DaoException("订单已完成");
			o.setAlipay_order_num(order.getAlipay_order_num());
			o.setStatus(3);
			orderService.payOrder(o);
			//configService.savePay_config(pay_config);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
	
	@RequestMapping("/detail")
	public void detail(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Order order = orderService.getOrder(id);
			if( null == order.getUser_id() ) order.setUser_account("-打赏-");
			else {
				User u = userService.getUserById(order.getUser_id());
				order.setUser_account(u.getAccount());
			}
			if( null != order.getVideo_id() ) {
				Video video = videoService.getVideo(order.getVideo_id());
				order.setVideo_title(video.getTitle());
			}
			request.setAttribute("item", order);
		}
	}
	
	/*@RequestMapping("/ajax_delete")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_delete(Integer _action_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			if( null != _action_id ) {
				Pay_config config = configService.getPay_config(_action_id);
				configService.deleteConfig(config);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}*/
}
