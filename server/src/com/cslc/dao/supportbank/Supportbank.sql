cslc_supportbank
id:Long,name:String,simplename:String,logourl:String,channel:Byte,status:Byte,singlemaxamount:Double,daymaxamount:Double,monthmaxamount:Double,serialno:Integer

<sqlMap resource="com/cslc/dao/supportbank/Supportbank.xml" />

CREATE TABLE `supportbank` (
  `name` varchar(20) default NULL,
  `channel` tinyint(2) default NULL,
  `simplename` varchar(20) default NULL,
  `monthmaxamount` decimal(16,2) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `singlemaxamount` decimal(16,2) default NULL,
  `logourl` varchar(20) default NULL,
  `daymaxamount` decimal(16,2) default NULL,
  `status` tinyint(2) default NULL,
  `serialno` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;