package com.cslc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountasset.Accountasset;
import com.cslc.dao.accountasset.AccountassetDao;
import com.cslc.dao.bonus.Bonus;
import com.cslc.dao.bonus.BonusDao;
import com.cslc.dao.reward.Reward;
import com.cslc.dao.score.Score;
import com.cslc.dao.score.ScoreDao;
import com.cslc.dao.selfitem.Selfitem;
import com.platform.util.StringUtil;

@Service("rewardService")
public class AwardService {
	
	protected static Logger log = Logger.getLogger(AwardService.class);
	
    @Resource
    private AccountDao               accountDao;

    @Resource
	private BonusDao bonusDao;

    @Resource
	private AccountassetDao accountassetDao;
    
    @Resource
    private ScoreDao scoreDao;
    
    public void giveBonus(Accountasset accountasset, String title, double bonus, int rate, int days, int category) {
    	Bonus b = new Bonus();
		b.setAccountid(accountasset.getAccountid());
		b.setTitle(title);
		b.setTotalbonus(bonus);
		b.setCategory(category);
		b.setCreatetime(new Date());
		b.setStarttime(new Date());
		b.setEndtime(StringUtil.getNextDate(new Date(), days));
		b.setRate(rate);
		b.setStatus(Bonus.STATUS_UNUSED);
		bonusDao.insert(b);
		
		Accountasset a = new Accountasset();
		a.setAccountid(accountasset.getAccountid());
		a.setBonusasset(accountasset.getBonusasset() + bonus);
		a.setBonustotal(accountasset.getBonustotal() + bonus);
		accountassetDao.update(a);
    }
    
	public void giveScore(Accountasset accountasset, String title, int score, int category){
		Score s = new Score();
		s.setAccountid(accountasset.getAccountid());
		s.setScore(score);
		s.setTitle(title);
		s.setCategory(category);
		s.setCreatetime(new Date());
		s.setInouttype(Score.INOUTTYPE_IN);
		s.setStatus(Score.STATUS_ENABLE);
		scoreDao.insert(s);
		
		Accountasset a = new Accountasset();
		a.setAccountid(accountasset.getAccountid());
		a.setScoreasset(accountasset.getScoreasset() + score);
		a.setScoretotal(accountasset.getScoretotal() + score);
		accountassetDao.update(a);
	}

}
