package com.cslc.dao.feedback;

import java.util.Date;

import com.cslc.dao.account.Account;
import com.platform.base.BaseEntity;

public class Feedback extends BaseEntity {

	public static final byte STATUS_NOT_REPLY = 0;// 未回复
	public static final byte STATUS_REPLIED = 1;// 已回复
	public static final byte STATUS_RESOLVE = 2;// 已解决
	
	public static final byte CHANNEL_APP = 0;// 通过app反馈
	public static final byte CHANNEL_CALL = 1;// 通过电话反馈
	
	private Long accountid;
	private Date createtime;
	private Date replytime;
	private String question;
	private String manager;
	private Byte channel;
	private Long id;
	private String reply;
	private Byte status;

	private Account account;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getReplytime(){
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public String getQuestion(){
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getManager(){
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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

	public String getReply(){
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}