cslc_reward
id:Long,title:String,category:Integer,createtime:Date,score:Integer,status:Byte,serialno:Integer

<sqlMap resource="com/cslc/dao/reward/Reward.xml" />

CREATE TABLE `reward` (
  `score` int(10) default NULL,
  `createtime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) default NULL,
  `category` int(10) default NULL,
  `status` tinyint(2) default NULL,
  `serialno` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;