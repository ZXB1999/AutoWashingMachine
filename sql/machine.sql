/*
Navicat MySQL Data Transfer

Source Server         : AWMsystem
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : machine

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2021-03-07 21:11:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for servercontext
-- ----------------------------
DROP TABLE IF EXISTS `servercontext`;
CREATE TABLE `servercontext` (
  `sc_id` varchar(60) NOT NULL COMMENT '服务唯一标识',
  `servertype` varchar(255) NOT NULL COMMENT '服务类型',
  `serverlevel` varchar(20) NOT NULL COMMENT '服务级别',
  `cost` decimal(10,0) NOT NULL COMMENT '服务金额',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `delflag` varchar(255) NOT NULL COMMENT '伪删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  PRIMARY KEY (`sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servercontext
-- ----------------------------
INSERT INTO `servercontext` VALUES ('239bc5f6711811eba10f509a4ccf4b2c', '1', 'MIN', '3', '2021-02-17 20:36:05', null, '0', '1');
INSERT INTO `servercontext` VALUES ('7912c10a711811eba10f509a4ccf4b2c', '1', 'MID', '4', '2021-02-17 20:36:09', null, '0', '1');
INSERT INTO `servercontext` VALUES ('802f8463711811eba10f509a4ccf4b2c', '1', 'MAX', '5', '2021-02-17 20:36:12', null, '0', '1');
INSERT INTO `servercontext` VALUES ('89016b9c711811eba10f509a4ccf4b2c', '1', 'FLUSH', '2', '2021-02-17 20:36:15', null, '0', '1');
INSERT INTO `servercontext` VALUES ('95123f23711811eba10f509a4ccf4b2c', '2', 'ONE', '3', '2021-02-17 20:36:18', null, '0', '1');
INSERT INTO `servercontext` VALUES ('9d70baac711811eba10f509a4ccf4b2c', '2', 'TWO', '4', '2021-02-17 20:36:20', null, '0', '1');
INSERT INTO `servercontext` VALUES ('a5bf34b9711811eba10f509a4ccf4b2c', '2', 'THREE', '5', '2021-02-17 20:36:23', null, '0', '1');
INSERT INTO `servercontext` VALUES ('ab9b5514711811eba10f509a4ccf4b2c', '2', 'FLUSH', '2', '2021-02-17 20:36:25', null, '0', '1');

-- ----------------------------
-- Table structure for washingmachine
-- ----------------------------
DROP TABLE IF EXISTS `washingmachine`;
CREATE TABLE `washingmachine` (
  `machine_id` varchar(60) NOT NULL COMMENT '机器的唯一标识',
  `brand` varchar(60) NOT NULL COMMENT '品牌',
  `model` varchar(60) NOT NULL COMMENT '型号',
  `type` varchar(60) NOT NULL COMMENT '洗衣机类别',
  `create_time` datetime NOT NULL COMMENT '入库日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `delflag` varchar(255) NOT NULL DEFAULT '0' COMMENT '伪删除标记，默认显示，为0',
  `version` int(10) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  PRIMARY KEY (`machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of washingmachine
-- ----------------------------
INSERT INTO `washingmachine` VALUES ('7737bcec617511eba407509a4ccf4b2c', '美的', 'MD100CQ7PRO', '1', '2021-01-28 22:31:08', null, '0', '1');
INSERT INTO `washingmachine` VALUES ('796c0204617511eba407509a4ccf4b2c', '海尔', 'EB80M39TH', '1', '2021-01-28 22:31:11', null, '0', '1');
INSERT INTO `washingmachine` VALUES ('79e8730c617511eba407509a4ccf4b2c', '美的', 'MB80ECO1', '1', '2021-01-28 22:31:12', null, '0', '1');
INSERT INTO `washingmachine` VALUES ('7a2e2297617511eba407509a4ccf4b2c', '海尔', 'EG10014B39GU1', '1', '2021-01-28 22:31:13', null, '0', '1');
INSERT INTO `washingmachine` VALUES ('7a80c7d8617511eba407509a4ccf4b2c', '美的', 'MSP60-01', '2', '2021-01-28 22:31:13', null, '0', '1');

-- ----------------------------
-- Table structure for washingserver
-- ----------------------------
DROP TABLE IF EXISTS `washingserver`;
CREATE TABLE `washingserver` (
  `machineserver_id` varchar(60) NOT NULL COMMENT '机器服务唯一标识',
  `machine_id` varchar(60) NOT NULL COMMENT '机器的唯一标识',
  `state` varchar(10) NOT NULL COMMENT '机器当前的状态,0:可用,1:占用,2:维护',
  `server` varchar(255) NOT NULL COMMENT '可提供的服务(按照洗衣机的型号与类型)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `delflag` varchar(255) NOT NULL COMMENT '伪删除标记',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  PRIMARY KEY (`machineserver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of washingserver
-- ----------------------------
INSERT INTO `washingserver` VALUES ('7577bcec240021eba407509a41144b2c', '7a80c7d8617511eba407509a4ccf4b2c', '0', '2', '2021-03-07 20:28:24', '2021-03-07 20:28:26', '0', '1');
INSERT INTO `washingserver` VALUES ('7577bcec240021eba407509a4ccf4b2c', '7a2e2297617511eba407509a4ccf4b2c', '0', '1', '2021-03-07 20:27:45', '2021-03-07 20:27:48', '0', '1');
INSERT INTO `washingserver` VALUES ('7577bcec247511eba407509a4ccf4b2c', '796c0204617511eba407509a4ccf4b2c', '0', '1', '2021-03-07 20:26:22', '2021-03-07 20:26:25', '0', '1');
INSERT INTO `washingserver` VALUES ('7577bcec249981eba407509a4ccf4b2c', '79e8730c617511eba407509a4ccf4b2c', '0', '1', '2021-03-07 20:27:13', '2021-03-07 20:27:16', '0', '1');
INSERT INTO `washingserver` VALUES ('7577bcec617511eba407509a4ccf4b2c', '7737bcec617511eba407509a4ccf4b2c', '0', '1', '2021-03-07 20:23:25', '2021-03-07 20:23:21', '0', '1');
