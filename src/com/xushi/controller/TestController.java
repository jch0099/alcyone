package com.xushi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.xushi.core.controller.BaseController;
import com.xushi.util.gson.JsonUtil;

/**
 * 
 * @author penken
 */

@Controller
@RequestMapping("/test/*")
public class TestController extends BaseController {

	@RequestMapping("/test")
	public void test(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String APP_ID = "2016073100135953";
		String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDfcDSmr1kShGUK1dT6Wf4PmOfW5A7oRpCnkve+EDkHcWwJPLlpCx/rGb+xt0qkY5PgrVRufIgunQtIxOByxhUqiBqqTcToDtd8HOS22TRUY1uyFaciPuWKHDcxpTTVoTRRTDpZCUKt5HUdYy0jl1b6309jQWxMcrcYapN/73F3X1Gf2MBdU95oN4k19mI+ZWlAV8qFakLkcbZeMWUNnfoVp8rQgbmG7ODtugRJRTZLb7OjTHaYSMDMC91Gd1VROpXQrk3DL4SndnNhXda4qgXFSVPTof8wkId80LsYHoHsl149gY8JxpOiXDmnMWvNod4oKb7UFPEJYZUdpnJjoNZ3AgMBAAECggEAU878kMEYwe9TDxthxX3X4/XQkDP+u3UscQ6ZSns/SOFsSpKyYQpBMKf51SkRIFQRdabOEps3YXSadPMT1TkjW06t4c9F2l0mynD0APm7vSN/k263wAqjgohO05pKFQ9gVlYnVJo6nosguqMmnUD46SS13F7/xjUJJGllZglkIAe3TEjF+hwZ/un8Opct5eDSgihcdJv7VA8I0sjrAb9YAZkjo46ZuT4Ga3shsvksKEtoWkY1CvGCnO5QZC9AZUqwIQuh5vvNVr/ZVhSpJuO1JxqPOp1a+JRd6PSYT/u4rVsX4KwyiklXFn/9/dl1tvjOrGMp7hsek3BnkfdI+BXnwQKBgQD3KW4Ogmiv4yznx+gENfo8R4QNsVbuvnKTjHMvmDxrrjY58jgbb5ZU7pwovjJV7eTxe1xI0XjQVsSxKgwUn4KVvmbALNkZ3Ci7+8KZiOzG49D5rRREppItIoSmwnWcS83gGHZBb3LcQIuRHj93/FB1B5//+dZanu/8Q8bjVLSPcQKBgQDnbZsOIsvQMuUmDuXkl9r09GGyUiTzNp0hQlKHaSa4ElPUwtqxm97vCj5bssi8podbTzbl2D1gInSLFSdTZG9GKUbAcYQsaGYQUDyVJhuONQPl4AXpdzv1Mm05jHVhwNLE8T5XLcV8mz+FoOHrULK5UwGxLQvybd7Eq3rYMV0gZwKBgFYL9mTWwdt6G4gV8JCGujeCLlcWBk3Xv8veebt4JJDYdch2q1Hh51RqPbNxg9Smna30ZIx1YLonexKysMH9RlkbkGoXMv3tx8CqRXVuVgBoD7jcqNG9q+ZKviPQT2G6glIiMglV44cKcYx0G2SKUvhrTyiushGag7IvZm5+bt4BAoGAM6p1jk9YXw+ttrRmwITiXgNc+Dl8hEuyAqXS1OY1vaxhyMMDndkliQopZ8FfLTakS09A+0kuUpL+n7pGQEjQysIWSIpPLrbT6lpvXADY8AOH7j74Munqmc/7MHjaD8vDgjI1b7MJPstOACAtr6IhdFxCqXJcE3BR5mg+EZvbpYsCgYAVEsGKi6dIm1ST8MVlP2jdMm77r7sNVrqFqSIjhj4ClHd3jnc1xJ4s9m6Hw36MeVTd0x+89/avfRP4uCfyRqrE9NqtpiY4LuH+pjX61/AazUtMwHht+ZSOSZ82ZNEaRDNaIwxni2cOEAKcjzjoTLju1/0Yux8DAp4xm7tQKV0qgg==";
		String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArF6jslubpapSn60Uu8wpSYdU6krIcK5Yj4QqBSK+aNi5TNIPaCNLGJxXNb/V1JmlYipuv2zWxYVE9lk4rpyrbUyXVkVsHWKpM+opGDXp9eSM2gDgJbLkXvJzsJIYeeGeUzoNKBpeoKPS7eoIkAmIERAYSPLwuGiB9f7kmf+T5r7BLNjLq+Pqhk59Pi1ifvzc+38997BvHNKQ01yjSuN7I40uvKAmZtRZ45ChnVZ9YTl4Z1ZbU8x8OkzNPqeWBFryTY543qI7OR80EjpbldJNtWQy5HZdSRvBJ3qYIzWgCS2uDipkW7tFcAKQ4jaZBzpgt5bL0IetHomjsYNdewcrvwIDAQAB";
		//实例化客户端
		String url = "https://openapi.alipaydev.com/gateway.do";
		//String url = "https://openapi.alipay.com/gateway.do";
		
		AlipayClient alipayClient = new DefaultAlipayClient(url,APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY); //获得初始化的AlipayClient
	    AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
	    alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
	    Map<String,String> map = new HashMap<String, String>();
		map.put("out_trade_no", System.currentTimeMillis()+"");
		map.put("subject", "測試標題11");
		map.put("seller_id", "2088102169350403");
		map.put("total_amount", "0.1");
		alipayRequest.setBizContent(JsonUtil.toJson(map));//填充业务参数
	    String form = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(form);
		response.setContentType("text/html;charset=" +"UTF-8");
		response.getWriter().write(form);//直接将完整的表单html输出到页面
		response.getWriter().flush();
	}
	
	public static void main(String[] args) throws AlipayApiException {

		System.setProperty("log4j.configuration", "file:/E:/workspace/xushi2/webapp/WEB-INF/log4j.properties");
		String APP_ID = "2016073100135953";
		String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDfcDSmr1kShGUK1dT6Wf4PmOfW5A7oRpCnkve+EDkHcWwJPLlpCx/rGb+xt0qkY5PgrVRufIgunQtIxOByxhUqiBqqTcToDtd8HOS22TRUY1uyFaciPuWKHDcxpTTVoTRRTDpZCUKt5HUdYy0jl1b6309jQWxMcrcYapN/73F3X1Gf2MBdU95oN4k19mI+ZWlAV8qFakLkcbZeMWUNnfoVp8rQgbmG7ODtugRJRTZLb7OjTHaYSMDMC91Gd1VROpXQrk3DL4SndnNhXda4qgXFSVPTof8wkId80LsYHoHsl149gY8JxpOiXDmnMWvNod4oKb7UFPEJYZUdpnJjoNZ3AgMBAAECggEAU878kMEYwe9TDxthxX3X4/XQkDP+u3UscQ6ZSns/SOFsSpKyYQpBMKf51SkRIFQRdabOEps3YXSadPMT1TkjW06t4c9F2l0mynD0APm7vSN/k263wAqjgohO05pKFQ9gVlYnVJo6nosguqMmnUD46SS13F7/xjUJJGllZglkIAe3TEjF+hwZ/un8Opct5eDSgihcdJv7VA8I0sjrAb9YAZkjo46ZuT4Ga3shsvksKEtoWkY1CvGCnO5QZC9AZUqwIQuh5vvNVr/ZVhSpJuO1JxqPOp1a+JRd6PSYT/u4rVsX4KwyiklXFn/9/dl1tvjOrGMp7hsek3BnkfdI+BXnwQKBgQD3KW4Ogmiv4yznx+gENfo8R4QNsVbuvnKTjHMvmDxrrjY58jgbb5ZU7pwovjJV7eTxe1xI0XjQVsSxKgwUn4KVvmbALNkZ3Ci7+8KZiOzG49D5rRREppItIoSmwnWcS83gGHZBb3LcQIuRHj93/FB1B5//+dZanu/8Q8bjVLSPcQKBgQDnbZsOIsvQMuUmDuXkl9r09GGyUiTzNp0hQlKHaSa4ElPUwtqxm97vCj5bssi8podbTzbl2D1gInSLFSdTZG9GKUbAcYQsaGYQUDyVJhuONQPl4AXpdzv1Mm05jHVhwNLE8T5XLcV8mz+FoOHrULK5UwGxLQvybd7Eq3rYMV0gZwKBgFYL9mTWwdt6G4gV8JCGujeCLlcWBk3Xv8veebt4JJDYdch2q1Hh51RqPbNxg9Smna30ZIx1YLonexKysMH9RlkbkGoXMv3tx8CqRXVuVgBoD7jcqNG9q+ZKviPQT2G6glIiMglV44cKcYx0G2SKUvhrTyiushGag7IvZm5+bt4BAoGAM6p1jk9YXw+ttrRmwITiXgNc+Dl8hEuyAqXS1OY1vaxhyMMDndkliQopZ8FfLTakS09A+0kuUpL+n7pGQEjQysIWSIpPLrbT6lpvXADY8AOH7j74Munqmc/7MHjaD8vDgjI1b7MJPstOACAtr6IhdFxCqXJcE3BR5mg+EZvbpYsCgYAVEsGKi6dIm1ST8MVlP2jdMm77r7sNVrqFqSIjhj4ClHd3jnc1xJ4s9m6Hw36MeVTd0x+89/avfRP4uCfyRqrE9NqtpiY4LuH+pjX61/AazUtMwHht+ZSOSZ82ZNEaRDNaIwxni2cOEAKcjzjoTLju1/0Yux8DAp4xm7tQKV0qgg==";
		String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArF6jslubpapSn60Uu8wpSYdU6krIcK5Yj4QqBSK+aNi5TNIPaCNLGJxXNb/V1JmlYipuv2zWxYVE9lk4rpyrbUyXVkVsHWKpM+opGDXp9eSM2gDgJbLkXvJzsJIYeeGeUzoNKBpeoKPS7eoIkAmIERAYSPLwuGiB9f7kmf+T5r7BLNjLq+Pqhk59Pi1ifvzc+38997BvHNKQ01yjSuN7I40uvKAmZtRZ45ChnVZ9YTl4Z1ZbU8x8OkzNPqeWBFryTY543qI7OR80EjpbldJNtWQy5HZdSRvBJ3qYIzWgCS2uDipkW7tFcAKQ4jaZBzpgt5bL0IetHomjsYNdewcrvwIDAQAB";
		System.out.println(APP_PRIVATE_KEY.length());
		System.out.println(ALIPAY_PUBLIC_KEY.length());
		//实例化客户端
		String url = "https://openapi.alipaydev.com/gateway.do";
		//String url = "https://openapi.alipay.com/gateway.do";
		
		AlipayClient alipayClient = new DefaultAlipayClient(url,APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY); //获得初始化的AlipayClient
	    AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
	    alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
	    Map<String,String> map = new HashMap<String, String>();
		map.put("out_trade_no", System.currentTimeMillis()+"");
		map.put("subject", "測試標題11");
		map.put("seller_id", "2088102169350403");
		map.put("total_amount", "0.1");
		alipayRequest.setBizContent(JsonUtil.toJson(map));//填充业务参数
	    String form = alipayClient.pageExecute(alipayRequest).getBody();
		System.out.println(form);
		
		
		/*AlipayClient client  = new DefaultAlipayClient(url,APP_ID,APP_PRIVATE_KEY,"json","UTF-8",ALIPAY_PUBLIC_KEY);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify 
		AlipayOpenPublicTemplateMessageIndustryModifyRequest r = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数
		//此次只是参数展示，未进行字符串转义，实际情况下请转义
		Map<String,String> map = new HashMap<String, String>();
		map.put("out_trade_no", System.currentTimeMillis()+"");
		map.put("subject", "測試標題11");
		map.put("seller_id", "2088102169350403");
		map.put("total_amount", "0.1");
		r.setBizContent(JsonUtil.toJson(map));
		AlipayOpenPublicTemplateMessageIndustryModifyResponse response = client.execute(r); 
		//调用成功，则处理业务逻辑
		if(response.isSuccess()){
		    //.....
			
			System.out.println(response.getBody());
		}*/
	}
	
}
