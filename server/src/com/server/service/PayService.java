/**
 * Project Name:ddsc_server File Name:PayService.java Package Name:com.server.service Date:2015年7月24日下午3:27:32 Copyright
 * (c) 2015, kitdnie@gmail.com All Rights Reserved.
 */

package com.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.accountbankcard.AccountbankcardDao;
import com.cslc.dao.selfitem.Selfitem;
import com.cslc.dao.selfitem.SelfitemDao;
import com.cslc.dao.supportbank.SupportbankDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.dao.trade.Trade;
import com.cslc.dao.trade.TradeDao;
import com.cslc.service.BizService;
import com.platform.util.StringUtil;


@Service("payService")
public class PayService {
	protected static Logger paysuccessLog = Logger.getLogger("paysuccessLog");
	protected static Logger concurrentLog = Logger.getLogger("concurrentLog");

    @Resource
    private AccountDao         accountDao;

    @Resource
    private TradeDao    tradeDao;

    @Resource
    private AccountbankcardDao accountbankcardDao;

    @Resource
    private SystemdefDao       systemdefDao;



    @Resource
    private SelfitemDao        selfitemDao;

    @Resource
    private SupportbankDao      supportbankDao;


    @Resource
    private BizService         bizService;
    
    


    /**
     * 新建订单实例
     */
    public Trade getNewTrade(String amount, long selfitemid, long accountid, long bankcardid,String mobile) {
    	Trade trade = new Trade();
    	if (StringUtil.isNotBlank(amount)) {
             trade.setAmount(Double.parseDouble(amount));
    	}
    	trade.setAccountid(accountid);

        trade.setSelfitemid(selfitemid);
        trade.setBankcardid(bankcardid);
        trade.setMobile(mobile);

        return trade;
    }

    /**
     * 获得accounttrade信息
     * @param tradeId
     * @return
     * @since JDK 1.6
     */
    public Trade getTradeById(Long tradeId) {
        return tradeDao.selectById(tradeId);
    }

    /**
     * 获得account信息
     * 
     * @param accountId
     */
    public Account getAccount(Long accountId) {
        return accountDao.selectById(accountId);
    }

    /**
     * 获得产品信息
     * 
     * @param selfitemId
     * @return
     */
    public Selfitem getSelfitemById(Long selfitemId) {
        return selfitemDao.selectById(selfitemId);
    }

    /**
     * 判断该身份证是否已使用
     * 
     * @param idcardno
     * @param accountId
     * @return
     * @since JDK 1.6
     */
    public boolean isUsedIdCard(String idcardno, Long accountId) {
        Map<String, Object> tmap = new HashMap<String, Object>();
        tmap.put("idcardno", idcardno);
        List<Account> account_list = accountDao.select(tmap);
        if (!(account_list.size() == 0 || (account_list.size() == 1 && account_list.get(0).getId().equals(accountId)))) {
            return true;
        }
        return false;
    }


  
    public double getFee(double amount){
    	double rate = Double.parseDouble(systemdefDao.selectById(Systemdef.FEE_LIANLIAN_RATE).getV());
    	double fee = amount * rate;
    	return fee;
    }



   
  

    /**
     * 生成交易信息
     * 
     * @param trade
     * @return
     */
    public Long createTrade(Trade trade) {
        return tradeDao.insert(trade);
    }
    
    
    
    
 // 冻结产品金额
    public String freezeSelfitemAmount(Trade trade) {
            // 产品表更新
            Selfitem item = selfitemDao.selectById(trade.getSelfitemid());
            double amount = trade.getAmount();
            if (amount >= 0) {
                Selfitem si = new Selfitem();
                si.setId(trade.getSelfitemid());
                si.setFreezeamount(item.getFreezeamount()+amount);
                selfitemDao.update(si);
                concurrentLog.info("selfitemid:" + trade.getSelfitemid() + ",tradeid:" + trade.getId() + ",freezeamount:"
                                   + si.getFreezeamount());
                return "success";
            }
        return "failure";
    }
    
    
    

    
}

 