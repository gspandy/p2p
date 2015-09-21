package com.task.settlesystem.settle;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("settletask")
public class SettleTask {
	
	@Resource
	private Sellout sellout;
	
	@Resource
	private ExceedEndtime exceedendtime;
	
	@Resource
	private Income income;
	
	@Resource
	private CashBack cashback;
	
	@Resource
	private BonusOverdue bonusoverdue;
	
	public void excute(){
		sellout.excute();
		exceedendtime.excute();
		income.excute();
		cashback.excute();
		bonusoverdue.excute();
	}	
}
