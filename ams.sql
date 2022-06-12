/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : ams

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 28/05/2022 17:50:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for growing_environment
-- ----------------------------
DROP TABLE IF EXISTS `growing_environment`;
CREATE TABLE `growing_environment`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `product_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `water_amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '用水量/kg',
  `illumination_time` decimal(10, 2) NULL DEFAULT NULL COMMENT '光照时长/h',
  `fertilization_amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '施肥量/kg',
  `fertilization_date` time(0) NULL DEFAULT NULL COMMENT '施肥时间',
  `is_pesticide` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否使用农药(接码表-yes_or_no)',
  `pesticide_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '农药名',
  `pesticide_amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '农药使用量/kg',
  `is_weeding` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否除草(接码表-yes_or_no)',
  `soil_conditions` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '土壤检测情况(接码表-soil_conditions)',
  `water_quality` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '水质检测情况(接码表-water_quality)',
  `collect_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `temperature` decimal(11, 2) NULL DEFAULT NULL,
  `humidity` decimal(11, 2) NULL DEFAULT NULL,
  `pest_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生长环境记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of growing_environment
-- ----------------------------
INSERT INTO `growing_environment` VALUES ('10372e876dd197a6b1a424239333d3f6', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '敌百虫混合药剂', 3.13, NULL, NULL, NULL, '2022-06-26 21:54:44', '2022-06-26 21:54:44', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 22:11:46', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 13.57, 59.58, 'pierid');
INSERT INTO `growing_environment` VALUES ('153054cc1c1e68b3a9807efcbeb65be2', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 07:53:19', '2022-06-26 07:53:19', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 07:53:20', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.57, 55.16, NULL);
INSERT INTO `growing_environment` VALUES ('1a2f54772aff41981926aa91dfb30db4', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 07:23:17', '2022-06-26 07:23:17', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 07:23:17', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.38, 55.43, NULL);
INSERT INTO `growing_environment` VALUES ('1b4ad935deb30617c225a41593260042', 'caf01b29b08ff4567b3c4262916d22fc', 3.00, 6.00, 4.00, '16:37:29', 'N', '', NULL, 'Y', '01', '01', NULL, '2022-06-22 04:38:21', '1', NULL, NULL, 0, 0.00, 0.00, '无');
INSERT INTO `growing_environment` VALUES ('1b8f482974b1cbe3fd38a68f4bb2d857', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 03:22:40', '2022-06-26 03:22:40', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 03:22:41', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 15.72, 60.09, NULL);
INSERT INTO `growing_environment` VALUES ('1d8973ea20487c91c331a01e3c008380', '0bcb280fb1bbd5cb653eeefae6d573ab', 12.00, 13.00, 10.00, '16:35:54', 'N', '', NULL, 'Y', '01', '01', NULL, '2022-06-22 03:02:27', '1', NULL, NULL, 0, 0.00, 0.00, '无');
INSERT INTO `growing_environment` VALUES ('28faa0c2134e4d0a15b9482370264d33', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 13:23:56', '2022-06-26 13:23:56', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 13:23:57', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 22.61, 57.51, NULL);
INSERT INTO `growing_environment` VALUES ('2c621ba4bad50bce6f2e1c692e16ea03', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 10:23:29', '2022-06-26 10:23:29', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 10:23:30', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 21.42, 50.99, NULL);
INSERT INTO `growing_environment` VALUES ('30d133233b6821677d7983dce62dd149', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 19:54:36', '2022-06-26 19:54:36', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 19:54:37', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 15.49, 60.75, NULL);
INSERT INTO `growing_environment` VALUES ('31ccaed17feb077c22ac932232c13a8b', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 05:23:07', '2022-06-26 05:23:07', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 05:23:08', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.29, 60.07, NULL);
INSERT INTO `growing_environment` VALUES ('34c728e4aaee249c55420822b6df1060', '417d8bb15344e3648b5e39f59d444452', 1.00, 2.00, 1.00, '16:31:09', 'N', '', NULL, 'Y', '01', '01', '2022-06-21 22:17:19', '2022-06-21 22:17:19', '1', '2022-06-26 00:43:07', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 0.00, 0.00, 'cossidae');
INSERT INTO `growing_environment` VALUES ('3e2aab62f1a06fa6a2ada59826cae3a2', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '敌百虫混合药剂', 3.50, NULL, NULL, NULL, '2022-06-26 11:53:50', '2022-06-26 11:53:50', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 12:14:24', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 21.28, 59.63, 'inchworm');
INSERT INTO `growing_environment` VALUES ('3ebc9565c5384ae737901107ae14a354', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 01:22:31', '2022-06-26 01:22:31', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 01:22:32', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.06, 58.86, NULL);
INSERT INTO `growing_environment` VALUES ('4086c9214898f1eda32ade70b9fdc5ca', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '辛硫磷混合药剂', 3.76, NULL, NULL, NULL, '2022-06-26 12:23:52', '2022-06-26 12:23:52', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 12:24:28', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 22.12, 59.33, 'sphingidae');
INSERT INTO `growing_environment` VALUES ('40c855198a6e1529f08474d6fcf8b70e', '417d8bb15344e3648b5e39f59d444452', 10.00, 15.00, 15.00, '16:34:47', 'N', '', NULL, 'Y', '01', '02', '2022-05-28 17:27:17', '2022-06-22 01:55:19', '1', '2022-06-26 10:08:27', '1', 0, 0.00, 0.00, '无');
INSERT INTO `growing_environment` VALUES ('44d691a1ac57e66c0fe2a0cc19653723', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '速灭杀丁混合药剂', 3.32, NULL, NULL, NULL, '2022-06-26 04:53:05', '2022-06-26 04:53:05', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 04:53:38', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.59, 59.23, 'eucleid');
INSERT INTO `growing_environment` VALUES ('46216e60a7854ccfd8a4046ae91df036', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 18:24:31', '2022-06-26 18:24:31', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 18:24:31', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.53, 59.16, NULL);
INSERT INTO `growing_environment` VALUES ('5ad2e4b9eff0459ad91ba94ebe90973a', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 20:24:38', '2022-06-26 20:24:38', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 20:24:39', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 15.16, 58.23, NULL);
INSERT INTO `growing_environment` VALUES ('60155b37909bb9ee4d064811f400853f', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '美曲膦脂混合药剂', 3.45, NULL, NULL, NULL, '2022-06-26 05:53:10', '2022-06-26 05:53:10', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 06:13:45', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 17.72, 59.47, 'curculionidae');
INSERT INTO `growing_environment` VALUES ('75cc61df2b81fba4648c5a9c54c9fe1d', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 14:54:02', '2022-06-26 14:54:02', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 14:54:03', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 23.63, 53.57, NULL);
INSERT INTO `growing_environment` VALUES ('90149f4f042fb64f159912fa5ff4e4be', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 06:23:12', '2022-06-26 06:23:12', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 06:23:13', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 17.98, 57.83, NULL);
INSERT INTO `growing_environment` VALUES ('9077e5174a01e9dfca433c9c894eed9e', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 09:53:27', '2022-06-26 09:53:27', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 09:53:27', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 20.74, 51.34, NULL);
INSERT INTO `growing_environment` VALUES ('91fe6bdf71e98a59fa2aa9e5530df911', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 19:24:34', '2022-06-26 19:24:34', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 19:24:35', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.00, 59.89, NULL);
INSERT INTO `growing_environment` VALUES ('9d801c9af0552ca25ffd4bea7382224d', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 06:53:15', '2022-06-26 06:53:15', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 06:53:16', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.62, 57.34, NULL);
INSERT INTO `growing_environment` VALUES ('9e11c4a2313ded6d4b29614debc4794a', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 03:52:42', '2022-06-26 03:52:42', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 03:52:43', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 15.10, 59.11, NULL);
INSERT INTO `growing_environment` VALUES ('a2ae798b022b510bca75876960d4767b', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '敌百虫混合药剂', 3.27, NULL, NULL, NULL, '2022-06-26 02:52:38', '2022-06-26 02:52:38', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 02:53:20', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.04, 59.48, 'pierid');
INSERT INTO `growing_environment` VALUES ('a5636a2260f8cbb064fc5d941a9adbdb', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 08:23:21', '2022-06-26 08:23:21', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 08:23:21', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.72, 53.51, NULL);
INSERT INTO `growing_environment` VALUES ('ab348dd439415bb16cfbe53d0fa0e88b', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 01:52:34', '2022-06-26 01:52:34', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 01:52:35', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 17.09, 59.11, NULL);
INSERT INTO `growing_environment` VALUES ('ad884eb4885e2e4ffd801e819477ee52', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 15:54:16', '2022-06-26 15:54:16', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 15:54:18', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.67, 60.28, NULL);
INSERT INTO `growing_environment` VALUES ('b50286b2561c846b5c2e63e5016b8448', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 02:22:36', '2022-06-26 02:22:36', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 02:22:37', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.92, 59.13, NULL);
INSERT INTO `growing_environment` VALUES ('b595eb7e254645a228b4cfa937d4a238', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 22:24:45', '2022-06-26 22:24:45', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 22:24:46', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 12.79, 59.77, NULL);
INSERT INTO `growing_environment` VALUES ('b621fcba0ea1cb9be350624c5bfb980d', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 14:24:00', '2022-06-26 14:24:00', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 14:24:01', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 23.41, 55.33, NULL);
INSERT INTO `growing_environment` VALUES ('bb8a7c584d919a7baae9241f266cd6b3', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 16:24:19', '2022-06-26 16:24:19', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 16:24:20', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.96, 59.28, NULL);
INSERT INTO `growing_environment` VALUES ('bf2a34b4ab3d6352ee3254ba9054c9ce', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 20:54:40', '2022-06-26 20:54:40', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 20:54:41', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 14.34, 58.99, NULL);
INSERT INTO `growing_environment` VALUES ('c4072812f1967cadfa76ac28eb1f9d3a', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 10:53:31', '2022-06-26 10:53:31', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 10:53:32', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 22.26, 49.74, NULL);
INSERT INTO `growing_environment` VALUES ('cb1673ae43160ecf0b8c918fe047abe8', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 17:24:25', '2022-06-26 17:24:25', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 17:24:27', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 17.45, 58.53, NULL);
INSERT INTO `growing_environment` VALUES ('cb8dd93b084229d0ff2ed84f6705c090', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 09:23:25', '2022-06-26 09:23:25', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 09:23:26', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.91, 52.88, NULL);
INSERT INTO `growing_environment` VALUES ('cc87883afe07f6c8f01880a6e701a611', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 21:24:42', '2022-06-26 21:24:42', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 21:24:43', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 13.68, 59.32, NULL);
INSERT INTO `growing_environment` VALUES ('d21030ea1d05f3fd72f0a11c8629df25', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 17:54:29', '2022-06-26 17:54:29', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 17:54:29', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.78, 58.66, NULL);
INSERT INTO `growing_environment` VALUES ('d22b0cc93105904e74bafd3a8b9589d1', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 12:53:54', '2022-06-26 12:53:54', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 12:53:55', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 22.35, 57.95, NULL);
INSERT INTO `growing_environment` VALUES ('dc3fa71083c437a07bb1d46706a9ffbe', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '美曲膦脂混合药剂', 6.72, NULL, NULL, NULL, '2022-06-26 15:24:04', '2022-06-26 15:24:04', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 15:45:37', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 23.49, 54.53, 'cricket');
INSERT INTO `growing_environment` VALUES ('e29338f3c442171dd90b0a0be3efe71f', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '氰戊菊酯混合药剂', 3.12, NULL, NULL, NULL, '2022-06-26 13:53:58', '2022-06-26 13:53:58', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 14:04:39', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 23.39, 56.70, 'wireworm');
INSERT INTO `growing_environment` VALUES ('e3cfe0861df079333a7b56386cba2a0f', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 08:53:23', '2022-06-26 08:53:23', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 08:53:24', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.81, 53.18, NULL);
INSERT INTO `growing_environment` VALUES ('e51e518c0ee3f909e1807772ebd2b8b8', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 16:54:22', '2022-06-26 16:54:22', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 16:54:23', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 18.22, 60.13, NULL);
INSERT INTO `growing_environment` VALUES ('e91647c1cf186bf7d0a270920d89c7b6', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'Y', '敌敌畏混合药剂', 6.57, NULL, NULL, NULL, '2022-06-26 00:43:09', '2022-06-26 00:43:09', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 01:03:13', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.01, 60.88, 'millipede');
INSERT INTO `growing_environment` VALUES ('f4efdde7f353402734ab86fb0c71bca8', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 11:23:46', '2022-06-26 11:23:46', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 11:23:48', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 20.40, 59.67, NULL);
INSERT INTO `growing_environment` VALUES ('f998dad75a926452e681432a564f5215', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 04:23:01', '2022-06-26 04:23:01', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 04:23:03', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 19.07, 60.30, NULL);
INSERT INTO `growing_environment` VALUES ('fc36eff287f8da73cdbcc5f424251577', '417d8bb15344e3648b5e39f59d444452', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, '2022-06-26 18:54:32', '2022-06-26 18:54:32', '1c3a5eb4ccfe3d636743ae56e2286f3b', '2022-06-26 18:54:33', '1c3a5eb4ccfe3d636743ae56e2286f3b', 0, 16.12, 59.85, NULL);

-- ----------------------------
-- Table structure for nutrition
-- ----------------------------
DROP TABLE IF EXISTS `nutrition`;
CREATE TABLE `nutrition`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品实例id',
  `protein` decimal(10, 2) NULL DEFAULT NULL COMMENT '蛋白自含量(克/100克)',
  `carbohydrate` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '碳水化合物(接码表-carbohydrate)',
  `minerals` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '矿物质含量(接码表-minerals',
  `moisture` decimal(10, 2) NULL DEFAULT NULL COMMENT '水分含量(%)',
  `df` decimal(10, 2) NULL DEFAULT NULL COMMENT '膳食纤维含量',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '营养成分检测表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nutrition
-- ----------------------------
INSERT INTO `nutrition` VALUES ('9c48b52405364acfe86645505552fad2', 'df2c94f7d2c7b72f19fe38175f277cce', 14.00, '01', '01,02,03,04,05', 11.00, 8.00, '2022-06-22 00:00:09', '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `money` decimal(11, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态（01:待发货，02:已发货；03:已收货）',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES ('61439d30d60deba09a5e0c52b8d53dee', 10.00, '上海市闵行区', '13611708740', '消费者', '02', NULL, '2022-06-22 15:52:23', '2acb4d81cb70d0f201dd30e4658dfe35', NULL, NULL, 0);
INSERT INTO `order_form` VALUES ('b731e5e2aa9ae01ecfe39b07ca212147', 10.00, '上海市闵行区', '13611708740', '消费者', '02', NULL, '2022-06-26 19:42:06', '2acb4d81cb70d0f201dd30e4658dfe35', NULL, NULL, 0);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品id',
  `number` int(11) NULL DEFAULT NULL COMMENT '产品数量',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('5b99bc5f730d406caae8914d1a380081', '61439d30d60deba09a5e0c52b8d53dee', 'df2c94f7d2c7b72f19fe38175f277cce', 1, '2022-06-22 15:52:23', '2acb4d81cb70d0f201dd30e4658dfe35', NULL, NULL);
INSERT INTO `order_info` VALUES ('c12b9d86a39595a904e4214cf416fe61', 'b731e5e2aa9ae01ecfe39b07ca212147', 'df2c94f7d2c7b72f19fe38175f277cce', 1, '2022-06-26 19:42:06', '2acb4d81cb70d0f201dd30e4658dfe35', NULL, NULL);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', 'gCfM7AF0YJBu92Hf2cuiCg==', 'anQnmIye4Co3pOESGcML+w==', '2022-05-28 09:49:26');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品表id',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类别-接码表product_type',
  `total` decimal(11, 2) NULL DEFAULT NULL COMMENT '总量/g',
  `shelf_life_num` int(11) NULL DEFAULT NULL COMMENT '保质期',
  `unit` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保质期单位-接码表shelf_life_unit',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `listener_id` bigint(32) NULL DEFAULT -1 COMMENT 'listenerId',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('0bcb280fb1bbd5cb653eeefae6d573ab', '/073f055f-95a6-4a16-8779-6ad573b3c5c8ccd87e76-92e0-46c0-9af8-7dd0306ea428铁观音.jpg', '铁观音', '04', 100000.00, 3, '03', 0, '2022-06-21 16:27:56', '1', NULL, NULL, 3033163026915400);
INSERT INTO `product` VALUES ('17144dac3ad0200d0ce7a4b8e5658518', '/29469200-6da9-46d3-bb31-d60b9d476d4a0e9092c3-6d71-429f-ac61-44b503d452dd谷子.jpg', '谷子', '01', 100000.00, 2, '03', 0, '2022-06-21 15:00:22', '1', NULL, NULL, 3033070198143200);
INSERT INTO `product` VALUES ('3bccf3471d7aed0bf3bc7f3eabdb1407', '/79c5b1d1-7c6f-4333-a297-44bcd5485d7349c2f31f-1f9d-4ae7-817e-dd590fb2181c苹果树.jpg', '苹果', '03', 100000.00, 1, '02', 0, '2022-06-21 16:07:39', '1', NULL, NULL, 3033141529368600);
INSERT INTO `product` VALUES ('417d8bb15344e3648b5e39f59d444452', '/7841eb5c-9303-45a2-a173-4ecc344974a434553115-cf29-45e7-bf47-e6ce7bdd80c7南瓜.jpg', '南瓜', '02', 100000.00, 2, '02', 0, '2022-06-21 14:29:45', '1', NULL, NULL, 3033037751314300);
INSERT INTO `product` VALUES ('caf01b29b08ff4567b3c4262916d22fc', '/9204bdf9-920b-4e05-90bc-17c2e8f7e2d0eff2b36c-ca8a-4ed2-9eb2-761954d8430b哈密瓜.jpg', '哈密瓜', '03', 100000.00, 2, '02', 0, '2022-06-21 15:26:09', '1', NULL, NULL, 3033097529220700);

-- ----------------------------
-- Table structure for product_example
-- ----------------------------
DROP TABLE IF EXISTS `product_example`;
CREATE TABLE `product_example`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键产品编码/批号',
  `product_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `inventory` int(11) NULL DEFAULT NULL COMMENT '库存',
  `unit` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售卖单位-关联码表price_unit',
  `production_date` datetime(0) NULL DEFAULT NULL COMMENT '生产日期',
  `producer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产地',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_example
-- ----------------------------
INSERT INTO `product_example` VALUES ('072f7fc5401c7ff41e7675b6d99bd0c4', 'caf01b29b08ff4567b3c4262916d22fc', '第一批哈密瓜', 15.00, 0, '03', '2022-05-28 00:00:00', '新疆', '', '2022-06-22 03:57:02', '1', NULL, NULL);
INSERT INTO `product_example` VALUES ('154fac8557041872f679b6e61b881996', '0bcb280fb1bbd5cb653eeefae6d573ab', '第二批铁观音', 10.00, 0, '01', '2022-05-28 00:00:00', '福建', '', '2022-06-21 19:23:28', '1', NULL, NULL);
INSERT INTO `product_example` VALUES ('408d7418b3ddb4cc1c8e2f6adea8ffb2', '417d8bb15344e3648b5e39f59d444452', '第二批南瓜', 12.00, 0, '04', '2022-05-23 00:00:00', '上海', '', '2022-06-21 17:31:26', '1', NULL, NULL);
INSERT INTO `product_example` VALUES ('7e937cb7b1e6fdef091d9fd7b3e1270b', '417d8bb15344e3648b5e39f59d444452', '第三批南瓜', 12.00, 0, '04', '2022-05-28 00:00:00', '上海', '', '2022-06-21 17:54:38', '1', NULL, NULL);
INSERT INTO `product_example` VALUES ('df2c94f7d2c7b72f19fe38175f277cce', '417d8bb15344e3648b5e39f59d444452', '第一批南瓜', 10.00, 98, '04', '2022-05-17 00:00:00', '上海市', '', '2022-06-21 17:08:58', '1', NULL, NULL);
INSERT INTO `product_example` VALUES ('ff4b02ab8a8b81980eee6b09ea9ec405', '0bcb280fb1bbd5cb653eeefae6d573ab', '第一批铁观音', 10.00, 0, '01', '2022-05-28 00:00:00', '福建', '', '2022-06-21 18:48:19', '1', NULL, NULL);

-- ----------------------------
-- Table structure for product_example_img
-- ----------------------------
DROP TABLE IF EXISTS `product_example_img`;
CREATE TABLE `product_example_img`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品实例id',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址路径',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品实例图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_example_img
-- ----------------------------
INSERT INTO `product_example_img` VALUES ('5e85a8fb76078e0bf9c2b226182dbc8a', '072f7fc5401c7ff41e7675b6d99bd0c4', '/016ab9af-dd6d-4924-92e3-10c6a257be23eff2b36c-ca8a-4ed2-9eb2-761954d8430b哈密瓜.jpg', '2022-06-22 23:24:28', '1');
INSERT INTO `product_example_img` VALUES ('5fc9f6e009949d678f8c37ae73b09d3f', 'df2c94f7d2c7b72f19fe38175f277cce', '/0c66ac3c-e7da-4a84-8249-74bfea1b22975c3d8372-ec36-4b83-bd28-f9cc9209921b9632c9a9-d51c-4f8b-bc98-365e86f3088e南瓜.jpg', '2022-06-21 20:46:09', '1');
INSERT INTO `product_example_img` VALUES ('d10a342c4eaa92e141cae4725ca17a72', '154fac8557041872f679b6e61b881996', '/fa3b5f97-bfb4-4586-9c70-2b074d9646a8d789b2fb-731d-4751-aa4f-b86c6d0db49d铁观音生子3.jpg', '2022-06-22 23:45:30', '1');
INSERT INTO `product_example_img` VALUES ('dad1830a457fadcaef70c7daccd548be', '072f7fc5401c7ff41e7675b6d99bd0c4', '/5910d037-6af6-40c2-8fd4-29ea56c551b353f4304d-9582-4b3e-9aa7-d9ae550345fe哈密瓜生子3.jpg', '2022-06-22 23:14:53', '1');
INSERT INTO `product_example_img` VALUES ('dcee373f941453344ad47ee0446e580b', '154fac8557041872f679b6e61b881996', '/28c2673c-7067-4c0c-b756-260bfa49c02b09346150-4b30-485d-a10f-274b61a376c9铁观音生子1.jpg', '2022-06-22 23:36:15', '1');
INSERT INTO `product_example_img` VALUES ('f0bc1813ab860a37205151839c4e6ab1', 'df2c94f7d2c7b72f19fe38175f277cce', '/a8eb1353-d540-4a7e-8eb7-87c9f6a5b9bc7fac3c56-c2a4-422d-a988-7159d7bfdb0e南瓜生长3.jpg', '2022-06-21 20:54:10', '1');
INSERT INTO `product_example_img` VALUES ('f4241a81c1b8f88036806c714f865abc', 'df2c94f7d2c7b72f19fe38175f277cce', '/0f834f54-21e7-4cb0-b80e-787dd464bc1d15bf9ab0-dc1f-4342-92e4-10d7eff52534南瓜生长4.jpg', '2022-06-21 20:58:34', '1');

-- ----------------------------
-- Table structure for spot_check
-- ----------------------------
DROP TABLE IF EXISTS `spot_check`;
CREATE TABLE `spot_check`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品实例id',
  `sensory` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '感官指标检查结果(接码表-check_situation',
  `pesticide` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '农药最大残留限量(接码表-check_situation',
  `metal` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重金属及有害物质限量检查结果(接码表-check_situation',
  `germs` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '病原菌检查情况(接码表-check_situation',
  `comprehensive` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '综合评价(接码表-check_situation',
  `check_unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查单位',
  `check_people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检查人',
  `check_date` date NULL DEFAULT NULL COMMENT '检查日期',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品抽查表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spot_check
-- ----------------------------
INSERT INTO `spot_check` VALUES ('93910340b0bbccd2df44aa16cc1166f2', 'df2c94f7d2c7b72f19fe38175f277cce', '01', '01', '01', '01', '01', 'A检查机构', 'A检查人', '2022-05-28', '', '2022-06-22 00:55:18', '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编码',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `indextable_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('2a1ba860fe2773aca6224e786e7114b3', '售卖单位', 'price_unit', '售卖单位', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('8b0962a65fc02b767edf99955b5ce0d8', '性别', 'sex', '性别', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('98949e7c26925862d76b3daab8ee7813', '传感器类型', 'sensor_type', '传感器类型', 0, '2022-05-12 10:19:32', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('b4dde3bdbf7da432143b737be6c93b7b', '是与否', 'yes_or_no', '是与否', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('b5ffb63dd5ea5cd8e906a1017d820b51', '保质期单位', 'shelf_life_unit', '保质期单位', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('b863af3389514744bf42add0d357df77', '土壤检测情况', 'soil_conditions', '土壤检测情况', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('c1430fee116642bc47f32fe039498dfc', '矿物质', 'minerals', '矿物质', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('c8314c2d818ada0a3abc304f4646d38b', '碳水化合物', 'carbohydrate', '碳水化合物', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('d06c3f6381fbce8d6fc61e8c9c5ca403', '产品类型', 'product_type', '产品类型', 0, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('e1cec6fc586da33822d619b2a6626689', '水质检测情况', 'water_quality', '水质检测情况', 0, '2022-05-11 19:19:35', '1', '2022-05-11 19:19:35', '1');
INSERT INTO `sys_dict` VALUES ('ebe92d51c8b23ec05f846c822c0d39a8', '检查情况', 'check_situation', '检查情况', 0, '2022-05-11 19:19:35', '1', '2022-05-11 19:19:35', '1');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项文本',
  `val` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典项值',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_dict_id`(`dict_id`) USING BTREE,
  INDEX `index_table_dict_status`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('0138b9979daac75c41e3885d212d68e6', '2a1ba860fe2773aca6224e786e7114b3', '株', '06', '一株', 6, 0);
INSERT INTO `sys_dict_item` VALUES ('08a6f5b24075a50f6cb928664211c4cf', 'c1430fee116642bc47f32fe039498dfc', '钙', '01', '钙', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('097d3a0c1a7791109dc2c50e8f00caf5', 'e1cec6fc586da33822d619b2a6626689', 'II类', '02', '主要适用于集中式生活饮用水地表水源地一级保护区、珍稀水生生物栖息地、鱼虾类产卵场、仔稚幼鱼的索饵场等', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('0a0ed1f56f598abe57522f4dbe8eccb4', 'e1cec6fc586da33822d619b2a6626689', 'IV类', '04', '主要适用于一般工业用水区及人体非直接触的娱乐用水区。', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('0e3da83a3e5acb08c1811411ada92ad8', 'e1cec6fc586da33822d619b2a6626689', 'III类', '03', '主要适用于集中式生活饮用水地表水源地二级保护区、鱼虾类越冬场、洄游通道、水产养殖区等渔业水域及游泳区。', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('221a753243d3d600c53400811c4d7ea8', '2a1ba860fe2773aca6224e786e7114b3', '千克', '02', '每千克', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('265f9c5d0754cb8d2ec6e64edee402b9', '2a1ba860fe2773aca6224e786e7114b3', '斤', '04', '斤', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('270793947c5c3ba664fe77fa9f9fd626', '8b0962a65fc02b767edf99955b5ce0d8', '女', '02', '女', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('287f9943c65ab3b07c959cd6c17ba59c', 'ebe92d51c8b23ec05f846c822c0d39a8', '优秀', '01', '优秀', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('3d87801c7737c444a1f9020f9aa3c6f7', 'c1430fee116642bc47f32fe039498dfc', '锌', '09', '锌', 9, 0);
INSERT INTO `sys_dict_item` VALUES ('43539de9775051b8f1247cfed823216a', 'ebe92d51c8b23ec05f846c822c0d39a8', '不合格', '01', '不合格', 1, 1);
INSERT INTO `sys_dict_item` VALUES ('4c932a9c85ffbc36cf92cfe72788113d', 'c1430fee116642bc47f32fe039498dfc', '钠', '05', '钠', 5, 0);
INSERT INTO `sys_dict_item` VALUES ('505db17b840d453ce0e35f38a75f0b6e', 'c1430fee116642bc47f32fe039498dfc', '铁', '08', '铁', 8, 0);
INSERT INTO `sys_dict_item` VALUES ('510fab5043252b560336b0d02402b1c4', 'e1cec6fc586da33822d619b2a6626689', 'I类', '01', '主要适用于源头水、国家自然保护区', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('52c5fb62d5e18d21d0f865593cfd8cb0', '2a1ba860fe2773aca6224e786e7114b3', '两', '05', '两', 5, 0);
INSERT INTO `sys_dict_item` VALUES ('595d6ff7e6f12ef1951bd76758827e7f', 'b5ffb63dd5ea5cd8e906a1017d820b51', '月', '02', '月', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('5a7ea793f63adbf6bf597ca525a6a2eb', '8b0962a65fc02b767edf99955b5ce0d8', '男', '01', '男', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('60187873469d459e34b19094ec817243', 'c1430fee116642bc47f32fe039498dfc', '钼', '12', '钼', 12, 0);
INSERT INTO `sys_dict_item` VALUES ('6740774fd24d667951aaaf926f0a29ad', 'c1430fee116642bc47f32fe039498dfc', '氯', '07', '氯', 7, 0);
INSERT INTO `sys_dict_item` VALUES ('70a894a8bc76844f7b9b3c9660a22876', 'b863af3389514744bf42add0d357df77', '三级标准', '03', '为保障农林业生产和植物正常生长的土壤临界值', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('744d7bc00c57b10a93f42f2aa874d445', '2a1ba860fe2773aca6224e786e7114b3', '株', '07', '一株', 7, 1);
INSERT INTO `sys_dict_item` VALUES ('83a05ee5455a8ff09147de6c87bda1cb', 'c1430fee116642bc47f32fe039498dfc', '铜', '10', '铜', 10, 0);
INSERT INTO `sys_dict_item` VALUES ('85950a6af5761372daecad88b0f51968', 'd06c3f6381fbce8d6fc61e8c9c5ca403', '水果类', '03', '水果类', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('87f1eb82f9d30c235b1e6eacf6a4340d', 'c1430fee116642bc47f32fe039498dfc', '钴', '11', '钴', 11, 0);
INSERT INTO `sys_dict_item` VALUES ('93c5c7fabea246e0e78bba6d62698d18', 'b863af3389514744bf42add0d357df77', '二级标准', '02', '为保障农业生产，维护人体健康的土壤限制值医`学教育网整理', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('9527fec51679786b1ac65607d213f148', '98949e7c26925862d76b3daab8ee7813', '温度传感器', 'TEMPERATURE', '温度传感器', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('9527fec51679786b1cc65607d213f148', '98949e7c26925862d76b3daab8ee7813', '湿度传感器', 'HUMITURE', '湿度传感器', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('9527fec51679786b1cc65607d213fd48', '98949e7c26925862d76b3daab8ee7813', '害虫识别传感器', 'PEST_RECOGNITION', '害虫识别传感器', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('967a28be76505b81610d3a1c2c957f30', '2a1ba860fe2773aca6224e786e7114b3', '克', '01', '每克', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('96a19499ba0ad57b997266d72184e742', 'ebe92d51c8b23ec05f846c822c0d39a8', '合格', '02', '合格', 1, 1);
INSERT INTO `sys_dict_item` VALUES ('97e39f4483ea644837fae6ca565d2d7c', 'd06c3f6381fbce8d6fc61e8c9c5ca403', '茶类', '04', '茶类', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('97e50256393249913dc28fd066fdab2a', 'c8314c2d818ada0a3abc304f4646d38b', '二糖', '02', '二糖', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('993bee0a6a8733d68fb17d38a0f4dbc6', 'c1430fee116642bc47f32fe039498dfc', '镁', '03', '镁', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('a386fb662c5514cfa1d8c88e58dfeead', 'c1430fee116642bc47f32fe039498dfc', '铬', '15', '铬', 15, 0);
INSERT INTO `sys_dict_item` VALUES ('a3c00e186b9d52231e730a8b07eaf1bf', 'c1430fee116642bc47f32fe039498dfc', '磷', '02', '磷', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('aa588029f5f045ddcfd3e63c51409df4', 'e1cec6fc586da33822d619b2a6626689', 'IV类以下', '06', 'IV类以下水质恶劣，不能作为饮用水源。', 6, 0);
INSERT INTO `sys_dict_item` VALUES ('afa7544945cff23f08eb3a86bddb85cf', '2a1ba860fe2773aca6224e786e7114b3', '吨', '03', '每吨', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('b1f9201100416421bf58da31d3eb3b02', 'c8314c2d818ada0a3abc304f4646d38b', '多糖', '04', '多糖', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('b2e2cbb0d2cdc98363d984ae3d64851f', 'ebe92d51c8b23ec05f846c822c0d39a8', '合格', '02', '合格', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('b5cc6af8ef4d83dbc93bd721b004bfa1', 'c1430fee116642bc47f32fe039498dfc', '钾', '04', '钾', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('c15d5135ced246508f69e0850eb676ba', 'b5ffb63dd5ea5cd8e906a1017d820b51', '年', '03', '年', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('c67a18e70688700ddb59e62ca88d6379', 'c1430fee116642bc47f32fe039498dfc', '硫', '06', '硫', 6, 0);
INSERT INTO `sys_dict_item` VALUES ('cd9917e25e555b6316b8f5d11f0ccc64', 'c1430fee116642bc47f32fe039498dfc', '碘', '14', '碘', 14, 0);
INSERT INTO `sys_dict_item` VALUES ('d1453a754ee8868d40a1c63aaeef590d', 'e1cec6fc586da33822d619b2a6626689', 'V类', '05', '主要适用于农业用水区及一般景观要求水域', 5, 0);
INSERT INTO `sys_dict_item` VALUES ('d15740fffe77fdeb700ea8f2617884c6', 'd06c3f6381fbce8d6fc61e8c9c5ca403', '禾谷类', '01', '谷类', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('d477b1c96cf2ea9368b67081d93d7ab6', 'c1430fee116642bc47f32fe039498dfc', '硒', '13', '硒', 13, 0);
INSERT INTO `sys_dict_item` VALUES ('de1f13dd0905b138477f5bd7198101de', 'ebe92d51c8b23ec05f846c822c0d39a8', '不合格', '04', '不合格', 4, 0);
INSERT INTO `sys_dict_item` VALUES ('e40aabc0c16a9a937af8780297072480', 'b5ffb63dd5ea5cd8e906a1017d820b51', '天', '01', '天', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('e7fa67975a1672b87867fb61d279bace', 'b4dde3bdbf7da432143b737be6c93b7b', '是', 'Y', '是', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('e834d17f2c38ce4cfb8a659ca643d16a', 'd06c3f6381fbce8d6fc61e8c9c5ca403', '蔬菜类', '02', '蔬菜类', 2, 0);
INSERT INTO `sys_dict_item` VALUES ('ed15fb4e3103a01e0f7d4ae57d7e35a6', 'b863af3389514744bf42add0d357df77', '一级标准', '01', '为保护区域自然生态，维持自然背景的土壤环境质量的限制值。', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('f004b15db38c8074d40fd7efb221f293', 'b4dde3bdbf7da432143b737be6c93b7b', '否', 'N', '否', 1, 0);
INSERT INTO `sys_dict_item` VALUES ('f269de8ceb9779ce2ea7945c1cce51a3', 'ebe92d51c8b23ec05f846c822c0d39a8', '差', '05', '差', 5, 0);
INSERT INTO `sys_dict_item` VALUES ('f679568c43b22472ce64ab987f8cbe73', 'ebe92d51c8b23ec05f846c822c0d39a8', '良好', '03', '良好', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('fa92f13568b2339ba0df40ef16419c58', 'c8314c2d818ada0a3abc304f4646d38b', '低聚糖', '03', '低聚糖', 3, 0);
INSERT INTO `sys_dict_item` VALUES ('fe7ba4c33ee60d7524a244c19cd57967', 'c8314c2d818ada0a3abc304f4646d38b', '单糖', '01', '单糖', 1, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级菜单',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标编码',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型：0：菜单：1：页面：2：按钮',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0d237ca74ad890c300a224902d1ae880', '1', '字典项管理', 'el-icon-picture', 'SYS_DICT', '/sys/dict/list.html', '0', 4);
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', 'el-icon-s-tools', 'SYS', '', '0', 1);
INSERT INTO `sys_menu` VALUES ('10', '1', '菜单管理', 'el-icon-s-grid', 'SYS_MENU', '/sys/menu/list.html', '1', 3);
INSERT INTO `sys_menu` VALUES ('11', '10', '菜单编辑', 'el-icon-edit', 'SYS_MENU_UPDATE', '', '2', 1);
INSERT INTO `sys_menu` VALUES ('12', '10', '菜单新增', 'el-icon-plus', 'SYS_MENU_ADD', '', '2', 2);
INSERT INTO `sys_menu` VALUES ('13', '10', '菜单删除', 'el-icon-delete', 'SYS_MENU_DEL', '', '2', 3);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'el-icon-user-solid', 'SYS_USER', '/sys/user/list.html', '1', 1);
INSERT INTO `sys_menu` VALUES ('3', '2', '用户编辑', 'el-icon-edit', 'SYS_USER_UPDATE', '', '2', 1);
INSERT INTO `sys_menu` VALUES ('3131b3aeb78f2c9f29009c23b1afe963', '2', '重置密码', 'el-icon-refresh-right', 'SYS_USER_RESET_PASSWORD', '', '2', 4);
INSERT INTO `sys_menu` VALUES ('321fa480a504a7a34d9d88126a22bbcf', 'f876a0ecab0261c796617e13e5762f6e', '产品统计', 'el-icon-s-data', 'PRODUCT_GRAPH', '/bus/productGraph/graph.html', '1', 4);
INSERT INTO `sys_menu` VALUES ('4', '2', '用户新增', 'el-icon-plus', 'SYS_USER_ADD', '', '2', 2);
INSERT INTO `sys_menu` VALUES ('4c6e74acf214c843a0526bbc566bedd8', '0d237ca74ad890c300a224902d1ae880', '字典新增', 'el-icon-plus', 'SYS_DICT_ADD', '', '2', 1);
INSERT INTO `sys_menu` VALUES ('5', '2', '用户删除', 'el-icon-delete', 'SYS_USER_DEL', '', '2', 3);
INSERT INTO `sys_menu` VALUES ('5e2db6fa3eebbad27de5eaf97e7430ba', '0d237ca74ad890c300a224902d1ae880', '字典项编辑', 'el-icon-edit', 'SYS_DICT_ITEM_UPDATE', '', '2', 5);
INSERT INTO `sys_menu` VALUES ('6', '1', '角色管理', 'el-icon-s-custom', 'SYS_ROLE', '/sys/role/list.html', '1', 2);
INSERT INTO `sys_menu` VALUES ('7', '6', '角色编辑', 'el-icon-edit', 'SYS_ROLE_UPDATE', '', '2', 1);
INSERT INTO `sys_menu` VALUES ('7f6368cdcf8dae04febe1c01d3a7651b', '0d237ca74ad890c300a224902d1ae880', '字典项新增', 'el-icon-plus', 'SYS_DICT_ITEM_ADD', '', '2', 4);
INSERT INTO `sys_menu` VALUES ('7f8b0208c72d9a5cfb7abb0e1ab0f230', 'f876a0ecab0261c796617e13e5762f6e', '上架产品', 'el-icon-more', 'PRODUCT_EXAMPLE', '/bus/productExample/list.html', '1', 2);
INSERT INTO `sys_menu` VALUES ('8', '6', '角色新增', 'el-icon-plus', 'SYS_ROLE_ADD', '', '2', 2);
INSERT INTO `sys_menu` VALUES ('8183c0c5d3e344391db8ac90a23333de', '2', '设置角色', 'el-icon-s-tools', 'SYS_USER_SET_ROLE', '', '2', 5);
INSERT INTO `sys_menu` VALUES ('8ca5d9ba569f3b542480cab5dc79de8c', 'f9c50f5702b7ca4482ea7d179aa48e91', '订单列表', 'el-icon-more', 'ORDER_LIST', '/bus/order/list.html', '1', 1);
INSERT INTO `sys_menu` VALUES ('9', '6', '角色删除', 'el-icon-delete', 'SYS_ROLE_DEL', '', '2', 3);
INSERT INTO `sys_menu` VALUES ('aaba448cc76a70718ef4ea0417503d8c', '0d237ca74ad890c300a224902d1ae880', '字典删除', 'el-icon-delete', 'SYS_DICT_DEL', '', '2', 3);
INSERT INTO `sys_menu` VALUES ('d2bcf381ad24ff724c76f8afef7bf3cb', '0d237ca74ad890c300a224902d1ae880', '字典项删除', 'el-icon-delete', 'SYS_DICT_ITEM_DEL', '', '2', 6);
INSERT INTO `sys_menu` VALUES ('db63aed639ea9debee584aa22858dc0e', 'f876a0ecab0261c796617e13e5762f6e', '产品抽查', 'el-icon-warning', 'SPOT_CHECK', '/bus/spot_check/index.html', '1', 3);
INSERT INTO `sys_menu` VALUES ('ed7eed436e6c5a1e86c0df021096f8a8', '0d237ca74ad890c300a224902d1ae880', '字典编辑', 'el-icon-edit', 'SYS_DICT_UPDATE', '', '2', 2);
INSERT INTO `sys_menu` VALUES ('f876a0ecab0261c796617e13e5762f6e', '0', '产品管理', 'el-icon-menu', 'PRODUCT', '/bus/product', '1', 2);
INSERT INTO `sys_menu` VALUES ('f9c50f5702b7ca4482ea7d179aa48e91', '0', '订单管理', 'el-icon-suitcase', 'ORDER_FORM', '/bus/order', '0', 3);
INSERT INTO `sys_menu` VALUES ('ff4e712a56cc27b70dfd94cfcb6961a1', 'f876a0ecab0261c796617e13e5762f6e', '传感器列表', 'el-icon-view', 'SENSOR_LIST', '/bus/sensor/list.html', '0', 5);
INSERT INTO `sys_menu` VALUES ('ff4e712a56cc27b70dfd94cfcb6961aa', 'f876a0ecab0261c796617e13e5762f6e', '产品列表', 'el-icon-folder', 'PRODUCT_LIST', '/bus/product/list.html', '0', 1);

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限id',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('00c7bf271cf04ed74e2a8c4a6f3f7a1f', '1', '4');
INSERT INTO `sys_menu_role` VALUES ('08979809946a9e47db8e2cc974934654', '11d0e5756cf1e8805e1942eef221a03e', '1');
INSERT INTO `sys_menu_role` VALUES ('0a6815c14e845f38d16a64ea7d902c8d', '1', '6');
INSERT INTO `sys_menu_role` VALUES ('10450253261f5a32c2fa232647a19217', '1', '7');
INSERT INTO `sys_menu_role` VALUES ('148e0896ad1a3fde5b0c29797d96d82c', 'cc3a9cc6b88c8c164a85191e16d4b384', 'ff4e712a56cc27b70dfd94cfcb6961aa');
INSERT INTO `sys_menu_role` VALUES ('16c96f0054ba20e2ae97f7624488e396', '1', 'f9c50f5702b7ca4482ea7d179aa48e91');
INSERT INTO `sys_menu_role` VALUES ('23e835219d8fc01f20c1d857e2506859', '1', '3');
INSERT INTO `sys_menu_role` VALUES ('2c909e7601255ad85aca129e34139cf1', '1', 'db63aed639ea9debee584aa22858dc0e');
INSERT INTO `sys_menu_role` VALUES ('2d56be4711485462ba5037483f635234', '11d0e5756cf1e8805e1942eef221a03e', '4');
INSERT INTO `sys_menu_role` VALUES ('312e7f0650f183393626d8c26aa65841', '1', 'aaba448cc76a70718ef4ea0417503d8c');
INSERT INTO `sys_menu_role` VALUES ('4027e17e1e64635c176f4d4c507cac43', '11d0e5756cf1e8805e1942eef221a03e', '3');
INSERT INTO `sys_menu_role` VALUES ('4049153f0f44d19141c8ad55817791fe', '1', 'd2bcf381ad24ff724c76f8afef7bf3cb');
INSERT INTO `sys_menu_role` VALUES ('437adbe3f06de75df4a6e28ffd37c576', '1', '0d237ca74ad890c300a224902d1ae880');
INSERT INTO `sys_menu_role` VALUES ('58e3e63a1068cf14a01de3e8e6832e1c', '1', '7f8b0208c72d9a5cfb7abb0e1ab0f230');
INSERT INTO `sys_menu_role` VALUES ('5966cccc693e3f900aad7c4f7686467f', '1', '8ca5d9ba569f3b542480cab5dc79de8c');
INSERT INTO `sys_menu_role` VALUES ('5bcdf10a2213b136b210ce7865623ba3', '11d0e5756cf1e8805e1942eef221a03e', '2');
INSERT INTO `sys_menu_role` VALUES ('6d51cf522e9259c3aeb4db3ab40fcab5', '1', '5');
INSERT INTO `sys_menu_role` VALUES ('751119811c32c0402846346efab8f949', 'cc3a9cc6b88c8c164a85191e16d4b384', 'f876a0ecab0261c796617e13e5762f6e');
INSERT INTO `sys_menu_role` VALUES ('7517e750e77c2c64050c465bb47d0c3e', '1', '4c6e74acf214c843a0526bbc566bedd8');
INSERT INTO `sys_menu_role` VALUES ('842104d8327e5e4c5fc99882094dcbe5', '1', '5e2db6fa3eebbad27de5eaf97e7430ba');
INSERT INTO `sys_menu_role` VALUES ('86d5ed7183d6739f5105f75d2b9d3ed1', 'cc3a9cc6b88c8c164a85191e16d4b384', '321fa480a504a7a34d9d88126a22bbcf');
INSERT INTO `sys_menu_role` VALUES ('94f80e0055eb2ca688ae2758c7f3e219', '1', '13');
INSERT INTO `sys_menu_role` VALUES ('996bdd778b815649cf78402881f77531', '1', '8');
INSERT INTO `sys_menu_role` VALUES ('a54ba181b304075564327e52db8c33ff', '4925c2037574a6af44ab20e7a5c179e3', 'f876a0ecab0261c796617e13e5762f6e');
INSERT INTO `sys_menu_role` VALUES ('ab647c0898d703a23a68935f1b34302f', '1', '321fa480a504a7a34d9d88126a22bbcf');
INSERT INTO `sys_menu_role` VALUES ('ad80542d57c020bec647ae0697403696', '1', '10');
INSERT INTO `sys_menu_role` VALUES ('b40ef95383cd598010b0235df8233851', '1', '12');
INSERT INTO `sys_menu_role` VALUES ('b689eda870a3b5c5298b667379a61bbe', '4925c2037574a6af44ab20e7a5c179e3', 'db63aed639ea9debee584aa22858dc0e');
INSERT INTO `sys_menu_role` VALUES ('b8173c13b7c2e6f8e1aab564ba6a97f4', '1', '11');
INSERT INTO `sys_menu_role` VALUES ('ba6a5d6769262c9cc512609b6949755b', '1', '8183c0c5d3e344391db8ac90a23333de');
INSERT INTO `sys_menu_role` VALUES ('bc62ca9f0b6c8e12aa22d206f651ff29', '1', 'f876a0ecab0261c796617e13e5762f6e');
INSERT INTO `sys_menu_role` VALUES ('bfdc3b52e4033e4c080a13625b76a841', 'cc3a9cc6b88c8c164a85191e16d4b384', '7f8b0208c72d9a5cfb7abb0e1ab0f230');
INSERT INTO `sys_menu_role` VALUES ('c1a6b4ead40ab61b2a90def446eb3533', '1', '3131b3aeb78f2c9f29009c23b1afe963');
INSERT INTO `sys_menu_role` VALUES ('c8c02a69f3f95be631d3cd949b4d5002', '1', '7f6368cdcf8dae04febe1c01d3a7651b');
INSERT INTO `sys_menu_role` VALUES ('cd0061e4427ac2460b5bc27bedb51621', '1', 'ed7eed436e6c5a1e86c0df021096f8a8');
INSERT INTO `sys_menu_role` VALUES ('cf7cfc520e97899f59a5b9fb0b3da1f0', '1', 'ff4e712a56cc27b70dfd94cfcb6961aa');
INSERT INTO `sys_menu_role` VALUES ('d318828b2e695bb0312486c9a6a68e81', '1', 'ff4e712a56cc27b70dfd94cfcb6961a1');
INSERT INTO `sys_menu_role` VALUES ('f7d63e66ad8a0f7d31c87abfe49265a4', '1', '2');
INSERT INTO `sys_menu_role` VALUES ('fa3ebf653cd7020d76e52ea455813eb9', '1', '9');
INSERT INTO `sys_menu_role` VALUES ('fd07cc56ad60558ff9818d9f0820e143', '1', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色表主键',
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '管理员', '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('2', 'USER', '普通用户', '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('4925c2037574a6af44ab20e7a5c179e3', 'REGULATORS', '监管部门', '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_role` VALUES ('cc3a9cc6b88c8c164a85191e16d4b384', 'STAFF', '员工', '2022-05-11 19:19:35', '1', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别-接码表sex',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$MuGOG5gCNZUeohGu1AtYreoYaBRcOr1ZstfS5XHsvAeFuJt6hXe1.', '管理员', '13812341111', '01', '2022-05-11 19:19:35', NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('1c3a5eb4ccfe3d636743ae56e2286f3b', '系统', '$2a$10$M99mzavXhA.N8Fj4QmMrWe03IhijU5KQRwJc.KC24tcOM4KR3LU.a', '系统', '0', '01', '2022-05-16 14:10:10', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES ('2acb4d81cb70d0f201dd30e4658dfe35', 'common_user', '$2a$10$c4PV2SASCRL3JbihcXCvkODu1V2TslbBnA36.wOCmvkmBkSJ69c66', '普通用户', '13812341114', '01', '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES ('acb24168705db80ba11a1f25206d2818', 'staff', '$2a$10$kIid83qybp2H4yPQvspsc.Q.wCTyUHu2/.N/V7hy5z/XOUlkiReou', '员工', '13812341113', '01', '2022-05-11 20:57:57', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES ('e3639a420dadeef04181bceb46a6d895', 'regulator', '$2a$10$rS6VKVb1h5AViLmFGMb/PuOFUxORg8QowRxGnHJ.JMyrDlF4pGv46', '监管部门', '13812341112', '01', '2022-05-11 20:56:42', '1', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色表',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('11f0f1b77f5696cdb73ca4b14ce954bb', 'cc3a9cc6b88c8c164a85191e16d4b384', 'acb24168705db80ba11a1f25206d2818');
INSERT INTO `sys_user_role` VALUES ('3bfb76fcb5144783c7fb3a787142c1ae', '4925c2037574a6af44ab20e7a5c179e3', 'e3639a420dadeef04181bceb46a6d895');
INSERT INTO `sys_user_role` VALUES ('5563757ced0d56ed847c36e4055dbc30', '2', 'cebd85f887213b24ff2c14c437760c75');
INSERT INTO `sys_user_role` VALUES ('6facf3980e988b6bbf20a6befb3d86f1', '2', 'fafec32d8d8ea20a7c0f94f45c0169cf');
INSERT INTO `sys_user_role` VALUES ('7c2282f8a156ab4e1db1996c4a40056d', '1', '1');
INSERT INTO `sys_user_role` VALUES ('91f3c4084f6f62abdcf42640c97b0e3b', '3', 'cebd85f887213b24ff2c14c437760c75');
INSERT INTO `sys_user_role` VALUES ('af5cb0492e9e04cbb82c19def9543894', '2', '2acb4d81cb70d0f201dd30e4658dfe35');
INSERT INTO `sys_user_role` VALUES ('bef1a753970e46b9bc3df6a7be01b5ee', '1', '1c3a5eb4ccfe3d636743ae56e2286f3b');
INSERT INTO `sys_user_role` VALUES ('ce7978e7323e40c5d3b48e5851b1ca85', '2', 'a43cfa3524894ce86ebde166338b6a26');

SET FOREIGN_KEY_CHECKS = 1;
