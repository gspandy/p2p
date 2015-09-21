package com.cslc.dao.reward;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("rewardDao")
public class RewardDao extends MysqlBaseDao {

	public Reward selectById(Long id) {
		Object meta = queryForObject("Reward.selectById", id);
		if(meta != null){
			return (Reward) meta;
		}
		return null;
	}

	public Long insert(Reward meta) {
		return insert("Reward.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Reward.delete", id);
	}

	public boolean update(Reward meta) {
		return update("Reward.update", meta);
	}

	public List<Reward> select(Map<String, Object> map) {
		return (List<Reward>) queryForList("Reward.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Reward.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Reward.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

