package com.server.interceptor;

import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.alibaba.fastjson.JSONObject;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.StringUtil;
import com.platform.util.encrypt.AES;

public class DecryptInterceptor implements Interceptor {
	
	protected static Logger log = Logger.getLogger(BaseAction.class);
	protected static Logger urlsLog = Logger.getLogger("urlsLog");
	
	@Resource
	private AccountDao accountDao;

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		
		if(!SystemConstant.PARAMS_ENCRYPT || request.getRequestURI().startsWith("/callback/")){
			return invocation.invoke();
		}
		
		String dynamicpwd = SystemConstant.DYNAMICPWD_KEY;
		TreeMap<String, Object> params = new TreeMap<String, Object>();

		// UA
		String agent = request.getHeader("user-agent");
        if (StringUtil.isNotBlank(agent)) {
        	String json = AES.decryptFromBase64(agent, dynamicpwd);
        	Map map = JSONObject.parseObject(json);
        	params.put("ua_terminalid", String.valueOf(map.get("terminalid")));
        	params.put("ua_phone", String.valueOf(map.get("phone")));
        	params.put("ua_version", String.valueOf(map.get("version")));
        	params.put("ua_rom", String.valueOf(map.get("rom")));
        	params.put("ua_platform", String.valueOf(map.get("platform")));
        	dynamicpwd = String.valueOf(map.get("dynamicpwd"));
        }
		
		Map ps = request.getParameterMap();
		Object vs = ps.get("v");
		if(vs != null){
			String v = ((String[]) vs)[0];
			if(StringUtil.isNotBlank(v) && dynamicpwd.length() == 16){
				String k = dynamicpwd.substring(12) + dynamicpwd.substring(0, 12);
				v = URLDecoder.decode(v).replace(" ", "+");
				try {	
					String json = AES.decryptFromBase64(v, k);
					Map map = JSONObject.parseObject(json);
					if(map != null){
						Set<Map.Entry> s = map.entrySet();
						for(Map.Entry m : s){
							if(m.getValue() instanceof String){
								String ss = (String) m.getValue();
								// post无需转码, get需要转码
								if(!SystemConstant.PARAMS_ENCRYPT && "GET".equals(request.getMethod())){
									ss = new String(ss.getBytes("iso8859-1"), "utf-8");
								}
								if(StringUtil.isNotBlank(ss)){
									params.put((String) m.getKey(), new String[]{ss});
								}
							}
						}
						
						String accountdynamicpwd = null;
						if(params.get("accountid") != null){
							String accountid = ((String[]) params.get("accountid"))[0];
							if(StringUtil.isNotBlank(accountid)){
								Account account = accountDao.selectById(Long.parseLong(accountid));
								if(account != null){
									accountdynamicpwd = account.getDynamicpwd();
								}
							}
						}
						if(dynamicpwd.equals(accountdynamicpwd)){
							request.setAttribute("paramsMap", params);
							request.setAttribute("decrypt39ba59163ad2165d", true);
							return invocation.invoke();
						}else{
							return "login";
						}
					}
				} catch (Exception e) {
					log.error(e);
					return "login";
				}
			}
		}else{
			request.setAttribute("paramsMap", params);
			request.setAttribute("decrypt39ba59163ad2165d", true);
			return invocation.invoke();
		}
		return "error";
	}

	public void destroy() {

	}

	public void init() {

	}

}
