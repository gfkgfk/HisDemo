/*
 Navicat Premium Data Transfer

 Source Server         : BANWAGONG_HONKONG_LOCAL
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 93.179.125.238:3306
 Source Schema         : docker_panel

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 03/08/2023 10:28:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for console_announcement
-- ----------------------------
DROP TABLE IF EXISTS `console_announcement`;
CREATE TABLE `console_announcement`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of console_announcement
-- ----------------------------
INSERT INTO `console_announcement` VALUES (1, '关于省健康信息平台数据采集服务项目', '省全民健康信息平台共享数据集与数据标准（中医药部分）的通知、关于开展全省二级公立中医医院数据采集工作的通知要求，省卫生健康委、省中医药管理局开展了省医疗服务综合监管系统项目建设，实现对全省二级中医医院数据的采集、转换、传输、控制、分析和监管，同时省中医药管理局部署了统一的数据采集工具，各医院需要通过该工具来实现与省全民健康信息平台的对接。', '2023-08-03 09:53:36');
INSERT INTO `console_announcement` VALUES (2, '大数据医疗智慧健康管理系统平台', '大数据医疗智慧健康管理系统平台通过智能硬件、边缘计算采集数据,平台做健康评估,数据分析,状态预测,异常预报预警,决策维护.', '2023-08-03 10:23:37');
INSERT INTO `console_announcement` VALUES (3, '医疗数据采集', '实时识别,记录医患问诊内容.向医生进行问诊内容提示,患教知识推荐,用药安全提醒等.可对接电子病历系统上传病历,提升医疗质量,提高临床效率,加速医院的数字化进程.', '2023-08-03 10:24:30');
INSERT INTO `console_announcement` VALUES (4, '关于定点医疗机构上传数据质量', '准确采集医疗服务数据信息 在采集参保人员医疗服务相关信息时,应按照要求正确分项录入医疗费用及相关信息,特别是准确采集入', '2023-08-03 10:25:08');

-- ----------------------------
-- Table structure for console_measure
-- ----------------------------
DROP TABLE IF EXISTS `console_measure`;
CREATE TABLE `console_measure`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测量设备类型',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测量值',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL COMMENT '被测量人用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of console_measure
-- ----------------------------
INSERT INTO `console_measure` VALUES (1, '1', '100/86', '2023-08-03 09:14:25', 1);
INSERT INTO `console_measure` VALUES (2, '1', '91/89', '2023-08-03 10:27:57', 1);
INSERT INTO `console_measure` VALUES (3, '1', '97/86', '2023-08-03 10:28:22', 1);

-- ----------------------------
-- Table structure for console_menu
-- ----------------------------
DROP TABLE IF EXISTS `console_menu`;
CREATE TABLE `console_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_menu
-- ----------------------------

-- ----------------------------
-- Table structure for console_role
-- ----------------------------
DROP TABLE IF EXISTS `console_role`;
CREATE TABLE `console_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `c_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_role
-- ----------------------------
INSERT INTO `console_role` VALUES (1, 'admin', '管理员');

-- ----------------------------
-- Table structure for console_user
-- ----------------------------
DROP TABLE IF EXISTS `console_user`;
CREATE TABLE `console_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别',
  `role` int(11) NULL DEFAULT NULL COMMENT '角色',
  `locked_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否被锁定:0-未被锁定;1-已被锁定',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否被删除:0-未被删除;1-已被删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_user
-- ----------------------------
INSERT INTO `console_user` VALUES (1, 'kent', 'e10adc3949ba59abbe56e057f20f883e', '111111', 1, 1, 0, 0, NULL);

-- ----------------------------
-- Table structure for console_user_token
-- ----------------------------
DROP TABLE IF EXISTS `console_user_token`;
CREATE TABLE `console_user_token`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of console_user_token
-- ----------------------------
INSERT INTO `console_user_token` VALUES (1, '5a4ba2bfabaf34791477e2c9bc9b92fa', '2023-08-03 09:13:28', '2023-08-05 09:13:28');

SET FOREIGN_KEY_CHECKS = 1;
