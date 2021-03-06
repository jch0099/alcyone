package com.xushi.util.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * http协议工具类
 * @author mojs
 *
 */
public class HttpUtil {
	public static String httpGet(String url,String coding, int timeout) throws IOException{
		StringBuffer temp = new StringBuffer();
		HttpURLConnection uc = getURLConnection(new URL(url));
		if(timeout==0){
			timeout = 20000;
		}
		uc.setConnectTimeout(timeout);
		uc.setReadTimeout(timeout);
		uc.setDoOutput(true);
		uc.setRequestMethod("GET");
		uc.connect();
		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in,coding);
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		return temp.toString();
	}

	public static String httpPost(String url,Map<String,String> params,String coding) throws IOException{
		String q = "";
		for (String key : params.keySet()) {
			String val = params.get(key);
			q += key+"="+val+"&";
		}
		StringBuffer temp = new StringBuffer();
		HttpURLConnection uc = getURLConnection(new URL(url));
		uc.setConnectTimeout(20000);
		uc.setReadTimeout(20000);
		uc.setDoOutput(true);
		uc.setRequestMethod("POST");
		uc.connect();
		OutputStream out = uc.getOutputStream();
		out.write(q.getBytes(coding));
		out.flush();
		out.close();

		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in,coding);
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		return temp.toString();
	}
	public static String httpPostJson(String url,String json,String coding) throws IOException{
		StringBuffer temp = new StringBuffer();
		HttpURLConnection uc = getURLConnection(new URL(url));
		uc.setConnectTimeout(20000);
		uc.setReadTimeout(20000);
		uc.setDoOutput(true);
		uc.setRequestMethod("POST");
		uc.connect();
		OutputStream out = uc.getOutputStream();
		out.write(json.getBytes(coding));
		out.flush();
		out.close();

		InputStream in = new BufferedInputStream(uc.getInputStream());
		Reader rd = new InputStreamReader(in,coding);
		int c = 0;
		while ((c = rd.read()) != -1) {
			temp.append((char) c);
		}
		in.close();
		return temp.toString();
	}
	public static HttpURLConnection getURLConnection(URL url) throws IOException{
		if (url.getProtocol().toUpperCase().equals("HTTPS")) {
			trustAllHosts();
			HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
			https.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			return https;
		} else {
			return (HttpURLConnection) url.openConnection();
		}
	}
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		// Android use X509 cert
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取经常代理后的真实ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * encodeURL中的中文
	 */
	public static String encodeUrl(String url) throws Exception{
		if(url.indexOf("/")!=-1){ 
			url=url.replace("\\", "/");
			String path=url.substring(0,url.lastIndexOf("/"));
			String filename=url.substring(url.lastIndexOf("/")+1,url.length());
			filename=java.net.URLEncoder.encode(filename,"utf-8");
			filename = filename.replace("+", "%20");
			return path+"/"+filename;
		}else{
			return url;
		}
	}

	/**
	 * 接收request流
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public static String getRequestAsString(HttpServletRequest request) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int read;
		InputStream in = request.getInputStream();
		do {
			read = in.read(buf);
			if (read > 0)
				out.write(buf, 0, read);
		} while (read > 0);
		String ret = out.toString("utf-8");
		out.close();
		out = null;
		return ret;
	}

	/**
	 * 获取客户端ＩＰ
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = StringUtils.defaultString(request.getHeader("x-forwarded-for"));
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equals(remoteAddr)) {
			remoteAddr = StringUtils.defaultString(request.getHeader("Proxy-Client-IP"));
		}
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equals(remoteAddr)) {
			remoteAddr = StringUtils.defaultString(request.getHeader("WL-Proxy-Client-IP"));
		}
		if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equals(remoteAddr)) {
			remoteAddr = StringUtils.defaultString(request.getRemoteAddr());
		}
		if (remoteAddr.indexOf(",") > -1) {
			remoteAddr = remoteAddr.split(",")[0];
		}
		return remoteAddr;
	}

	/**
	 * 获取客户端端口
	 * @param request
	 * @return
	 */
	public static int getRemotePort(HttpServletRequest request) {
		int remotePort = Integer.parseInt(StringUtils.defaultString(request.getHeader("x-forwarded-for-port"), "0"));
		if (remotePort == 0) {
			remotePort = request.getRemotePort();
		}
		return remotePort;
	}
	public static void main(String[] args) throws IOException {
		System.out.println(HttpUtil.httpGet("https://app.grwth.hk/mobile/base/data", "utf-8", 20000));
	}
}
