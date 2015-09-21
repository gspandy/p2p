package com.cslc.dao.tasklog;

import java.util.Date;

import com.cslc.dao.task.Task;
import com.platform.base.BaseEntity;

public class Tasklog extends BaseEntity {

	private Long accountid;
	private Integer score;
	private Date createtime;
	private Long id;
	private Long taskid;
	
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Long getAccountid(){
		return accountid;
	}

	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}

	public Integer getScore(){
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public Long getTaskid(){
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

}