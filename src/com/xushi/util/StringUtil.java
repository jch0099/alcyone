package com.xushi.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 公共方法。
 */
public class StringUtil {

	//判断是否空值
	public static boolean isEmpty(String str){
		if(null != str && !"".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	
	/** 最大长度字符显示 */
	public static String leftString(String str, int len, boolean showdot) {
		if (str == null)
			return "";
		if (showdot)
			return StringUtil.cutstring(str, len, "...");
		else
			return StringUtil.cutstring(str, len, "");
	}

	/**
	 * 按字节长度截取字符串
	 * 
	 * @param str
	 *            将要截取的字符串参数
	 * @param toCount
	 *            截取的字节长度
	 * @param more
	 *            字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String cutstring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";

		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		byte[] CharLen = str.getBytes();
		byte[] moreLen = more.getBytes();

		if (CharLen.length > toCount) {
			toCount = toCount - moreLen.length;
		}

		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}

		if (CharLen.length > toCount)
			reStr += more;

		return reStr;
	}

	// 把字符串按照一定字符进行分割
	public static String[] splitString(String szSource, String token) {
		if ((szSource == null) || (token == null))
			return null;
		java.util.StringTokenizer st1 = new java.util.StringTokenizer(szSource,
				token);
		String[] d1 = new String[st1.countTokens()];
		for (int x = 0; x < d1.length; x++)
			if (st1.hasMoreTokens())
				d1[x] = st1.nextToken();
		return d1;
	}

	// 把字符串按照一定字符进行分割 第二种方法
	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null)
			return null;
		ArrayList<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return al.toArray(new String[0]);
	}

	public static byte[] toByte(String szStr) {
		if (szStr == null)
			return null;
		byte[] tmp = szStr.getBytes();
		return tmp;
	}

	public static String toSpace(Object obj) {
		if (obj == null || obj.equals(" "))
			return "&nbsp;";
		return obj.toString();
	}

	public static String toSpace(Date date) {
		if (date == null)
			return "&nbsp;";
		return toString(date);
	}

	public static String toSpace(Date date, String format) {
		if (date == null)
			return "&nbsp;";
		return toString(date, format);
	}

	// 数字数组转换成字符串
	public static String arrayInttoString(int[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1)
				ret += String.valueOf(ids[i]) + separator;
			else
				ret += String.valueOf(ids[i]);
		}
		return ret;
	}

	// 字符数组转换成字符串
	public static String arrayStrtoString(String[] ids, String separator) {
		String ret = "";
		for (int i = 0; i < ids.length; i++) {
			if (i < ids.length - 1)
				ret += String.valueOf(ids[i]) + separator;
			else
				ret += String.valueOf(ids[i]);
		}
		return ret;
	}

	public static String toString(byte[] byBuf) {
		if (byBuf == null)
			return null;
		return new String(byBuf);
	}

	public static String toString(Object obj) {
		if (obj == null)
			return "";
		return obj.toString().trim();
	}

	public static String toString(String obj) {
		if (obj == null)
			return "";
		return obj;
	}

	public static String toString(Date obj) {
		return toString(obj, "yyyy-MM-dd HH:mm:ss");
	}

	public static String toString(Date obj, String format) {
		if (obj == null)
			return "";
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(format);
		return df.format(obj);
	}

	/** 最大长度字符显示 */
	public static String subString(String str, int len) {
		if (str == null)
			return "";
		if (str.length() <= len)
			return str;
		return str.substring(0, len);
	}

	public static String subString(String str, int len, String expandstr) {
		if (str == null)
			return "";
		if (str.length() <= len)
			return str;
		return str.substring(0, len) + expandstr;
	}

	/**
	 * 字符串转为list
	 * 
	 * @param urlStr
	 * @return
	 */
	public static List<String> str2List(String urlStr) {
		final String URL_SPLIT_PATTERN = "[, ;\r\n]";// 逗号 空格 分号 换行
		if (urlStr == null) {
			return null;
		}
		String[] urlArray = urlStr.split(URL_SPLIT_PATTERN);
		List<String> urlList = new ArrayList<String>();
		for (String url : urlArray) {
			url = url.trim();
			if (url.length() == 0) {
				continue;
			}
			urlList.add(url);
		}
		return urlList;
	}
	
	//list转字符","
	public static String list2Str(List<Integer> args) {  
	    String str = "";  
	    if (args != null && args.size() > 0) {  
	        for (int i : args) {  
	            str += i + ",";  
	        }  
	    }  
	    str = str.substring(0, str.length() - 1);  
	    return str;  
	} 
		
	// 数字显示格式的处理
	public static String toPrice(double d) {
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(d);
	}

	public static String toPrice(float d) {
		DecimalFormat df = new DecimalFormat("##0.00");
		return df.format(d);
	}

	// 数字显示格式的处理
	public static String toPrice_Fin(double d) {
		DecimalFormat df = new DecimalFormat("###0.00");
		return df.format(d);
	}
	
	/**
	 * <p>方法名: defaultString	</p>
	 * <p>描述:	null 或者 "" 返回默认字符串</p>
	 * <p>参数:	</p>
	 * @return		
	 * <p>return	String</p>
	 */
	public static String defaultString(String originalString,String defaultString){
		if(null == originalString || "".equals(originalString.trim())){
			return defaultString;
		}
		return originalString;
	}
	/**
	 * <p>方法名: defaultString	</p>
	 * <p>参数:	</p>
	 * @return		
	 * <p>return	String</p>
	 */
	public static String defaultString(String originalString){
		return defaultString(originalString, "");
	}
	/**
	 * 
	 * 方法名: findDiff <br />  
	 * 描述:返回oriStr中有的，但str上没有的词 <br /> 
	 * 参数：<br /> 
	 * @param oriStr
	 * @param str
	 * @return <br />    
	 * @return String <br />    
	 * @throws
	 */
	public static String findDiff(String oriStr, String str, String token){
		if(null == token) token = ",";
			String ret = "";
			if(null == oriStr || 0 == oriStr.length()){
				return "";
			}else if(null == str || 0 == str.length()){
				return oriStr;
			}else{
				String[] arr = oriStr.split(token);
				str += token;
				for(String val : arr){
					if(str.indexOf(val + token) == -1){
						ret += val + ",";
					}
				}
				if(ret.length() > 0)
					ret = ret.substring(0, ret.length() - 1);
				return ret;
			}
	}

	// 过滤特殊字符  
    public static  String StringFilter(String str)throws PatternSyntaxException   {     
          // 只允许字母和数字       
          // String   regEx  =  "[^a-zA-Z0-9]";                     
          // 清除掉所有特殊字符  
          String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
          Pattern   p   =   Pattern.compile(regEx);     
          Matcher   m   =   p.matcher(str);     
          return   m.replaceAll("").trim();     
   } 
	
	/**
	 * 替换字符串
	 * @param text
	 * @param repl
	 * @param with
	 * @return
	 */
	public static String replace(String text, String repl, String with) {
		
		return replace(text, repl, with, -1);
	}

	/**
	 * 替换字符串
	 * @param text
	 * @param repl
	 * @param with
	 * @param max
	 * @return
	 */
	public static String replace(String text, String repl, String with, int max) {
		
		if ((text == null) || (repl == null) || (with == null)
				|| (repl.length() == 0) || (max == 0)) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0;
		int end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			max--;
			if (max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}
	
	// 过滤怪字符
	public static String filterBadChar(String szText) {
		String tmp = szText;

		String result = "";
		for (int i = 0; i < tmp.length(); i++) {
			char ch = tmp.charAt(i);
			if (ch < ' ' || (ch > '~' && ch < 255))
				continue;
			result += ch;
		}
		return result;
	}
	
	public static List<String> txtToLink(String text){
		List<String> urls = new ArrayList<String>();
		String regexp = "((http|https)://|www)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";                                     // 结束条件  
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(text);  
	    while(matcher.find()){
	        String url = matcher.group().indexOf("www")==0 ? "http://" + matcher.group() : matcher.group();
	        urls.add(url);
	    }
	    return urls;  
	}
}