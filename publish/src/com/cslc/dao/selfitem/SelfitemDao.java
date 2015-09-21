package com.cslc.dao.selfitem;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("selfitemDao")
public class SelfitemDao extends MysqlBaseDao {

	public Selfitem selectById(Long id) {
		Object meta = queryForObject("Selfitem.selectById", id);
		if(meta != null){
			return (Selfitem) meta;
		}
		return null;
	}

	public Long insert(Selfitem meta) {
		return insert("Selfitem.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Selfitem.delete", id);
	}

	public boolean update(Selfitem meta) {
		return update("Selfitem.update", meta);
	}

	public List<Selfitem> select(Map<String, Object> map) {
		return (List<Selfitem>) queryForList("Selfitem.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Selfitem.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Selfitem.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

