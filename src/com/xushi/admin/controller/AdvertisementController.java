package com.xushi.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xushi.core.controller.BaseController;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Advertisement;
import com.xushi.entity.Image;
import com.xushi.service.AdvertisementService;
import com.xushi.service.ImageService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/advertisement/*")
public class AdvertisementController extends BaseController{
	
	@Autowired AdvertisementService adService;
	@Autowired ImageService imageService;
	
	@RequestMapping("/list")
	public void list(Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		Page<Advertisement> page = adService.findAdvertisementPage(pr);
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Advertisement ad = adService.getAdvertisement(id);
			request.setAttribute("item", ad);
			
			List<Image> list = imageService.findImage(1);
			JsonArray outarr = new JsonArray();
			if(null != list){
				for (Image image : list) {
					JsonObject jo = new JsonObject();
					jo.addProperty("id", image.getId());
					jo.addProperty("pId", 0);
					jo.addProperty("name", image.getTitle());
					jo.addProperty("img", image.getImg());
					outarr.add(jo);
				}
			}
			request.setAttribute("zNodes", outarr.toString());
		}
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_edit(Advertisement advertisement,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			adService.saveAdvertisement(advertisement);
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
				Advertisement ad = adService.getAdvertisement(_action_id);
				ad.setStatus(3-ad.getStatus());
				adService.saveAdvertisement(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
}
