package com.xushi.util.security;

import com.xushi.util.system.Const;


/**
 * 用户生成md5密码
 * @author root
 *
 */
public class MD5MixUtil {

	/**
	 * 先添加混淆串再进行两次md5
	 * @param pwd
	 * @return
	 */
	public static String md5Mix(String pwd) {
		String chartset = "utf-8";
		if (pwd == null) {
			return null;
		}
		String md5Pwd = MD5Util.MD5Encode(Const.MD5_MIX + pwd, chartset);
		return MD5Util.MD5Encode(md5Pwd, chartset);
	}
	
	public static void main(String [] args) {
		System.out.println(MD5MixUtil.md5Mix("23288033"));
	}
}
