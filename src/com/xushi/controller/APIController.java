package com.xushi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Encoder;

import com.xushi.core.controller.BaseController;
import com.xushi.service.FileGroupService;
import com.xushi.util.ZxingQrcode;
import com.xushi.util.gson.JsonUtil;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/api/*")
public class APIController extends BaseController {
	@Autowired FileGroupService fileGroupService;
	
	@RequestMapping("/getQrCodeString")
	public void getQrCodeString(String filename,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"操作成功");
		try {
			MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
			MultipartFile mfile = mrequest.getFile("uploadimg");
			String url = ZxingQrcode.decode(mfile.getInputStream());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", url);
			vo.setRet(map);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg("解析二维码出错");
		}
		writeHtml(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/getQrCodeImg")
	public void getQrCodeImg(String string,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"获取成功");
		try {
			byte[] b = ZxingQrcode.encoderQRCode(string);
			String imgdata = new BASE64Encoder().encodeBuffer(b);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", imgdata);
			vo.setRet(map);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg("解析二维码出错");
		}
		writeHtml(response, JsonUtil.toJson(vo));
	}
}
