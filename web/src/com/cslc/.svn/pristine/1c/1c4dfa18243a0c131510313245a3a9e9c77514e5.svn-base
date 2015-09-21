package com.cslc.dao.question;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("questionDao")
public class QuestionDao extends MysqlBaseDao {

	public Question selectById(Long id) {
		Object meta = queryForObject("Question.selectById", id);
		if(meta != null){
			return (Question) meta;
		}
		return null;
	}

	public Long insert(Question meta) {
		return insert("Question.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Question.delete", id);
	}

	public boolean update(Question meta) {
		return update("Question.update", meta);
	}

	public List<Question> select(Map<String, Object> map) {
		return (List<Question>) queryForList("Question.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Question.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Question.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

