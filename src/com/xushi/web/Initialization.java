package com.xushi.web;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.xushi.core.util.Global;
import com.xushi.service.UserService;
import com.xushi.util.system.Const;

/**
 * 
 * 类名称：Initialization <br />
 * 类描述： 初始化系统参数 <br />
 * 
 * @version
 */
@Service
public class Initialization implements ServletContextAware {
	
	ServletContext context;
	@Autowired UserService userService;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@PostConstruct
	public void init() {
		initContentPath();
		// 公共数组
		//if (null != context)context.setAttribute(Const.CONTEXT_COMMARRAY, CommArray.getInstance());
	}

	/**
	 * 初始化项目路径
	 */
	public void initContentPath() {
		
		// 加载全局变量
		if (null == Global.WebPath) Global.load();
		if (null == context) {

			// 单元测试时
			System.out.println("Initialization not context.");
			return;
		}
		
		// 网站根路径
		String contextPath = context.getContextPath();
		context.setAttribute(Const.CONTEXT_PATH, contextPath);

		// 上传路径
		context.setAttribute(Const.CONTEXT_UPLOADPATH, Const.UPLOAD_URL_ROOT);

		// web路径
		String webPath = Global.WebPath + "/";
		context.setAttribute(Const.CONTEXT_WEBPATH, webPath);
		
		/*// 平台根路径
		String platformPath = contextPath + "/" + "platform";
		context.setAttribute(Const.CONTEXT_PLATFORMPATH, platformPath);*/
		
		//初始管理员
		userService.initAdminuser();
	}
}
