package com.cslc.dao.selfitemproject;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Selfitemproject extends BaseEntity {

	private Long id;
	private String title;
	private String json;
	private Long selfitemagreementid;

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJson(){
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Long getSelfitemagreementid() {
		return selfitemagreementid;
	}

	public void setSelfitemagreementid(Long selfitemagreementid) {
		this.selfitemagreementid = selfitemagreementid;
	}

}