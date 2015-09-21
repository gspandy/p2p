package com.platform.util;

import java.util.ResourceBundle;

/**
 * 读取应用配置
 */
public final class ResourceUtil {

	private static ResourceBundle system;

	static {
		system = ResourceBundle.getBundle("system");
	}

	public static String getSystem(final String key) {
		try {
			return system.getString(key);
		} catch (Exception e) {
			return null;
		}
	}
	
}
