package com.cslc.dao.systemdef;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("systemdefDao")
public class SystemdefDao extends MysqlBaseDao {

	public Systemdef selectById(String id) {
		Object meta = queryForObject("Systemdef.selectById", id);
		if(meta != null){
			return (Systemdef) meta;
		}
		return null;
	}

	public void insert(Systemdef meta) {
		insert("Systemdef.insert", meta);
	}

	public boolean delete(String id) {
		return delete("Systemdef.delete", id);
	}

	public boolean update(Systemdef meta) {
		return update("Systemdef.update", meta);
	}

	public List<Systemdef> select(Map<String, Object> map) {
		return (List<Systemdef>) queryForList("Systemdef.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Systemdef.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Systemdef.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

