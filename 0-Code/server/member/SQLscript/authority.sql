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

 Date: 10/05/2020 12:57:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authority_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
