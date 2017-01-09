package com.xushi.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xushi.core.util.ResponseUtil;
import com.xushi.util.NumberUtil;
import com.xushi.util.StringUtil;

/**
 * 控制器基类
 * 
 */
public class BaseController {

	public static final String DB_ZERO = "0";
	public static final String NULL = "null";

	protected Log _Log = LogFactory.getLog(this.getClass());

	/**
	 * 获取Context对象
	 * 
	 * @param request
	 * @param attrName
	 * @return
	 */
	protected Object getContextAttr(HttpServletRequest request, String attrName) {
		// request.getServletContext() servlet3.0添加的方法，如果直接获取会报错
		return request.getSession().getServletContext().getAttribute(attrName);
	}

	/**
	 * 获取项目路径
	 * 
	 * @param request
	 * @return
	 */
	protected String getContextPath(HttpServletRequest request) {
		return (String) getContextAttr(request, "path");
	}

	/**
	 * 获取请求参数中的字符串,非null
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	protected String getString(String name, HttpServletRequest request) {
		String method = request.getMethod();
		name = StringUtil.toString(request.getParameter(name));
		if ("GET".equalsIgnoreCase(method)) {
			try {
				name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {

			}
		}
		return name;
	}

	/**
	 * 当参数为空时使用默认值
	 * 
	 * @param name
	 * @param value
	 * @param request
	 * @return
	 */
	protected String getString(String name, String value,
			HttpServletRequest request) {
		String method = request.getMethod();
		name = StringUtil.defaultString(request.getParameter(name), value);
		if ("GET".equalsIgnoreCase(method)) {
			try {
				name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {

			}
		}
		return name;
	}

	/**
	 * 获取请求参数中的 int值
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	protected int getInteger(String name, HttpServletRequest request) {
		return NumberUtil.toInt(StringUtil.defaultString(
				request.getParameter(name), DB_ZERO));
	}

	/**
	 * 输出json结果
	 * 
	 * @param response
	 * @param context
	 * @throws Exception
	 */
	protected void writeJson(HttpServletResponse response, String context)
			throws Exception {
		ResponseUtil.outJson(context, response);
	}

	/**
	 * 输出xml
	 * 
	 * @param response
	 * @param context
	 * @throws Exception
	 */
	protected void writeXml(HttpServletResponse response, String context)
			throws Exception {
		ResponseUtil.outXml(context, response);
	}

	/**
	 * 输出html
	 * 
	 * @param response
	 * @param context
	 * @throws IOException
	 */
	protected void writeHtml(HttpServletResponse response, String context)
			throws IOException {
		ResponseUtil.outHtml(context, response);
	}
}
