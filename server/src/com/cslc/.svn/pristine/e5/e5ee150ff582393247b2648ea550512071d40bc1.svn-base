package com.cslc.dao.question;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Question extends BaseEntity {

	public static final byte STATUS_DISABLE = 0;// 无效
	public static final byte STATUS_ENABLE = 1;// 有效
	
	public static final byte CATEGORY_NEWER = 0;// 新手
	public static final byte CATEGORY_ACCOUNT = 1;// 账号
	public static final byte CATEGORY_ASSET = 2;// 资产
	public static final byte CATEGORY_SELFITEM = 3;// 产品
	public static final byte CATEGORY_PAY = 4;// 支付
	public static final byte CATEGORY_CASHBACK = 5;// 回款
	public static final byte CATEGORY_SECURITY = 6;// 安全
	public static final byte CATEGORY_BONUS = 7;// 红包
	public static final byte CATEGORY_SCORE = 8;// 积分
	
	private Date createtime;
	private String question;
	private String answer;
	private Long id;
	private Integer category;
	private Byte status;
	private Integer serialno;

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getQuestion(){
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer(){
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getSerialno(){
		return serialno;
	}

	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}

}