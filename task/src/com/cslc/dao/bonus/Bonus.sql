cslc_bonus
id:Long,accountid:Long,totalbonus:Double,usebonus:Double,title:String,selfitemid:Long,createtime:Date,starttime:Date,endtime:Date,usetime:Date,backtime:Date,status:Byte,channel:Integer,rate:Integer

<sqlMap resource="com/cslc/dao/bonus/Bonus.xml" />

CREATE TABLE `bonus` (
  `createtime` datetime default NULL,
  `totalbonus` decimal(16,2) default NULL,
  `channel` int(10) default NULL,
  `endtime` datetime default NULL,
  `starttime` datetime default NULL,
  `title` varchar(20) default NULL,
  `selfitemid` bigint(40) default NULL,
  `backtime` datetime default NULL,
  `usetime` datetime default NULL,
  `usebonus` decimal(16,2) default NULL,
  `accountid` bigint(40) default NULL,
  `rate` int(10) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;