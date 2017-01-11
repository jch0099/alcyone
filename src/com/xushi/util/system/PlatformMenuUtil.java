package com.xushi.util.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xushi.entity.User;
import com.xushi.util.file.FileRwUtil;

public class PlatformMenuUtil {
	
	public static List<String> defaultUrls = new ArrayList<String>();
	
	static{
		defaultUrls.add("/" + "admin" + "/home");
		defaultUrls.add("/" + "admin" + "/loadmenu");
		defaultUrls.add("/" + "admin" + "/logout");
		defaultUrls.add("/" + "admin" + "/profile/*");
		defaultUrls.add("/" + "admin" + "/changepassword");
		defaultUrls.add("/" + "admin" + "/ajax_changepassword");
	}

	/**
	 * 获取相关用户的菜单
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JsonObject getMenu(User user, HttpServletRequest request) throws Exception{
		String path= request.getSession().getServletContext().getRealPath("/");
		String menu =FileRwUtil.readFileReader(path + "/WEB-INF/res/modules.json");
		JsonObject root = new JsonParser().parse(menu).getAsJsonObject();

		if(Const.ADMINUSER_NAME.equals(user.getAccount())){//初始管理员不鉴权
			return root;
		}
		
		JsonArray ja = root.get("item").getAsJsonArray();
		JsonArray newmenus1 = new JsonArray();
		for (JsonElement je : ja) {
			String url = "";
			JsonObject cmenu1 = je.getAsJsonObject();
			JsonArray menus2 = cmenu1.get("item").getAsJsonArray();
			JsonArray newmenus2 = new JsonArray();
			for (JsonElement cje2 : menus2) {
				JsonObject cmenu2 = cje2.getAsJsonObject();
				JsonArray menus3 = cmenu2.get("item").getAsJsonArray();
				JsonArray newmenus3 = new JsonArray();
				for (JsonElement cje3 : menus3) {
					JsonObject cmenu3 = cje3.getAsJsonObject();
					newmenus3.add(cmenu3);
					/*String code = cmenu3.get("code").getAsString();
					for (String str : rolecodes) {
						if(str.equals(code)){
							if("".equals(url))url = cmenu3.get("url").getAsString();
							newmenus3.add(cmenu3);
							break;
						}
					}*/
				}
				cmenu2.add("item", newmenus3);
				if(newmenus3.size()!=0){
					newmenus2.add(cmenu2);
				}
			}
			cmenu1.add("item", newmenus2);
			cmenu1.addProperty("url", url);
			if(newmenus2.size()!=0){
				newmenus1.add(cmenu1);
			}
		}
		root.add("item", newmenus1);
		return root;
	}

	public static JsonObject getSessionMenu(User user, HttpServletRequest request) throws Exception{
		JsonObject sessionmenu = (JsonObject)request.getSession().getAttribute(Const.SESSION_KEY_USER_MENU);
		if(sessionmenu==null){
			sessionmenu = getMenu(user, request);
			request.getSession().setAttribute(Const.SESSION_KEY_USER_MENU,sessionmenu);
		}
		return sessionmenu;
	}
	
	public static boolean isDefaultUrl(String url){
		boolean ret = false;
		for(String e : defaultUrls){
			if(e.indexOf("/*") > -1){
				e = e.replace("/*", "/");
				if(url.indexOf(e) > -1) ret = true;
			}else{
				if(url.indexOf(e) > -1) return true;
			}
			if(ret) break;
		}
		return false;
	}
}
