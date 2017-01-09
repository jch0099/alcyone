package com.xushi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Encoder;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.core.util.Global;
import com.xushi.entity.File_group;
import com.xushi.entity.User;
import com.xushi.service.FileGroupService;
import com.xushi.service.FileLogService;
import com.xushi.service.UserService;
import com.xushi.util.NumberUtil;
import com.xushi.util.StringUtil;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.ZxingQrcode;
import com.xushi.util.file.FileUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.system.Const;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/platform/*")
public class PlatformController extends BaseController {

	@Autowired UserService userService;
	@Autowired FileGroupService fileGroupService;
	@Autowired FileLogService fileLogService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request) throws Exception {
		try {
			User user = UserSessionUtil.getUser(request);
			List<File_group> list = fileGroupService.findFileGroup(user);
			if( null != list ) {
				for (File_group file_group : list) {
					String string = Global.WebPath;
					if( NumberUtil.toInt(file_group.getStatus()) == 2 ) continue;
					string += "platform/edit?id="+file_group.getId();
					_Log.info(string);
					byte[] b = ZxingQrcode.encoderQRCode(string);
					String imgdata = new BASE64Encoder().encodeBuffer(b);
					file_group.setQrcode(imgdata);
				}
			}
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			File_group item;
			if( null != id ) {//有id
				item = fileGroupService.getFileGroup(id);
				if( NumberUtil.toInt(item.getStatus()) == 2 ) return "redirect:/nologin/see?uuid="+item.getUuid();
				//if( NumberUtil.toInt(item.getUser().getId()) != NumberUtil.toInt(user.getId()) ) throw new DaoException("无权限");
				request.setAttribute("item", item);
				String string = Global.WebPath;
				string += "platform/edit?id="+item.getId();
				_Log.info(string);
				byte[] b = ZxingQrcode.encoderQRCode(string);
				String imgdata = new BASE64Encoder().encodeBuffer(b);
				request.setAttribute("imgdata", imgdata);
			}else {
				User user = UserSessionUtil.getUser(request);
				if( null == user ) {
					return "redirect:/nologin/login";
				}
				if( !fileLogService.check(user) ) {
					request.setAttribute("errmsg", "今日明信片超過限制");
					request.getRequestDispatcher("/platform/list").forward(request, response);
					//return "redirect:/platform/list";
				}
				item = new File_group();
				item.setUser(user);
				fileGroupService.saveFileGroup(item);
				//request.setAttribute("item", item);
				return "redirect:/platform/edit?id="+item.getId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/nologin/index";
		}
		return "/platform/edit";
	}
	
	@RequestMapping("/ajax_edit")
	@DataTypeAnnotation(value=DataTypeEnum.json)
	public void ajax_edit(File_group file_group,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"保存成功");
		try {
			//if( NumberUtil.toInt(file_group.getStatus()) == 2 ) throw new DaoException("该send已经保存。");
			//User user = UserSessionUtil.getUser(request);
			//if( null != file_group.getUser() && NumberUtil.toInt(file_group.getUser().getId()) != NumberUtil.toInt(user.getId()) ) throw new DaoException("无权限");
			//file_group.setUser(user);
			if( null == file_group.getUser() ) throw new DaoException("保存失敗");
			if( StringUtil.isEmpty(file_group.getVoice_name()) )  file_group.setVoice_name(null);
			if( StringUtil.isEmpty(file_group.getImg_name()) )  file_group.setImg_name(null);
			fileGroupService.saveFileGroup(file_group);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", file_group.getId());
			vo.setRet(map);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/delete")
	@DataTypeAnnotation(value=DataTypeEnum.json)
	public void delete(Integer id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"删除成功");
		try {
			User user = UserSessionUtil.getUser(request);
			if( NumberUtil.toInt(id) == 0 ) throw new DaoException("参数错误");
			File_group file_group = fileGroupService.getFileGroup(id);
			if( null != file_group.getUser() && NumberUtil.toInt(file_group.getUser().getId()) != NumberUtil.toInt(user.getId()) ) throw new DaoException("无权限");
			fileGroupService.deleteFileGroup(file_group);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResult(false);
			vo.setMsg(e.getMessage());
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/nologin/login");
	}
	
	@RequestMapping("/downloadall")
	public void downloadall(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultVo vo = new ResultVo(true,"");
		try {
			User user = UserSessionUtil.getUser(request);
			if( null == user ) {writeJson(response, "500"); return;}
			String timestr = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
			FileUtil.delAllFile(Const.UPLOAD_FLODER_ROOT+"tmps/"+ user.getId());
			List<File_group> list = fileGroupService.findFileGroup(user);
			if( null != list ) {
				for (File_group file_group : list) {
					String string = Global.WebPath;
					string += "platform/edit?id="+file_group.getId();
					ZxingQrcode.encoderQRCode(string, Const.UPLOAD_FLODER_ROOT+"tmps/"+ user.getId()+"/"+UUID.randomUUID().toString()+".jpg");
				}
			}
			byte[] buffer = new byte[1024];
			//生成的ZIP
			String dirPath = Const.UPLOAD_FLODER_ROOT + "/" + timestr + "/" + user.getId();
			String strZipName =  dirPath + "/qrcode.zip";  
			FileUtil.delAllFile(Const.UPLOAD_FLODER_ROOT + "/" + timestr + "/" + user.getId());
			FileUtil.createFileDir(dirPath);
			FileUtil.newFile(strZipName, null);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipName));  

			File dirfile = new File(Const.UPLOAD_FLODER_ROOT+"tmps/"+ user.getId());
			File[] files = dirfile.listFiles();
			if( null == files ) {writeJson(response, "500"); return;}
			for (int i = 0; i < files.length; i++) {
				FileInputStream fis = new FileInputStream(files[i]);  
		        out.putNextEntry(new ZipEntry(files[i].getName()));  
		        int len;
		        //读入需要下载的文件的内容，打包到zip文件  
		        while((len = fis.read(buffer))>0) {  
		        	out.write(buffer,0,len);
		        }  
		        out.closeEntry();
		        fis.close();  
			}
			out.close();
			FileUtil.delAllFile(Const.UPLOAD_FLODER_ROOT+"tmps/"+ user.getId());
			String url = Const.UPLOAD_URL_ROOT + timestr + "/" + user.getId() + "/qrcode.zip";
			HashMap<String, Object> ret = new HashMap<String,Object>();
			ret.put("url", url);
			vo.setRet(ret);
		} catch (Exception e) {
			vo.setResult(false);
			vo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		writeJson(response, JsonUtil.toJson(vo));
	}
}
