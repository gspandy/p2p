cslc_submitcode
id:Long,category:Integer,code:String,accountid:Long,mobile:String,ip:String,terminalid:String,status:Byte,createtime:Date

<sqlMap resource="com/cslc/dao/submitcode/Submitcode.xml" />

CREATE TABLE `submitcode` (
  `accountid` bigint(40) default NULL,
  `createtime` datetime default NULL,
  `code` varchar(20) default NULL,
  `ip` varchar(20) default NULL,
  `mobile` varchar(20) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `terminalid` varchar(20) default NULL,
  `category` int(10) default NULL,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;