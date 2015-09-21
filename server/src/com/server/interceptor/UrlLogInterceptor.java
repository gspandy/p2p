package com.server.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.platform.constant.SystemConstant;
import com.platform.util.StringUtil;

public class UrlLogInterceptor implements Interceptor {
	
	protected static Logger urlsLog = Logger.getLogger("urlsLog");
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);

		// 解析链接
		if(SystemConstant.LOG_REQUEST_URL_ENABLE){
			StringBuffer sb = new StringBuffer();
			Map ps = request.getParameterMap();
			if(!ps.isEmpty()){
				sb.append("?");
				Set<Map.Entry> s = ps.entrySet();
				int k = 0;
				for(Map.Entry m : s){
					if(m.getValue() instanceof String[]){
						String[] ss = (String[]) m.getValue();
						for(int i = 0; i < ss.length; i ++){
							String sss = new String(ss[i].getBytes("iso8859-1"), "utf-8");
							if(k > 0){
								sb.append("&");
							}
							sb.append((String) m.getKey());
							sb.append("=");
							sb.append(sss);
							k ++;
						}
					}
				}
			}
			urlsLog.info(StringUtil.getIp(request) + " - " + request.getRequestURL() + sb.toString());
		}
		
		return invocation.invoke();
	}

	public void destroy() {

	}

	public void init() {

	}

}
