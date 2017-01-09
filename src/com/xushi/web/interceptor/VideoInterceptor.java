package com.xushi.web.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xushi.core.util.ResponseUtil;
import com.xushi.entity.User;
import com.xushi.service.UserService;
import com.xushi.util.UserSessionUtil;
import com.xushi.util.system.Const;
import com.xushi.web.annotation.DataTypeAnnotation;
import com.xushi.web.annotation.DataTypeEnum;
import com.xushi.web.vo.ResultVo;

public class VideoInterceptor extends HandlerInterceptorAdapter {
	private Set<String> m_excludes;
	private static final String PATH_A = "/video";

	private String[] m_suffixs = Const.RESOURCES;

	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String currentPath = PATH_A;
		String contextPath;
		String requestURI;
		HandlerMethod handlerMethod = null;
		DataTypeAnnotation dataType = null;
		User user;

		contextPath = request.getContextPath();
		requestURI = request.getRequestURI().replaceAll("\\/\\/", "/");

		//过滤后缀,直接跳过
		for (String suffix : m_suffixs) {
			if (requestURI.endsWith("." + suffix)) {
				return super.preHandle(request, response, handler);
			}
		}

		//例外列表
		if( null != m_excludes ) {
			for (String ex : m_excludes) {
				if (requestURI.equals(contextPath + ex)) {
					return super.preHandle(request, response, handler);
				}
			}
		}
		if(null != handler){
			try {
				handlerMethod = (HandlerMethod)handler;
				dataType = handlerMethod.getMethodAnnotation(DataTypeAnnotation.class);
			} catch (Exception e) {e.printStackTrace();}
		}
		user = UserSessionUtil.getVideoUser(request);
		if( null != user ) return true;
		redirectTo(dataType, contextPath + currentPath, requestURI, "請登錄。", request, response);
		return false;
		/*// 获取一个Cookie
		String userid = CookieUtil.getCookieValue(Const.COOKIES_KEY_SCHOOLPF, request);
		// 判断Cookie是否存在
		if (!StringUtil.isEmpty(userid)){
			// 帮助用户登录
			user = userService.getUserById(Integer.parseInt(userid));
			// 把用户存入Session
			UserSessionUtil.setUser(user, request);
			return true;
		}else{
			redirectTo(dataType, contextPath + currentPath, requestURI, "請登錄。", request, response);
			return false;
		}*/
	}

	public void setExcludes(Set<String> excludes) {
		m_excludes = excludes;
	}

	void sendRedirectScript(String gotoUrl, HttpServletResponse response) throws IOException{
		String str = "<script>location.href='" + gotoUrl + "';</script>";
		response.getWriter().print(str);
		response.getWriter().flush();
		response.getWriter().close();
	}

	//调到对应登陆地址
	void redirectTo(DataTypeAnnotation dt, String path, String requestURI, String msg, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String url;
		url = path  + "/login?ref="+URLEncoder.encode(requestURI,"utf-8");
		//默认跳转
		if(null == dt){
			sendRedirectScript(url, response);
		}else{
			// 根据注解设置的值 得知是返回页面是否json
			if(DataTypeEnum.json == dt.value()){
				// 返回json格式数据提示
				ResultVo resultVo = new ResultVo(false, msg); 
				ResponseUtil.outJson(resultVo.toJsonMsg(), response);
			}else{
				sendRedirectScript(url, response);
			}
		}
	}
}
