package com.cslc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.platform.constant.SystemConstant;
import com.platform.util.MD5Util;

@Service("loginService")
public class LoginService {
	
	@Resource
	private AccountDao accountDao;
	
	public Account login(String mobile, String loginpwd){
		if (mobile.length() == 11 && loginpwd.length() >= 6) {
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("status", Account.STATUS_NORMAL);
	        map.put("mobile", mobile);
	        map.put("password", MD5Util.encrypt(loginpwd + SystemConstant.LOGINPWD_KEY));
	        List<Account> accountList = accountDao.select(map);
	        if (accountList.size() > 0) {
	        	return accountList.get(0);
	        }
		}
		return null;
	}
	
}

