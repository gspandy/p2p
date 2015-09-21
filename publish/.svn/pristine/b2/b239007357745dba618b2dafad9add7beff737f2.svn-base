publish_projectbug
id:Long,createtime:Date,resolvetime:Date,content:String,title:String,tester:String,developer:String,status:Byte,files:String,projectid:Long

<sqlMap resource="com/publish/dao/projectbug/Projectbug.xml" />

CREATE TABLE `projectbug` (
  `content` varchar(1000) default NULL,
  `resolvetime` datetime default NULL,
  `createtime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `files` varchar(400) default NULL,
  `tester` varchar(20) default NULL,
  `title` varchar(100) default NULL,
  `status` tinyint(2) default NULL,
  `developer` varchar(20) default NULL,
  `projectid` bigint(40) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;