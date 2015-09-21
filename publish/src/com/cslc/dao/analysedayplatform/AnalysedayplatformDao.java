package com.cslc.dao.analysedayplatform;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("AnalysedayplatformDao")
public class AnalysedayplatformDao extends MysqlBaseDao {

	public Long insert(Analysedayplatform meta) {
		return insert("Analysedayplatform.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Analysedayplatform.delete", id);
	}

	public boolean update(Analysedayplatform meta) {
		return update("Analysedayplatform.update", meta);
	}

	public List<Analysedayplatform> select(Map<String, Object> map) {
		return (List<Analysedayplatform>) queryForList("Analysedayplatform.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Analysedayplatform.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Analysedayplatform.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

