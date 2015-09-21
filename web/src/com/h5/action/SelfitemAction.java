package com.h5.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;
import com.platform.util.encrypt.AES;

@ParentPackage("web")
public class SelfitemAction extends BaseAction {

	@Autowired
	private SelfitemDao selfitemDao;

	@Autowired
	private AccountselfitemDao accountselfitemDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AccountassetDao accountassetDao;

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -3973123213063479294L;

	// 产品购买人列表
	@Action("accountlist")
	public String accountlist() {
		Long selfitemid = Long.parseLong(getParameter("selfitemid"));
		
		request.setAttribute("selfitemid", selfitemid);

		return layout(null, null, "已购人数", "/h5/accountlist.ftl", SystemConstant.LAYOUT_H5);
	}
	
	
	//产品购买人列表分页
	@Action("accountlistpage")
	public void accountlistpage() {
		Long selfitemid = Long.parseLong(getParameter("selfitemid"));
		String perCount = getParameter("perCount");
		String pageNo = getParameter("pageNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selfitemid", selfitemid);
		map.put("orderBy", "createtime desc");
		PageUtil.getMap(map, pageNo, accountselfitemDao.selectCount(map), perCount);
		List<Accountselfitem> list = accountselfitemDao.select(map);

		List<Map<String, Object>> accountlist = new ArrayList<Map<String, Object>>();
		if (list.size() > 0) {
			for (Accountselfitem i : list) {
				Map<String, Object> m = new HashMap<String, Object>();
				Account account = accountDao.selectById(i.getAccountid());
				m.put("name", StringUtil.encryptName(account.getRealname()));
				m.put("amount", StringUtil.getFormatAmount(i.getAmount()));
				m.put("createtime", StringUtil.convertDateToString(i.getCreatetime(), "yyyy-MM-dd HH:mm"));
				m.put("income", StringUtil.getFormatAmount(i.getTotalincome()));
				accountlist.add(m);
			}
		}
		
		ajax(JSONObject.toJSONString(accountlist));
        
	}

	// 我的投资
	@Action("selfitemlist")
	public String selfitemlist() {
		 String login = request.getHeader("login");
		 String code = request.getHeader("code");
//		String login = getParameter("login");
//		String code = getParameter("code");


		if (null != code && !"".equals(code) && "0".equals(login)) {
			String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
			Long accountid = Long.valueOf(saccountid);

			Accountasset accountasset = accountassetDao.selectById((accountid));
			// 我的红包
			request.setAttribute("bonusasset", StringUtil.getFormatAmount(accountasset.getBonusasset()));
			// 定期本金
			request.setAttribute("amount", StringUtil.getFormatAmount(accountasset.getSelfitemamount()));
			// 收益
			request.setAttribute("totalincome", StringUtil.getFormatAmount(accountasset.getSelfitemtotalincome()));
		}
		request.setAttribute("login", login);
		request.setAttribute("code", code);

		return layout(null, null, "我的投资", "/h5/selfitemlist.ftl", SystemConstant.LAYOUT_H5);
	}
	
	
	
	   //我的投资分页
		@Action("selfitemlistpage")
		public void selfitemlistpage() {
			// String login = request.getHeader("login");
			// String code = request.getHeader("code");
			String login = getParameter("login");
			String code = getParameter("code");
			String perCount = getParameter("perCount");
			String pageNo = getParameter("pageNo");

			if (null != code && !"".equals(code) && "0".equals(login)) {
				String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
				Long accountid = Long.valueOf(saccountid);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("accountid", accountid);
				map.put("orderBy", "createtime desc");
				PageUtil.getMap(map, pageNo, accountselfitemDao.selectCount(map), perCount);
				List<Accountselfitem> list = accountselfitemDao.select(map);

				List<Map<String, Object>> accountlist = new ArrayList<Map<String, Object>>();
				for (Accountselfitem i : list) {
					Map<String, Object> m = new HashMap<String, Object>();
					Selfitem item = selfitemDao.selectById(i.getSelfitemid());
				    m.put("selfitemid", item.getId());					
				    m.put("name", item.getName());
					m.put("title", item.getActivitytitle());
					m.put("amount", StringUtil.getFormatAmount(i.getAmount()));
					m.put("status", item.getStatus());
					m.put("bonus", StringUtil.getFormatAmount(i.getBonus()));
					m.put("days", item.getIncomedays());
					m.put("income", StringUtil.getFormatAmount(i.getTotalincome()));
					accountlist.add(m);
				}

				ajax(JSONObject.toJSONString(accountlist));
			}

		}
	
	

	// 我的投资 日历
	@Action("selfitemdate")
	public String selfitemdate() {
		 String login = request.getHeader("login");
		 String code = request.getHeader("code");
//		String login = getParameter("login");
//		String code = getParameter("code");
		String date = getParameter("date");

		if (null != code && !"".equals(code) && "0".equals(login)) {
			String saccountid = AES.decryptFromBase64(((String) code).replace(" ", "+"), SystemConstant.ACTIVITY_KEY);
			Long accountid = Long.valueOf(saccountid);

			Accountasset accountasset = accountassetDao.selectById(accountid);

			// 我的红包
			request.setAttribute("bonusasset", StringUtil.getFormatAmount(accountasset.getBonusasset()));
			// 定期本金
			request.setAttribute("amount", StringUtil.getFormatAmount(accountasset.getSelfitemamount()));
			// 已获收益
			request.setAttribute("totalincome", StringUtil.getFormatAmount(accountasset.getSelfitemtotalincome()));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accountid", accountid);
			map.put("orderBy", "createtime desc");
			List<Accountselfitem> list = accountselfitemDao.select(map);
			Set<String> datelist = new HashSet<String>();
			for (Accountselfitem i : list) {
				datelist.add(StringUtil.convertDateToString(i.getCreatetime(), "yyyy-MM"));

			}
			request.setAttribute("list", datelist);
			if (StringUtil.isBlank(date)) {
				if (datelist.size() > 0) {
					date = StringUtil.convertDateToString(list.get(0).getCreatetime(), "yyyy-MM");
					
				}
			}

			List<Map<String, Object>> accountlist = new ArrayList<Map<String, Object>>();

			Map<String, Object> smap = new HashMap<String, Object>();
			smap.put("accountid", accountid);
			String startTime = date + "-01 00:00:00";
			String endTime = StringUtil.convertDateToString(
					StringUtil.getNextMonth(StringUtil.convertStringToDate(startTime), 1), "yyyy-MM-dd HH:mm:ss");
			smap.put("starttime", startTime);
			smap.put("endtime", endTime);
			smap.put("orderBy", "createtime desc");
			List<Accountselfitem> slist = accountselfitemDao.select(smap);
			for (Accountselfitem i : slist) {
				Map<String, Object> m = new HashMap<String, Object>();
				Selfitem item = selfitemDao.selectById(i.getSelfitemid());
				m.put("selfitemid", item.getId());
				m.put("name", item.getName());
				m.put("title", item.getActivitytitle());
				m.put("amount", StringUtil.getFormatAmount(i.getAmount()));
				m.put("status", item.getStatus());
				m.put("bonus", StringUtil.getFormatAmount(i.getBonus()));
				m.put("days", item.getIncomedays());
				m.put("income", StringUtil.getFormatAmount(i.getTotalincome()));
				m.put("createtime", i.getCreatetime());
				accountlist.add(m);
			}

			request.setAttribute("records", accountlist);
		}
		request.setAttribute("login", login);
		request.setAttribute("code", code);
		request.setAttribute("date", date);
		return layout(null, null, "我的投资", "/h5/selfitemdate.ftl", SystemConstant.LAYOUT_H5);

	}

}
