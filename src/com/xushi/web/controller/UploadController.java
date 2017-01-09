package com.xushi.web.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.xushi.core.controller.BaseController;
import com.xushi.core.dao.DaoException;
import com.xushi.util.StringUtil;
import com.xushi.util.UploadFile;
import com.xushi.util.file.FileUtil;
import com.xushi.util.gson.JsonUtil;
import com.xushi.util.system.Const;
import com.xushi.web.vo.UploadResultVo;

/**
 * 上传 考虑旧文件删除和多余上传的无用文件
 */
@Controller
public class UploadController extends BaseController {
	Logger _log = Logger.getLogger(this.getClass());

	/**
	 * @throws Exception
	 * @throws IOException
	 * 
	 *             方法名: upload <br />
	 *             描述: 上传文件<br />
	 *             参数：<br />
	 * @param request
	 * @param response
	 * <br />
	 * @return void <br />
	 * @throws
	 */
	@RequestMapping(value = "/uploadfile")
	public void uploadfile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String uploadPath = Const.UPLOAD_FLODER_ROOT;
		final String uploadUrl = Const.UPLOAD_URL_ROOT;
		String from = getString("from", request), dir = getString("dir",
				request), filetype = getString("filetype", request);

		String fullUploadPath = uploadPath + dir + "/";
		String fullUploadUrl = uploadUrl + dir + "/";
		UploadFile up = new UploadFile(request, fullUploadPath);
		if (from.equals("kindeditor")) {// 编辑器返回参数不一样
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				up.saveEditorFile(filetype);
				if (null != up.saveFile && up.saveFile.exists()) {
					map.put("error", 0);
					map.put("url", fullUploadUrl + up.fileName);
				} else {
					map.put("error", 1);
					map.put("message", "上传失败！");
				}
			} catch (Exception e) {
				map.put("error", 1);
				map.put("message", e.getMessage());
			}
			writeHtml(response, JsonUtil.toJson(map));
		} else {
			UploadResultVo vo;
			up.saveFile();
			if (null != up.saveFile && up.saveFile.exists()) {
				vo = new UploadResultVo(up.fileName, fullUploadUrl
						+ up.fileName, up.oriName);
			} else {
				vo = new UploadResultVo(false, "上传失败", "", "");
			}
			// writeJson(response, JsonUtil.toJson(vo));
			writeHtml(response, JsonUtil.toJson(vo));
		}
	}

	/**
	 * 上傳頭像保存
	 * 
	 * @param MultipartFile
	 *            imageFile
	 * @return void
	 * **/
	@RequestMapping(value = "/uploadimg")
	public void uploadHeadImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "imgFile") MultipartFile imageFile)
			throws Exception {
		if (imageFile != null) {
			final String uploadPath = Const.UPLOAD_FLODER_ROOT;
			final String uploadUrl = Const.UPLOAD_URL_ROOT;
			File f = new File(uploadPath);
			if (!f.exists()) {
				FileUtil.createFileDir(uploadPath);
			}

			String fileName = StringUtils.defaultString(imageFile
					.getOriginalFilename());
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			// 扩展名格式：
			boolean flag = false;
			for (Object key : UploadFile.extMap.keySet()) {
				flag = flag
						|| Arrays.<String> asList(
								UploadFile.extMap.get(key).split(","))
								.contains("." + ext);
			}
			if (!flag) {
				throw new DaoException("不允許上載" + ext + "檔案");
			}
			byte[] bs = imageFile.getBytes();
			fileName = FileUtil.newFile(uploadPath, ext, bs);

			Map<String, Object> r = new HashMap<String, Object>();
			r.put("src", uploadUrl + fileName);
			r.put("name", fileName);
			writeHtml(response, JsonUtil.toJson(r));
		}
	}

	/**
	 * 頭像裁剪保存
	 * 
	 * @param null
	 * @return void
	 * **/
	// @RequestMapping(value = "/uploadHeadImage")
	// public void uploadHeadImage(
	// HttpServletRequest request,HttpServletResponse response,
	// @RequestParam(value = "x") String x,
	// @RequestParam(value = "y") String y,
	// @RequestParam(value = "h") String h,
	// @RequestParam(value = "w") String w,
	// @RequestParam(value = "imgFile") MultipartFile imageFile
	// ) throws Exception{
	// if(imageFile!=null){
	// if(true){
	// int imageX = (int)Double.parseDouble(x);
	// int imageY = (int)Double.parseDouble(y);
	// int imageH = (int)Double.parseDouble(h);
	// int imageW = (int)Double.parseDouble(w);
	// final String uploadPath = Const.UPLOAD_FLODER_ROOT;
	// final String uploadUrl = Const.UPLOAD_URL_ROOT;
	// File f =new File(uploadPath);
	// if(!f.exists()){
	// FileUtil.createFileDir(uploadPath);
	// }
	// ImageUploadUtil.imgCut(uploadPath,imageFile,imageX,imageY,imageW,imageH);
	// String json = "{" + "\"src\":" + "\"" +uploadUrl +
	// ImageUploadUtil.getImagename() + "\"}";
	// response.getWriter().write(json);
	// }
	// }
	// }
	/**
	 * 客户端上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/mobile/uploadfile")
	public void mobileUploadfile(String file, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		try {
			fileName = StringUtils.defaultString(fileName);
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!StringUtil.isEmpty(file)) {
				BASE64Decoder base64decoder = new BASE64Decoder();
				// 扩展名格式：
				boolean flag = false;
				for (Object key : UploadFile.extMap.keySet()) {
					flag = flag
							|| Arrays.<String> asList(
									UploadFile.extMap.get(key).split(","))
									.contains("." + ext);
				}
				if (!flag) {
					throw new DaoException("不允許上載" + ext + "檔案");
				}
				byte[] bs = base64decoder.decodeBuffer(file);
				fileName = FileUtil.newFile(Const.UPLOAD_FLODER_ROOT, ext, bs);
			}
			Map<String, Object> r = new HashMap<String, Object>();
			r.put("src", Const.UPLOAD_URL_ROOT + fileName);
			r.put("name", fileName);
			root.put("data", r);
			root.put("code", "200");
		} catch (Exception e) {
			root.put("code", "401");
			root.put("msg", e.getMessage());
			_log.error(e, e);
		}
		writeJson(response, JsonUtil.toJson(root));
	}
}
