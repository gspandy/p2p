package com.h5.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;
import com.cslc.dao.score.ScoreDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.platform.util.encrypt.AES;

@ParentPackage("web")
public class CenterAction extends BaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = -7161977663469842889L;

    @Resource
    private AccountDao        accountDao;

    @Resource
    private ScoreDao   scoreDao;
    
    @Resource
    private AccountassetDao   accountassetDao;

    @Resource
    private BonusDao   bonusDao;


    // 我的奖励
    @Action("totalasset")
    public String totalasset() {

        String login = request.getHeader("login");
        String code = request.getHeader("code");
        
//        String login = getParameter("login");
//        String code =getParameter("code");

        if (null != code && !"".equals(code) && "0".equals(login)) {
            String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
            Long accountid = Long.valueOf(saccountid);
            Accountasset accountasset = accountassetDao.selectById(accountid);
            

            //定期本金
            request.setAttribute("amount", StringUtil.getFormatAmount(accountasset.getSelfitemamount()));
            //定期资产
            request.setAttribute("asset", StringUtil.getFormatAmount(accountasset.getSelfitemasset()));
            //已获收益
            request.setAttribute("totalincome", StringUtil.getFormatAmount(accountasset.getSelfitemtotalincome()));
            //待收收益
            request.setAttribute("restincome", StringUtil.getFormatAmount(accountasset.getSelfitemrestincome()));
            //回款中金额
            request.setAttribute("cashbackamount", StringUtil.getFormatAmount(accountasset.getSelfitemcashbackamount()));
            //我的红包
            request.setAttribute("bonusasset", StringUtil.getFormatAmount(accountasset.getBonusasset()));
            
        } 
            return layout(null, null, "我的奖励", "/h5/totalasset.ftl", SystemConstant.LAYOUT_H5);
    }

    //我的红包
    @Action("bonus")
    public String bonus() {
        String login = request.getHeader("login");
        String code = request.getHeader("code");
//        String login = getParameter("login");
//        String code =getParameter("code");
        
        if (null != code && !"".equals(code) && "0".equals(login)) {
            String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
            Long accountid = Long.valueOf(saccountid);

            Accountasset accountasset = accountassetDao.selectById(accountid);
            //剩余红包
            request.setAttribute("bonusasset", StringUtil.getFormatAmount(accountasset.getBonusasset()));
            //累计红包
            request.setAttribute("totalbonus", StringUtil.getFormatAmount(accountasset.getBonustotal()));
            request.setAttribute("login", login);
            request.setAttribute("code", code);
        }
        return layout(null, null, "我的红包", "/h5/bonus.ftl", SystemConstant.LAYOUT_H5);
    }
    
    
    //我的红包分页
    @Action("bonuspage")
    public void bonuspage() {
//      String login = request.getHeader("login");
//      String code = request.getHeader("code");
      String login = getParameter("login");
      String code =getParameter("code");
      String perCount = getParameter("perCount");
	  String pageNo = getParameter("pageNo");
      
      if (null != code && !"".equals(code) && "0".equals(login)) {
          String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"),SystemConstant.ACTIVITY_KEY);
          Long accountid = Long.valueOf(saccountid);
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("accountid", accountid);
          map.put("orderBy", "createtime desc");
          PageUtil.getMap(map, pageNo, accountassetDao.selectCount(map), perCount);
          List<Bonus> list = bonusDao.select(map);

          List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();
          if (list != null) {
              for (Bonus bonus : list) {
                  Map<String, Object> m = new HashMap<String, Object>();
                  m.put("title", bonus.getTitle());
                  m.put("bonus", bonus.getTotalbonus());
                  m.put("status", bonus.getStatus());
                  m.put("endtime", StringUtil.convertDateToString(bonus.getEndtime()));
                  itemlist.add(m);
              }
          }
          ajax(JSONObject.toJSONString(itemlist));
      }
    	
    	
    }


}
