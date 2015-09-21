package com.h5.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.selfitemagreement.Selfitemagreement;
import com.cslc.dao.selfitemagreement.SelfitemagreementDao;
import com.cslc.dao.selfitemproject.Selfitemproject;
import com.cslc.dao.selfitemproject.SelfitemprojectDao;
import com.cslc.service.BizService;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;

@ParentPackage("web")
@Namespace("/")
public class DetailAction extends BaseAction {
	@Resource
	private SelfitemprojectDao selfitemprojectDao;

	@Resource
	private SelfitemDao selfitemDao;

	@Resource
	private SelfitemagreementDao selfitemagreementDao;

	@Resource
	private BizService bizService;

	// 项目描述
	@Action("describe")
	public String describe() {
		String selfitemid = request.getParameter("selfitemid");

		Selfitem item = selfitemDao.selectById(Long.parseLong(selfitemid));
		Long projectid = item.getSelfitemprojectid();
		if (null != projectid) {
			Selfitemproject project = selfitemprojectDao.selectById(projectid);
			if (null != project) {
				String json = project.getJson();
				Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
				request.setAttribute("zijinyongtu", map.get("zijinyongtu"));
				request.setAttribute("diyawu", map.get("diyawu"));
				request.setAttribute("huankuanlaiyuan", map.get("huankuanlaiyuan"));
				String projectfiles = (String) map.get("projectfiles");
				List<String> list = Arrays.asList(projectfiles.split("，"));
				System.out.println(list.size());
				request.setAttribute("list", list);
			}
		}

		return layout(null, null, "项目描述", "/h5/describe.ftl", SystemConstant.LAYOUT_H5);
	}

	// 资金保障
	@Action("safeguard")
	public String safeguard() {
		String selfitemid = request.getParameter("selfitemid");

		Selfitem item = selfitemDao.selectById(Long.parseLong(selfitemid));
		Long projectid = item.getSelfitemprojectid();
		if (null != projectid) {
			Selfitemproject project = selfitemprojectDao.selectById(projectid);
			if (null != project) {
				String json = project.getJson();
				Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
				request.setAttribute("danbaofangshi", map.get("danbaofangshi"));
				request.setAttribute("danbaofang", map.get("danbaofang"));
				request.setAttribute("zijinanquan", map.get("zijinanquan"));

				String securityfiles = (String) map.get("securityfiles");
				List<String> list = Arrays.asList(securityfiles.split("，"));
				System.out.println(list.size());
				request.setAttribute("list", list);
			}
		}

		return layout(null, null, "资金保障", "/h5/safeguard.ftl", SystemConstant.LAYOUT_H5);
	}

	// 还款方式
	@Action("repayment")
	public String repayment() {
		String selfitemid = request.getParameter("selfitemid");

		Selfitem item = selfitemDao.selectById(Long.parseLong(selfitemid));
		Long projectid = item.getSelfitemprojectid();
		if (null != projectid) {
			Selfitemproject project = selfitemprojectDao.selectById(projectid);
			if (null != project) {
				String json = project.getJson();
				Map<String, Object> map = (Map<String, Object>) JSONObject.parse(json);
			}
		}

		return layout(null, null, "还款方式", "/h5/repayment.ftl", SystemConstant.LAYOUT_H5);
	}

	// 财术金融服务协议
	@Action("agreement")
	public String agreement() {
		String selfitemid = request.getParameter("selfitemid");
		Selfitem item = selfitemDao.selectById(Long.parseLong(selfitemid));
		Long projectid = item.getSelfitemprojectid();
		if (null != projectid) {
			Selfitemproject project = selfitemprojectDao.selectById(projectid);
			if (null != project) {
				Long selfitemagreementid = project.getSelfitemagreementid();
				Selfitemagreement selfitemagreement = selfitemagreementDao.selectById(selfitemagreementid);

				request.setAttribute("content", selfitemagreement.getContent());
			}

		}
		return layout(null, null, "服务协议", "/h5/agreement.ftl", SystemConstant.LAYOUT_H5);
	}

}
