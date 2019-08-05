DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户Id,主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime COMMENT '更新时间',
  `user_alias` varchar(50) NOT NULL  COMMENT '用户名',
  `login_key`  varchar(50) NOT NULL  COMMENT '密码',
  `salt`  varchar(50) NOT NULL COMMENT '加盐字符串',
  `phone` varchar(20)  COMMENT '手机号码',
  `states` int(10) default 0 COMMENT '状态,0=正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
