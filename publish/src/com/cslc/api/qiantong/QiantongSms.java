package com.cslc.api.qiantong;

/*
 HTTP接口C#调用说明
 上海谦通信息科技有限公司
 http://www.esms100.com
 咨询电话：021-20223313
 技术支持：
 QQ:613784728
 Email:jezzy@qq.com

 示例仅供参考,请求参数及调用地址以文档为准
 url连接请参考接口文档具体说明
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.platform.util.MD5Util;

/*
 * 发送短信基础类
 * @author administration
 *
 */
public class QiantongSms {
	private static String x_uid = "2354";
	private static String x_pwd_md5 = MD5Util.encrypt("767474").toUpperCase();// "D255FAAD652E844B0FB9C047970E5E16"
	private Integer x_gate_id = 300;

	public static String sendSms(String mobile, String content) {
		Integer x_ac = 10;// 发送信息
		HttpURLConnection httpconn = null;
		String result = "-20";
		// String memo = content.length() < 70 ? content.trim() : content.trim().substring(0, 70);
		String memo = content.trim();
		StringBuilder sb = new StringBuilder();
		sb.append("http://223.4.131.214:8081/mt/?cpid=");
		sb.append(x_uid);
		sb.append("&cppwd=").append(x_pwd_md5);
		sb.append("&phone=").append(mobile);
		try {
			sb.append("&msgtext=").append(URLEncoder.encode(memo, "gb2312"));
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return result;
	}
	
	public static String balance() {
		HttpURLConnection httpconn = null;
		String result = "-20";
		StringBuilder sb = new StringBuilder();
		sb.append("http://223.4.131.214:8081/qamount/?cpid=");
		sb.append(x_uid);
		sb.append("&cppwd=").append(x_pwd_md5);
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return result;
	}
	
	public static String getReport() {
		HttpURLConnection httpconn = null;
		String result = "-20";
		StringBuilder sb = new StringBuilder();
		sb.append("http://223.4.131.214:8081/qreport/?cpid=");
		sb.append(x_uid);
		sb.append("&cppwd=").append(x_pwd_md5);
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
			result = rd.readLine();
			//String resultXml = new String(Base64.decode(resultText.getBytes()), "utf-8");
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(QiantongSms.balance());
		String xml = QiantongSms.sendSms("15888801797", "谦通短信API测试！");
		System.out.println(xml);
//		Map<String, String> map = XmlUtil.convertResponseXml2Map("<?xml version=\"1.0\" encoding=\"utf-8\"?><result><status>0</status><sms><phone>15888801797</phone><msgid>4048172</msgid></sms><sms><phone>158888017972</phone><msgid>40481722</msgid></sms></result>");
		//if(map != null){
		//System.out.println(map.get("status"));
		//System.out.println(map.get("phone"));
		//System.out.println(map.get("msgid"));
	//}
//		List<Map<String, String>> maps=XmlUtil.convertRequestXml2Maps("<?xml version=\"1.0\" encoding=\"utf-8\"?><result><status>14</status><sms><phone>15888801797</phone><msgid>4031616</msgid><state>1</state><datetime>2014-12-30 14:42:12</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15397080079</phone><msgid>4031631</msgid><state>1</state><datetime>2014-12-30 14:42:59</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15168323720</phone><msgid>4033071</msgid><state>1</state><datetime>2014-12-30 18:14:23</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15168323720</phone><msgid>4033076</msgid><state>1</state><datetime>2014-12-30 18:15:52</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>18806711756</phone><msgid>4033305</msgid><state>1</state><datetime>2014-12-30 19:50:01</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>18806711756</phone><msgid>4033306</msgid><state>2</state><datetime>2014-12-30 19:50:07</datetime><wgcode>REJECTD</wgcode></sms><sms><phone>18806711756</phone><msgid>4033311</msgid><state>1</state><datetime>2014-12-30 19:51:20</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4034430</msgid><state>1</state><datetime>2014-12-31 09:44:25</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4034639</msgid><state>1</state><datetime>2014-12-31 10:19:15</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4036115</msgid><state>1</state><datetime>2014-12-31 14:35:07</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4048047</msgid><state>1</state><datetime>2015-01-04 14:34:30</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4048128</msgid><state>1</state><datetime>2015-01-04 14:43:09</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4048149</msgid><state>1</state><datetime>2015-01-04 14:44:50</datetime><wgcode>DELIVRD</wgcode></sms><sms><phone>15888801797</phone><msgid>4048172</msgid><state>1</state><datetime>2015-01-04 14:49:15</datetime><wgcode>DELIVRD</wgcode></sms></result>");
//		System.out.println(maps.toString());
	}
	
}
