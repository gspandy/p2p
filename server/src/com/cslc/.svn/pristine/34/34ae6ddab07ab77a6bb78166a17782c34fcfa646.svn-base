package com.cslc.dao.cashback;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("cashbackDao")
public class CashbackDao extends MysqlBaseDao {

	public Cashback selectById(Long id) {
		Object meta = queryForObject("Cashback.selectById", id);
		if(meta != null){
			return (Cashback) meta;
		}
		return null;
	}

	public Long insert(Cashback meta) {
		return insert("Cashback.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Cashback.delete", id);
	}

	public boolean update(Cashback meta) {
		return update("Cashback.update", meta);
	}

	public List<Cashback> select(Map<String, Object> map) {
		return (List<Cashback>) queryForList("Cashback.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Cashback.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Cashback.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

