cslc_trade
id:Long,accountid:Long,selfitemid:Long,paychannel:Byte,mobile:String,ip:String,terminalid:String,phone:String,bankcardid:Long,platform:Byte,version:String,status:Byte,orderno:String,payorderno:String,amount:Double,fee:Double,createtime:Date,paysuccesstime:Date

<sqlMap resource="com/cslc/dao/trade/Trade.xml" />

CREATE TABLE `trade` (
  `amount` decimal(16,2) default NULL,
  `createtime` datetime default NULL,
  `orderno` varchar(20) default NULL,
  `ip` varchar(20) default NULL,
  `terminalid` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `bankcardid` bigint(40) default NULL,
  `fee` decimal(16,2) default NULL,
  `mobile` varchar(20) default NULL,
  `selfitemid` bigint(40) default NULL,
  `version` varchar(20) default NULL,
  `payorderno` varchar(20) default NULL,
  `platform` tinyint(2) default NULL,
  `accountid` bigint(40) default NULL,
  `paychannel` tinyint(2) default NULL,
  `paysuccesstime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;