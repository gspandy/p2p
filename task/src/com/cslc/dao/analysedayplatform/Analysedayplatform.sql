cslc_analysedayplatform
createtime:Date,selfitempayamount:Double,selfitempaysuccessamount:Double,cashbackamount:Double,payfee:Double,cashbackfee:Double,selfitemtodayincome:Double,selfitemtotalamount:Double,sendbonus:Double,usebonus:Double,sendscore:Integer,usescore:Integer,smscount:Integer,feedbackcount:Integer,registcount:Integer,bindcardcount:Integer,paycount:Integer,paysuccesscount:Integer,finishselfitemcount:Integer,finishtaskcount:Integer,messagecount:Integer,joinactivitycount:Integer

<sqlMap resource="com/cslc/dao/analysedayplatform/Analysedayplatform.xml" />

CREATE TABLE `analysedayplatform` (
  `selfitempaysuccessamount` decimal(16,2) default NULL,
  `finishselfitemcount` int(10) default NULL,
  `createtime` datetime default NULL,
  `registcount` int(10) default NULL,
  `joinactivitycount` int(10) default NULL,
  `sendscore` int(10) default NULL,
  `selfitemincome` decimal(16,2) default NULL,
  `smscount` int(10) default NULL,
  `paycount` int(10) default NULL,
  `messagecount` int(10) default NULL,
  `cashbackamount` decimal(16,2) default NULL,
  `cashbackfee` decimal(16,2) default NULL,
  `sendbonus` decimal(16,2) default NULL,
  `finishtaskcount` int(10) default NULL,
  `feedbackcount` int(10) default NULL,
  `payfee` decimal(16,2) default NULL,
  `usebonus` decimal(16,2) default NULL,
  `selfitempayamount` decimal(16,2) default NULL,
  `selfitemtotalamount` decimal(16,2) default NULL,
  `bindcardcount` int(10) default NULL,
  `usescore` int(10) default NULL,
  `paysuccesscount` int(10) default NULL
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;