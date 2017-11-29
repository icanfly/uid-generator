/*
 Navicat Premium Data Transfer

 Source Server         : kxl_dev_db
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 116.62.207.246
 Source Database       : kxl_dev_db

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : utf-8

 Date: 11/29/2017 14:26:04 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `kxl_id_worker`
-- ----------------------------
DROP TABLE IF EXISTS `kxl_id_worker`;
CREATE TABLE `kxl_id_worker` (
  `worker_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'worker id',
  `host` varchar(39) NOT NULL COMMENT 'worker host',
  `host_mac` varchar(36) NOT NULL COMMENT 'host mac address',
  `launch_time` datetime NOT NULL COMMENT 'worker launched time',
  `heartbeat_time` datetime NOT NULL COMMENT 'worker lastest heartbeat time',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`worker_id`),
  UNIQUE KEY `uk_ip_mac` (`host`,`host_mac`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gbk;

SET FOREIGN_KEY_CHECKS = 1;
