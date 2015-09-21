package com.cslc.dao.systemlog;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("systemlogDao")
public class SystemlogDao extends MysqlBaseDao {

	public Systemlog selectById(Long id) {
		Object meta = queryForObject("Systemlog.selectById", id);
		if(meta != null){
			return (Systemlog) meta;
		}
		return null;
	}

	public Long insert(Systemlog meta) {
		return insert("Systemlog.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Systemlog.delete", id);
	}

	public boolean update(Systemlog meta) {
		return update("Systemlog.update", meta);
	}

	public List<Systemlog> select(Map<String, Object> map) {
		return (List<Systemlog>) queryForList("Systemlog.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Systemlog.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Systemlog.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

