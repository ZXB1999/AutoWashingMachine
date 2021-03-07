/*
Navicat MySQL Data Transfer

Source Server         : AWMsystem
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : awmorders

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2021-03-07 21:11:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for awmorder
-- ----------------------------
DROP TABLE IF EXISTS `awmorder`;
CREATE TABLE `awmorder` (
  `order_id` varchar(60) NOT NULL COMMENT '订单唯一id',
  `customer_id` varchar(60) NOT NULL COMMENT '顾客id',
  `machine_id` varchar(60) NOT NULL COMMENT '机器id',
  `serverlevel` varchar(20) NOT NULL COMMENT '服务等级',
  `start_time` datetime NOT NULL COMMENT '开始洗衣时间',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `delflag` varchar(255) NOT NULL DEFAULT '0' COMMENT '伪删除标记',
  `version` int(10) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_state` varchar(2) NOT NULL DEFAULT '0' COMMENT '订单当前状态，默认0不可见，1运行中',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of awmorder
-- ----------------------------
