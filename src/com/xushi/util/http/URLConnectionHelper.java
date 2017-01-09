package com.xushi.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.xushi.util.system.Const;

/***
 * 公共的请求方法
 * @author pyepye
 * 2014.10.14
 */
public class URLConnectionHelper {

	private HttpURLConnection httpConnection; //连接
	private String sessionid;//获取链接会话ID
	private String content; //网页内容
	private int httpcode; //返回的状态代码
	private Map<String, List<String>> headerfields; //遍历所有的响应头字段
	private String url; //需要访问的地址
	private String params; //需要访问的参数
	private String charset=Const.UTF8;//字符集
	private String user_agent="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"; //访问代理
	
	public URLConnectionHelper(String url) throws IOException {
		this.httpConnection = HttpClientUtil.getHttpURLConnection(url);
		this.httpConnection.setRequestProperty("Cache-Control", "no-cache");
		this.httpConnection.setRequestProperty("connection", "Keep-Alive");//设为长链接
		this.httpConnection.setRequestProperty("User-Agent",this.user_agent);
	}
	
	public HttpURLConnection getHttpConnection() {
		return httpConnection;
	}

	public void setHttpConnection(HttpURLConnection httpConnection) {
		this.httpConnection = httpConnection;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public int getHttpcode() {
		return httpcode;
	}

	public void setHttpcode(int httpcode) {
		this.httpcode = httpcode;
	}

	public Map<String, List<String>> getHeaderfields() {
		return headerfields;
	}

	public void setHeaderfields(Map<String, List<String>> headerfields) {
		this.headerfields = headerfields;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/***
	 * 以Get方式发送
	 * @return
	 * isSetSession 是否设置SessionID
	 */
	public  void doSendGet(boolean isSetSessionID) {
		String cookieVal;
		String temp="";
		
		try {
			httpConnection.setRequestMethod("GET");

			/***
			 * 获取Cookied
			 * 该SessionID和服务器端的SessionID是一致的；
			 */
            if (sessionid !=null && isSetSessionID){
            	//System.out.println("doGetInfo->sessionid->"+sessionid);
            	httpConnection.setRequestProperty("cookie",sessionid);
            }
            
			httpConnection.setConnectTimeout(40000);
			httpConnection.setReadTimeout(40000);
			httpConnection.connect();
			
			httpcode = httpConnection.getResponseCode();
			
			if (httpcode == HttpURLConnection.HTTP_OK) {

				/***
				 * 获取Cookied
				 * 该SessionID和服务器端的SessionID是一致的；
				 */
				cookieVal = httpConnection.getHeaderField("set-cookie"); 
				if(cookieVal != null) { 
				   sessionid = cookieVal.substring(0, cookieVal.indexOf(";")); 
				}
				
				//System.out.println("sessionid->"+sessionid);
				this.headerfields = httpConnection.getHeaderFields();
				
				InputStream inStream = httpConnection.getInputStream();
				final BufferedReader in = new BufferedReader(new InputStreamReader(inStream, this.charset));// 读取网页全部内容

				final StringBuffer sb = new StringBuffer();
				
				while ((temp = in.readLine()) != null) {
					sb.append(temp + "\r\n");
				}
				in.close();
				
				if (sb!=null) this.content = sb.toString();
			}

			httpConnection.disconnect(); // 关闭
			
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
			me.getMessage();
			
		} catch (final IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	/***
	 * 以Post方式发送
	 * @return
	 */
	public void doSendPost(boolean isSetSessionID) {
		PrintWriter out = null;
		String cookieval;
		String temp="";
		
		try {
			httpConnection.setRequestMethod("POST");

			/***
			 * 获取Cookied
			 * 该SessionID和服务器端的SessionID是一致的；
			 */
            if (sessionid !=null && isSetSessionID){
            	//System.out.println("doGetInfo->sessionid->"+sessionid);
            	httpConnection.setRequestProperty("cookie",sessionid);
            }
            
            // 发送POST请求必须设置如下两行
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(httpConnection.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            
			httpConnection.setConnectTimeout(40000);
			httpConnection.setReadTimeout(40000);
			httpConnection.connect();
			
			httpcode = httpConnection.getResponseCode();
			
			if (httpcode == HttpURLConnection.HTTP_OK) {

				/***
				 * 获取Cookied
				 * 该SessionID和服务器端的SessionID是一致的；
				 */
				cookieval = httpConnection.getHeaderField("set-cookie"); 
				if(cookieval != null) { 
				   sessionid = cookieval.substring(0, cookieval.indexOf(";")); 
				}
				
				//System.out.println("sessionid->"+sessionid);
				this.headerfields = httpConnection.getHeaderFields();
				
				InputStream inStream = httpConnection.getInputStream();
				final BufferedReader in = new BufferedReader(new InputStreamReader(inStream, this.charset));// 读取网页全部内容
				
				final StringBuffer sb = new StringBuffer();
				
				while ((temp = in.readLine()) != null) {
					sb.append(temp + "\r\n");
				}
				in.close();
				
				if (sb!=null) this.content = sb.toString();
			}

			httpConnection.disconnect(); // 关闭
			
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
			System.out.println("发送POST请求出现异常！" + me);
			me.getMessage();
			
		} catch (final IOException e) {
			e.printStackTrace();
			
		}		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String smsUrl="http://web.cr6868.com/asmx/smsservice.aspx";

		String params = "name=18665718951&pwd=868270EA0205BD89E79B8BFFD9DA&content=请您回复是否在家&mobile=13660775955&stime=2015-01-08&sign=51家庭管家&type=pt&extno=123";
		
		String smsGetUrl="http://web.cr6868.com/asmx/smsservice.aspx?name=18665718951&pwd=868270EA0205BD89E79B8BFFD9DA&type=pt&sign=51家庭管家&mobile=13660775955,15918883583&content=请您回复是否在家&stime=2015-02-10&extno=123";
		
		URLConnectionHelper uh = new URLConnectionHelper(smsGetUrl);
		uh.doSendGet(true);
		System.out.println("获得SessionID->"+uh.getHttpcode());
		System.out.println("获得SessionID->"+uh.getSessionid());
		System.out.println("获得content->"+uh.getContent());
//		
//	    Map<String, List<String>> map = uh.getHeaderfields();
//        
//	    // 遍历所有的响应头字段
//        for (String key : map.keySet()) {
//           System.out.println(key + "--->" + map.get(key));
//        }
//        System.out.println("###########################");  
//		uh.setUrl(infoUrl);
//		uh.doSendGet(true);
//		System.out.println("获得SessionID->"+uh.getHttpcode());
//		System.out.println("获得SessionID->"+uh.getSessionid());
//		System.out.println("获得content->"+uh.getContent());
//		
//		System.out.println("###########################");  
		//下面是Post方式
		uh = new URLConnectionHelper(smsUrl);
		uh.setParams(params);
		//uh.doSendPost(false);
		System.out.println("获得SessionID->"+uh.getHttpcode());
		System.out.println("获得SessionID->"+uh.getSessionid());
		System.out.println("获得content->"+uh.getContent());
		
	
	}

}
