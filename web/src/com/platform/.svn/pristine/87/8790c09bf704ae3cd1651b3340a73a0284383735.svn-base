package com.platform.util.httpclient;

import java.io.IOException;

public class HttpUtil {

	public static String request(String requestURL, String requestParameters, boolean post) throws IOException {
		HttpClient4Util util = null;
		if (requestURL.indexOf("https") >= 0) {
			util = new HttpClient4Util(30000, true);
		} else {
			util = new HttpClient4Util(30000, false);
		}

		HttpParameter httpParameter = new HttpParameter();
		if (requestParameters != null) {
			httpParameter.add("requestParameters", requestParameters);
		}

		HttpResp httpResp = new HttpResp();
		if (post) {
			httpResp = util.doPost(requestURL, httpParameter, "utf-8");
		} else {
			httpResp = util.doGet(requestURL, httpParameter, "utf-8");
		}
		return httpResp.getText("utf-8");
	}

}
