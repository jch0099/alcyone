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
import com.xushi.entity.Image;
import com.xushi.entity.Video;
import com.xushi.service.ImageService;
import com.xushi.service.VideoService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/video/*")
public class AdminVideoController extends BaseController{
	
	@Autowired VideoService videoService;
	@Autowired ImageService imageService;
	
	@RequestMapping("/list")
	public void list(String keyword,Integer is_free,Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		Page<Video> page = videoService.findVideoPage(keyword,is_free,pr);
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Video video = videoService.getVideo(id);
			request.setAttribute("item", video);
			
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
	public void ajax_edit(Video video,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			videoService.saveVideo(video);
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
				Video video = videoService.getVideo(_action_id);
				videoService.deleteVideo(video);
			}
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
				Video video = videoService.getVideo(_action_id);
				video.setStatus(3-video.getStatus());
				videoService.saveVideo(video);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
}
