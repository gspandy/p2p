cslc_selfitemagreement
id:Long,content:String,title:String

<sqlMap resource="com/cslc/dao/selfitemagreement/Selfitemagreement.xml" />

CREATE TABLE `selfitemagreement` (
  `content` varchar(20) default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;