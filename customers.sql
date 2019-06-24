/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.85.132:3306
 Source Schema         : customers

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 24/06/2019 18:21:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user`  (
  `id` int(11) UNSIGNED NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_no` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `phone_num` int(11) NULL DEFAULT NULL COMMENT '用户手机号',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES (10000, NULL, NULL, 'admin', 'a66abb5684c45962d887564f08346e8d', NULL, 1, NULL, '2019-06-24 10:45:23');

-- ----------------------------
-- Table structure for customer_interview
-- ----------------------------
DROP TABLE IF EXISTS `customer_interview`;
CREATE TABLE `customer_interview`  (
  `customer_interview_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话访问id',
  `customer_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被访问人姓名',
  `customer_company` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被访问人公司',
  `called_num` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '被叫号码',
  `label` int(2) NULL DEFAULT NULL COMMENT '标签',
  `type` int(2) NOT NULL DEFAULT 0 COMMENT '回访类型 0=手机,1=固话',
  `result` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回访结果',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '业务员',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '呼叫时间',
  PRIMARY KEY (`customer_interview_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_interview
-- ----------------------------
INSERT INTO `customer_interview` VALUES ('7464abef965211e99cd6000c291193b0', '37', '15', '12', 0, 0, NULL, 10000, '2019-06-24 15:34:09');
INSERT INTO `customer_interview` VALUES ('a85106ae965211e99cd6000c291193b0', '15', '39', '50', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510c52965211e99cd6000c291193b0', '31', '5', '34', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510d49965211e99cd6000c291193b0', '1', '3', '12', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510dca965211e99cd6000c291193b0', '3', '25', '16', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510e23965211e99cd6000c291193b0', '4', '23', '2', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510e71965211e99cd6000c291193b0', '40', '42', '40', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8510f93965211e99cd6000c291193b0', '26', '8', '12', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8511137965211e99cd6000c291193b0', '33', '31', '7', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a85111a6965211e99cd6000c291193b0', '39', '25', '7', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a85111f9965211e99cd6000c291193b0', '9', '22', '35', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8511249965211e99cd6000c291193b0', '8', '36', '2', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8511298965211e99cd6000c291193b0', '5', '17', '22', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a85112e5965211e99cd6000c291193b0', '5', '9', '30', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a851133b965211e99cd6000c291193b0', '24', '31', '29', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a851138b965211e99cd6000c291193b0', '2', '21', '49', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a8511473965211e99cd6000c291193b0', '34', '21', '3', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a85114be965211e99cd6000c291193b0', '1', '44', '19', 0, 0, NULL, 10000, '2019-06-24 15:35:36');
INSERT INTO `customer_interview` VALUES ('a851150a965211e99cd6000c291193b0', '10', '45', '43', 0, 0, NULL, 10000, '2019-06-24 15:35:36');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `fid` int(11) NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menu_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL,
  `depart_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remake` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
