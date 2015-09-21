package com.platform.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 不需要登录前台即可访问的地址
 * @author Administrator
 */
public class ExcludeUrlsUtil {
	
	// ResourceUtil.get("");
	
	private static String[] excludeUrls = {
		"user/to_login.html", "user/login.html", "user/to_regist.html", "user/regist.html", "user/test.html",
		// 默认允许根目录下所有页面
		// ddsc --允许ddsc下所有请求
	};

	private static Map<String, Boolean> urls;

	public static boolean isExcludeUrl(String url) {
		if (StringUtil.isBlank(url)) {
			return false;
		}
		if (urls == null) {
			urls = init();
		}
		Boolean exist = urls.get(url);
		if (exist == null) {
			return false;
		}
		return true;
	}

	private static Map<String, Boolean> init() {
		urls = new HashMap<String, Boolean>();
		for (String s : excludeUrls) {
			if(s.contains("*")){
				urls.put(s.substring(0, s.indexOf("/")), true);
			}else{
				urls.put(s, true);
			}
		}
		return urls;
	}

}
