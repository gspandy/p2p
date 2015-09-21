package com.cslc.dao.submitcode;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("submitcodeDao")
public class SubmitcodeDao extends MysqlBaseDao {

	public Submitcode selectById(Long id) {
		Object meta = queryForObject("Submitcode.selectById", id);
		if(meta != null){
			return (Submitcode) meta;
		}
		return null;
	}

	public Long insert(Submitcode meta) {
		return insert("Submitcode.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Submitcode.delete", id);
	}

	public boolean update(Submitcode meta) {
		return update("Submitcode.update", meta);
	}

	public List<Submitcode> select(Map<String, Object> map) {
		return (List<Submitcode>) queryForList("Submitcode.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Submitcode.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Submitcode.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

