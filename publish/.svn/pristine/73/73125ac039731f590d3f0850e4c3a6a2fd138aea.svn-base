package com.publish.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.feedback.Feedback;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.publish.dao.project.Project;
import com.publish.dao.project.ProjectDao;
import com.publish.dao.projectbug.Projectbug;
import com.publish.dao.projectbug.ProjectbugDao;

@ParentPackage("web")
@Namespace("/adminproject")
public class ProjectAction extends BaseAction {

    @Resource
    private ProjectDao projectDao;
    
    @Resource
    private ProjectbugDao projectbugDao;
    
    public Project project;
    public Projectbug bug;
    
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Projectbug getBug() {
		return bug;
	}

	public void setBug(Projectbug bug) {
		this.bug = bug;
	}

	@Action("projectlist")
    public String projectlist() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), projectDao.selectCount(map), "10");
    	List<Project> list = projectDao.select(map);
    	request.setAttribute("list", list);
    	request.setAttribute("map", map);
        return layout("project", "project", "项目", "/project/project/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }
    
	@Action("projectadd")
    public String projectadd() {
		String id = getParameter("id");
    	
    	if(StringUtil.isNotBlank(id)){
    		Project project = projectDao.selectById(Long.parseLong(id));
    		request.setAttribute("project", project);
    	}
    	
        return layout("project", "project", "项目", "/project/project/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
    @Action("projectsave")
    public String projectsave() {
    	project.setCreatetime(new Date());
		if(project.getId() != null){
			projectDao.update(project);
		}else{
    		projectDao.insert(project);
		}
    	
        return redirect("/adminproject/projectlist.html");
    }
    
    
    @Action("buglist")
    public String buglist() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("orderBy", "id desc");
    	PageUtil.getMap(map, getParameter("currentPage"), projectbugDao.selectCount(map), "10");
    	List<Projectbug> list = projectbugDao.select(map);
    	request.setAttribute("list", list);
    	request.setAttribute("map", map);
        return layout("project", "bug", "项目", "/project/bug/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }
    
	@Action("bugadd")
    public String bugadd() {
		String id = getParameter("id");
    	
    	if(StringUtil.isNotBlank(id)){
    		Projectbug bug = projectbugDao.selectById(Long.parseLong(id));
    		request.setAttribute("bug", bug);
    	}
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("progressLT", 100);
    	PageUtil.getMap(map, "1", projectDao.selectCount(map), "5");
    	List<Project> list = projectDao.select(map);
    	request.setAttribute("list", list);
    	
        return layout("project", "bug", "Bug", "/project/bug/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
	@Action("bugview")
    public String bugview() {
		String id = getParameter("id");
    	
		Projectbug bug = projectbugDao.selectById(Long.parseLong(id));
		if(StringUtil.isNotBlank(bug.getFiles())){
			String[] fs = bug.getFiles().split("，");
			List<String> pictures = new ArrayList<String>();
			for(String f : fs){
	    		pictures.add(f);
			}
			bug.setPictures(pictures);
		}
		request.setAttribute("bug", bug);
    	
        return layout("project", "bug", "Bug", "/project/bug/view.ftl", SystemConstant.LAYOUT_ADMIN);
    }
	
    @Action("bugsave")
    public String bugsave() {
    	bug.setCreatetime(new Date());
    	
    	if(bug.getStatus() == Projectbug.STATUS_VERIFIED){
    		bug.setResolvetime(new Date());
    	}
    	
		if(bug.getId() != null){
			projectbugDao.update(bug);
		}else{
    		projectbugDao.insert(bug);
		}
    	
        return redirect("/adminproject/buglist.html");
    }
    
    @Action("projectbugfixed")
	public void projectbugfixed() {
		long id = Long.parseLong(getParameter("id"));
		byte status = Byte.parseByte(getParameter("status"));
		
		Projectbug bug = new Projectbug();
		bug.setId(id);
		bug.setStatus(status);
		projectbugDao.update(bug);
	}
    
    @Action("projectbugassign")
	public void projectbugassign() {
		long id = Long.parseLong(getParameter("id"));
		String developer = getParameter("developer");
		
		Projectbug bug = new Projectbug();
		bug.setId(id);
		bug.setDeveloper(developer);
		projectbugDao.update(bug);
	}

}
