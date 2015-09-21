cslc_accountselfitem
accountid:Long,bankcardid:Long,selfitemid:Long,status:Byte,dayincome:Double,totalincome:Double,restincome:Double,amount:Double,bonus:Double,score:Integer,createtime:Date,backtime:Date,incometime:Date,bonusid:String

<sqlMap resource="com/cslc/dao/accountselfitem/Accountselfitem.xml" />

CREATE TABLE `accountselfitem` (
  `accountid` bigint(40) default NULL,
  `bankcardid` bigint(40) default NULL,
  `score` int(10) default NULL,
  `amount` decimal(16,2) default NULL,
  `createtime` datetime default NULL,
  `incometime` datetime default NULL,
  `bonus` decimal(16,2) default NULL,
  `totalincome` decimal(16,2) default NULL,
  `restincome` decimal(16,2) default NULL,
  `dayincome` decimal(16,2) default NULL,
  `selfitemid` bigint(40) default NULL,
  `backtime` datetime default NULL,
  `status` tinyint(2) default NULL,
  `tradeid` bigint(40) default NULL,
   `bonusid` varchar(200) default NULL
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;