package com.platform.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminModulesUtil {
	
	// ResourceUtil.get("");

	private static String[] adminModules = {
		"adminuser:/adminuser/ - 用户管理", "adminuserright:/adminuserright/ - 用户权限管理"
	};

	private static List<Module> modules;
	private static Map<String, String> modulesMap;

	public static List<Module> getModules() {
		if (modules == null) {
			init();
		}
		return modules;
	}
	
	public static String getInfoByName(String name) {
		if (modulesMap == null) {
			init();
		}
		return modulesMap.get(name);
	}

	private static void init() {
		modules = new ArrayList<Module>();
		modulesMap = new HashMap<String, String>();
		for (String s : adminModules) {
			Module m = new Module();
			String[] arr = s.split(":");
			m.setName(arr[0]);
			m.setInfo(arr[1]);
			modules.add(m);
			modulesMap.put(arr[0], arr[1]);
		}
	}
	
	public static class Module {
		private String name;
		private String info;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
	}

}
