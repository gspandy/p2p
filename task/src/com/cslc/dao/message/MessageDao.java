package com.cslc.dao.message;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("messageDao")
public class MessageDao extends MysqlBaseDao {

	public Message selectById(Long id) {
		Object meta = queryForObject("Message.selectById", id);
		if(meta != null){
			return (Message) meta;
		}
		return null;
	}

	public Long insert(Message meta) {
		return insert("Message.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Message.delete", id);
	}

	public boolean update(Message meta) {
		return update("Message.update", meta);
	}

	public List<Message> select(Map<String, Object> map) {
		return (List<Message>) queryForList("Message.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Message.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Message.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

