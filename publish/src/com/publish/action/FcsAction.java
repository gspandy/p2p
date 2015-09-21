package com.publish.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.systemlog.Systemlog;
import com.cslc.dao.systemlog.SystemlogDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;

@ParentPackage("web")
@Namespace("/adminfcs")
public class FcsAction extends BaseAction {
   
    @Resource
    private SystemlogDao systemlogDao;
    
	@Action("checkreport")
	public String checkreport() {
		Map<String, Object> systemlogMap = new HashMap<String, Object>();
		systemlogMap.put("category", Systemlog.CATEGORY_SETTLE);
		systemlogMap.put("orderBy", "createtime desc");
		PageUtil.getMap(systemlogMap, getParameter("currentPage"), systemlogDao.selectCount(systemlogMap), "10");
		List<Systemlog> systemlogList = systemlogDao.select(systemlogMap);
			request.setAttribute("list", systemlogList);
			request.setAttribute("map", systemlogMap);
			return layout("fcs", "checkreport", "对账报告", "/fcs/settle/check.ftl", SystemConstant.LAYOUT_ADMIN);
	}
	
    @Action("index")
    public String index() {
        return layout(null, null, "index", "/test.ftl", SystemConstant.LAYOUT_ADMIN);
    }

}
