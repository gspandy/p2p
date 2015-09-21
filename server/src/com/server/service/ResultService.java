package com.server.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountbankcard.Accountbankcard;
import com.cslc.dao.accountbankcard.AccountbankcardDao;
import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;
import com.cslc.dao.message.MessageDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.supportbank.SupportbankDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.dao.systemlog.Systemlog;
import com.cslc.dao.systemlog.SystemlogDao;
import com.cslc.dao.trade.Trade;
import com.cslc.dao.trade.TradeDao;
import com.cslc.service.BizService;
import com.platform.constant.SystemConstant;
import com.platform.util.MemcacheUtil;
import com.platform.util.StringUtil;
import com.platform.util.UUIDUtil;

@Service("resultService")
public class ResultService {

	protected static Logger paysuccessLog = Logger.getLogger("paysuccessLog");
	protected static Logger concurrentLog = Logger.getLogger("concurrentLog");

	@Resource
	private SelfitemDao selfitemDao;

	@Resource
	private SystemdefDao systemdefDao;

	@Resource
	private SystemlogDao systemlogDao;

	@Resource
	private TradeDao tradeDao;

	@Resource
	private AccountselfitemDao accountselfitemDao;

	@Resource
	private AccountbankcardDao accountbankcardDao;

	@Resource
	private AccountDao accountDao;

	@Resource
	private BonusDao bonusDao;

	@Resource
	protected MemcacheUtil memcacheUtil;

	@Resource
	private SupportbankDao supportbankDao;

	@Resource
	private BizService bizService;

	@Resource
	private MessageDao messageDao;

	@Resource
	private AccountassetDao accountassetDao;

	public void updateSuccessRecords(Trade trade, String realname, String idcardno, String bonusid) {

		Account account_before = accountDao.selectById(trade.getAccountid());// 获得更新之前的account信息，判断是否交易

		Accountasset accountasset_before = accountassetDao.selectById(trade.getAccountid());//
		// 记录成功支付日志
		Map<String, Object> logmap = new HashMap<String, Object>();
		logmap.put("trade", trade);
		paysuccessLog.info(JSONObject.toJSONString(logmap));

		// 交易记录表更新
		Trade t = new Trade();
		t.setId(trade.getId());
		t.setPaysuccesstime(new Date());
		tradeDao.update(t);

		// 持有产品表插入
		Map<String, Object> asmap = new HashMap<String, Object>();
		asmap.put("accountid", trade.getAccountid());
		asmap.put("selfitemid", trade.getSelfitemid());
		asmap.put("tradeid", trade.getId());
		List<Accountselfitem> asList = accountselfitemDao.select(asmap);
		double totalamount = trade.getAmount();
		if (asList.size() > 0) {
			Accountselfitem ai = asList.get(0);
			ai.setAmount(ai.getAmount() + totalamount);
			accountselfitemDao.update(ai);
		} else {
			Accountselfitem ai = new Accountselfitem();
			ai.setAccountid(trade.getAccountid());
			ai.setSelfitemid(trade.getSelfitemid());
			ai.setBankcardid(trade.getBankcardid());
			ai.setTradeid(trade.getId());
			ai.setAmount(totalamount);
			ai.setStatus(Accountselfitem.STATUS_ACTIVE);
			ai.setCreatetime(new Date());
			accountselfitemDao.insert(ai);
		}
		concurrentLog.info("selfitemid_current:" + trade.getSelfitemid() + ",tradeid:" + trade.getId()
				+ ",finish accountselfitem operations without selfitem table!");

		// 产品表更新
		try {
			concurrentLog.info(
					"selfitemid_current:" + trade.getSelfitemid() + ",tradeid:" + trade.getId() + ",start selfitem");
			Selfitem item = selfitemDao.selectById(trade.getSelfitemid());
			concurrentLog.info("selfitemid_current:" + trade.getSelfitemid() + ",tradeid:" + trade.getId()
					+ ",find the selfitem record");
			double freeamount = item.getFreezeamount() - totalamount;
			double restamount = item.getRestamount() - totalamount;
			Selfitem si = new Selfitem();
			si.setId(trade.getSelfitemid());
			si.setFreezeamount(freeamount);
			si.setRestamount(restamount);
			if (restamount == 0 && freeamount <= 0) {
				si.setStatus(Selfitem.STATUS_SELLOUT);
				si.setEndtime(new Date());
			}
			selfitemDao.update(si);
			concurrentLog.info("selfitemid_current:" + trade.getSelfitemid() + ",tradeid:" + trade.getId()
					+ ",find the selfitem update");
		} catch (Exception e) {
			concurrentLog.error("find a error!");
			concurrentLog.error(e.getMessage());
		}

		// 红包


		double tamout = totalamount;
		String[] list = bonusid.split(",");
		for (int i = 0; i < list.length; i++) {
			Bonus bonus = bonusDao.selectById(Long.parseLong(list[i]));
			if (bonus.getStatus() == Bonus.STATUS_UNUSED) {

				Bonus b = new Bonus();
				if (bonus.getTotalbonus() * bonus.getRate() >= tamout) {
					break;
				} else {
					bonus.setUsebonus(bonus.getTotalbonus());
					tamout -= bonus.getTotalbonus() * bonus.getRate();
				}

				b.setId(bonus.getId());
				b.setBacktime(new Date());
				b.setSelfitemid(trade.getSelfitemid());
				bonus.setStatus(Bonus.STATUS_USING);
				bonusDao.update(bonus);

			}
		}

		// 账户表更新
		Account a = new Account();
		a.setId(trade.getAccountid());
		if (account_before.getTradecount() == 0) {
			a.setIdcardno(idcardno);
			a.setRealname(realname);
		}
		a.setTradecount(account_before.getTradecount() + 1);
		a.setLasttradetime(new Date());
		accountDao.update(a);

		// 账户收益表更新
		Accountasset s = new Accountasset();
		s.setAccountid(trade.getAccountid());
		s.setSelfitemamount(accountasset_before.getSelfitemamount() + trade.getAmount());
		s.setSelfitemasset(accountasset_before.getSelfitemasset() + trade.getAmount());
		accountassetDao.update(s);

		// 银行卡表插入
		Map<String, Object> cm = new HashMap<String, Object>();
		cm.put("accountid", trade.getAccountid());
		cm.put("statusIn", Accountbankcard.STATUS_VERIFIED + "," + Accountbankcard.STATUS_NOT_VERIFIED);
		List<Accountbankcard> cmlist = accountbankcardDao.select(cm);
		for (Accountbankcard c : cmlist) {
			Accountbankcard ab = new Accountbankcard();
			ab.setId(c.getId());

			if (String.valueOf(trade.getBankcardid()).equals(String.valueOf((c.getId())))) {
				ab.setStatus(Accountbankcard.STATUS_VERIFIED);
				ab.setIsdefault(Accountbankcard.ISDEFAULT_YES);
			} else {
				ab.setIsdefault(Accountbankcard.ISDEFAULT_NO);
			}
			accountbankcardDao.update(ab);
		}

	}

	public void updateFailureRecords(Trade trade, String errorMsg, boolean selfitemFreeze) {

		Account account = accountDao.selectById(trade.getAccountid());
		double totalamount = trade.getAmount();

		// 交易记录表更新
		Trade st = new Trade();
		st.setId(trade.getId());
		st.setStatus(Trade.STATUS_PAY_FAILURE);
		st.addField("errorMsg", errorMsg, trade.getJson());
		tradeDao.update(st);

		// 产品表更新
		if (selfitemFreeze) {
			Selfitem item = selfitemDao.selectById(trade.getSelfitemid());
			double freeamount = item.getFreezeamount() - totalamount;
			Selfitem si = new Selfitem();
			si.setId(trade.getSelfitemid());
			si.setFreezeamount(freeamount);
			selfitemDao.update(si);
			concurrentLog.info("selfitemid:" + trade.getSelfitemid() + ",tradeid:" + trade.getId() + ",freezeamount:"
					+ si.getFreezeamount());
		}
	}

}
