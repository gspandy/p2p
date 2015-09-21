package com.task.settlesystem.settle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;

@Service("sellout")
public class Sellout {

	@Resource
	private SelfitemDao selfitemDao;
	
	@Resource
	private AccountselfitemDao accountselfitemDao;
	
	public boolean excute(){
		Map<String, Object> selfitemMap = new HashMap<String, Object>();
		//设置查询条件状态为募集完成
		selfitemMap.put("status", Selfitem.STATUS_SELLOUT );		
		//查询符合条件的记录
		List<Selfitem> selflitemList = selfitemDao.select(selfitemMap);
		//遍历
		for(Selfitem s : selflitemList){
			Selfitem a= new Selfitem();
			//根据id进行操作
			a.setId(s.getId());
			//设置状态=计息中
			a.setStatus(Selfitem.STATUS_INCOME);			
			//更新其它字段
			selfitemDao.update(a);		
			
			Accountselfitem as = new Accountselfitem();
			//根据产品id进行操作
			as.setSelfitemid(s.getId());
			//设置状态=计息中
			as.setStatus(Selfitem.STATUS_INCOME);
			//更新其它字段
			accountselfitemDao.update(as);
		}		
		return true;
	}
	
}
