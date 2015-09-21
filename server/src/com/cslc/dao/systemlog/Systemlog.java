package com.cslc.dao.systemlog;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Systemlog extends BaseEntity {

	public static final byte CATEGORY_CHECK= 0;// 对账系统
	public static final byte CATEGORY_SETTLE = 1;// 结算系统
	public static final byte CATEGORY_ANDROID_EXCEPTION = 2;// 安卓异常
	public static final byte CATEGORY_IOS_EXCEPTION = 3;// iOS异常
	
	private String content;
	private Date createtime;
	private Byte category;
	private Long accountid;

	public String getContent(){
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Byte getCategory(){
		return category;
	}

	public void setCategory(Byte category) {
		this.category = category;
	}

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

}