package com.cslc.dao.sms;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("smsDao")
public class SmsDao extends MysqlBaseDao {

	public Sms selectById(Long id) {
		Object meta = queryForObject("Sms.selectById", id);
		if(meta != null){
			return (Sms) meta;
		}
		return null;
	}

	public Long insert(Sms meta) {
		return insert("Sms.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Sms.delete", id);
	}

	public boolean update(Sms meta) {
		return update("Sms.update", meta);
	}

	public List<Sms> select(Map<String, Object> map) {
		return (List<Sms>) queryForList("Sms.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Sms.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Sms.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

