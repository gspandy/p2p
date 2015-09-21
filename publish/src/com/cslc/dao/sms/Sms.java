package com.cslc.dao.sms;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Sms extends BaseEntity {

	public static final byte CHANNEL_QIANTONG = 0;// 谦通
	public static final byte CHANNEL_YIMEI = 1;// 亿美
	
	public static final byte STATUS_WAITTING = 0;// 待发送
	public static final byte STATUS_POSTED = 1;// 已提交
	public static final byte STATUS_RECEIVED = 2;// 已收到
	public static final byte STATUS_VERIFIED = 3;// 已验证
	public static final byte STATUS_FAILURE = 4;// 发送失败
	
	public static final byte CATEGORY_ACCOUNT = 0;// 账户
	public static final byte CATEGORY_ASSET = 1;// 资产
	public static final byte CATEGORY_NOTIFICATION = 2;// 通知
	public static final byte CATEGORY_ACTIVITY = 3;// 活动
	public static final byte CATEGORY_PUBLISH = 4;// 后台
	
	private Date createtime;
	private Date sendtime;
	private String verifycode;
	private String mobile;
	private Byte channel;
	private Long id;
	private Integer category;
	private Date verifytime;
	private String content;
	private Byte status;
	private String orderid;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getVerifycode(){
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getChannel(){
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCategory(){
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Date getVerifytime(){
		return verifytime;
	}

	public void setVerifytime(Date verifytime) {
		this.verifytime = verifytime;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}