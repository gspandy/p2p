package com.platform.util;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.platform.constant.SystemConstant;

@Service("memcacheUtil")
public class MemcacheUtil {

	@Resource
	private MemCachedClient memcache;

	public Object get(String key) {
		if (SystemConstant.CACHE_ENABLE) {
			return memcache.get(key);
		}
		return null;
	}

	public void put(String key, Object value) {
		if (SystemConstant.CACHE_ENABLE) {
			memcache.set(key, value, new Date(System.currentTimeMillis() + 60 * 60 * 1000));// 1h
		}
	}

	public boolean remove(String key) {
		if (SystemConstant.CACHE_ENABLE) {
			return memcache.delete(key);
		}
		return false;
	}
}
