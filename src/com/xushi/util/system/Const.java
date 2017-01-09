package com.xushi.util.system;

import com.xushi.core.util.Global;

/**
 * 全局参数
 *
 */
public final class Const {
	
	/**保存到session中的随机图片变量名*/
	public static final String SESSION_KEY_RAND = "_rcode";
	
	/**保存到session中的后台用户变量名*/
	public static final String SESSION_KEY_USER = "_user";
	
	/**保存到session中的后台用户菜单变量名*/
	public static final String SESSION_KEY_SCHOOLPF_MENU = "_schoolpf_menu";
	public static final String SESSION_KEY_PARENTSTUDENT_MENU = "_parentstudent_menu";
	/**保存到cookies中的openid*/
	public static final String COOKIES_KEY_OPENID = "_COOKIES_1";
	
	public static final String COOKIES_KEY_SCHOOLPF = "_COOKIES_SCHOOLPF";
	
	public static final String COOKIES_KEY_DATAINTEGRATION = "_COOKIES_DATAINTEGRATION";
	public static final String COOKIES_KEY_DATAINTEGRATION_SCHOOLCODE = "_COOKIES_DATAINTEGRATION_SCHOOLCODE";
	
	public static final String SESSION_KEY_XPS_USERTOKEN = "_XPS-Usertoken";
	/**保存到cookie中变量名*/
	
	/**文件上传相关配置**/
	public final static String UPLOAD_URL_ROOT = Global.WebPath + "/uploads/";
	public final static String QRCODE_URL_ROOT = Global.WebPath + "/qrcode/";
	
	public final static String UPLOAD_FLODER_ROOT = Global.FilePath + "/uploads/";
	public final static String QRCODE_FLODER_ROOT = Global.FilePath + "/qrcode/";
	
	/**用于与密码结合MD5混淆*/
	public static final String MD5_MIX = "jw134#%pqNLVfn";
	
	/**后台管理员默认用户名密码*/
	public static final String ADMINUSER_NAME = "admin";
	public static final String ADMINUSER_PWD = "123456";
	
	/**全局变量中的变量名称*/
	public static final String CONTEXT_PATH = "path";
	public static final String OSS_PATH = "osspath";
	public static final String OSS_IMAGE_PATH = "ossimagepath";
	public static final String OSS_STYLE_NAME = "ossstylename";
	public static final String CONTEXT_PLATFORMPATH = "platform";
	public static final String CONTEXT_UPLOADPATH = "CONTEXT_UPLOADPATH";
	public static final String CONTEXT_WEBPATH = "CONTEXT_WEBPATH";
	public static final String CONTEXT_COMMARRAY = "commArray";
	public static final int MAXRANK = 1000;
	
	/**默认显示条数*/
	public static final int DEFINE_PAGE = 10;
	
	/**静态文件*/
	public static final String[] RESOURCES =  new String[] { "jpg", "png", "gif", "css","js", "swf", "ttf", "woff" };
	
	public static final String UTF8 = "utf-8";
	
	public static final String LANGUAGE_DEFAULT = Const.LANGUAGE_ZH;
	public static final String LANGUAGE_ZH = "zh";
	public static final String LANGUAGE_EN = "en";

	public static final String NOTICT_CONTENT_CSS = "<link href='"+Global.WebPath+"/js/grwth/assets/plugins/boostrapv3/css/bootstrap.min.css' rel='stylesheet' type='text/css'>";
}
