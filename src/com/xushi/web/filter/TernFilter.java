package com.xushi.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xushi.util.security.XxsGuardRequest;

/**
 * Tern框架下在web拦截访问防XXS
 * 
 */
public class TernFilter implements Filter {
	protected ServletContext m_ServletContext;

	public TernFilter() {
	}

	public void init(FilterConfig config) throws ServletException {
		m_ServletContext = config.getServletContext();
		// m_Initial = true;
		// // 由web.xml中读取配置
		// m_TimestampName = config.getInitParameter("timestamp");
		// m_FlusherName = config.getInitParameter("flusher");
		// m_AuthorizerName = config.getInitParameter("authorizer");
		// m_Encoding = config.getInitParameter("encoding");
	}

	public void destroy() {
		m_ServletContext = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();  
        if(uri != null && uri.toLowerCase().endsWith(".jsp")) { 
        	HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
        	httpServletResponse.sendRedirect("/nofind");  
            return;  
        }  		
		if (req.getQueryString()!=null && req.getQueryString().length()>0 && req.getMethod().equalsIgnoreCase("get")){
				//|| StringUtil.toString(req.getContentType()).indexOf("application/x-www-form-urlencoded") >= 0) {
			request = new XxsGuardRequest(req);
		}
		nextFilter.doFilter(request, response);
	}
}
