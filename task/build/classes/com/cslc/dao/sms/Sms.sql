cslc_sms
id:Long,category:Integer,mobile:String,createtime:Date,sendtime:Date,verifycode:String,content:String,verifytime:Date,status:Byte,channel:Byte

<sqlMap resource="com/cslc/dao/sms/Sms.xml" />

CREATE TABLE `sms` (
  `createtime` datetime default NULL,
  `sendtime` datetime default NULL,
  `verifycode` varchar(20) default NULL,
  `mobile` varchar(20) default NULL,
  `channel` tinyint(2) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `category` int(10) default NULL,
  `verifytime` datetime default NULL,
  `content` varchar(20) default NULL,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;