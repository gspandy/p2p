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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountbankcard.Accountbankcard;
import com.cslc.dao.accountbankcard.AccountbankcardDao;
import com.cslc.dao.accountconfig.AccountconfigDao;
import com.cslc.dao.message.MessageDao;
import com.cslc.dao.score.ScoreDao;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.supportbank.Supportbank;
import com.cslc.dao.supportbank.SupportbankDao;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.dao.trade.TradeDao;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.PlaceUtil;
import com.platform.util.StringUtil;

@ParentPackage("app")
@Namespace("/")
public class CenterAction extends BaseAction {
	@Resource
	private AccountDao accountDao;

	@Resource
	private AccountassetDao accountassetDao;

	@Resource
	private TradeDao tradeDao;

	@Resource
	private SelfitemDao selfitemDao;

	@Resource
	private MessageDao messageDao;

	@Resource
	private AccountbankcardDao accountbankcardDao;

	@Resource
	private SystemdefDao systemdefDao;

	@Resource
	private ScoreDao scoreDao;

	@Resource
	private AccountconfigDao accountconfigDao;

	@Resource
	private SupportbankDao supportbankDao;

	// 我的资产
	@Action("130")
	public void accountcenter() {
		Long accountid = Long.parseLong(getParameter("accountid"));

		Map<String, Object> result = new HashMap<String, Object>();

		Account account = accountDao.selectById(accountid);

		Accountasset accountasset = accountassetDao.selectById(accountid);

		// 账户信息
		Map<String, Object> accountmap = new HashMap<String, Object>();

		accountmap.put("totalincome", StringUtil.getFormatAmount(accountasset.getSelfitemtotalincome()));

		accountmap.put("todayincome", StringUtil.getFormatAmount(accountasset.getSelfitemtodayincome()));

		accountmap.put("mobile", StringUtil.encryptPhone(account.getMobile()));

		result.put("account", accountmap);

		List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> assetmap = new HashMap<String, Object>();

		assetmap.put("title", "总资产");
		assetmap.put("tip", StringUtil.getFormatAmount(accountasset.getSelfitemasset()));
		assetmap.put("url", SystemConstant.WEB_URL + "/totalasset.html");
		itemlist.add(assetmap);

		Map<String, Object> investmap = new HashMap<String, Object>();
		investmap.put("title", "我的投资");
		investmap.put("tip", String.valueOf(account.getTradecount()));
		investmap.put("url", SystemConstant.WEB_URL + "/selfitemlist.html");
		itemlist.add(investmap);

		Map<String, Object> bonusmap = new HashMap<String, Object>();
		bonusmap.put("title", "我的红包");
		bonusmap.put("tip", StringUtil.getFormatAmount(accountasset.getBonusasset()));
		bonusmap.put("url", SystemConstant.WEB_URL + "/bonus.html");
		itemlist.add(bonusmap);

		Map<String, Object> scoremap = new HashMap<String, Object>();
		scoremap.put("title", "我的积分");
		scoremap.put("tip", "签到领积分");
		scoremap.put("url", SystemConstant.WEB_URL + "/score.html");
		itemlist.add(scoremap);

		result.put("items", itemlist);

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 我的账号
	@Action("131")
	public void myaccount() {

		Long accountid = Long.parseLong(getParameter("accountid"));

		Account account = accountDao.selectById(accountid);

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> accountmap = new HashMap<String, Object>();
		if (null != account.getRealname()) {
			accountmap.put("realname", account.getRealname());
		}

		if (null != account.getIdcardno()) {
			accountmap.put("idcardno", StringUtil.encryptCard(account.getIdcardno()));
		}

		if (null != account.getMobile()) {
			accountmap.put("mobile", StringUtil.encryptPhone(account.getMobile()));
		}

		result.put("account", accountmap);

		Map<String, Object> bankmap = new HashMap<String, Object>();
		bankmap.put("accountid", accountid);
		bankmap.put("statusIn", Accountbankcard.STATUS_NOT_VERIFIED + "," + Accountbankcard.STATUS_VERIFIED);
		List<Accountbankcard> banklist = accountbankcardDao.select(bankmap);
		List<Map<String, Object>> cardlist = new ArrayList<Map<String, Object>>();

		for (Accountbankcard bank : banklist) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("bankcardid", bank.getId());
			m.put("bankcardno", "尾号"+bank.getBankcardno().substring(bank.getBankcardno().length() - 4));
			m.put("bankname", supportbankDao.selectById(bank.getBankid()).getName());
			m.put("isdefault", bank.getIsdefault());
			cardlist.add(m);
		}

		result.put("bankcards", cardlist);

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 添加银行卡
	@Action("132")
	public void addbankcard() {
		Long accountid = Long.parseLong(getParameter("accountid"));

		Account account = accountDao.selectById(accountid);

		Map<String, Object> result = new HashMap<String, Object>();
		if (account.getTradecount() > 0) {

			result.put("realname", StringUtil.encryptName(account.getRealname()));

			result.put("idcardno", StringUtil.encryptCard(account.getIdcardno()));

			result.put("isaccount", 2);
		} else {
			result.put("isaccount", 1);
		}

		result.put("isallowbincard", 1);
		result.put("tip", "允许绑卡");

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 获取银行名称
	@Action("133")
	public void getbankname() {
		Long accountid = Long.parseLong(getParameter("accountid"));

		String bankcardno = getParameter("bankcardno");
		Map<String, Object> result = new HashMap<String, Object>();

		// 用户的银行卡是否需要填写省份、城市
		result.put("needpcb", 1);

		result.put("province", JSONArray.toJSON(PlaceUtil.prov));
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 提交银行卡
	@Action("134")
	public void commitbankcard() {
		String accountid = getParameter("accountid");
		String bankcardid = getParameter("bankcardid");
		String realname = getParameter("realname");
		String idcardno = getParameter("idcardno");
		String bankcardno = getParameter("bankcardno");
		String bankname = getParameter("bankname");
		String province = getParameter("province");
		String city = getParameter("city");
		String branchname = getParameter("branchname");
        Account account = accountDao.selectById(Long.parseLong(accountid));
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("bankcardno", bankcardno);
		List<Accountbankcard> blist = accountbankcardDao.select(bmap);
		if (blist.size() > 0) {
			result.put("returnCode", 1);
			result.put("returnMsg", "您已提交过改银行卡");
			print(result);
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", bankname);
		List<Supportbank> list = supportbankDao.select(map);
		if (list.size() > 0) {
			Supportbank bank = list.get(0);

			if (StringUtil.isBlank(bankcardid)) {

				Accountbankcard card = new Accountbankcard();
				card.setAccountid(Long.parseLong(accountid));
				card.setBankcardno(bankcardno);
				card.setBranchname(branchname);
				card.setCity(city);
				card.setBankid(bank.getId());
				card.setCreatetime(new Date());
				card.setIsdefault(Accountbankcard.ISDEFAULT_NO);
				card.setProvince(province);
				card.setStatus(Accountbankcard.STATUS_NOT_VERIFIED);
				accountbankcardDao.insert(card);

			} else {
				Accountbankcard card = accountbankcardDao.selectById(Long.parseLong(bankcardid));
				card.setBankcardno(bankcardno);
				card.setBranchname(branchname);
				card.setCity(city);
				card.setBankid(bank.getId());
				card.setProvince(province);
				accountbankcardDao.update(card);
			}
			
			if (account.getTradecount() <= 0) {
				account.setRealname(realname);
				account.setIdcardno(idcardno);
				accountDao.update(account);
			}


		}

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 修改银行卡
	@Action("135")
	public void changebankcard() {
		String accountid = getParameter("accountid");
		String bankcardid = getParameter("bankcardid");

		Map<String, Object> result = new HashMap<String, Object>();

		Account account = accountDao.selectById(Long.parseLong(accountid));

		Accountbankcard bank = accountbankcardDao.selectById(Long.parseLong(bankcardid));
		Supportbank support = supportbankDao.selectById(bank.getBankid());

		if (bank.getStatus() == Accountbankcard.STATUS_NOT_VERIFIED) {
			result.put("isverified", 1);
		} else {
			result.put("isverified", 2);
		}

		if (account.getTradecount() > 0) {
			result.put("isaccount", 2);
			result.put("realname", StringUtil.encryptName(account.getRealname()));
			
			result.put("idcardno", StringUtil.encryptCard(account.getIdcardno()));
		} else {
			result.put("isaccount", 1);
            result.put("realname", account.getRealname());
			
			result.put("idcardno", account.getIdcardno());
		}

		result.put("needpcb", 1);


		result.put("bankcardno", bank.getBankcardno());
		result.put("bankname", support.getName());
		result.put("provice", bank.getProvince());
		result.put("city", bank.getCity());
		result.put("branchname", bank.getBranchname());

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 设为默认银行卡
	@Action("136")
	public void defaultbankcard() {

		String accountid = getParameter("accountid");
		String bankcardid = getParameter("bankcardid");

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountid", accountid);
		map.put("statusIn", Accountbankcard.STATUS_NOT_VERIFIED + "," + Accountbankcard.STATUS_VERIFIED);
		List<Accountbankcard> list = accountbankcardDao.select(map);
		if (list.size() > 0) {
			for (Accountbankcard card : list) {
				if (card.getStatus() == Accountbankcard.STATUS_VERIFIED) {
					if (bankcardid.equals(String.valueOf(card.getId()))) {
						card.setIsdefault(Accountbankcard.ISDEFAULT_YES);

					} else {
						card.setIsdefault(Accountbankcard.ISDEFAULT_NO);
					}

					accountbankcardDao.update(card);
				}else{
					result.put("returnCode", 1);
					result.put("returnMsg", "未认证的银行卡不能设为默认银行卡");
					print(result);
					return;
					
				}

			}
		}

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 解绑银行卡
	@Action("137")
	public void unbindbankcard() {
		String accountid = getParameter("accountid");
		String bankcardid = getParameter("bankcardid");

		Map<String, Object> result = new HashMap<String, Object>();
		Accountbankcard card = accountbankcardDao.selectById(Long.parseLong(bankcardid));
		if(card.getStatus()==Accountbankcard.STATUS_VERIFIED){
			card.setStatus(Accountbankcard.STATUS_UNBIND);
			accountbankcardDao.update(card);
		}else{
			result.put("returnCode", 1);
			result.put("returnMsg", "未认证的银行卡不能解绑");
			print(result);
			return;
			
		}


		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 获取省份
	@Action("138")
	public void selectprovinces() {
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("province", JSONArray.toJSON(PlaceUtil.prov));
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 获取城市
	@Action("139")
	public void selectcity() {

		String provinces = getParameter("provinces");

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("city", JSONArray.toJSON(PlaceUtil.citymap.get(provinces)));
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}
	
	
	// 删除银行卡
		@Action("140")
		public void deletebankcard() {
			String accountid = getParameter("accountid");
			String bankcardid = getParameter("bankcardid");

			Map<String, Object> result = new HashMap<String, Object>();
			Accountbankcard card = accountbankcardDao.selectById(Long.parseLong(bankcardid));
			if(card.getStatus()==Accountbankcard.STATUS_NOT_VERIFIED){
				card.setStatus(Accountbankcard.STATUS_DELETE);
				accountbankcardDao.update(card);
			}else{
				result.put("returnCode", 1);
				result.put("returnMsg", "已认证的银行卡不能删除");
				print(result);
				return;
				
			}
			result.put("returnCode", 0);
			result.put("returnMsg", "success");
			print(result);
			return;

		}

}
