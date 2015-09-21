package com.cslc.dao.task;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("taskDao")
public class TaskDao extends MysqlBaseDao {

	public Task selectById(Long id) {
		Object meta = queryForObject("Task.selectById", id);
		if(meta != null){
			return (Task) meta;
		}
		return null;
	}

	public Long insert(Task meta) {
		return insert("Task.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Task.delete", id);
	}

	public boolean update(Task meta) {
		return update("Task.update", meta);
	}

	public List<Task> select(Map<String, Object> map) {
		return (List<Task>) queryForList("Task.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Task.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Task.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

