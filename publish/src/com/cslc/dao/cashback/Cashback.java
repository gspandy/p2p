package com.cslc.dao.cashback;

import java.util.Date;

import com.cslc.dao.accountbankcard.Accountbankcard;
import com.cslc.dao.selfitem.Selfitem;
import com.platform.base.BaseEntity;

public class Cashback extends BaseEntity {
	
	public static final byte STATUS_NOT_SUBMIT = 0;// 待付款
	public static final byte STATUS_SYSTEM_CHECKED = 1;// 系统已核实
	public static final byte STATUS_CAIWU_CHECKED = 2;// 财务已对账
	public static final byte STATUS_SUBMIT = 3;// 已提交银行

	private Long accountid;
	private Date submittime;
	private Byte paychannel;
	private Double amount;
	private Double income;
	private Date createtime;
	private Long bankcardid;
	private Double bonus;
	private Double fee;
	private Long id;
	private Byte status;
	private String selfitemid;
	
	private Accountbankcard accountbankcard;

	public Accountbankcard getAccountbankcard() {
		return accountbankcard;
	}

	public void setAccountbankcard(Accountbankcard accountbankcard) {
		this.accountbankcard = accountbankcard;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Date getSubmittime(){
		return submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}

	public Byte getPaychannel(){
		return paychannel;
	}

	public void setPaychannel(Byte paychannel) {
		this.paychannel = paychannel;
	}

	public Double getAmount(){
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getBankcardid(){
		return bankcardid;
	}

	public void setBankcardid(Long bankcardid) {
		this.bankcardid = bankcardid;
	}

	public Double getBonus(){
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getFee(){
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	public String getSelfitemid(){
		return selfitemid;
	}

	public void setSelfitemid(String selfitemid) {
		this.selfitemid = selfitemid;
	}
}