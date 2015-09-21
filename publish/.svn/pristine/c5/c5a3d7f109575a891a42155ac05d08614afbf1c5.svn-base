package test;

import java.util.HashMap;
import java.util.Map;

import com.platform.constant.SystemConstant;
import com.platform.util.JSONUtil;
import com.platform.util.MD5Util;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, String> banners = new HashMap<String, String>();
		banners.put("imageurl", "http://i3.mifile.cn/a4/T191YvB4Lv1RXrhCrK.jpg");
		banners.put("linkurl", "http://www.mi.com/note2/");
		result.put("banners", banners);
		
		Map<String, String> activity = new HashMap<String, String>();
		activity.put("title", "首次购买返现20元红包");
		activity.put("url", "http://www.mi.com/note2/");
		activity.put("color", "#FF0000");
		result.put("activity", activity);
		
		Map<String, String> selfitem = new HashMap<String, String>();
		selfitem.put("selfitemid", "100000000000000001");
		selfitem.put("annualrate", "8.0%");
		selfitem.put("days", "61天");
		selfitem.put("progress", "80");
		result.put("selfitem", selfitem);
		
		Map<String, String> message = new HashMap<String, String>();
		message.put("title", "生财有术");
		message.put("count", "3");
		result.put("message", message);
		
		System.out.println(JSONUtil.getJSON(result));
		
		System.out.println(MD5Util.encrypt("111111" + SystemConstant.LOGINPWD_KEY));
		byte b = 100;
	}
	
}
