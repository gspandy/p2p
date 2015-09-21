package com.cslc.dao.bonus;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("bonusDao")
public class BonusDao extends MysqlBaseDao {

	public Bonus selectById(Long id) {
		Object meta = queryForObject("Bonus.selectById", id);
		if(meta != null){
			return (Bonus) meta;
		}
		return null;
	}

	public Long insert(Bonus meta) {
		return insert("Bonus.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Bonus.delete", id);
	}

	public boolean update(Bonus meta) {
		return update("Bonus.update", meta);
	}

	public List<Bonus> select(Map<String, Object> map) {
		return (List<Bonus>) queryForList("Bonus.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Bonus.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Bonus.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

