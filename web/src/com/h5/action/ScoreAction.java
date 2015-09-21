package com.h5.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.reward.Reward;
import com.cslc.dao.reward.RewardDao;
import com.cslc.dao.score.Score;
import com.cslc.dao.score.ScoreDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.platform.util.encrypt.AES;

@ParentPackage("web")
@Namespace("/")
public class ScoreAction extends BaseAction{
	
	@Autowired
	private AccountassetDao accountassetDao;
	
	@Autowired
	private ScoreDao scoreDao;
	
	@Autowired
	private RewardDao rewardDao;
	
	@Autowired
	private AccountDao accountDao;
	

	
	
	
	// 我的积分
    @Action("score")
    public String score() {
    	  String login = request.getHeader("login");
          String code = request.getHeader("code");
          String ua = request.getHeader("ua");
//          String login = getParameter("login");
//          String code =getParameter("code");
          
          if (null != code && !"".equals(code) && "0".equals(login)) {
              String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
              Long accountid = Long.valueOf(saccountid);
              Accountasset accountasset = accountassetDao.selectById(accountid);
              
              request.setAttribute("scoreasset", accountasset.getScoreasset());
              
              request.setAttribute("scoretotal", accountasset.getScoretotal());
          }
          request.setAttribute("login", login);
  		  request.setAttribute("code", code);
          return layout(null, null, "我的积分", "/h5/score.ftl", SystemConstant.LAYOUT_H5);
      	
      }
	
    // 兑换奖品
    @Action("scoreaward")
    public String scoreAward() {
    	
    	  String login = getParameter("login");
          String code =getParameter("code");
//        String login = request.getHeader("login");
//        String code = request.getHeader("code");
        
        if (null != code && !"".equals(code) && "0".equals(login)) {
            String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
            Long accountid = Long.valueOf(saccountid);
            Accountasset accountasset = accountassetDao.selectById(accountid);

            
            request.setAttribute("scoreasset", accountasset.getScoreasset());
            
            request.setAttribute("scoretotal", accountasset.getScoretotal());
        }
        request.setAttribute("login", login);
		request.setAttribute("code", code);
        return layout(null, null, "兑换奖品", "/h5/scoreaward.ftl", SystemConstant.LAYOUT_H5);
    	
    }
    
    
     // 兑换奖品分页
    @Action("scoreawardpage")
    public void scoreawardpage() {
    	
  	  String login = getParameter("login");
        String code =getParameter("code");
        String perCount = getParameter("perCount");
        String pageNo =getParameter("pageNo");
//      String login = request.getHeader("login");
//      String code = request.getHeader("code");
      
      if (null != code && !"".equals(code) && "0".equals(login)) {
          String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
          Long accountid = Long.valueOf(saccountid);
          
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("orderBy", "serialno asc");
          PageUtil.getMap(map, pageNo, rewardDao.selectCount(map), perCount);
          List<Reward> list = rewardDao.select(map);
          List<Map<String, Object>> scorelist = new ArrayList<Map<String, Object>>();
          
          for(Reward reward : list){
          	Map<String, Object> m = new HashMap<String, Object>();
          	m.put("title", reward.getTitle());
          	m.put("score", reward.getScore());
          	m.put("createtime", StringUtil.convertDateToString(reward.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
          	m.put("rewardid", reward.getId());
          	scorelist.add(m);
          }
       
          ajax(JSONObject.toJSONString(scorelist));
      }
  	
  }
    
 // 兑换奖品
    @Action("executescoreaward")
    public void executescoreaward() {
    	String login = getParameter("login");
        String code =getParameter("code");
        String rewardid = getParameter("rewardid");
        if (null != code && !"".equals(code) && "0".equals(login)) {
            String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
            Long accountid = Long.valueOf(saccountid);
            Reward reward = rewardDao.selectById(Long.parseLong(rewardid));
            Integer rscore = reward.getScore();
            Accountasset accountasset = accountassetDao.selectById(accountid);
            Integer ascore = accountasset.getScoreasset();
            if(rscore>ascore){
            	ajax("error");
            	
            }else{
            	Accountasset asset = new Accountasset();
            	asset.setAccountid(accountid);
            	asset.setScoreasset(ascore-rscore);
            	accountassetDao.update(asset);
            	
            	Score sc = new Score();
            	sc.setAccountid(accountid);
            	sc.setCategory(1);
            	sc.setCreatetime(new Date());
            	sc.setInouttype(Score.INOUTTYPE_OUT);
            	sc.setRewardid(Long.parseLong(rewardid));
            	sc.setScore(rscore);
            	sc.setStatus(Score.STATUS_ENABLE);
            	sc.setTitle(reward.getTitle());
            	scoreDao.insert(sc);
            	ajax("success");
            	
            }
            return;
            
        }
        ajax("notlogin");
        return;
    	
    	
    }
    
    
    
    
    // 我的任务
    @Action("scoretask")
    public String scoreTask() {
    	
//    	  String login = getParameter("login");
//          String code =getParameter("code");
        String login = request.getHeader("login");
        String code = request.getHeader("code");
        
          if (null != code && !"".equals(code) && "0".equals(login)) {
              String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
              Long accountid = Long.valueOf(saccountid);
              Accountasset accountasset = accountassetDao.selectById(accountid);
              
              request.setAttribute("scoreasset", accountasset.getScoreasset());
              request.setAttribute("scoretotal", accountasset.getScoretotal());
          }
          request.setAttribute("login", login);
  		  request.setAttribute("code", code);
    	return layout(null, null, "我的任务", "/h5/scoretask.ftl", SystemConstant.LAYOUT_H5);
    	
    }
    
    
    // 我的任务分页
    @Action("scoretaskpage")
    public void scoretaskpage() {
    	 String login = getParameter("login");
         String code =getParameter("code");
         String perCount = getParameter("perCount");
         String pageNo =getParameter("pageNo");
//       String login = request.getHeader("login");
//       String code = request.getHeader("code");
       
         if (null != code && !"".equals(code) && "0".equals(login)) {
             String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
             Long accountid = Long.valueOf(saccountid);
             
             Map<String, Object> map = new HashMap<String, Object>();
             map.put("accountid", accountid);
             map.put("inouttype", Score.INOUTTYPE_IN);
             map.put("status", Score.STATUS_ENABLE);
             map.put("orderBy", "createtime desc");
             PageUtil.getMap(map, pageNo, scoreDao.selectCount(map), perCount);
             List<Score> list = scoreDao.select(map);
             List<Map<String, Object>> scorelist = new ArrayList<Map<String, Object>>();
             
             for(Score score : list){
             	Map<String, Object> m = new HashMap<String, Object>();
             	m.put("title", score.getTitle());
             	m.put("score", score.getScore());
             	scorelist.add(m);
             }
             ajax(JSONObject.toJSONString(scorelist));
         }
    	
    	
    }
    
    
    
    // 积分记录
    @Action("scorerecord")
    public String scoreRecord() {
    	
//    	String login = getParameter("login");
//        String code =getParameter("code");
      String login = request.getHeader("login");
      String code = request.getHeader("code");
   	
         if (null != code && !"".equals(code) && "0".equals(login)) {
             String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
             Long accountid = Long.valueOf(saccountid);
             Accountasset accountasset = accountassetDao.selectById(accountid);
             request.setAttribute("scoreasset", accountasset.getScoreasset());
             request.setAttribute("scoretotal", accountasset.getScoretotal());
         }
         request.setAttribute("login", login);
 		 request.setAttribute("code", code);
    	return layout(null, null, "积分记录", "/h5/scorerecord.ftl", SystemConstant.LAYOUT_H5);
    	
    }
    
    
    
    // 积分记录分页
    @Action("scorerecordpage")
    public void scorerecordpage() {
    	
    	String login = getParameter("login");
        String code =getParameter("code");
        String perCount = getParameter("perCount");
        String pageNo =getParameter("pageNo");
//      String login = request.getHeader("login");
//      String code = request.getHeader("code");
   	
         if (null != code && !"".equals(code) && "0".equals(login)) {
             String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
             Long accountid = Long.valueOf(saccountid);
             
             Map<String, Object> map = new HashMap<String, Object>();
             map.put("accountid", accountid);
             map.put("status", Score.STATUS_ENABLE);
             map.put("orderBy", "createtime desc");
             PageUtil.getMap(map, pageNo, scoreDao.selectCount(map), perCount);
             List<Score> list = scoreDao.select(map);
             List<Map<String, Object>> scorelist = new ArrayList<Map<String, Object>>();
             
             for(Score score : list){
             	Map<String, Object> m = new HashMap<String, Object>();
             	m.put("title", score.getTitle());
             	m.put("score", score.getScore());
             	m.put("inouttype", score.getInouttype());
             	scorelist.add(m);
             }
             ajax(JSONObject.toJSONString(scorelist));
             
         }
    	
    }


}
