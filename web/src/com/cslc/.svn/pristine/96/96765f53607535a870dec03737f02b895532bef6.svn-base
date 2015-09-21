package com.cslc.dao.picture;

import java.util.List;
import java.util.Map;
import com.platform.base.MysqlBaseDao;
import org.springframework.stereotype.Service;

@Service("pictureDao")
public class PictureDao extends MysqlBaseDao {

	public Picture selectById(Long id) {
		Object meta = queryForObject("Picture.selectById", id);
		if(meta != null){
			return (Picture) meta;
		}
		return null;
	}

	public Long insert(Picture meta) {
		return insert("Picture.insert", meta);
	}

	public boolean delete(Long id) {
		return delete("Picture.delete", id);
	}

	public boolean update(Picture meta) {
		return update("Picture.update", meta);
	}

	public List<Picture> select(Map<String, Object> map) {
		return (List<Picture>) queryForList("Picture.select", map);
	}

	public long selectCount(Map<String, Object> map) {
		return (Long) queryForObject("Picture.selectCount", map);
	}

	public double selectSum(Map<String, Object> map) {
		Object result = queryForObject("Picture.selectSum", map);
		if(result != null){
			return (Double) result;
		}
		return 0;
	}

}

