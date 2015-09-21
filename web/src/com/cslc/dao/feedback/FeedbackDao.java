package com.cslc.dao.feedback;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("feedbackDao")
public class FeedbackDao extends MysqlBaseDao {

	public Feedback selectById(Long id) {
		Object meta = queryForObject("Feedback.selectById", id);
		if(meta != null){
			return (Feedback) meta;
		}
		return null;
	}

	public Long insert(Feedback meta) {
		return insert("Feedback.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Feedback.delete", id);
	}

	public boolean update(Feedback meta) {
		return update("Feedback.update", meta);
	}

	public List<Feedback> select(Map<String, Object> map) {
		return (List<Feedback>) queryForList("Feedback.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Feedback.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Feedback.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

