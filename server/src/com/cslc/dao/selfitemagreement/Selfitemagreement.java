package com.cslc.dao.selfitemagreement;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Selfitemagreement extends BaseEntity {

	private String content;
	private Long id;
	private String title;

	public String getContent(){
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

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

}