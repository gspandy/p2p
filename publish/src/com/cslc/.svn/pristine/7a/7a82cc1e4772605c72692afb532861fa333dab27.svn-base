package com.cslc.dao.picture;

import java.util.Date;
import com.platform.base.BaseEntity;

public class Picture extends BaseEntity {

	public static final byte STATUS_DISABLE = 0;// 无效
	public static final byte STATUS_ENABLE = 1;// 有效
	
	public static final String CHANNEL_SELFITEM = "selfitem";
	public static final String CHANNEL_CMS = "cms";
	public static final String CHANNEL_BUG = "bug";
	
	private Date createtime;
	private String manager;
	private Long id;
	private String channel;
	private String url;
	private String content;
	private Byte status;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getManager(){
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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