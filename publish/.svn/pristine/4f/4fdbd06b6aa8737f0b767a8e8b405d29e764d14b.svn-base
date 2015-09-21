package com.publish.dao.projectbug;

import java.util.Date;
import java.util.List;

import com.platform.base.BaseEntity;

public class Projectbug extends BaseEntity {

	public static final byte STATUS_ACTIVE = 0;// 未解决
	public static final byte STATUS_RESOLVED = 1;// 已解决
	public static final byte STATUS_VERIFIED = 2;// 已验证
	public static final byte STATUS_INVALID = 3;// 无法重现
	public static final byte STATUS_OTHERS = 4;// 外部原因
	public static final byte STATUS_REOPEN = 5;// 重新打开
	public static final byte STATUS_CLOSED = 6;// 已关闭
	
	private String content;
	private Date resolvetime;
	private Date createtime;
	private Long id;
	private String files;
	private String tester;
	private String title;
	private Byte status;
	private String developer;
	private Long projectid;
	
	private List<String> pictures;
	
	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getResolvetime(){
		return resolvetime;
	}

	public void setResolvetime(Date resolvetime) {
		this.resolvetime = resolvetime;
	}

	public Date getCreatetime(){
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFiles(){
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getTester(){
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Byte getStatus(){
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getDeveloper(){
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public Long getProjectid(){
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

}