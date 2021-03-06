package com.server.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONArray;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountbankcard.Accountbankcard;
import com.cslc.dao.accountbankcard.AccountbankcardDao;
import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;
import com.cslc.dao.message.Message;
import com.cslc.dao.message.MessageDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.supportbank.SupportbankDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.dao.trade.TradeDao;
import com.cslc.service.BizService;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;

@ParentPackage("app")
@Namespace("/")
public class SelfitemAction extends BaseAction {

	@Resource
	private AccountassetDao accountassetDao;

	@Resource
	private TradeDao tradeDao;

	@Resource
	private AccountDao accountDao;

	@Resource
	private SelfitemDao selfitemDao;

	@Resource
	private MessageDao messageDao;
	
	@Resource
	private BonusDao bonusDao;

	@Resource
	private AccountselfitemDao accountselfitemDao;

	@Resource
	private SystemdefDao systemdefDao;
	
	@Resource
	private AccountbankcardDao accountbankcardDao;	
	
	@Resource
	private SupportbankDao supportbankDao;	
	
	@Resource
	private BizService bizService;
	

	

	// 首页
	@Action("100")
	public void homepage() {
		String accountid = getParameter("accountid");
		Map<String, Object> result = new HashMap<String, Object>();

		// banner
		result.put("banners", JSONArray.parse(bizService.getSystemval(Systemdef.BANNERS)));
		//warning
		result.put("warnning", bizService.getSystemjson(Systemdef.APP_WARNNING));

		Map<String, Object> itemmap = new HashMap<String, Object>();
		Selfitem item = null;
		if (StringUtil.isNotBlank(accountid)) {
			Account account = accountDao.selectById(Long.parseLong(accountid));
			if (account.getTradecount() > 0) {
				 itemmap.put("notStatus", Selfitem.STATUS_NOT_SALE);
				 itemmap.put("noactivitytitle", "新手专享");
				 itemmap.put("orderBy", "status asc,starttime asc,annualrate desc,endtime desc");
				 
			} else {
				 itemmap.put("status", Selfitem.STATUS_SALE);
				 itemmap.put("starttimeEnd", StringUtil.convertDateToString(new Date()));		 
				 itemmap.put("activitytitle", "新手专享");
			}
			// 消息
			Map<String, Object> messagemap = new HashMap<String, Object>();

			Map<String, Object> memap = new HashMap<String, Object>();
			memap.put("accountid", accountid);
			memap.put("status", Message.STATUS_NOT_READ);
			Long count = messageDao.selectCount(memap);
			if (count > 0) {
				messagemap.put("count", count);
				messagemap.put("url", SystemConstant.WEB_URL+"/messagelist.html");
			}

			result.put("message", messagemap);

		} else {
			 itemmap.put("status", Selfitem.STATUS_SALE);
			 itemmap.put("starttimeEnd", StringUtil.convertDateToString(new Date()));		 
			 itemmap.put("activitytitle", "新手专享");

		}
		List<Selfitem> itemlist = selfitemDao.select(itemmap);
		 if (itemlist.size() > 0) {
				item = itemlist.get(0);
		 }
		
		if(null==item){
			 Map<String, Object> selfmap = new HashMap<String, Object>();
			 selfmap.put("notStatus", Selfitem.STATUS_NOT_SALE);
			 selfmap.put("noactivitytitle", "新手专享");
			 selfmap.put("orderBy", "status asc,starttime asc,annualrate desc,endtime desc");
			 List<Selfitem> selflist = selfitemDao.select(selfmap);
			 if (selflist.size() > 0) {
					item = selflist.get(0);
			 }
			
		}

		// 产品详情
		Map<String, Object> selfitemmap = new HashMap<String, Object>();
		selfitemmap.put("selfitemid", String.valueOf(item.getId()));
		selfitemmap.put("activitycontent", item.getActivitycontent());
		selfitemmap.put("annualrate", StringUtil.getFormatNumber(item.getAnnualrate()+item.getAnnualrateextra(), "0.0")+"%");
		
		selfitemmap.put("unitpricecontent", StringUtil.getFormatNumber(item.getUnitprice(), "0")+"元起购");
		selfitemmap.put("unitprice", item.getUnitprice());
		selfitemmap.put("unitincome", item.getUnitincome());
		selfitemmap.put("days", item.getincomeDays()+"天");
		double pro = (1 - ((item.getRestamount() + item.getFreezeamount()) / item.getTotalamount())) * 100;
		if (pro > 0 && pro <= 1) {
			pro = 1;
		} else if (pro >= 99 && pro < 100) {
			pro = 99;
		}
		selfitemmap.put("progress", StringUtil.getFormatNumber(pro, "0"));
		result.put("selfitem", selfitemmap);

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 产品列表
	@Action("110")
	public void itemlist() {
		String accountid = getParameter("accountid");
		String perCount = getParameter("perCount");
		String pageNo = getParameter("pageNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notStatus", Selfitem.STATUS_NOT_SALE);
		map.put("orderBy", "status asc,starttime asc,annualrate desc,endtime desc");

//		map.put("orderBy", "status asc,endtime desc,starttime asc,");
		PageUtil.getMap(map, pageNo, selfitemDao.selectCount(map), perCount);
		List<Selfitem> list = selfitemDao.select(map);

		List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();
		for (Selfitem item : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("selfitemid", String.valueOf(item.getId()));
			m.put("name", item.getName());
			m.put("annualrate", StringUtil.getFormatNumber(item.getAnnualrate(), "0.0")+"%");
			if(item.getAnnualrateextra()!=0){
				m.put("annualrateextra", StringUtil.getFormatNumber(item.getAnnualrateextra(), "0.0")+"%");
			}
			m.put("days", String.valueOf(item.getincomeDays())+"天到期");
			
			m.put("unitpricecontent", StringUtil.getFormatNumber(item.getUnitprice(), "0")+"元起购");
			m.put("unitprice", item.getUnitprice());
			
			m.put("unitincome", item.getUnitincome());
			
			m.put("activitytitle", item.getActivitytitle());
			m.put("activitycolor", 1);
			
			
			if (item.getStatus() == Selfitem.STATUS_SELLOUT||item.getStatus()==Selfitem.STATUS_INCOME||item.getStatus()==Selfitem.STATUS_CASHBACK) {
				m.put("status", 4);
				m.put("statustext", "已售完");
			} else if (item.getStatus() == Selfitem.STATUS_SALE) {
				if(item.getStarttime().after(new Date())){
					m.put("status", 3);
					
					m.put("statustext", StringUtil.convertDateToString(item.getStarttime(), "HH-mm")+"开售");
				}else{
					m.put("status", 2);
				}
				if(item.getActivitytitle().equals("新手专享")){
					m.put("status", 1);
					m.put("statustext", "新手专享");
				}
			}
				

			itemlist.add(m);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		result.put("records", itemlist);
		print(result);
		return;

	}
	
	
	
	// 产品详情
	@Action("111")
	public void itemdetail() {
		
		String accountid = getParameter("accountid");
		Long selfitemid = Long.parseLong(getParameter("selfitemid"));
		
		Selfitem item = selfitemDao.selectById(selfitemid);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> selfitemmap = new HashMap<String, Object>();
		
		selfitemmap.put("selfitemid", String.valueOf(item.getId()));
		selfitemmap.put("name", item.getName());
		selfitemmap.put("annualrate", StringUtil.getFormatNumber(item.getAnnualrate(), "0.0")+"%");
		if(item.getAnnualrateextra()!=0){
			selfitemmap.put("annualrateextra", StringUtil.getFormatNumber(item.getAnnualrateextra(), "0.0")+"%");
		}
		
		selfitemmap.put("days", String.valueOf(item.getincomeDays()+"天"));
		selfitemmap.put("unitpricecontent", StringUtil.getFormatNumber(item.getUnitprice(), "0")+"元起购");
		selfitemmap.put("unitprice", item.getUnitprice());
		
		selfitemmap.put("unitincome", item.getUnitincome());
		selfitemmap.put("totalamount","募集"+StringUtil.getFormatAmount(item.getTotalamount())+"元");
		selfitemmap.put("restamount","还剩"+StringUtil.getFormatAmount(item.getRestamount())+"元");
		if(StringUtil.isBlank(item.getActivitycontent())){
			selfitemmap.put("activitycontent","投资10000元，到期收益"+StringUtil.getFormatNumber(10000/item.getUnitprice()*item.getUnitincome(),"0.00")+"元。");
		}else{
			selfitemmap.put("activitycontent",item.getActivitycontent());
			selfitemmap.put("activityurl",item.getActivityurl());
		}
		
		selfitemmap.put("activitycolor",1);
		
		if (item.getStatus() == Selfitem.STATUS_SELLOUT||item.getStatus()==Selfitem.STATUS_INCOME||item.getStatus()==Selfitem.STATUS_CASHBACK) {
			selfitemmap.put("status", 4);
			selfitemmap.put("statustext", "已售完");
		} else if (item.getStatus() == Selfitem.STATUS_SALE) {
			if(item.getStarttime().after(new Date())){
				selfitemmap.put("status", 3);
				result.put("starttime", item.getStarttime().getTime());
		        result.put("systemtime", new Date().getTime());
			}else{
				selfitemmap.put("status", 2);
			}
			if(item.getActivitytitle().equals("新手专享")){
				selfitemmap.put("status", 1);
				selfitemmap.put("statustext", "新手专享");
			}
		}
		
		double pro = (1 - (item.getRestamount() + item.getFreezeamount()) / item.getTotalamount()) * 100;
		if (pro > 0 && pro <= 1) {
			pro = 1;
		} else if (pro >= 99 && pro < 100) {
			pro = 99;
		}
		selfitemmap.put("progress", StringUtil.getFormatNumber(pro, "0"));
		
		result.put("selfitems", selfitemmap);
		

		
		
		Map<String, Object> imap = new HashMap<String, Object>();
		imap.put("selfitemid", selfitemid);
		imap.put("orderBy", "createtime desc");
		long count = accountselfitemDao.selectCount(imap);
		List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();
		
		
		Map<String, Object> selfmap = new HashMap<String, Object>();
		selfmap.put("title", "已购人数");
		selfmap.put("tip", count);
		selfmap.put("url", SystemConstant.WEB_URL+"/accountlist.html?selfitemid="+selfitemid);
		itemlist.add(selfmap);
		
		Map<String, Object> repaymap = new HashMap<String, Object>();
		repaymap.put("title", "还款方式");
		repaymap.put("tip", "");
		repaymap.put("url", SystemConstant.WEB_URL+"/repayment.html?selfitemid="+selfitemid);
		itemlist.add(repaymap);
		
		Map<String, Object> describemap = new HashMap<String, Object>();
		describemap.put("title", "项目描述");
		describemap.put("tip", "");
		describemap.put("url", SystemConstant.WEB_URL+"/describe.html?selfitemid="+selfitemid);
		itemlist.add(describemap);
		
		Map<String, Object> guaranteemap = new HashMap<String, Object>();
		guaranteemap.put("title", "资金保障");
		guaranteemap.put("tip", "");
		guaranteemap.put("url", SystemConstant.WEB_URL+"/safeguard.html?selfitemid="+selfitemid);
		itemlist.add(guaranteemap);
		
		Map<String, Object> safemap = new HashMap<String, Object>();
		safemap.put("title", "安全体系");
		safemap.put("tip", "");
		safemap.put("url", "");
		itemlist.add(safemap);
		
		
		result.put("items", itemlist);
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;
		
		
	}
	
	
	// 购买界面
	@Action("113")
	public void paypage() {
		Long accountid = Long.parseLong(getParameter("accountid"));
		String selfitemid = getParameter("selfitemid");
		Account account = accountDao.selectById(accountid);
		Selfitem item = selfitemDao.selectById(Long.parseLong(selfitemid));
		Map<String, Object> result = new HashMap<String, Object>();

           double freezeamount = (item.getStatus() == Selfitem.STATUS_SELLOUT || item.getStatus() == Selfitem.STATUS_INCOME) ? 0 : item.getFreezeamount();
           if (item.getRestamount() + freezeamount <= 0) {
               result.put("isallowbuy", 2);
               result.put("returnCode", 0);
               result.put("returnMsg", "success");
               result.put("info", "该产品已售完！");
               print(result);
               return;
           }

           if ((account.getTradecount() > 0) && (item.getActivitytitle().equals("新手专享"))) {
               result.put("isallowbuy", 2);
               result.put("returnCode", 0);
               result.put("returnMsg", "success");
               result.put("info", "新手标产品只限注册未交易用户购买，只能购买一次。");
               print(result);
               return;
           }
           result.put("isallowbuy", 1);

           Map<String, Object> agreemap = new HashMap<String, Object>();
           agreemap.put("title", "财术金融服务协议");
           agreemap.put("url", SystemConstant.WEB_URL + "/serviceagreement.html");// 财术金融服务协议
           result.put("agreement", agreemap);
           
           
           Map<String, Object> selfitemmap = new HashMap<String, Object>();
           selfitemmap.put("selfitemid", selfitemid);
           selfitemmap.put("name", item.getName());
           selfitemmap.put("unitprice", item.getUnitprice());
           selfitemmap.put("restamount", StringUtil.getFormatAmount(item.getRestamount()));
           selfitemmap.put("unitincome", item.getUnitincome());
           selfitemmap.put("totalamount", StringUtil.getFormatAmount(item.getTotalamount()));
           result.put("selfitem", selfitemmap);
           
           List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
           Map<String, Object> incomemmap = new HashMap<String, Object>();
           incomemmap.put("title", "预计起息日期");
           incomemmap.put("tip", StringUtil.convertDateToString(StringUtil.getNextDate(item.getStarttime(), 3)));
           
           
           l.add(incomemmap);
           result.put("list", l);
           
           
           result.put("isbonus", 1);
           result.put("isscore", 1);
           result.put("scorerate", 1);
           Map<String, Object> bankmap = new HashMap<String, Object>();
           bankmap.put("accountid", accountid);
           bankmap.put("statusIn", Accountbankcard.STATUS_NOT_VERIFIED+","+Accountbankcard.STATUS_VERIFIED);
           List<Map<String, Object>> cardlist = new ArrayList<Map<String, Object>>();
           List<Accountbankcard> banklist = accountbankcardDao.select(bankmap);
           for(Accountbankcard card : banklist){
        	   Map<String, Object> m = new HashMap<String, Object>();
        	   m.put("title", supportbankDao.selectById(card.getBankid()).getName());  
        	   m.put("bankcardid", card.getId());  
        	   m.put("isdefault", card.getIsdefault());  
        	   cardlist.add(m);
           }
           result.put("bankcards", cardlist);
           
           result.put("returnCode", 0);
           result.put("returnMsg", "success");
           print(result);
           return;
	}
	
	
	
	 //返现红包
		@Action("116")
		public void bonus() {
			Long accountid = Long.parseLong(getParameter("accountid"));
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> bmap = new HashMap<String, Object>();
	           bmap.put("accountid", accountid);
	           bmap.put("status", Bonus.STATUS_UNUSED);
	           List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();
	           List<Bonus> list = bonusDao.select(bmap);
	           if(list.size()>0){
	           for(Bonus bonus : list){
	        		Map<String, Object> m = new HashMap<String, Object>();
	        		m.put("bonusid", bonus.getId());
	        		m.put("title", bonus.getTitle());  
	        		m.put("endtime", StringUtil.convertDateToString(bonus.getEndtime()));
	        		m.put("bonus", bonus.getTotalbonus());
	        		m.put("rate", bonus.getRate());
	        		itemlist.add(m);
	              }
	           }
	           result.put("bonuses", itemlist);
	           
	           result.put("returnCode", 0);
	           result.put("returnMsg", "success");
	           print(result);
	           return;
			
		}
	
	

}
