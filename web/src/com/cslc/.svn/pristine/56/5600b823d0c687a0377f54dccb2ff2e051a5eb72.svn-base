package com.cslc.dao.selfitemagreement;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("selfitemagreementDao")
public class SelfitemagreementDao extends MysqlBaseDao {

	public Selfitemagreement selectById(Long id) {
		Object meta = queryForObject("Selfitemagreement.selectById", id);
		if(meta != null){
			return (Selfitemagreement) meta;
		}
		return null;
	}

	public Long insert(Selfitemagreement meta) {
		return insert("Selfitemagreement.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Selfitemagreement.delete", id);
	}

	public boolean update(Selfitemagreement meta) {
		return update("Selfitemagreement.update", meta);
	}

	public List<Selfitemagreement> select(Map<String, Object> map) {
		return (List<Selfitemagreement>) queryForList("Selfitemagreement.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		Object count = queryForObject("Selfitemagreement.selectCount", map);
		return count != null ? (Long) count : 0;
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Selfitemagreement.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

