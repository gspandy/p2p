package com.h5.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;
import com.cslc.service.BizService;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.platform.util.encrypt.AES;

@ParentPackage("web")
public class InviteAction extends BaseAction{
	
    @Resource
    private AccountDao        accountDao;
    
    @Resource
    private BizService        bizService;
    
    @Resource
    private BonusDao        bonusDao;
    
	 //邀请好友
    @Action("invite")
    public String invite() {
      String login = request.getHeader("login");
      String code = request.getHeader("code");
//        String login = getParameter("login");
//        String code =getParameter("code");
		String type = getParameter("type");
		String invitecode = "";
		String url = SystemConstant.WEB_URL + "/invite/toregist.html?type="+type;
		if (null != code && !"".equals(code) && "0".equals(login)){
			String saccountid = AES.decryptFromBase64(((String)code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
			Long accountid = Long.valueOf(saccountid);
			Account account = accountDao.selectById(accountid);
			if(account!=null){
				invitecode = account.getInvitecode();
				url = SystemConstant.WEB_URL + "/toregist.html?invitecode=" + invitecode+"&type="+type;
			}
		}
		String shortcode = bizService.createShortUrl(url);
		request.setAttribute("invitecode", invitecode);
		request.setAttribute("url", shortcode);
		request.setAttribute("login", login);
		request.setAttribute("code", code);
    	return layout(null, null, "邀请好友", "/h5/invite.ftl", SystemConstant.LAYOUT_H5);
    	
    }
    
    
	 //邀请记录
    @Action("inviterecord")
    public String inviterecord() {
      String login = request.getHeader("login");
      String code = request.getHeader("code");
//        String login = getParameter("login");
//        String code =getParameter("code");
		
		request.setAttribute("login", login);
		request.setAttribute("code", code);
    	return layout(null, null, "邀请记录", "/h5/inviterecord.ftl", SystemConstant.LAYOUT_H5);
    	
    }
    
    
    
    //邀请记录分页
    @Action("inviterecordpage")
    public void inviterecordpage() {
    	
//      String login = request.getHeader("login");
//      String code = request.getHeader("code");
        String login = getParameter("login");
        String code =getParameter("code");
        String perCount = getParameter("perCount");
		String pageNo = getParameter("pageNo");
		if (null != code && !"".equals(code) && "0".equals(login)){
			String saccountid = AES.decryptFromBase64(((String)code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
			Long accountid = Long.valueOf(saccountid);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accountid", accountid);
			map.put("category", Bonus.CATEGORY_COMMAND_FRIEND);
			map.put("orderBy", "createtime desc");
	        PageUtil.getMap(map, pageNo, bonusDao.selectCount(map), perCount);
			List<Bonus> list  = bonusDao.select(map);
			List<Map<String, Object>> bonuslist = new ArrayList<Map<String, Object>>();
			for(Bonus bonus : list){
				Map<String, Object> b = new HashMap<String, Object>();
				b.put("backtime", StringUtil.convertDateToString(bonus.getBacktime(), "yyyy-MM-dd"));
				b.put("title", bonus.getTitle());
				b.put("status", bonus.getStatus());
				b.put("bonus", bonus.getTotalbonus());
				
				bonuslist.add(b);
				
			}
			
			ajax(JSONObject.toJSONString(bonuslist));
			
		}
    	
    }
    
    
    
    
    //收益红包
    @Action("inviteincome")
    public String inviteincome() {
      String login = request.getHeader("login");
      String code = request.getHeader("code");
//        String login = getParameter("login");
//        String code =getParameter("code");
    	
		request.setAttribute("login", login);
		request.setAttribute("code", code);
    	return layout(null, null, "收益红包", "/h5/inviteincome.ftl", SystemConstant.LAYOUT_H5);
    }
    
    
    
    //收益红包分页
    @Action("inviteincomepage")
    public void inviteincomepage() {
//      String login = request.getHeader("login");
//      String code = request.getHeader("code");
        String login = getParameter("login");
        String code =getParameter("code");
        String perCount = getParameter("perCount");
		String pageNo = getParameter("pageNo");
		if (null != code && !"".equals(code) && "0".equals(login)){
			String saccountid = AES.decryptFromBase64(((String)code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
			Long accountid = Long.valueOf(saccountid);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accountid", accountid);
			map.put("orderBy", "createtime desc");
			PageUtil.getMap(map, pageNo, bonusDao.selectCount(map), perCount);
			List<Bonus> list  = bonusDao.select(map);
			List<Map<String, Object>> bonuslist = new ArrayList<Map<String, Object>>();
			for(Bonus bonus : list){
				Map<String, Object> b = new HashMap<String, Object>();
				b.put("backtime", StringUtil.convertDateToString(bonus.getBacktime(), "yyyy-MM-dd"));
				b.put("title", bonus.getTitle());
				b.put("status", bonus.getStatus());
				b.put("bonus", bonus.getTotalbonus());
				bonuslist.add(b);
				
			}
			
			ajax(JSONObject.toJSONString(bonuslist));
			
		}
    	
    	
    	
    }


}
