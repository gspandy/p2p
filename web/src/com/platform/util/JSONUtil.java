package com.platform.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {

	public static String getJSON(Object... args) {
		if (args != null) {
			JSONArray ja = new JSONArray();
			for (Object o : args) {
				ja.add(o);
			}
			return ja.toJSONString();
		}
		return null;
	}

	public static String getJSON(List<Object> list) {
		if (list != null) {
			JSONArray ja = new JSONArray();
			for (Object o : list) {
				ja.add(o);
			}
			return ja.toJSONString();
		}
		return null;
	}

	public static String getJSON(Map<String, Object> map) {
		if (map != null) {
			JSONObject ja = new JSONObject();
			return ja.toJSONString(map);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(format("{\"records\":[{\"annualrate\":\"9.8\",\"icon\":2,\"itemid\":100000000000000038,\"itemtitle\":\"房抵贷A001\",\"progress\":\"20\",\"status\":1,\"tag\":5,\"totalamount\":\"1,000,000.00\",\"totalperiods\":2,\"unitprice\":\"50\"},{\"annualrate\":\"10.0\",\"icon\":5,\"itemid\":100000000000000036,\"itemtitle\":\"车金融A001\",\"progress\":\"100\",\"status\":20,\"tag\":10,\"totalamount\":\"500,000.00\",\"totalperiods\":3,\"unitprice\":\"50\"},{\"annualrate\":\"9.8\",\"icon\":5,\"itemid\":100000000000000037,\"itemtitle\":\"车金融B001\",\"progress\":\"100\",\"status\":20,\"tag\":5,\"totalamount\":\"250,000.00\",\"totalperiods\":2,\"unitprice\":\"100\"}],\"returnCode\":0,\"returnMsg\":\"success\"}", 0));
	}

	private static Stack<Integer> stack = new Stack<Integer>();

	public static String format(String json, int depth) {
		String result = "";

		for (int i = 0; i < json.length(); i++) {
			char c = json.charAt(i);
			if (c == '[')
				stack.push(i);
			if (c == ']') {
				int popTag = stack.pop();
				if (stack.isEmpty()) {
					String childJson = json.substring(popTag + 1, i);
					// 递归解析数组
					result += "[" + format(childJson, depth + 2) + tokenStr(depth) + "";
				}
			}
			if (stack.isEmpty()) {
				String token = getToken(c, depth, json, i);
				result += token;
			}
		}
		return result;

	}

	//
	private static String getToken(char c, int depth, String json, int i) {
		if (c == '{' || c == '}')
			return "\n" + tokenStr(depth + 1) + c + "\n" + tokenStr(depth + 2);
		if (c == ',' && json.charAt(i + 1) == '\"')
			return c + "\n" + tokenStr(depth + 2);
		if (c == ']')
			return "\n" + tokenStr(depth + 2) + c + "\n" + tokenStr(depth + 2);
		return c + "";
	}

	// 分隔符
	private static String tokenStr(int depth) {
		String token = "";
		for (int i = 0; i < depth; i++)
			token += "   ";
		return token;
	}
}
