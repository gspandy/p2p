package com.publish.interceptor;

import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountconfig.Accountconfig;
import com.cslc.dao.accountconfig.AccountconfigDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.publish.CookieUtil;

public class DepartmentInterceptor implements Interceptor {

    @Resource
    private AccountDao accountDao;
    
    @Resource
    private AccountconfigDao accountconfigDao;

    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

        String url = request.getRequestURI();
        if (url.lastIndexOf("/") == 0 || url.startsWith("/adminpicture")) {
            return invocation.invoke();
        }

        Account account = accountDao.selectById(CookieUtil.getUserCookie(request).getId());
        Accountconfig config = accountconfigDao.selectById(account.getId());

        boolean isLogin = CookieUtil.isLogin(request);
        if (isLogin) {
            String modules = ResourceBundle.getBundle("department").getString(config.getDepartment());
            request.setAttribute("modules", modules);
        } else {
            response.sendRedirect("/to_login.html?url=" + url + (request.getQueryString() != null ? URLEncoder.encode("?" + request.getQueryString()) : ""));
            return null;
        }

        if (Department.isAllowed(url, config.getDepartment())) {
            return invocation.invoke();
        }
        response.sendRedirect("/error.html");
        return null;
    }

    public void destroy() {

    }

    public void init() {

    }

}
