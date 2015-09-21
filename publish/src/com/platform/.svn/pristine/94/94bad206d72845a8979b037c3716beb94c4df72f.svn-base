package com.platform.base;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.platform.util.EhcacheUtil;
import com.platform.util.MemcacheUtil;

public abstract class MysqlBaseDao {

	private static final Logger log = Logger.getLogger(MysqlBaseDao.class);

	@Resource
	protected SqlMapClient mysqlClient;

	@Resource
	protected EhcacheUtil ehacheUtil;
	
	@Resource 
	protected MemcacheUtil memcacheUtil;

	public Object queryForObject(String namespace, Object arg) {
		try {
			return mysqlClient.queryForObject(namespace, arg);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	public Long insert(String namespace, Object arg) {
		try {
			Object lastId = mysqlClient.insert(namespace, arg);
			if (lastId != null) {
				return (Long) lastId;
			}
		} catch (Exception e) {
			log.error("insert failure~" + namespace + "~" + JSONObject.toJSONString(arg) + "~" + e);
		}
		return null;
	}

	public boolean delete(String namespace, Object arg) {
		try {
			int result = mysqlClient.delete(namespace, arg);
			return result == 1 ? true : false;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

	public boolean update(String namespace, Object arg) {
		try {
			int result = mysqlClient.update(namespace, arg);
			return result == 1 ? true : false;
		} catch (Exception e) {
			log.error("update failure~" + namespace + "~" + JSONObject.toJSONString(arg) + "~" + e);
			return false;
		}
	}

	public Object queryForList(String namespace, Object arg) {
		try {
			return mysqlClient.queryForList(namespace, arg);
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

}
