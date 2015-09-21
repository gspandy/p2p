package com.platform.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public final class StringUtil {

	private static final Logger log = Logger.getLogger(StringUtil.class);

	/**
	 * 小写金额转大写金额 add by hubin 2014-12-12 09:06
	 */
	public static String digitUppercase(double n){
        String fraction[] = {"角", "分"};
        String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String unit[][] = {{"元", "万", "亿"},
                     {"", "拾", "佰", "仟"}};
 
        String head = n < 0? "负": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
        }
        if(s.length()<1){
            s = "整";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
        }
        return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
    }
	
	/**
	 * 定义年月日的时间常量 add by fangwenjie 2014-10-31 15:48
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_TIME_STAMP = "yyyyMMddHHmmss";

	public static String convertDateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String convertDateToString(Date date, String dateformat) {// yyyy/MM/dd
		if (date == null) {
			return null;
		}
		if (isBlank(dateformat)) {
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(dateformat).format(date);
	}

	public static Date convertStringToDate(String str, String dateformat) {// yyyy/MM/dd
		if (!isNotBlank(dateformat)) {
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			return new SimpleDateFormat(dateformat).parse(str);
		} catch (ParseException e) {
			log.error(e);
			return null;
		}
	}
	
	public static Date convertStringToDate(String str) {
	    return convertStringToDate(str, null);
	}

	public static String getDate(String dateformat) {
		return convertDateToString(new Date(), dateformat);
	}

	public static String getWeek(Date date) {
		int day = date.getDay();
		String dayCn = "星期";
		switch (day) {
		case 1:
			dayCn += '一';
			break;
		case 2:
			dayCn += '二';
			break;
		case 3:
			dayCn += '三';
			break;
		case 4:
			dayCn += '四';
			break;
		case 5:
			dayCn += '五';
			break;
		case 6:
			dayCn += '六';
			break;
		case 7:
			dayCn += '七';
			break;
		default:
			break;
		}
		return dayCn;
	}

	public static boolean isNumeric(String str) {
		return isPattern(str, "[0-9.]*");
	}

	public static boolean isEnglish(String str) {
		return isPattern(str, "[0-9a-z]*");
	}

	public static boolean isPattern(String str, String rex) {
		if (isNotBlank(str)) {
			Pattern pattern = Pattern.compile(rex);
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}
		return false;
	}

	// 将时间转换为汉字
	public static String timeToCn(Date date) {
		if (date == null) {
			return "";
		}
		long cha = (new Date().getTime() - date.getTime()) / 1000;
		long day = cha / (24 * 3600);
		long hour = cha % (24 * 3600) / 3600;
		long minute = cha % 3600 / 60;
		String str = "";
		if (day >= 1) {
			str = day + "天前";
		} else if (day < 1 && hour >= 1) {
			str = hour + "小时前";
		} else if (hour < 1 && minute > 1) {
			str = minute + "分钟前";
		} else {
			str = "刚才";
		}
		return str;
	}

	public static boolean isNotBlank(String param) {
		return !isBlank(param);
	}

	public static boolean isBlank(String param) {
		if (param == null || "".equals(param)) {
			return true;
		}
		return false;
	}

	public static String convertPageToDatabase(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		str = str.replace("\r\n", "<br>").replace("\n", "<br>");
		return str;
	}

	public static String convertDatabaseToPage(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		str = str.replace("<br>", "\r\n");
		return str;
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr.replace("&nbsp;", "").replace(" ", "").replace("　", "").replace("	", "").replace("\r", "").replace("\n", "");
		} catch (Exception e) {
			log.error(e);
		}

		return textStr;
	}

	/**
	 * 截取字符串并添加后缀 truncate("ssssss", 5, "...") --> sssss...
	 * 
	 * @param source
	 * @param length
	 * @param suffix
	 * @return
	 */
	public static String truncate(String source, int length, String suffix) {
		if (isNotBlank(source) && source.length() > length) {
			source = source.substring(0, length) + (suffix != null ? suffix : "...");
		}
		return source;
	}

	/**
	 * 固定长度并填充字符 fill("sss", 5, "-") --> sss--
	 * 
	 * @param source
	 * @param length
	 * @param placeholder
	 * @return
	 */
	public static String fill(String source, int length, String placeholder) {
		if (source != null) {
			int len = source.length() - length;
			if (source.length() > length) {
				source = source.substring(0, len);
			}
			for (int i = 0; i < Math.abs(len); i++) {
				source += placeholder;
			}
		}
		return source;
	}

	/**
	 * 替换字符
	 * 
	 * @param source
	 * @param str
	 * @param placeholder
	 * @return
	 */
	public static String replace(String source, String str, String placeholder) {
		if (isNotBlank(source)) {
			return source.replace(str, placeholder);
		}
		return source;
	}

	public static boolean contains(String source, String str) {
		if (isNotBlank(source) && isNotBlank(str)) {
			return source.contains(str);
		}
		return false;
	}

	public static boolean isAllowedRule(String rule) {
		String[] ALLOWED_IMAGE_RULES = { "200-200", "50-50" };
		for (String s : ALLOWED_IMAGE_RULES) {
			if (s.equals(rule)) {
				return true;
			}
		}
		return false;
	}

	// 0001
	public static String getNo(int index, int length) {
		String in = String.valueOf(index);
		String ret = in;
		for (int i = 0; i < length - in.length(); i++) {
			ret = "0" + ret;
		}
		return ret;
	}

	public static String encryptName(String name) {
		return "**" + name.substring(1);
	}

	public static String encryptCard(String card) {
		return card.substring(0, 6) + "****" + card.substring(card.length() - 4);
	}

	public static String encryptPhone(String phone) {
		return phone.substring(0, 3) + "****" + phone.substring(7, 11);
	}

	public static String encryptEmail(String email) {
		String enc = email.substring(3, email.lastIndexOf("@"));
		String enc2 = "";
		for (int i = 0; i < enc.length(); i++) {
			enc2 += "*";
		}
		return email.substring(0, 3) + enc2 + email.substring(email.lastIndexOf("@"));
	}

	/**
	 * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
	 * 
	 * @param obj
	 *            被验证的对象
	 * @param message
	 *            异常信息
	 */
	public static void notEmpty(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof String && obj.toString().trim().length() == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
		if (obj instanceof Map && ((Map) obj).isEmpty()) {
			throw new IllegalArgumentException(message + " must be specified");
		}
	}

	public static String getFormatNumber(Double d, String format) {
		if (d == null) {
			d = 0d;
		}
		DecimalFormat df = new DecimalFormat(format);// "0.00"
		return df.format(d);
	}

	public static String getFormatAmount(Double amount) {
		if (amount == null) {
			amount = 0d;
		}
		DecimalFormat bf = new DecimalFormat("###,##0.00");
		return bf.format(amount);
	}
	
	public static String getFormatAmount(BigDecimal amount) {
	    if (null == amount) {
	        amount = new BigDecimal(0);
	    }
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(amount);
	}

	public static String getIp(HttpServletRequest request) {
		String realip = request.getHeader("x-real-ip");
		String forwardip = request.getHeader("x-forwarded-for");
		String remoteip = request.getRemoteAddr();
//		log.error("x-real-ip:"+realip);
//		log.error("x-forwarded-for:"+forwardip);
//		log.error("remote-ip:"+remoteip);
		
		String ip = realip;
		if(isBlank(ip)){
			ip = forwardip;
		}
		if(isBlank(ip)){
			ip = remoteip;
		}
		return ip;
	}

	public static Date getCurrentMonthFirstDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getNextMonth(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
		return calendar.getTime();
	}
	
	public static Date getCurrentMonthLastDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getNextDate(Date date, int day) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		return calendar.getTime();
	}
	
	public static String getRandomCode(int length, boolean isOnlyNumber, boolean isOnlyLowerCase) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if (isOnlyNumber) {
				charOrNum = "num";
			}
			if ("char".equalsIgnoreCase(charOrNum)) {// 字符串
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;// 取得大写字母还是小写字母
				if (isOnlyLowerCase) {
					choice = 97;
				}
				char v = (char) (choice + random.nextInt(26));
				if (v != 'l' && v != 'O') {
					val += v;
				} else {
					i--;
				}
			} else if ("num".equalsIgnoreCase(charOrNum)) {// 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	public static int getMaxIndex(double[] ds) {
		int k = 0;
		double max = ds[k];
		for (int i = 1; i < ds.length; i++) {
			max = Math.max(max, ds[i]);
			if (max == ds[i]) {
				k = i;
			}
		}
		return k;
	}

	public static int getMinIndex(double[] ds) {
		int k = 0;
		double min = ds[k];
		for (int i = 1; i < ds.length; i++) {
			min = Math.min(min, ds[i]);
			if (min == ds[i]) {
				k = i;
			}
		}
		return k;
	}

	public static double getMinVal(double[] ds) {
		double min = ds[0];
		for (int i = 1; i < ds.length; i++) {
			min = Math.min(min, ds[i]);
		}
		return min;
	}

	public static double getMaxVal(double[] ds) {
		double max = ds[0];
		for (int i = 1; i < ds.length; i++) {
			max = Math.max(max, ds[i]);
		}
		return max;
	}
	
	/**
     * 根据key获取手机客户端的参数值
     * 
     * @param request
     * @param key:platform,appversion,osversion,phone,terminalid,channel
     * @return
     */
    public static String getUserAgent(HttpServletRequest request, String key) {
    	String result = null;
        String agent = request.getHeader("user-agent");
        if (StringUtil.isNotBlank(agent)) {
            try {
                result = String.valueOf(JSONObject.parseObject(agent).get(key));
            } catch (Exception e) {
                return null;
            }
        }
        return result;
    }
    
    public static String getPlatform(HttpServletRequest request){
    	return getUserAgent(request, "platform");
	}
    
    public static int getBetweenDays(Date startdate, Date enddate){
    	if(enddate == null){
    		enddate = new Date();
    	}
    	int days = (int) ((enddate.getTime() - startdate.getTime()) / (1000 * 3600 * 24));
    	if(enddate.getTime() < startdate.getTime() && days == 0){
    		return 0;
    	}else{
    		return days + 1;
    	}
    }
    
    public static int getBetweenIncomeDays(Date startdate, Date enddate){
    	if(enddate == null){
    		enddate = new Date();
    	}
    	int days = (int) ((enddate.getTime() - startdate.getTime()) / (1000 * 3600 * 24));
    	if(enddate.getTime() < startdate.getTime() && days == 0){
    		return 0;
    	}else{
    		return days;
    	}
    }
    
    // 是否在时间段内
 	public static boolean isBetweenTime(String starttime, String endtime){
 		Date date = new Date();
 		String prefix = StringUtil.convertDateToString(date, "yyyy-MM-dd ");
 		Date startTime = StringUtil.convertStringToDate(prefix + starttime, "yyyy-MM-dd HH:mm:ss");
 		Date endTime = StringUtil.convertStringToDate(prefix + endtime, "yyyy-MM-dd HH:mm:ss");
 		if (date.getTime() >= startTime.getTime() && date.getTime() < endTime.getTime()){
 			return true;
 		}
 		return false;
 	}
 	
 	public static String getRestTime(String endtime){
 		Date date = new Date();
 		String prefix = StringUtil.convertDateToString(date, "yyyy-MM-dd ");
 		Date endTime = StringUtil.convertStringToDate(prefix + endtime, "yyyy-MM-dd HH:mm:ss");
 		double minutes = ((double) (endTime.getTime() - new Date().getTime())) / 1000 / 60;
 		if(minutes < 1){
 			return Math.round(minutes * 60) + "秒钟";
 		}
 		return Math.round(minutes) + "分钟";
 	}
    
    public static String getDefaultTimeStamp() {
        return new SimpleDateFormat(DEFAULT_TIME_STAMP).format(new Date()); 
    }
    
    public static Date getDateTime() {//获得当日零点
        return convertStringToDate(getDate("yyyy/MM/dd 00:00:00"), "yyyy/MM/dd 00:00:00"); 
    }
    
    public static Integer convertLongtoInteger(long a){
    	return Integer.valueOf(String.valueOf(a)).intValue();
    }
    
    //精确的加法运算
    public   static   double   add(double   v1,double   v2,double v3){   
        BigDecimal   b1   =   new   BigDecimal(Double.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));
        BigDecimal   b3   =   new   BigDecimal(Double.toString(v3));
        return   b1.add(b2).add(b3).doubleValue(); 
    } 
    
  //精确的加法运算
    public   static   double   add(double   v1,double   v2){   
        BigDecimal   b1   =   new   BigDecimal(Double.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));   
        return   b1.add(b2).doubleValue(); 
    } 
    
  //精确的减法运算
    public   static   double   sub(double   v1,double   v2){   
        BigDecimal   b1   =   new   BigDecimal(Double.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));   
        return   b1.subtract(b2).doubleValue();   
    }
    
	public static void main(String[] args) {
		// System.out.println(convertDateToString(getCurrentMonthLastDate(new
		// Date(), 4)));
		// System.out.println(getNextDate(new Date(), 3));
		// System.out.println(getRandomCode(6, true, false));
		// System.out.println(getFormatAmount(10.12));
		// System.out.println(encryptCard("1234567890123456789"));
	    System.out.println(getDefaultTimeStamp());
	    System.out.println("20150120193433".length());
	}

}
