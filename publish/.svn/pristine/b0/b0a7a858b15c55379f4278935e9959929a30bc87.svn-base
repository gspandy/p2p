package com.publish.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.account.AccountDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.selfitemagreement.Selfitemagreement;
import com.cslc.dao.selfitemagreement.SelfitemagreementDao;
import com.cslc.dao.selfitemproject.Selfitemproject;
import com.cslc.dao.selfitemproject.SelfitemprojectDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;

@ParentPackage("web")
@Namespace("/adminpbs")
public class PbsAction extends BaseAction {

    @Resource
    private AccountDao accountDao;
    
    @Resource
    private SelfitemDao selfitemDao;
    
    @Resource
    private SelfitemprojectDao selfitemprojectDao;
    
    @Resource
    private SelfitemagreementDao selfitemagreementDao;
    
    public Selfitemproject project;
    public Selfitem selfitem;
    public Selfitemagreement agreement;
    
    public Selfitemproject getProject() {
		return project;
	}

	public void setProject(Selfitemproject project) {
		this.project = project;
	}

	public Selfitem getSelfitem() {
		return selfitem;
	}

	public void setSelfitem(Selfitem selfitem) {
		this.selfitem = selfitem;
	}

	public Selfitemagreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Selfitemagreement agreement) {
		this.agreement = agreement;
	}

	
	// 项目
	
	
	@Action("projectlist")
    public String projectlist() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), selfitemprojectDao.selectCount(map), "10");
    	List<Selfitemproject> list = selfitemprojectDao.select(map);
    	request.setAttribute("list", list);
    	request.setAttribute("map", map);
        return layout("pbs", "project", "项目", "/pbs/project/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }
    
	@Action("projectadd")
    public String projectadd() {
		String id = getParameter("id");
    	
    	if(StringUtil.isNotBlank(id)){
    		Selfitemproject project = selfitemprojectDao.selectById(Long.parseLong(id));
    		request.setAttribute("project", project);
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Selfitemagreement> list = selfitemagreementDao.select(map);
    	request.setAttribute("list", list);
    	
        return layout("pbs", "project", "项目", "/pbs/project/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
    @Action("projectsave")
    public String projectsave() {
    	String zijinyongtu = getParameter("zijinyongtu");
    	String diyawu = getParameter("diyawu");
    	String huankuanlaiyuan = getParameter("huankuanlaiyuan");
    	String projectfiles = getParameter("projectfiles");
    	String danbaofangshi = getParameter("danbaofangshi");
    	String danbaofang = getParameter("danbaofang");
    	String zijinanquan = getParameter("zijinanquan");
    	String securityfiles = getParameter("securityfiles");
    	
    	project.addField("zijinyongtu", zijinyongtu);
    	project.addField("diyawu", diyawu);
    	project.addField("huankuanlaiyuan", huankuanlaiyuan);
    	project.addField("projectfiles", projectfiles);
    	project.addField("danbaofangshi", danbaofangshi);
    	project.addField("danbaofang", danbaofang);
    	project.addField("zijinanquan", zijinanquan);
    	project.addField("securityfiles", securityfiles);
    	
		if(project.getId() != null){
			selfitemprojectDao.update(project);
		}else{
    		selfitemprojectDao.insert(project);
		}
    	
        return redirect("/adminpbs/projectlist.html");
    }
    
    
    // 协议
    
    
    @Action("agreementlist")
    public String agreementlist() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), selfitemagreementDao.selectCount(map), "10");
    	List<Selfitemagreement> list = selfitemagreementDao.select(map);
    	request.setAttribute("list", list);
    	request.setAttribute("map", map);
        return layout("pbs", "agreement", "协议", "/pbs/agreement/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }
    
	@Action("agreementadd")
    public String agreementadd() {
		String id = getParameter("id");
    	
    	if(StringUtil.isNotBlank(id)){
    		Selfitemagreement agreement = selfitemagreementDao.selectById(Long.parseLong(id));
    		request.setAttribute("agreement", agreement);
    	}
    	
        return layout("pbs", "agreement", "协议", "/pbs/agreement/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
    @Action("agreementsave")
    public String agreementsave() {
		if(agreement.getId() != null){
			selfitemagreementDao.update(agreement);
		}else{
    		selfitemagreementDao.insert(agreement);
		}
    	
        return redirect("/adminpbs/agreementlist.html");
    }

    
    // 产品
    
    
    @Action("selfitemlist")
    public String selfitemlist() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), selfitemDao.selectCount(map), "10");
    	List<Selfitem> list = selfitemDao.select(map);
    	request.setAttribute("list", list);
    	request.setAttribute("map", map);
        return layout("pbs", "selfitem", "产品", "/pbs/selfitem/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }
    
	@Action("selfitemadd")
    public String selfitemadd() {
		String id = getParameter("id");
    	
    	if(StringUtil.isNotBlank(id)){
    		Selfitem selfitem = selfitemDao.selectById(Long.parseLong(id));
    		request.setAttribute("selfitem", selfitem);
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), selfitemprojectDao.selectCount(map), "10");
    	List<Selfitemproject> list = selfitemprojectDao.select(map);
    	request.setAttribute("list", list);
    	
        return layout("pbs", "selfitem", "产品", "/pbs/selfitem/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
    @Action("selfitemsave")
    public String selfitemsave() {
    	selfitem.setEndtime(StringUtil.convertStringToDate(StringUtil.convertDateToString(selfitem.getEndtime(), "yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
    	selfitem.setRestamount(selfitem.getTotalamount());
		selfitem.setUnitincome(selfitem.getTotalamount() * (selfitem.getAnnualrate() + selfitem.getAnnualrateextra()) / 365 * selfitem.getincomeDays());
    	if(selfitem.getId() != null && selfitem.getStatus() == Selfitem.STATUS_NOT_SALE){
			selfitemDao.update(selfitem);
		}else{
			selfitem.setCreatetime(new Date());
    		selfitemDao.insert(selfitem);
		}
    	
        return redirect("/adminpbs/selfitemlist.html");
    }

}
