cslc_message
id:Long,accountid:Long,category:Integer,status:Byte,content:String,title:String,createtime:Date

<sqlMap resource="com/cslc/dao/message/Message.xml" />

CREATE TABLE `message` (
  `accountid` bigint(40) default NULL,
  `createtime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `category` int(10) default NULL,
  `content` varchar(20) default NULL,
  `title` varchar(20) default NULL,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;