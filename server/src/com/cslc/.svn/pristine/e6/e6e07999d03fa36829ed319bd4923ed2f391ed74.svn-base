package com.cslc.dao.supportbank;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("supportbankDao")
public class SupportbankDao extends MysqlBaseDao {

	public Supportbank selectById(Long id) {
		Object meta = queryForObject("Supportbank.selectById", id);
		if(meta != null){
			return (Supportbank) meta;
		}
		return null;
	}

	public Long insert(Supportbank meta) {
		return insert("Supportbank.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Supportbank.delete", id);
	}

	public boolean update(Supportbank meta) {
		return update("Supportbank.update", meta);
	}

	public List<Supportbank> select(Map<String, Object> map) {
		return (List<Supportbank>) queryForList("Supportbank.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Supportbank.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Supportbank.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

