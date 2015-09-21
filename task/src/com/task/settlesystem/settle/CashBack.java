package com.task.settlesystem.settle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.cashback.Cashback;
import com.cslc.dao.cashback.CashbackDao;

@Service("cashback")
public class CashBack {
	
	@Resource
	private AccountselfitemDao accountselfitemDao;
	
	@Resource
	private CashbackDao cashbackDao;
	
	public boolean excute(){
		Map<String, Object> accountselfitemMap = new HashMap<String, Object>();
		//设置条件backtime=now()
		accountselfitemMap.put("backtime", new Date() );		
		//查询符合条件的记录
		List<Accountselfitem> accountselflitemList = accountselfitemDao.select(accountselfitemMap);
		//遍历
		for(Accountselfitem s : accountselflitemList){
			//计算对应银行卡的所有回款产品id
			Map<String, Object> accountselfitemMap3 = new HashMap<String, Object>();
			accountselfitemMap3.put("bankcardid", s.getBankcardid());
			accountselfitemMap3.put("backtime", new Date() );
			List<Accountselfitem> accountselflitemList3 = accountselfitemDao.select(accountselfitemMap3);
			String selfitemids = "";
			//遍历获得对应银行卡的所有回款产品id
			for(Accountselfitem a : accountselflitemList3){
				selfitemids += "," + a.getSelfitemid();
			}
			selfitemids = selfitemids.substring(1);
			//计算回款金额
			Map<String, Object> accountselfitemMap2 = new HashMap<String, Object>();
			accountselfitemMap2.put("bankcardid", s.getBankcardid());
			accountselfitemMap2.put("backtime", new Date() );
			accountselfitemMap2.put("field", "amount");
			//计算回款的本金和
			double amountSum = accountselfitemDao.selectSum(accountselfitemMap2);
			accountselfitemMap2.put("field", "totalincome");
			//计算回款的利息和
			double incomeSum = accountselfitemDao.selectSum(accountselfitemMap2);
			accountselfitemMap2.put("field", "bonus");
			//计算回款的红包和
			double bonusSum = accountselfitemDao.selectSum(accountselfitemMap2);
			
			//在cashback表中插入回款数据
			Cashback a = new Cashback();
			a.setAccountid(s.getAccountid());
			a.setAmount(amountSum+incomeSum+bonusSum);
			a.setCreatetime(new Date());
			a.setBankcardid(s.getBankcardid());
			a.setBonus(bonusSum);
			a.setSelfitemid(selfitemids);
			a.setStatus(Cashback.STATUS_NOT_SUBMIT);
			cashbackDao.update(a);
		}		
		return true;
	}	
}
