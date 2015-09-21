cslc_activitylog
id:Long,accountid:Long,activityid:Long,score:Integer,bonus:Double,createtime:Date

<sqlMap resource="com/cslc/dao/activitylog/Activitylog.xml" />

CREATE TABLE `activitylog` (
  `accountid` bigint(40) default NULL,
  `activityid` bigint(40) default NULL,
  `score` int(10) default NULL,
  `createtime` datetime default NULL,
  `bonus` decimal(16,2) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;