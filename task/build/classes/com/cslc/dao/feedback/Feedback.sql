cslc_feedback
id:Long,accountid:Long,mobile:Long,status:Byte,channel:Byte,question:String,reply:String,manager:String,createtime:Date,replytime:Date

<sqlMap resource="com/cslc/dao/feedback/Feedback.xml" />

CREATE TABLE `feedback` (
  `accountid` bigint(40) default NULL,
  `createtime` datetime default NULL,
  `replytime` datetime default NULL,
  `question` varchar(20) default NULL,
  `manager` varchar(20) default NULL,
  `mobile` bigint(40) default NULL,
  `channel` tinyint(2) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `reply` varchar(20) default NULL,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;