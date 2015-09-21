publish_project
id:Long,createtime:Date,content:String,title:String,leader:String,partners:String,starttime:Date,endtime:Date,progress:Integer

<sqlMap resource="com/publish/dao/project/Project.xml" />

CREATE TABLE `project` (
  `progress` int(10) default NULL,
  `endtime` datetime default NULL,
  `content` varchar(2000) default NULL,
  `createtime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) default NULL,
  `starttime` datetime default NULL,
  `partners` varchar(100) default NULL,
  `leader` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;