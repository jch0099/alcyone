package com.xushi.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xushi.core.controller.BaseController;
import com.xushi.core.page.Page;
import com.xushi.core.page.PageRequest;
import com.xushi.entity.Video_source;
import com.xushi.service.Video_sourceService;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;


@Controller
@RequestMapping("/admin/video_source/*")
public class AdminVideoSourceController extends BaseController{
	
	@Autowired Video_sourceService video_sourceService;
	
	@RequestMapping("/list")
	public void list(Integer pageno,HttpServletRequest request){
		PageRequest pr = new PageRequest(pageno, 10);
		Page<Video_source> page = video_sourceService.findVideo_sourcePage(pr);
		request.setAttribute("page", page);
	}
	
	@RequestMapping("/edit")
	public void edit(Integer id,Integer pageno,HttpServletRequest request){
		if( null != id ) {
			Video_source ad = video_sourceService.getVideo_source(id);
			request.setAttribute("item", ad);
		}
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(DataTypeEnum.json)
	public void ajax_edit(Video_source video_source,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ResultVo resultVo = new ResultVo();
		try {
			video_sourceService.saveVideo_source(video_source);
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
				Video_source video_source = video_sourceService.getVideo_source(_action_id);
				video_source.setStatus(3-video_source.getStatus());
				video_sourceService.saveVideo_source(video_source);
			}
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
				Video_source video_source = video_sourceService.getVideo_source(_action_id);
				video_sourceService.deleteVideo_source(video_source);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo = new ResultVo(false, e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(resultVo));
	}
}
