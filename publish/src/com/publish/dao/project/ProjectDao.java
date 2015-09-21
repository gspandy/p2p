package com.publish.dao.project;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("projectDao")
public class ProjectDao extends MysqlBaseDao {

	public Project selectById(Long id) {
		Object meta = queryForObject("Project.selectById", id);
		if(meta != null){
			return (Project) meta;
		}
		return null;
	}

	public Long insert(Project meta) {
		return insert("Project.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Project.delete", id);
	}

	public boolean update(Project meta) {
		return update("Project.update", meta);
	}

	public List<Project> select(Map<String, Object> map) {
		return (List<Project>) queryForList("Project.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Project.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		return (Double) queryForObject("Project.selectSum", map);
	}

}

