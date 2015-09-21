package com.server.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.platform.base.BaseAction;

@ParentPackage("app")
@Namespace("/ddsc")
public class RedirectAction extends BaseAction  {
	
	@Resource
	private SystemdefDao systemdefDao;
	
	@Action("error")
	public void error() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("returnCode", "-1");
		result.put("returnMsg", "亲，网络不给力哦");
		print(result);
		return;
	}
	

	@Action("login")
	public void login() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("returnCode", "-2");
		result.put("returnMsg", "请重新登录哦");
		print(result);
		return;
	}
	
}
