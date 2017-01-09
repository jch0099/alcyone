package com.xushi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * sql方法。
 */
public class SqlHelper {
	private static final Log _Log = LogFactory.getLog(SqlHelper.class);

	/**
	 * 返回合法SQL输入,带引号。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toSql(String str) {
		StringBuffer szTemp = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case '\'':
				szTemp.append("''");
				break;
			case '\"':
				szTemp.append("\"\"");
				break;
			default:
				szTemp.append(str.charAt(i));
			}
		}
		return "'" + szTemp.toString() + "'";
	}

	/**
	 * 返回合法SQL输入。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toSqlLike(String str) {
		return toSql("%"+str+"%");
	}
	
	/**
	 * 返回合法SQL输入--Dataset类专用。 创建日期：(2004-1-8 17:12:02)
	 * 
	 * @return java.lang.String
	 */
	public static String toDataset(String str) {
		StringBuffer szTemp = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case '\'':
				szTemp.append("''");
				break;
			default:
				szTemp.append(str.charAt(i));
			}
		}
		return szTemp.toString();
	}
	
	/**
	 * 返回合法sqllike，
	 * 
	 * @return java.lang.String
	 */
	public static String toJpqlParamLike(String str) {
		return "%"+str+"%";
	}

}