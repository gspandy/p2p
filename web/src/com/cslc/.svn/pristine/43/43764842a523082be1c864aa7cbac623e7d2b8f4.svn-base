package com.cslc.dao.selfitemproject;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("selfitemprojectDao")
public class SelfitemprojectDao extends MysqlBaseDao {

	public Selfitemproject selectById(Long id) {
		Object meta = queryForObject("Selfitemproject.selectById", id);
		if(meta != null){
			return (Selfitemproject) meta;
		}
		return null;
	}

	public Long insert(Selfitemproject meta) {
		return insert("Selfitemproject.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Selfitemproject.delete", id);
	}

	public boolean update(Selfitemproject meta) {
		return update("Selfitemproject.update", meta);
	}

	public List<Selfitemproject> select(Map<String, Object> map) {
		return (List<Selfitemproject>) queryForList("Selfitemproject.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Selfitemproject.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Selfitemproject.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

