package com.cslc.dao.trade;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("tradeDao")
public class TradeDao extends MysqlBaseDao {

	public Trade selectById(Long id) {
		Object meta = queryForObject("Trade.selectById", id);
		if(meta != null){
			return (Trade) meta;
		}
		return null;
	}

	public Long insert(Trade meta) {
		return insert("Trade.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Trade.delete", id);
	}

	public boolean update(Trade meta) {
		return update("Trade.update", meta);
	}

	public List<Trade> select(Map<String, Object> map) {
		return (List<Trade>) queryForList("Trade.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Trade.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Trade.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

