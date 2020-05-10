/*
 Navicat Premium Data Transfer

 Source Server         : root1234-虚拟机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.201.220:3306
 Source Schema         : memberdb

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/05/2020 12:58:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `register_data` timestamp NULL DEFAULT NULL COMMENT '注册日期',
  `total_consumption` varchar(255) DEFAULT NULL COMMENT '总消费额',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` VALUES (1, '张三3', '2020-05-01 00:00:00', NULL, '131313');
INSERT INTO `member` VALUES (2, '李四', '2020-05-05 14:55:44', '1000', '13131');
INSERT INTO `member` VALUES (3, '王五', '2020-05-05 14:55:44', '1000', '1313');
INSERT INTO `member` VALUES (4, '赵六', '2020-05-05 14:55:44', '1000', '1331');
INSERT INTO `member` VALUES (5, 'string555', '2020-05-07 11:29:02', NULL, 'string555');
INSERT INTO `member` VALUES (6, '111', '2020-05-01 00:00:00', NULL, '11');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
