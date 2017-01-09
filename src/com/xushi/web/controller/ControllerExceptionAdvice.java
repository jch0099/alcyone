package com.xushi.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xushi.web.vo.ResultVo;

/**
 * Controller异常处理
 * @author ll
 *
 */
//@ControllerAdvice
public class ControllerExceptionAdvice {
	
	/**
	 * 处理绑定异常
	 * @param error
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Object handlerBindException(Exception error, HttpServletRequest request) throws Exception {

		// 返回失败
		if (isJson(request)) {

			// json
			return new ResultVo(false, error.toString());
		} 
		else {

			// 页面
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("msg", error.toString());
			return mv;
		}
	}
	
	/**
	 * 处理所有异常
	 * @param error
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler()
	@ResponseBody
	public Object handlerException(Exception error, HttpServletRequest request) throws Exception {

		// 返回失败
		if (isJson(request)) {

			// json
			return new ResultVo(false, error.toString());
		} 
		else {

			// 页面
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("msg", error.toString());
			return mv;
		}
	}
	
	/**
	 * 判断是否为json格式
	 * @param request
	 * @return
	 */
	private boolean isJson(HttpServletRequest request) {

		String str = request.getHeader("Accept");
		return (null != str && str.indexOf("/json") > 0);
	}
}
