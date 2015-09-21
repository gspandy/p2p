package com.publish.interceptor;

import java.util.ResourceBundle;

public class Department {
	
    // 运营
    public static final String YUNYING_SITE = "YUNYING_SITE";
    public static final String YUNYING_APP = "YUNYING_APP";
    public static final String YUNYING_PBS = "YUNYING_PBS";
    
    // 客服
    public static final String KEFU = "KEFU";
    
    // 财务
    public static final String CAIWU = "CAIWU";
    
    // 技术
    public static final String JISHU = "JISHU";

    public static boolean isAllowed(String url, String department) {
        ResourceBundle group = ResourceBundle.getBundle("department");
        String module = url.substring(1, url.indexOf("/", 1));
        String[] modules = group.getString(department).split(",");
        for (String m : modules) {
            if (m.equals(module)) {
                return true;
            }
        }
        return false;
    }

}
