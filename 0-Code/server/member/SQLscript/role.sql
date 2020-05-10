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

 Date: 10/05/2020 12:58:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `authority_id` varchar(0) DEFAULT NULL COMMENT '权限id',
  `user_id` varchar(0) DEFAULT NULL COMMENT '人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
