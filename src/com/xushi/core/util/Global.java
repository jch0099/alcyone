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
	public static String OSSPath;
	public static String OSSImagePath;
	public static String OSSStylename;

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
		OSSPath = ps.getProperty("oss_path");
		OSSImagePath = ps.getProperty("oss_image_path");
		OSSStylename = ps.getProperty("oss_stylename");
		return true;
	}
}
