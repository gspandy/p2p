package com.cslc.dao.accountbankcard;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("accountbankcardDao")
public class AccountbankcardDao extends MysqlBaseDao {

	public Accountbankcard selectById(Long id) {
		Object meta = queryForObject("Accountbankcard.selectById", id);
		if(meta != null){
			return (Accountbankcard) meta;
		}
		return null;
	}

	public Long insert(Accountbankcard meta) {
		return insert("Accountbankcard.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Accountbankcard.delete", id);
	}

	public boolean update(Accountbankcard meta) {
		return update("Accountbankcard.update", meta);
	}

	public List<Accountbankcard> select(Map<String, Object> map) {
		return (List<Accountbankcard>) queryForList("Accountbankcard.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Accountbankcard.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Accountbankcard.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

