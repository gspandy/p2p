package com.cslc.dao.accountconfig;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("accountconfigDao")
public class AccountconfigDao extends MysqlBaseDao {

	public Accountconfig selectById(Long id) {
		Object meta = queryForObject("Accountconfig.selectById", id);
		if(meta != null){
			return (Accountconfig) meta;
		}
		return null;
	}

	public Long insert(Accountconfig meta) {
		return insert("Accountconfig.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Accountconfig.delete", id);
	}

	public boolean update(Accountconfig meta) {
		return update("Accountconfig.update", meta);
	}

	public List<Accountconfig> select(Map<String, Object> map) {
		return (List<Accountconfig>) queryForList("Accountconfig.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Accountconfig.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Accountconfig.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

