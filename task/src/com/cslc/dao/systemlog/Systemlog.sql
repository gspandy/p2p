cslc_systemlog
createtime:Date,content:String,category:Byte,accountid:Long

<sqlMap resource="com/cslc/dao/systemlog/Systemlog.xml" />

CREATE TABLE `systemlog` (
  `content` varchar(5000) default NULL,
  `createtime` datetime default NULL,
  `category` tinyint(2) default NULL,
  `accountid` bigint(40) default NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;