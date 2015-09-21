package com.task.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.platform.base.BaseAction;
import com.task.settlesystem.settle.SettleTask;

@ParentPackage("app")
@Namespace("/")
public class TestAction extends BaseAction {
	
	@Resource
	private SettleTask settleTask;
		
	@Action("test")
	public void test() {
		settleTask.excute();
	}

}

