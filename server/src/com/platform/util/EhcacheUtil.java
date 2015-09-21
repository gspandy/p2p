package com.platform.util;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.platform.constant.SystemConstant;

@Service("ehcacheUtil")
public class EhcacheUtil {

	@Resource
	private Ehcache cache;
	
	public Object get(String key) {
		if(SystemConstant.CACHE_ENABLE){
			Element e = cache.get(key);
			if(e != null){
				return (Object) e.getValue();
			}
		}
		return null;
	}

	public void put(String key, Object value) {
		if(SystemConstant.CACHE_ENABLE){
			cache.put(new Element(key, value));
		}
	}

	public boolean remove(String key) {
		if(SystemConstant.CACHE_ENABLE){
			return cache.remove(key);
		}
		return false;
	}
}
