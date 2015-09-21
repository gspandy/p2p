package com.server.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountbankcard.AccountbankcardDao;
import com.cslc.dao.accountconfig.Accountconfig;
import com.cslc.dao.accountconfig.AccountconfigDao;
import com.cslc.dao.message.Message;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.sms.Sms;
import com.cslc.dao.sms.SmsDao;
import com.cslc.dao.submitcode.Submitcode;
import com.cslc.dao.submitcode.SubmitcodeDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.dao.trade.TradeDao;
import com.cslc.service.BizService;
import com.cslc.service.RegistService;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.MD5Util;
import com.platform.util.MemcacheUtil;
import com.platform.util.StringUtil;
import com.platform.util.TemplateUtil;
import com.platform.util.UUIDUtil;

@ParentPackage("app")
@Namespace("/")
public class AccountAction extends BaseAction {
	@Resource
	private AccountDao accountDao;

	@Resource
	private AccountassetDao accountassetDao;

	@Resource
	private TradeDao tradeDao;

	@Resource
	private SelfitemDao selfitemDao;

	@Resource
	private SmsDao smsDao;

	@Resource
	private AccountbankcardDao accountbankcardDao;

	@Resource
	private SystemdefDao systemdefDao;

	@Resource
	private SubmitcodeDao submitcodeDao;

	@Resource
	private AccountconfigDao accountconfigDao;

	@Resource
	private MemcacheUtil memcacheUtil;

	@Resource
	private RegistService registService;

	@Resource
	private BizService bizService;

	// 输入手机号
	@Action("150")
	public void mobile() {
		String mobile = getParameter("mobile");

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		List<Account> list = accountDao.select(map);
		if (list.size() > 0) {
			Account account = list.get(0);
			if (account.getStatus() == Account.STATUS_NORMAL) {
				result.put("status", 1);
				result.put("tip", "登录");
			}
		} else {
			result.put("status", 2);
			result.put("tip", "注册");
		}

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 注册
	@Action("151")
	public void regist() {
		String mobile = getParameter("mobile");
		String password = getParameter("password");
		String code = getParameter("code");

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> smap = new HashMap<String, Object>();
		smap.put("mobile", mobile);
		smap.put("minutes", 10);
		smap.put("category", Sms.CATEGORY_ACCOUNT);
		smap.put("status", Sms.STATUS_POSTED);
		smap.put("orderBy", "createtime desc");
		List<Sms> slist = smsDao.select(smap);
		if (slist.size() > 0) {
			Sms s = slist.get(0);
			if (s.getVerifycode().equals(code)) {
				Sms sms = new Sms();
				sms.setId(s.getId());
				sms.setStatus(Sms.STATUS_VERIFIED);
				smsDao.update(sms);

				String channel = StringUtil.getUserAgent(request, "channel");
				if (StringUtil.isBlank(channel)) {
					channel = "web";
				}

				String ret = registService.regist(mobile, password, channel, null, null);
				if ("commandcodeError".equals(ret)) {
					result.put("returnCode", 1);
					result.put("returnMsg", "邀请码错误");
					print(result);
					return;
				} else if ("mobileError".equals(ret)) {
					result.put("returnCode", 1);
					result.put("returnMsg", "该手机号已注册");
					print(result);
					return;
				} else {
					result.put("returnCode", 0);
					result.put("returnMsg", "success");
					Account account = accountDao.selectById(Long.parseLong(ret));
					result.put("accountid", account.getId());
					result.put("dynamicpwd", account.getDynamicpwd());

					print(result);
					return;
				}
			}
		}
		result.put("returnCode", 1);
		result.put("returnMsg", "您的验证码输入有误");
		print(result);
		return;

	}

	// 登录
	@Action("152")
	public void login() {
		String mobile = getParameter("mobile");
		String password = getParameter("password");
		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtil.isBlank(mobile)) {
			result.put("returnCode", 1);
			result.put("returnMsg", "请输入手机号");
			print(result);
			return;
		}

		Map<String, Object> tmap = new HashMap<String, Object>();
		tmap.put("mobile", mobile);
		if (accountDao.selectCount(tmap) == 0) {
			result.put("returnCode", 1);
			result.put("returnMsg", "该手机号还未注册");
			print(result);
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("status", Account.STATUS_NORMAL);
		map.put("loginpwd", MD5Util.encrypt(password + SystemConstant.LOGINPWD_KEY));
		List<Account> alist = accountDao.select(map);
		if (alist.size() > 0) {
			Account account = alist.get(0);
			String dynamicpwd = UUIDUtil.get().substring(0, 16);

			Account a = new Account();
			a.setId(account.getId());
			a.setDynamicpwd(dynamicpwd);
			a.setLastlogintime(new Date());
			accountDao.update(a);

			Accountconfig ac = new Accountconfig();
			ac.setAccountid(account.getId());
			ac.setPlatform(StringUtil.getPlatform(request));
			ac.setVersion(StringUtil.getUserAgent(request, "version"));
			ac.setTerminalid(StringUtil.getUserAgent(request, "terminalid"));
			ac.setRom(StringUtil.getUserAgent(request, "rom"));
			ac.setPhone(StringUtil.getUserAgent(request, "phone"));
			accountconfigDao.createOrUpdate(ac);
			result.put("dynamicpwd", dynamicpwd);
			result.put("accountid", account.getId());

			result.put("returnCode", 0);
			result.put("returnMsg", "success");
			print(result);
			return;
		}

		result.put("returnCode", 1);
		result.put("returnMsg", "您的密码输入有误，请重新输入");
		print(result);
		return;
	}

	// 修改密码
	@Action("153")
	public void changepassword() {
		long accountid = Long.parseLong(getParameter("accountid"));
		String newpassword = getParameter("newpassword");
		String oldpassword = getParameter("oldpassword");

		Map<String, Object> result = new HashMap<String, Object>();

		Account account = accountDao.selectById(accountid);
		if (account.getLoginpwd().equals(MD5Util.encrypt(oldpassword + SystemConstant.LOGINPWD_KEY))) {
			if (MD5Util.encrypt(newpassword + SystemConstant.LOGINPWD_KEY).equals(account.getLoginpwd())) {
				result.put("returnCode", 1);
				result.put("returnMsg", "新密码不能与原密码相同");
				print(result);
				return;
			}

			Account a = new Account();
			a.setId(accountid);
			a.setLoginpwd(MD5Util.encrypt(newpassword + SystemConstant.LOGINPWD_KEY));
			accountDao.update(a);

			result.put("returnCode", 0);
			result.put("returnMsg", "success");
			print(result);
			return;
		}
		result.put("returnCode", 1);
		result.put("returnMsg", "您的原密码输入有误");
		print(result);
		return;
	}

	// 找回密码
	@Action("154")
	public void findpassword() {
		String mobile = getParameter("mobile");
		String code = getParameter("code");
		String password = getParameter("password");

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> smap = new HashMap<String, Object>();
		smap.put("mobile", mobile);
		smap.put("minutes", 10);
		smap.put("category", Sms.CATEGORY_ACCOUNT);
		smap.put("status", Sms.STATUS_POSTED);
		smap.put("orderBy", "createtime desc");
		List<Sms> slist = smsDao.select(smap);
		if (slist.size() > 0) {
			Sms s = slist.get(0);
			if (s.getVerifycode().equals(code)) {
				Sms sms = new Sms();
				sms.setId(s.getId());
				sms.setStatus(Sms.STATUS_VERIFIED);
				smsDao.update(sms);

				Map<String, Object> amap = new HashMap<String, Object>();
				amap.put("mobile", mobile);
				List<Account> list = accountDao.select(amap);
				if (list.size() > 0) {
					Account a = new Account();
					a.setId(list.get(0).getId());
					a.setLoginpwd(MD5Util.encrypt(password + SystemConstant.LOGINPWD_KEY));
					accountDao.update(a);
					result.put("returnCode", 0);
					result.put("returnMsg", "success");
					print(result);
					return;
				} else {
					result.put("returnCode", 1);
					result.put("returnMsg", "该手机号还未注册");
					print(result);
					return;
				}
			}
		}

		result.put("returnCode", 1);
		result.put("returnMsg", "您的验证码输入有误");
		print(result);
		return;

	}

	// 找回密码发送短信
	@Action("155")
	public void sendsubmitcode() {
		Map<String, Object> result = new HashMap<String, Object>();
		String mobile = getParameter("mobile");

		Map<String, Object> smap = new HashMap<String, Object>();
		smap.put("mobile", mobile);
		smap.put("minutes", 1);
		smap.put("category", Sms.CATEGORY_ACCOUNT);
		smap.put("status", Sms.STATUS_POSTED);
		smap.put("orderBy", "createtime desc");
		if(smsDao.selectCount(smap) == 0){
			String code = StringUtil.getRandomCode(6, true, false);
			bizService.sendSmsWithLimit(mobile, TemplateUtil.merge(bizService.getSystemval(Systemdef.SMS_FORGET_PWD),
					new String[] { "code" }, new String[] { code }), code, Sms.CATEGORY_ACCOUNT);
		}
		
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;
	}

	// 注册发送短信
	@Action("156")
	public void sendregistcode() {
		Map<String, Object> result = new HashMap<String, Object>();
		String mobile = getParameter("mobile");

		Map<String, Object> smap = new HashMap<String, Object>();
		smap.put("mobile", mobile);
		smap.put("minutes", 1);
		smap.put("category", Sms.CATEGORY_ACCOUNT);
		smap.put("status", Sms.STATUS_POSTED);
		smap.put("orderBy", "createtime desc");
		if(smsDao.selectCount(smap) == 0){
			String code = StringUtil.getRandomCode(6, true, false);
			bizService.sendSmsWithLimit(mobile, TemplateUtil.merge(bizService.getSystemval(Systemdef.SMS_REGIST),
					new String[] { "code" }, new String[] { code }), code, Sms.CATEGORY_ACCOUNT);
		}
		
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;
	}

}
