package com.cslc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.shorturl.Shorturl;
import com.cslc.dao.shorturl.ShorturlDao;
import com.cslc.dao.sms.Sms;
import com.cslc.dao.sms.SmsDao;
import com.cslc.dao.submitcode.Submitcode;
import com.cslc.dao.submitcode.SubmitcodeDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.platform.constant.SystemConstant;
import com.platform.util.StringUtil;
import com.platform.util.UUIDUtil;

@Service("bizService")
public class BizService {

	@Resource
	private SmsDao smsDao;
	
	@Resource
	private SmsService smsService;

	@Resource
	private SystemdefDao systemdefDao;

	@Resource
	private SubmitcodeDao submitcodeDao;

	@Resource
	private ShorturlDao shorturlDao;

	public void sendSmsNow(String mobile, String content, int category) {
		Sms sms = new Sms();
		sms.setMobile(mobile);
		sms.setCreatetime(new Date());
		sms.setCategory(category);
		sms.setStatus(Sms.STATUS_WAITTING);
		sms.setContent(content);
		smsDao.insert(sms);

		smsService.sendSingle(sms);
	}

	public boolean sendSmsWithLimit(String mobile, String content, String code, int category) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobile", mobile);
		map.put("daystime", new Date());
		map.put("days", 1);
		map.put("status", Sms.STATUS_POSTED);
		if (smsDao.selectCount(map) <= 5) {
			Sms sms = new Sms();
			sms.setMobile(mobile);
			sms.setCreatetime(new Date());
			sms.setCategory(category);
			sms.setStatus(Sms.STATUS_WAITTING);
			sms.setVerifycode(code);
			sms.setContent(content);
			smsDao.insert(sms);

			smsService.sendSingle(sms);
			return true;
		}
		return false;
	}

	public void createSmsAssginTime(String[] mobiles, String content, Date sendTime, int category, byte channel) {
		if (mobiles.length == 0 || sendTime == null) { // 手机号、发送时间不能为空
			return;
		}
		if (sendTime.before(new Date())) { // 发送时间不能比当前时间早
			return;
		}
		for (String mobile : mobiles) {
			if (StringUtil.isBlank(mobile) || !mobile.matches("^\\d{11}$")) { // 是手机号
				continue;
			}
			Sms sms = new Sms();
			sms.setChannel(channel);// 指定渠道
			sms.setContent(content);
			sms.setCreatetime(new Date());
			sms.setMobile(mobile);
			sms.setStatus(Sms.STATUS_WAITTING);
			sms.setCategory(category);
			sms.setSendtime(sendTime);
			smsDao.insert(sms);
		}
	}
	
	// 重发失败短信
	public boolean sendFail(String orderid, byte oldChannel){
		if(StringUtil.isNotBlank(orderid)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderid", orderid);
			List<Sms> smsList = smsDao.select(map);
			if(smsList.size() > 0){
				Sms s = smsList.get(0);
				
				Sms old = new Sms();
				old.setId(s.getId());
				old.setStatus(Sms.STATUS_FAILURE);
				smsDao.update(old);
				
				Sms sms = new Sms();
				sms.setMobile(s.getMobile());
				sms.setCreatetime(new Date());
				sms.setCategory(s.getCategory());
				sms.setStatus(Sms.STATUS_POSTED);
				sms.setVerifycode(s.getVerifycode());
				sms.setContent(s.getContent());
				smsDao.insert(sms);
				return smsService.sendSingle(sms, smsService.changeChannel(oldChannel));
			}
		}
		return false;
	}

	public String getSystemval(String k) {
		return systemdefDao.selectById(k).getV();
	}

	public Map<String, Object> getSystemjson(String key) {
		return (Map<String, Object>) JSONObject.parse(getSystemval(key));
	}
	
	public void updateSystemval(String key, String val) {
		Systemdef def = new Systemdef();
		def.setK(key);
		def.setV(val);
		systemdefDao.update(def);
	}

	public void updateSystemjson(String key, Map<String, Object> val) {
		updateSystemval(key, JSONObject.toJSONString(val));
	}

	public String createSubmitcode(String ip, int category) {
		if (StringUtil.isNotBlank(ip)) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("ip", ip);
			m.put("status", Submitcode.STATUS_UNUSED);
			m.put("category", category);
			m.put("daystime", new Date());
			m.put("days", 1);
			if (submitcodeDao.selectCount(m) > 1000) {// 同一页面可被刷新多少次
				return null;
			}

			String submitcode = UUIDUtil.get();
			Date createTime = new Date();
			Submitcode sc = new Submitcode();
			sc.setCategory(category);
			sc.setStatus(Submitcode.STATUS_UNUSED);
			sc.setCode(submitcode);
			sc.setCreatetime(createTime);
			sc.setIp(ip);
			submitcodeDao.insert(sc);

			return submitcode;
		}
		return null;
	}

	public boolean verifySubmitcode(String code, int category) {
		int retryCount = 0;
		if (category == Submitcode.CATEGORY_SEND_SMS) {
			retryCount = 20;
		} else if (category == Submitcode.CATEGORY_REGIST) {
			retryCount = 20;
		}

		if (StringUtil.isNotBlank(code)) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("code", code);
			m.put("category", category);
			List<Submitcode> codelist = submitcodeDao.select(m);
			if (codelist.size() > 0) {
				Submitcode c = codelist.get(0);

				Map<String, Object> m2 = new HashMap<String, Object>();
				m2.put("ip", c.getIp());
				m2.put("status", Submitcode.STATUS_USED);
				m2.put("category", category);
				m2.put("daytime", new Date());
				m2.put("days", 1);
				if (submitcodeDao.selectCount(m2) <= retryCount) {
					if (c.getStatus() == Submitcode.STATUS_UNUSED) {
						Submitcode s = new Submitcode();
						s.setId(codelist.get(0).getId());
						s.setStatus(Submitcode.STATUS_USED);
						return submitcodeDao.update(s);
					} else {
						// 支持重发
						if (category == Submitcode.CATEGORY_SEND_SMS) {
							Submitcode sc = new Submitcode();
							sc.setCategory(Submitcode.CATEGORY_SEND_SMS);
							sc.setStatus(Submitcode.STATUS_USED);
							sc.setCreatetime(new Date());
							sc.setIp(c.getIp());
							submitcodeDao.insert(sc);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// 生成短链接
	public String createShortUrl(String url) {
		String shortcode = null;
		for (int i = 0; i < 20; i++) {
			String code = StringUtil.getRandomCode(6, false, false);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("code", code);
			long count = shorturlDao.selectCount(m);
			if (count == 0) {
				shortcode = code;
				break;
			}
		}

		Shorturl u = new Shorturl();
		u.setCode(shortcode);
		u.setCreatetime(new Date());
		u.setUrl(url);
		shorturlDao.insert(u);

		return SystemConstant.WEB_URL + "/" + shortcode + "/";
	}

}
