package com.cslc.dao.accountselfitem;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("accountselfitemDao")
public class AccountselfitemDao extends MysqlBaseDao {

	public Long insert(Accountselfitem meta) {
		return insert("Accountselfitem.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Accountselfitem.delete", id);
	}

	public boolean update(Accountselfitem meta) {
		return update("Accountselfitem.update", meta);
	}

	public List<Accountselfitem> select(Map<String, Object> map) {
		return (List<Accountselfitem>) queryForList("Accountselfitem.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Accountselfitem.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Accountselfitem.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

