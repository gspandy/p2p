package com.cslc.dao.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private List<Question> list;
	
	
	public static List<Map<Byte, String>> getCategoryList(){
		List<Map<Byte, String>> l = new ArrayList<Map<Byte, String>>();
		Map<Byte, String> m = new HashMap<Byte, String>();
		m.put(CATEGORY_NEWER, "新手引导");
		l.add(m);
		
		Map<Byte, String> m1 = new HashMap<Byte, String>();
		m1.put(CATEGORY_ACCOUNT, "账号");
		l.add(m1);
		
		Map<Byte, String> m2 = new HashMap<Byte, String>();
		m2.put(CATEGORY_ASSET, "资产");
		l.add(m2);
		
		Map<Byte, String> m3 = new HashMap<Byte, String>();
		m3.put(CATEGORY_SELFITEM, "产品");
		l.add(m3);
		
		Map<Byte, String> m4 = new HashMap<Byte, String>();
		m4.put(CATEGORY_PAY, "支付");
		l.add(m4);
		
		Map<Byte, String> m5 = new HashMap<Byte, String>();
		m5.put(CATEGORY_CASHBACK, "回款");
		l.add(m5);
		
		Map<Byte, String> m6 = new HashMap<Byte, String>();
		m6.put(CATEGORY_SECURITY, "安全");
		l.add(m6);
		
		Map<Byte, String> m7 = new HashMap<Byte, String>();
		m7.put(CATEGORY_BONUS, "红包");
		l.add(m7);
		
		Map<Byte, String> m8 = new HashMap<Byte, String>();
		m8.put(CATEGORY_SCORE, "积分");
		l.add(m8);
		
		return l;
	}
	
	public List<Question> getList() {
		return list;
	}
	
	public void setList(List<Question> list) {
		this.list = list;
	}

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