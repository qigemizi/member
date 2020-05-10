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

 Date: 10/05/2020 12:57:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consumption
-- ----------------------------
DROP TABLE IF EXISTS `consumption`;
CREATE TABLE `consumption` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL COMMENT '会员ID',
  `consumption_date` timestamp NULL DEFAULT NULL COMMENT '本次消费日期',
  `consumption_quota` varchar(255) DEFAULT NULL COMMENT '本次消费额度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of consumption
-- ----------------------------
BEGIN;
INSERT INTO `consumption` VALUES (1, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (2, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (3, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (4, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (5, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (6, 1, '2020-05-08 16:30:51', '111');
INSERT INTO `consumption` VALUES (8, 2, '2020-05-09 17:52:29', '1110');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
