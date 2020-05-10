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

 Date: 10/05/2020 12:58:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role_id` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'qqq', '111', NULL);
INSERT INTO `user` VALUES (2, 'string', 'string', NULL);
INSERT INTO `user` VALUES (3, '', '', NULL);
INSERT INTO `user` VALUES (4, '1111', '1111', NULL);
INSERT INTO `user` VALUES (5, '', '111', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
