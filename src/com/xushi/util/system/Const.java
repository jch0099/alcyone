package com.xushi.util.system;

import com.xushi.core.util.Global;

/**
 * 全局参数
 *
 */
public final class Const {
	
	/**保存到session中的随机图片变量名*/
	
	/**保存到session中的后台用户变量名*/
	public static final String SESSION_KEY_ADMIN_USER = "_admin_user";
	public static final String SESSION_KEY_VIDEO_USER = "_video_user";
	/**保存到session中的随机图片变量名*/
	public static final String SESSION_KEY_RAND = "_rcode";
	/**保存到cookies中的openid
	public static final String COOKIES_KEY_OPENID = "_COOKIES_1";*/
	/**保存到session中的后台用户菜单变量名*/
	public static final String SESSION_KEY_USER_MENU = "_user_menu";
	
	/**保存到cookie中变量名*/
	
	/**文件上传相关配置**/
	public final static String UPLOAD_URL_ROOT = Global.WebPath + "/uploads/";
	public final static String UPLOAD_FLODER_ROOT = Global.FilePath + "/uploads/";
	public final static String VIDEO_URL_ROOT = Global.WebPath + "/video_file/";
	public final static String VIDEO_FLODER_ROOT = Global.FilePath + "/video_file/";
	
	/**用于与密码结合MD5混淆*/
	public static final String MD5_MIX = "jw134#%pqNLVfn";
	
	/**后台管理员默认用户名密码*/
	public static final String ADMINUSER_NAME = "admin";
	public static final String ADMINUSER_PWD = "123456";
	
	/**全局变量中的变量名称*/
	public static final String CONTEXT_PATH = "path";
	//public static final String CONTEXT_PLATFORMPATH = "platform";
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

}
