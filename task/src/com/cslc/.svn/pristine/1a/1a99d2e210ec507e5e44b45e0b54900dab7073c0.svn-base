package com.cslc.dao.tasklog;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("tasklogDao")
public class TasklogDao extends MysqlBaseDao {

	public Tasklog selectById(Long id) {
		Object meta = queryForObject("Tasklog.selectById", id);
		if(meta != null){
			return (Tasklog) meta;
		}
		return null;
	}

	public Long insert(Tasklog meta) {
		return insert("Tasklog.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Tasklog.delete", id);
	}

	public boolean update(Tasklog meta) {
		return update("Tasklog.update", meta);
	}

	public List<Tasklog> select(Map<String, Object> map) {
		return (List<Tasklog>) queryForList("Tasklog.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Tasklog.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Tasklog.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

