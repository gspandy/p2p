package com.task.settlesystem.settle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.platform.util.PageUtil;
import com.platform.util.StringUtil;

@Service("income")
public class Income {
	
	@Resource
	private AccountselfitemDao accountselfitemDao;
	
	@Resource
	private AccountassetDao accountassetDao;
	
	@Resource
	private SelfitemDao selfitemDao;
	
	public boolean excute(){
		Map<String, Object> accountassetMap1 = new HashMap<String, Object>();
		//设置条件定期待收收益大于0
		accountassetMap1.put("selfitemrestincomeGt", 0D );				
		//统计数量
		long accountCount = accountassetDao.selectCount(accountassetMap1);
		int page = 1;
		while(true){
			//分页，每1000条记录为1页
			PageUtil.getMap(accountassetMap1, String.valueOf(page ++), accountCount, "1000");
			List<Accountasset> assetList = accountassetDao.select(accountassetMap1);
			//遍历
			for(Accountasset asset : assetList){				
				Map<String, Object> accountselfitemMap1 = new HashMap<String, Object>();
				//根据账户id查找
				accountselfitemMap1.put("accountid", asset.getAccountid());
				//设置条件now()=backtime-1,查找最后一天计息的记录
				accountselfitemMap1.put("backtime", StringUtil.getNextDate(new Date(),1) );
				//选择字段
				accountselfitemMap1.put("field", "restincome");
				//计算回款前最后一天的产品的今日收益和
				double dayincomeSum1 = accountselfitemDao.selectSum(accountselfitemMap1);
				
				
				Map<String, Object> accountselfitemMap2 = new HashMap<String, Object>();
				//根据账户id查找
				accountselfitemMap2.put("accountid", asset.getAccountid());
				//设置条件backtime>now()+1，小于回款时间-1
				accountselfitemMap2.put("backtimeGt", StringUtil.getNextDate(new Date(),1) );
				//设置条件incometime<=now()，大于等于起息时间
				accountselfitemMap2.put("incometimeLEt", new Date() );
				//选择字段
				accountselfitemMap2.put("field", "dayincome");
				//计算正常计息的的产品今日收益和
				double dayincomeSum2 = accountselfitemDao.selectSum(accountselfitemMap2);
				//查询符合条件的记录
				List<Accountselfitem> accountselfitemList2 = accountselfitemDao.select(accountselfitemMap2);
				
				for(Accountselfitem accountselfitem2 : accountselfitemList2){	
					Accountselfitem a = new Accountselfitem();
					//根据交易id更改
					a.setTradeid(accountselfitem2.getTradeid());
					//待收收益设为待收收益-今日收益
					a.setRestincome(a.getRestincome()-a.getDayincome());					
					//更新其它字段
					accountselfitemDao.update(a);					
				}	
				
				Accountasset as = new Accountasset();
				as.setAccountid(asset.getAccountid());
				as.setSelfitemtodayincome(dayincomeSum1+dayincomeSum2);
				as.setSelfitemtotalincome(as.getSelfitemtotalincome()+as.getSelfitemtodayincome());
				as.setSelfitemrestincome(as.getSelfitemrestincome()-as.getSelfitemtodayincome());
				accountassetDao.update(as);
			}	
			if(assetList.isEmpty()){
				break;
		    }
		}		
		Map<String, Object> accountselfitemMap3 = new HashMap<String, Object>();
		//设置条件now()=backtime-1,查找最后一天计息的记录
		accountselfitemMap3.put("backtime", StringUtil.getNextDate(new Date(),1));
		List<Accountselfitem> accountselfitemtList = accountselfitemDao.select(accountselfitemMap3);
		//遍历
		for(Accountselfitem s : accountselfitemtList){
			Accountselfitem a= new Accountselfitem();
			//根据交易id进行操作
		    a.setTradeid(s.getTradeid());
		    //最后一天每日收益等于待收收益
		    a.setDayincome(s.getRestincome());
			//设置待收收益为0
			a.setRestincome(0.0);
			//更新其它字段
			accountselfitemDao.update(a);		
		}		
		return true;
	}	
}
