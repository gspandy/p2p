cslc_task
id:Long,title:String,url:String,category:Integer,createtime:Date,starttime:Date,endtime:Date,score:Integer,status:Byte,serialno:Integer

<sqlMap resource="com/cslc/dao/task/Task.xml" />

CREATE TABLE `task` (
  `score` int(10) default NULL,
  `createtime` datetime default NULL,
  `endtime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `starttime` datetime default NULL,
  `title` varchar(20) default NULL,
  `category` int(10) default NULL,
  `url` varchar(20) default NULL,
  `status` tinyint(2) default NULL,
  `serialno` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;