DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户Id,主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime COMMENT '更新时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `order_no` varchar(50) NOT NULL  COMMENT '订单号',
  `amount` decimal(20,2) NOT NULL  COMMENT '订单金额',
  `states` int(10) default 0 COMMENT '发货状态,0=未发货,1=已发货',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
