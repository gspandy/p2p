package com.task.settlesystem.settle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;


@Service("bonusoverdue")
public class BonusOverdue {

	@Resource
	private BonusDao bonusDao;	
	
	public boolean excute(){
		Map<String, Object> bonusMap = new HashMap<String, Object>();
		//设置查询条件为now()>endtime
		bonusMap.put("endtimeLt", new Date() );		
		//设置条件状态为未使用
		bonusMap.put("status", Bonus.STATUS_UNUSED);
		//查询符合条件的记录
		List<Bonus> bonusList = bonusDao.select(bonusMap);
		//遍历
		for(Bonus s : bonusList){
			Bonus a= new Bonus();
			//根据id进行操作
			a.setId(s.getId());
			//设置相应红包状态为已过期
			a.setStatus(Bonus.STATUS_EXPIRED);			
			//更新其它字段
			bonusDao.update(a);			
		}		
		return true;
	}
	
}
