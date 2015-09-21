cslc_account
id:Long,category:Byte,status:Byte,channel:String,createtime:Date,loginpwd:String,lastlogintime:Date,lasttradetime:Date,dynamicpwd:String,invitecode:String,inviteid:Long,mobile:String,realname:String,idcardno:String,tradecount:Integer

<sqlMap resource="com/cslc/dao/account/Account.xml" />

CREATE TABLE `account` (
  `createtime` datetime default NULL,
  `dynamicpwd` varchar(20) default NULL,
  `loginpwd` varchar(20) default NULL,
  `invitecode` varchar(20) default NULL,
  `inviteid` bigint(40) default NULL,
  `channel` varchar(20) default NULL,
  `mobile` varchar(20) default NULL,
  `tradecount` int(10) default NULL,
  `idcardno` varchar(20) default NULL,
  `lastlogintime` datetime default NULL,
  `realname` varchar(20) default NULL,
  `lasttradetime` datetime default NULL,
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `category` tinyint(2) default NULL,
  `status` tinyint(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000000000000001 DEFAULT CHARSET=utf8;