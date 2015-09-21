package com.publish.dao.projectbug;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("projectbugDao")
public class ProjectbugDao extends MysqlBaseDao {

	public Projectbug selectById(Long id) {
		Object meta = queryForObject("Projectbug.selectById", id);
		if(meta != null){
			return (Projectbug) meta;
		}
		return null;
	}

	public Long insert(Projectbug meta) {
		return insert("Projectbug.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Projectbug.delete", id);
	}

	public boolean update(Projectbug meta) {
		return update("Projectbug.update", meta);
	}

	public List<Projectbug> select(Map<String, Object> map) {
		return (List<Projectbug>) queryForList("Projectbug.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Projectbug.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		return (Double) queryForObject("Projectbug.selectSum", map);
	}

}

