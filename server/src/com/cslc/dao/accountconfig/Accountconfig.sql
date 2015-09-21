cslc_accountconfig
accountid:Long,platform:Byte,terminalid:String,version:String,rom:String,phone:String

<sqlMap resource="com/cslc/dao/accountconfig/Accountconfig.xml" />

CREATE TABLE `accountconfig` (
  `accountid` bigint(40) default NULL,
  `rom` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `terminalid` varchar(20) default NULL,
  `version` varchar(20) default NULL,
  `department` varchar(20) default NULL,
  `platform` varchar(20) default NULL
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;