package com.site.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.platform.base.BaseAction;

@ParentPackage("app")
@Namespace("/")
public class TestAction extends BaseAction {

	@Action("test")
	public void test() {
		
	}

}

