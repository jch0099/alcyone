package com.xushi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Pay_config;
import com.xushi.service.ConfigService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/pay_config/*")
public class AdminPayConfigController extends BaseController{
	
	@Autowired ConfigService configService;
	
	@RequestMapping("/list")
	public void list(String keyword,Integer is_free,Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		List<Pay_config> list = configService.findPay_config();
		Page<Pay_config> page = new Page<Pay_config>(list, pr, 10);
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Pay_config video = configService.getPay_config(id);
			request.setAttribute("item", video);
		}
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_edit(Pay_config pay_config,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			configService.savePay_config(pay_config);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
	
	@RequestMapping("/ajax_delete")
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
	}
}
