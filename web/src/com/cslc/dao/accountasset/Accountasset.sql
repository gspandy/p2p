cslc_accountasset
accountid:Long,selfitemasset:Double,selfitemamount:Double,selfitemcashbackamount:Double,selfitemtotalincome:Double,selfitemtodayincome:Double,selfitemrestincome:Double,bonusasset:Double,bonustotal:Double,scoreasset:Integer,scoretotal:Integer

<sqlMap resource="com/cslc/dao/accountasset/Accountasset.xml" />

CREATE TABLE `accountasset` (
  `selfitemcashbackamount` decimal(16,2) default NULL,
  `accountid` bigint(40) default NULL,
  `bonustotal` decimal(16,2) default NULL,
  `selfitemtotalincome` decimal(16,2) default NULL,
  `selfitemamount` decimal(16,2) default NULL,
  `selfitemrestincome` decimal(16,2) default NULL,
  `selfitemasset` decimal(16,2) default NULL,
  `scoreasset` int(10) default NULL,
  `selfitemtodayincome` decimal(16,2) default NULL,
  `bonusasset` decimal(16,2) default NULL,
  `scoretotal` int(10) default NULL
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;