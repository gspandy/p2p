package com.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.StringUtil;

public class ServiceDoorInterceptor implements Interceptor {
    
	protected static Logger log = Logger.getLogger(ServiceDoorInterceptor.class);
    private static final Logger urlsLog = Logger.getLogger("urlsLog");
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

		String url = request.getRequestURI();

		// 浏览器访问
		String version = StringUtil.getUserAgent(request, "version");
        String terminalid = StringUtil.getUserAgent(request, "terminalid");
        if (SystemConstant.VERIFY_APP_ENABLE && (StringUtil.isBlank(version) || StringUtil.isBlank(terminalid))) {
        	log.error("Illegal Request: " + StringUtil.getIp(request) + ", " + url);
            if(url.startsWith("/ddsc/error.html") || url.startsWith("/callback/") || (SystemConstant.X_ENABLE && url.startsWith("/x/"))) {
                return invocation.invoke();
            }
            response.sendRedirect("/ddsc/error.html");
            return null;
        }
        
        return invocation.invoke();
	}

	public void destroy() {

	}

	public void init() {

	}

}
