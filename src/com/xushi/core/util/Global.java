package com.xushi.core.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局变量
 * @author ll
 */
public class Global {

	public static String WebPath;
	public static String FilePath;
	public static String alipay_account;
	public static String alipay_memo;

	/**
	 * 读取配置
	 * @throws IOException 
	 */
	public static boolean load() {
		String strFileName = "/global.properties";
		Properties ps = new Properties();
		try {
			// 加载
			ps.load(Global.class.getResourceAsStream(strFileName));
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		// path
		WebPath = ps.getProperty("web_path");
		FilePath = ps.getProperty("file_path");
		alipay_account = ps.getProperty("alipay_account");
		alipay_memo = ps.getProperty("alipay_memo");
		return true;
	}
}
