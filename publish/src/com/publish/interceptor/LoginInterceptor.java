package com.publish.interceptor;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.publish.CookieUtil;

public class LoginInterceptor implements Interceptor {
	
	@Resource
	private AccountDao accountDao;
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

		String url = request.getRequestURI();
		if(url.lastIndexOf("/") == 0){
			return invocation.invoke();
		}else{
			boolean isLogin = CookieUtil.isLogin(request);
			if(isLogin){
				Account account = CookieUtil.getUserCookie(request);
				account = accountDao.selectById(account.getId());
				request.setAttribute("loginUser", account);
			}else{
				response.sendRedirect("/to_login.html?url=" + url + (request.getQueryString() != null ? URLEncoder.encode("?" + request.getQueryString()) : ""));
				return null;
			}
		}
		
		return invocation.invoke();
	}
	
	public void destroy() {

	}

	public void init() {

	}

}
