package com.cslc.dao.account;

import java.util.Date;

import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountconfig.Accountconfig;
import com.platform.base.BaseEntity;

public class Account extends BaseEntity {
	
	public static final byte STATUS_NORMAL = 0;// 普通
	public static final byte STATUS_FREEZE_ASSET = 1;// 已冻结资金
	public static final byte STATUS_LIMIT_LOGIN = 2;// 已限制登录
	
	public static final byte CATEGORY_USER = 0;// 用户
	public static final byte CATEGORY_EMPLOYER = 1;// 员工
	public static final byte CATEGORY_COMPANY = 2;// 企业

	private Date createtime;
	private String dynamicpwd;
	private String loginpwd;
	private String invitecode;
	private Long inviteid;
	private String channel;
	private String mobile;
	private Integer tradecount;
	private String idcardno;
	private Date lastlogintime;
	private String realname;
	private Date lasttradetime;
	private Long id;
	private Byte category;
	private Byte status;
	
	private Accountasset accountasset;
	private Accountconfig accountconfig;

	public Accountconfig getAccountconfig() {
		return accountconfig;
	}

	public void setAccountconfig(Accountconfig accountconfig) {
		this.accountconfig = accountconfig;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDynamicpwd(){
		return dynamicpwd;
	}

	public void setDynamicpwd(String dynamicpwd) {
		this.dynamicpwd = dynamicpwd;
	}

	public String getLoginpwd(){
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getInvitecode(){
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public Long getInviteid(){
		return inviteid;
	}

	public void setInviteid(Long inviteid) {
		this.inviteid = inviteid;
	}

	public String getChannel(){
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getTradecount(){
		return tradecount;
	}

	public void setTradecount(Integer tradecount) {
		this.tradecount = tradecount;
	}

	public String getIdcardno(){
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public Date getLastlogintime(){
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getRealname(){
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getLasttradetime(){
		return lasttradetime;
	}

	public void setLasttradetime(Date lasttradetime) {
		this.lasttradetime = lasttradetime;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getCategory(){
		return category;
	}

	public void setCategory(Byte category) {
		this.category = category;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Accountasset getAccountasset() {
		return accountasset;
	}

	public void setAccountasset(Accountasset accountasset) {
		this.accountasset = accountasset;
	}

	
}