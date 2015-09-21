package com.cslc.dao.shorturl;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Shorturl extends BaseEntity {

	private Date createtime;
	private String code;
	private Long id;
	private String url;

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}