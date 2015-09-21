package com.task.settlesystem.settle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.accountselfitem.Accountselfitem;
import com.cslc.dao.accountselfitem.AccountselfitemDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.platform.util.StringUtil;

@Service("exceedendtime")
public class ExceedEndtime {

	@Resource
	private SelfitemDao selfitemDao;
	
	@Resource
	private AccountselfitemDao accountselfitemDao;
	//超过募集期限
	public boolean excute(){
		Map<String, Object> selfitemMap = new HashMap<String, Object>();
		//设置条件endtime<=now() and reetamount>0
		selfitemMap.put("endtimeLEt", new Date() );		
		selfitemMap.put("restamountGt", 0D);
		//查询符合条件的记录
		List<Selfitem> selflitemList = selfitemDao.select(selfitemMap);
		//遍历
		for(Selfitem s : selflitemList){
			Selfitem a= new Selfitem();
			//根据id进行操作
			a.setId(s.getId());
			//设置状态=募集完成
			a.setStatus(Selfitem.STATUS_SELLOUT);
			//设置募集完成时间=募集截止时间
			a.setFinishtime(a.getEndtime());
			//设置起息时间=募集截止时间
			a.setIncometime(a.getEndtime());			
			//设置回款时间等于起息时间+计息天数
			a.setBacktime(StringUtil.getNextDate(a.getIncometime(),a.getincomeDays()));
			//设置募集总额=原募集总额-剩余金额
			a.setTotalamount(a.getTotalamount()-a.getRestamount());
			//设置剩余金额为0
			a.setRestamount(0.0);
			//设置产品总收益为现募集总额*（年化收益率+赠送年化收益率）/365天*计息天数
			a.setTotalincome(a.getTotalamount()*(a.getAnnualrate()+a.getAnnualrateextra())/365*a.getincomeDays());
			//更新其它字段
			selfitemDao.update(a);
			
			Accountselfitem as = new Accountselfitem();
			//根据超过募集期的产品id进行操作
			as.setSelfitemid(s.getId());
			//设置状态=募集完成
			as.setStatus(Selfitem.STATUS_SELLOUT);
			//设置起息时间产品表中的起息时间
			as.setIncometime(s.getIncometime());
			//设置回款时间等于产品表中的回款时间
			as.setBacktime(s.getBacktime());
			//更新其它字段
			accountselfitemDao.update(as);
		}
		return true;
	}	
}
