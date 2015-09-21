package com.cslc.dao.account;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("accountDao")
public class AccountDao extends MysqlBaseDao {

	public Account selectById(Long id) {
		Object meta = queryForObject("Account.selectById", id);
		if(meta != null){
			return (Account) meta;
		}
		return null;
	}

	public Long insert(Account meta) {
		return insert("Account.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Account.delete", id);
	}

	public boolean update(Account meta) {
		return update("Account.update", meta);
	}

	public List<Account> select(Map<String, Object> map) {
		return (List<Account>) queryForList("Account.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Account.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Account.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

