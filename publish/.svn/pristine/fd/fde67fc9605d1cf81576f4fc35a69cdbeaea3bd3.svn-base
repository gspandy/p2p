package com.publish.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.analysedayplatform.Analysedayplatform;
import com.cslc.dao.analysedayplatform.AnalysedayplatformDao;
import com.cslc.dao.picture.Picture;
import com.cslc.dao.picture.PictureDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.publish.CookieUtil;

@ParentPackage("web")
@Namespace("/admincslc")
public class CslcAction extends BaseAction {
	
	@Resource
    private PictureDao pictureDao;
	
	@Resource
    private AnalysedayplatformDao analysedayplatformDao;

	private File[]         upload;
    private String[]       uploadContentType;
    private String[]       uploadFileName;

    public File[] getUpload() {
        return upload;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public String[] getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String[] getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
	
    @Action("index")
    public String index() {    	
		
    	boolean isLogin = CookieUtil.isLogin(request);
    	
    	Map<String, Object> analysedayplatformMap = new HashMap<String, Object>();
    	analysedayplatformMap.put("createtimeGEt", StringUtil.getNextDate(StringUtil.getDateTime(), -12));
    	analysedayplatformMap.put("orderBy", "createtime asc");
    	List<Analysedayplatform> analysedayplatformList = analysedayplatformDao.select(analysedayplatformMap);
    	request.setAttribute("list", analysedayplatformList);
		request.setAttribute("map", analysedayplatformMap);		
		
        if(isLogin){
        	return layout(null, null, "我的信息", "/user/index.ftl", SystemConstant.LAYOUT_ADMIN);
        }
        return redirect("/to_login.html");
    }
    
    @Action("pictureadd")
    public String pictureadd() {
    	String channel = getParameter("channel");
    	request.setAttribute("channel", channel);
        return layout(channel, "picture", "图片", "/cslc/picture/add.ftl", SystemConstant.LAYOUT_ADMIN);
    }

    @Action("picturesave")
    public String picturesave() {
    	String channel = getParameter("channel");
    	
        // 图片上传
        if (upload != null) {
        	String files = "";
            for (int i = 0; i < upload.length; i++) {
                String fileurl = uploadLocal("/" + channel, upload[i], uploadFileName[i]);

                Picture p = new Picture();
                p.setChannel(channel);
                p.setCreatetime(new Date());
                p.setManager(CookieUtil.getLoginUser(request).getRealname());
                p.setStatus(Picture.STATUS_ENABLE);
                p.setUrl(fileurl);
                pictureDao.insert(p);
                
                files += "，" + fileurl;
            }
            request.setAttribute("files", files.substring(1));
        }
        request.setAttribute("channel", channel);
        return layout(channel, "picture", "图片", "/cslc/picture/result.ftl", SystemConstant.LAYOUT_ADMIN);
    }

    @Action("picturelist")
    public String picturelist() {
    	String channel = getParameter("channel");
    	
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channel", channel);
        map.put("orderBy", "createtime desc");
        PageUtil.getMap(map, getParameter("currentPage"), pictureDao.selectCount(map));
        List<Picture> list = pictureDao.select(map);
        request.setAttribute("map", map);
        request.setAttribute("list", list);
        request.setAttribute("channel", channel);
        return layout(channel, "picture", "图片", "/cslc/picture/list.ftl", SystemConstant.LAYOUT_ADMIN);
    }

}
