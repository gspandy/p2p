package com.platform.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ParamsInterceptor implements Interceptor {
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);

		if(request.getAttribute("decrypt39ba59163ad2165d") == null){
			Map ps = request.getParameterMap();
			Map params = new HashMap();
			Set<Map.Entry> s = ps.entrySet();
			for(Map.Entry m : s){
				if(m.getValue() instanceof String[]){
					String[] ss = (String[]) m.getValue();
					for(int i = 0; i < ss.length; i ++){
						// post无需转码, get需要转码
						if("GET".equals(request.getMethod())){
							ss[i] = new String(ss[i].getBytes("iso8859-1"), "utf-8");
						}
					}
					params.put((String) m.getKey(), ss);
				}
			}
			request.setAttribute("paramsMap", params);
		}
		
		return invocation.invoke();
	}
	
	public void destroy() {

	}

	public void init() {

	}

}
