package com.platform.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 分页缓存工具
 * @author shengguo.hu
 * 
 * 单个模块moduleName保持一致
 * moduleName命名规则:项目名+模块名,map.put("moduleName", "SmairNews");
 * 如果没有关联的mainKey,在DaoImpl层map.put("mainKey", "none")即可
 * 使用分页缓存,必须放入mainKey,moduleName
 * moduleName必须放在Action,决定是否缓存分页,moduleName放在Dao层将缓存所有查询
 * mainKey作为分页最重要的条件,必须放在Action,例如userId,albumId..据此分页
 * 缓存形式:
 * 		MAIN-moduleNamemainKey:[key1,key2,key3...]
 * 		COUNT-moduleNamemainKey:[key1,key2,key3...]
 * 		moduleName-condition.hashCode():[key1,key2,key3...]
 */
public class PageCacheUtil {
	
	private final static Log log = LogFactory.getLog(PageCacheUtil.class);
	
	private static String MAIN_KEY = "MAIN-";
	private static String COUNT_KEY = "COUNT-";

	/**
	 * 根据map获取缓存对象,此方法更为高效,避免对cache的无效读操作
	 * @param mapKey
	 * @param cache
	 * @return
	 */
	public static Object get(String mainKey, Map<String, Object> mapKey, EhcacheUtil cache){
		if(mapKey == null || mapKey.get(mainKey) == null || mapKey.get("moduleName") == null){
			return null;
		}
		return get(getPageKeyFromMapKey(mapKey, (String) mapKey.get("moduleName")), cache);
	}
	
	/**
	 * 根据pageKey获取缓存对象
	 * @param pageKey
	 * @param cache
	 * @return
	 */
	public static Object get(String pageKey, EhcacheUtil cache){
		if(StringUtil.isBlank(pageKey) || cache == null){
			return null;
		}
		return cache.get(pageKey);
	}
	
	/**
	 * @param mainKey 唯一标识key
	 * @param pageKey 页面查询map
	 * @param value 缓存对象
	 * @param cache 缓存服务
	 */
	public static void put(String mainKey, Map<String, Object> mapKey, Object value, EhcacheUtil cache){
		if(mapKey == null || mapKey.get(mainKey) == null || mapKey.get("moduleName") == null){
			return;
		}
		put((String) mapKey.get("moduleName") + String.valueOf(mapKey.get(mainKey)), getPageKeyFromMapKey(mapKey, (String) mapKey.get("moduleName")), value, cache);
	}
	
	/**
	 * @param mainKeyValue 唯一标识key
	 * @param pageKey 页面唯一标识key
	 * @param value 缓存对象
	 * @param cache 缓存服务
	 */
	public static void put(String mainKeyValue, String pageKey, Object value, EhcacheUtil cache){
		if(StringUtil.isBlank(mainKeyValue) || StringUtil.isBlank(pageKey) || value == null || cache == null){
			return;
		}
		cache.put(pageKey, value);
		cache.put(MAIN_KEY + mainKeyValue, getNewKeysByMainKeyValue(mainKeyValue, pageKey, cache));
	}
	
	/**
	 * @param mainKeyValue 唯一标识key
	 * @param cache 缓存服务
	 */
	public static void remove(String mainKeyValue, EhcacheUtil cache){
		if(StringUtil.isBlank(mainKeyValue) || cache == null){
			return;
		}
		String[] keys = (String[]) cache.get(MAIN_KEY + mainKeyValue);
		if(keys != null && keys.length > 0){
			for(String k : keys){
				cache.remove(k);
			}
		}
		cache.remove(MAIN_KEY + mainKeyValue);
	}
	
	private static String getPageKeyFromMapKey(Map<String, Object> mapKey, String moduleName){
		if(mapKey == null){
			return null;
		}
		StringBuffer temp = new StringBuffer();
		for(Map.Entry<String, Object> e : mapKey.entrySet()){
			if((e.getValue() instanceof String || e.getValue() instanceof Boolean || e.getValue() instanceof Number) && !"startRow".equals(e.getKey()) && !"endRow".equals(e.getKey())){
				temp.append("_").append(e.getValue());
			}
		}
		String pageKey = temp.length() > 1 ? (moduleName + String.valueOf(temp.substring(1).hashCode())) : null;
		return pageKey;
	}
	
	private static String[] getNewKeysByMainKeyValue(String mainKeyValue, String pageKey, EhcacheUtil cache){
		String[] newKeys;
		String[] oldKeys = (String[]) cache.get(MAIN_KEY + mainKeyValue);
		if(oldKeys != null){
			newKeys = new String[oldKeys.length + 1];
			for(int i = 0; i < oldKeys.length; i ++){
				newKeys[i] = oldKeys[i];
			}
			newKeys[oldKeys.length] = pageKey;
		}else{
			String[] arr = {pageKey};
			newKeys = arr;
		}
		return newKeys;
	}
	
	public static Object getCount(String mainKey, Map<String, Object> mapKey, EhcacheUtil cache){
		if(mapKey == null || mapKey.get(mainKey) == null || mapKey.get("moduleName") == null){
			return null;
		}
		return get(COUNT_KEY + getPageKeyFromMapKey(mapKey, (String) mapKey.get("moduleName")), cache);
	}
	
	public static void putCount(String mainKey, Map<String, Object> mapKey, Object value, EhcacheUtil cache){
		if(StringUtil.isBlank(mainKey) || mapKey.get(mainKey) == null || mapKey.get(mainKey) == null || mapKey.get("moduleName") == null || mapKey == null || value == null || cache == null){
			return;
		}
		String pageKey = COUNT_KEY + getPageKeyFromMapKey(mapKey, (String) mapKey.get("moduleName"));
		cache.put(pageKey, value);
		String mainKeyValue = mapKey.get("moduleName") + String.valueOf(mapKey.get(mainKey));
		cache.put(MAIN_KEY + mainKeyValue, getNewKeysByMainKeyValue(mainKeyValue, pageKey, cache));
	}
	
}
