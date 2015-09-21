cslc_question
id:Long,category:Integer,question:String,answer:String,status:Byte,serialno:Integer,createtime:Date

<sqlMap resource="com/cslc/dao/question/Question.xml" />

CREATE TABLE `question` (
  `createtime` datetime default NULL,
  `question` varchar(20) default NULL,
  `answer` varchar(20) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `category` int(10) default NULL,
  `status` tinyint(2) default NULL,
  `serialno` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;