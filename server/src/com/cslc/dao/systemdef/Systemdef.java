package com.cslc.dao.systemdef;

import com.platform.base.BaseEntity;

public class Systemdef extends BaseEntity {
	
	
    public static String     BANNERS             = "BANNERS";
    
    public static String     SECURITY_BANK             = "SECURITY_BANK";
    
    public static String     APP_START_IMAGE             = "APP_START_IMAGE";
    
    public static String     IOS_VERSION             = "IOS_VERSION";
    
    public static String     ANDROID_VERSION             = "ANDROID_VERSION";
    
    public static String     SMS_CHANNEL             = "SMS_CHANNEL";
    
    public static String     APP_WARNNING             = "APP_WARNNING";
    
    public static String     FEE_LIANLIAN_RATE             = "FEE_LIANLIAN_RATE";
    
    
    public static String     SMS_REGIST             = "SMS_REGIST";
    
    public static String     SMS_FORGET_PWD                    = "SMS_FORGET_PWD";
    
	private String v;
	private String category;
	private String k;
	private Byte status;
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	
	
	

	
}