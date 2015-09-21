package com.cslc.dao.shorturl;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("shorturlDao")
public class ShorturlDao extends MysqlBaseDao {

	public Shorturl selectById(Long id) {
		Object meta = queryForObject("Shorturl.selectById", id);
		if(meta != null){
			return (Shorturl) meta;
		}
		return null;
	}

	public Long insert(Shorturl meta) {
		return insert("Shorturl.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Shorturl.delete", id);
	}

	public boolean update(Shorturl meta) {
		return update("Shorturl.update", meta);
	}

	public List<Shorturl> select(Map<String, Object> map) {
		return (List<Shorturl>) queryForList("Shorturl.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Shorturl.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Shorturl.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

