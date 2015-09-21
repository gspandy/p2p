package com.cslc.service;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cslc.api.qiantong.QiantongSms;
import com.cslc.api.qiantong.XmlUtil;
import com.cslc.api.yimei.example.YimeiSms;
import com.cslc.dao.sms.Sms;
import com.cslc.dao.sms.SmsDao;
import com.cslc.dao.systemdef.Systemdef;
import com.platform.util.StringUtil;

@Service("smsService")
public class SmsService {
	
	protected static Logger log = Logger.getLogger(SmsService.class);
	
	@Resource
	private SmsDao smsDao;
	
	@Resource
	private BizService bizService;
	
	public boolean sendSingle(Sms sms){
		Map<String, Object> channel = bizService.getSystemjson(Systemdef.SMS_CHANNEL);
		return sendSingle(sms, (String) channel.get("single"));
	}
	
	public boolean sendSingle(Sms sms, String channel){
		if(!(sms != null && StringUtil.isNotBlank(sms.getMobile()) && sms.getMobile().length() == 11)){
			return false;
		}
		
		try {
			if("qiantong".equals(channel)){
				String xml = QiantongSms.sendSms(sms.getMobile(), sms.getContent());
				Map<String, String> map = XmlUtil.convertResponseXml2Map(xml);
				if(map != null && "0".equals(map.get("status"))){
					Sms s = new Sms();
					s.setId(sms.getId());
					s.setOrderid(map.get("msgid"));
					s.setChannel(Sms.CHANNEL_QIANTONG);
					s.setStatus(Sms.STATUS_POSTED);
					smsDao.update(s);
					return true;
				}else{
					channel = changeChannel(Sms.CHANNEL_QIANTONG);
				}
			}
			// 发送失败，则发亿美
			if("yimei".equals(channel)){
				int result = YimeiSms.getClient().sendSMS(new String[]{sms.getMobile()}, sms.getContent(), null, 5);
				if(result == 0){
					Sms s = new Sms();
					s.setId(sms.getId());
					s.setChannel(Sms.CHANNEL_YIMEI);
					s.setStatus(Sms.STATUS_POSTED);
					smsDao.update(s);
					return true;
				}else{
					channel = changeChannel(Sms.CHANNEL_YIMEI);
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		Sms s = new Sms();
		s.setId(sms.getId());
		s.setStatus(Sms.STATUS_FAILURE);
		smsDao.update(s);
		return false;
	}
	
	public String changeChannel(byte oldChannel){
		Map<String, Object> channel = bizService.getSystemjson(Systemdef.SMS_CHANNEL);
		String failChannel = "";
		if(oldChannel == Sms.CHANNEL_QIANTONG){
			failChannel = (String) channel.get("qiantongFail");
		}else if(oldChannel == Sms.CHANNEL_YIMEI){
			failChannel = (String) channel.get("yimeiFail");
		}
		return failChannel;
	}
	
	public boolean sendBatch(String[] mobiles, String content, int category){
		try {
			Map<String, Object> channel = bizService.getSystemjson(Systemdef.SMS_CHANNEL);
			
			if("yimei".equals(channel.get("batch"))){
				if(mobiles.length > 0){
					int result = YimeiSms.getMarketingClient().sendSMS(mobiles, content, null, 3);
					if(result == 0){
						Sms s = new Sms();
						s.setContent(content);
						s.setChannel(Sms.CHANNEL_YIMEI);
						s.setMobile("批量发送：" + mobiles.length + "。");
						s.setCreatetime(new Date());
						s.setCategory(category);
						s.setStatus(Sms.STATUS_POSTED);
						smsDao.insert(s);
						return true;
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		return false;
	}
    
}

