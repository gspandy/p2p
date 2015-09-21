package com.platform.util.httpclient;

import com.alibaba.fastjson.JSON;

/**
 * 错误信息
 *
 * @author eric
 * @date 2013-3-10
 */
public class ErrorLocal {
	/**
	 * 错误码
	 */
	private String error_code;
	/**
	 * 错误信息
	 */
	private String error_msg;
	
	/**
	 * 构造器
	 * @param code
	 *         错误码
	 * @param msg
	 *         错误信息
	 */
	public ErrorLocal(String code, String msg) {
		this.error_code = code;
		this.error_msg = msg;
	}
	
	/**
	 * 构造器
	 */
	public ErrorLocal() {
	}

	/**
	 * @return the error_code
	 */
	public String getError_code() {
		return error_code;
	}

	/**
	 * @param error_code the error_code to set
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * @return the error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * @param error_msg the error_msg to set
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	
	/**
	 * 转换成json字符串
	 * 
	 * @return json字符串
	 */
	public String toJson() {
		return JSON.toJSONString(this);
	}	
}
