/*
 Navicat Premium Data Transfer

 Source Server         : LOCALHOST
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : docker_panel

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 20/06/2023 16:02:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for console_menu
-- ----------------------------
DROP TABLE IF EXISTS `console_menu`;
CREATE TABLE `console_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_menu
-- ----------------------------

-- ----------------------------
-- Table structure for console_role
-- ----------------------------
DROP TABLE IF EXISTS `console_role`;
CREATE TABLE `console_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_role
-- ----------------------------
INSERT INTO `console_role` VALUES (1, 'admin', '管理员');

-- ----------------------------
-- Table structure for console_user
-- ----------------------------
DROP TABLE IF EXISTS `console_user`;
CREATE TABLE `console_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `phone_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `gender` int NULL DEFAULT NULL COMMENT '性别',
  `role` int NULL DEFAULT NULL COMMENT '角色',
  `locked_flag` tinyint NULL DEFAULT 0 COMMENT '是否被锁定:0-未被锁定;1-已被锁定',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否被删除:0-未被删除;1-已被删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_user
-- ----------------------------
INSERT INTO `console_user` VALUES (1, 'kent', 'e10adc3949ba59abbe56e057f20f883e', '111111', 1, 1, 0, 0, NULL);

-- ----------------------------
-- Table structure for console_user_token
-- ----------------------------
DROP TABLE IF EXISTS `console_user_token`;
CREATE TABLE `console_user_token`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_user_token
-- ----------------------------
INSERT INTO `console_user_token` VALUES (1, '309874d9abe3305e0350eff962fe00c7', '2023-06-20 16:01:45', '2023-06-22 16:01:45');

SET FOREIGN_KEY_CHECKS = 1;
