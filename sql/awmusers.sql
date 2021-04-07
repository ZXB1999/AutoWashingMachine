/*
Navicat MySQL Data Transfer

Source Server         : AWMsystem
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : awmusers

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2021-04-07 13:27:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for awmuser
-- ----------------------------
DROP TABLE IF EXISTS `awmuser`;
CREATE TABLE `awmuser` (
  `awmuser_id` varchar(60) NOT NULL COMMENT '用户唯一身份标识',
  `awmname` varchar(60) DEFAULT NULL COMMENT '昵称',
  `awmusername` varchar(60) NOT NULL COMMENT '用户登陆系统账号',
  `password` varchar(255) NOT NULL COMMENT '登陆系统密码（加密）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delflag` varchar(255) NOT NULL DEFAULT '0' COMMENT '伪删除标记位',
  `version` int(10) NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `paypwd` varchar(6) NOT NULL DEFAULT '123456' COMMENT '支付密码',
  PRIMARY KEY (`awmuser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of awmuser
-- ----------------------------
INSERT INTO `awmuser` VALUES ('1356954215529799681', null, 'ttmy', 'system', '2021-02-03 21:14:31', '2021-04-05 14:58:57', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1356954748864913410', '管理员', 'admin', 'system', '2021-02-03 21:16:38', '2021-04-05 15:19:04', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1356963758829625346', null, 'zxb', 'zxb', '2021-02-03 21:52:26', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1358442189740306433', null, 'system', 'system', '2021-02-07 23:47:11', '2021-04-05 16:08:26', '1', '1', '123456');
INSERT INTO `awmuser` VALUES ('1359512689446924289', null, 'iostest ', 'system', '2021-02-10 22:40:58', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1359513820260642817', null, 'androdtest', 'system', '2021-02-10 22:45:28', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360422154652319746', null, 'test01', '1', '2021-02-13 10:54:52', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360422821836062721', null, 'test02', '1', '2021-02-13 10:57:31', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360423134752112642', null, 'test03', '1', '2021-02-13 10:58:45', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360423394928984066', null, 'test04', '1', '2021-02-13 10:59:47', '2021-04-05 15:19:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360423545089261570', null, 'test05', '1', '2021-02-13 11:00:23', '2021-02-13 11:00:23', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360433816864186369', null, 'test06', '1', '2021-02-13 11:41:12', '2021-02-13 11:41:12', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360619482390663170', null, 'test001', '1', '2021-02-13 23:58:58', '2021-02-13 23:58:58', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360938919052378113', null, 'test002', '1', '2021-02-14 21:08:18', '2021-02-14 21:08:18', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360942487373283329', null, '12343', '123456', '2021-02-14 21:22:29', '2021-02-14 21:22:29', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1360944083767328769', null, 'test003', '1', '2021-02-14 21:28:49', '2021-02-14 21:28:49', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361292587119304705', null, 'test005', '1', '2021-02-15 20:33:39', '2021-02-15 20:33:39', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361292842497892354', null, 'test006', '1', '2021-02-15 20:34:40', '2021-02-15 20:34:40', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361293137961443330', null, 'test007', '1', '2021-02-15 20:35:50', '2021-02-15 20:35:50', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361293910275416066', null, 'test008', '1', '2021-02-15 20:38:54', '2021-02-15 20:38:54', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361294317076766722', null, 'test009', '1', '2021-02-15 20:40:31', '2021-02-15 20:40:31', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361294499176669186', null, 'test010', '1', '2021-02-15 20:41:15', '2021-02-15 20:41:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361312287555477506', null, 'yw', '1', '2021-02-15 21:51:56', '2021-02-15 21:51:56', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1361337665309270018', null, 'test012', '1', '2021-02-15 23:32:46', '2021-02-15 23:32:46', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1362257022034018306', null, 'zxbtest01', '1', '2021-02-18 12:25:58', '2021-02-18 12:25:58', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1362258017967308802', null, 'zxbtest02', '1', '2021-02-18 12:29:56', '2021-02-18 12:29:56', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1363089848887365633', null, '123456', '123456', '2021-02-20 19:35:20', '2021-02-20 19:35:20', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1363090025425620993', null, 'zxbtest011', '1', '2021-02-20 19:36:02', '2021-02-20 19:36:02', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1368188596244430849', null, 'zzq', '997996', '2021-03-06 21:15:56', '2021-03-06 21:15:56', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1368189654580576258', null, 'zzqq', '997996', '2021-03-06 21:20:08', '2021-03-06 21:20:08', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1368531092442853378', null, '可乐', 'kele', '2021-03-07 19:56:53', '2021-03-07 19:56:53', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1368539487979892738', null, 'yhj1998', 'yhj1998', '2021-03-07 20:30:15', '2021-03-07 20:30:15', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1369269776834269185', null, 'zxb1', '1', '2021-03-09 20:52:09', '2021-03-09 20:52:09', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1369276895650287617', null, 'zwy', '123456', '2021-03-09 21:20:27', '2021-03-09 21:20:27', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1369624836898500609', null, 'test011', '1', '2021-03-10 20:23:02', '2021-03-10 20:23:02', '0', '1', '123456');
INSERT INTO `awmuser` VALUES ('1371077555941203969', null, 'test000001', '1', '2021-03-14 20:35:37', '2021-03-14 20:35:37', '0', '1', '123456');
