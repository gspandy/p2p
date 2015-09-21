package com.cslc.dao.score;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("scoreDao")
public class ScoreDao extends MysqlBaseDao {

	public Score selectById(Long id) {
		Object meta = queryForObject("Score.selectById", id);
		if(meta != null){
			return (Score) meta;
		}
		return null;
	}

	public Long insert(Score meta) {
		return insert("Score.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Score.delete", id);
	}

	public boolean update(Score meta) {
		return update("Score.update", meta);
	}

	public List<Score> select(Map<String, Object> map) {
		return (List<Score>) queryForList("Score.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Score.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Score.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

