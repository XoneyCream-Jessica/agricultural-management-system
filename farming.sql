/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : farming

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 13/05/2022 23:14:03
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
  `pe_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品实例id',
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
  `collect_date` date NULL DEFAULT NULL COMMENT '采集时间',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '生长环境记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of growing_environment
-- ----------------------------
INSERT INTO `growing_environment` VALUES ('21c6e5ee97fe91318bd0da05df30af52', 'c66bb6a433d6253693022c5bcdee68c4', 25.00, 18.00, 30.00, '20:37:54', 'Y', '不知名农药', 35.00, 'N', '01', '01', '2021-06-01', '2021-06-01 21:51:47', '1', '2021-06-01 22:01:01', '1', 0);
INSERT INTO `growing_environment` VALUES ('35690bc9133308a68dbf9eeb2485e6a3', '1311ecde397b7ebdf80b15bdf6ad69b7', 20.00, 12.00, 10.00, '19:18:18', 'Y', '不知名农药', 25.00, 'Y', '01', '01', '2021-06-05', '2021-06-06 19:18:37', '1', NULL, NULL, 0);
INSERT INTO `growing_environment` VALUES ('79e7c5789411600a1ae06dc21bd98c20', 'c66bb6a433d6253693022c5bcdee68c4', 122.00, 13.00, 12.00, '18:05:12', 'N', '', NULL, 'N', '02', '02', '2021-06-02', '2021-06-01 22:05:25', '1', '2021-06-01 22:12:38', '1', 0);
INSERT INTO `growing_environment` VALUES ('f1364f9953ac8ff8a1d8ab9489fc6a6b', '1311ecde397b7ebdf80b15bdf6ad69b7', 30.00, 18.00, 30.00, '18:18:44', 'N', '', NULL, 'Y', '02', '02', '2021-06-04', '2021-06-06 19:18:55', '1', NULL, NULL, 0);

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
INSERT INTO `nutrition` VALUES ('0139e7b99ab876583d98cdedee212103', '1311ecde397b7ebdf80b15bdf6ad69b7', 20.00, '01,02', '02,03,04,05', 25.00, 10.00, '2022-05-11 19:19:35', '1', NULL, NULL, 0);
INSERT INTO `nutrition` VALUES ('52c1889d80f2eb0d96c83aab7e55d584', 'c66bb6a433d6253693022c5bcdee68c4', 11.00, '01,02,03', '01,03,04', 22.00, 33.00, '2022-05-11 19:19:35', '1', '2021-06-01 23:02:33', '1', 0);
INSERT INTO `nutrition` VALUES ('d6bbb7f116fe4a86e239b10f9ef06a38', '1311ecde397b7ebdf80b15bdf6ad69b7', 25.00, '03,02', '03', 11.00, 111.00, '2022-05-11 19:19:35', '1', NULL, NULL, 0);

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
INSERT INTO `order_form` VALUES ('2aa688def64f3b8a77f63fb56f07084b', 1200.00, '上海市闵行区', '13611111112', '张三', '01', NULL, '2022-05-11 22:25:14', '1', NULL, NULL, 0);
INSERT INTO `order_form` VALUES ('a55361f03f83e76e6b7151e89f834f51', 1275.00, '上海市闵行区', '135477988777', '张三', '03', '测试', '2022-05-11 21:36:57', '1', NULL, NULL, 0);
INSERT INTO `order_form` VALUES ('fd914c7503a82d5208e3446f6a734e22', 3110.00, '上海市闵行区', '135477898777', '王五', '03', NULL, '2022-05-11 21:36:57', '1', NULL, NULL, 0);

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
INSERT INTO `order_info` VALUES ('2c1906d4e1d31465abb46a2699d5c82d', 'fd914c7503a82d5208e3446f6a734e22', 'cf54d5551307876454d5b74b79de2ed9', 7, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('2f64e408c9b6d4b7a8010e15759b0e38', 'a55361f03f83e76e6b7151e89f834f51', 'cf54d5551307876454d5b74b79de2ed9', 1, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('337fface219e9b430bd7c95cda8799e2', 'a55361f03f83e76e6b7151e89f834f51', 'cb88197e1de576ea66ea2a408bf6dde4', 1, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('71c359c2e210fe7323f52e45b5665ddc', 'fd914c7503a82d5208e3446f6a734e22', 'd2d0a60bffdd9aaa159ee24392fa97f8', 1, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('7fd745fdde430415f1e5a6e07ac121e4', 'a55361f03f83e76e6b7151e89f834f51', 'c66bb6a433d6253693022c5bcdee68c4', 1, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('84effcbe5f259266d5ccadd5a826ecfc', 'fd914c7503a82d5208e3446f6a734e22', '1311ecde397b7ebdf80b15bdf6ad69b7', 2, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('9128a34166d5b619c3602b73ca5c7f47', 'fd914c7503a82d5208e3446f6a734e22', 'cb88197e1de576ea66ea2a408bf6dde4', 2, '2022-05-11 19:19:35', '1', NULL, NULL);
INSERT INTO `order_info` VALUES ('a039991856dfe631f2b8c6ce83e2afa8', '2aa688def64f3b8a77f63fb56f07084b', 'cb88197e1de576ea66ea2a408bf6dde4', 1, '2022-05-11 22:25:14', '1', NULL, NULL);

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
INSERT INTO `persistent_logins` VALUES ('admin', 'hiJfpAbQRpLNURIOdq+QOg==', 'W/gF8K4VbEXUmS+UgFknJw==', '2022-05-13 14:09:00');
INSERT INTO `persistent_logins` VALUES ('test', 'hljK6T2OZ3gpO+ISBWA8Xw==', 'bii8ye3YhNaBtQRB7FYhnQ==', '2022-05-11 19:19:35');
INSERT INTO `persistent_logins` VALUES ('admin', 'MlX58rztWrAcfMo0HKHcjg==', 'nkECC3pa9QgLDSW152Iteg==', '2022-05-13 15:06:58');
INSERT INTO `persistent_logins` VALUES ('test', 'mMKYrDNdpuxODtpZA5ga0Q==', 'Z2A0luQtdtG7zROI36X+Yg==', '2022-05-11 19:19:35');
INSERT INTO `persistent_logins` VALUES ('admin', 'opq6HKPvu9/n3vrXpQKHmA==', 'RPBrSmPhPDHOQx0JXXSwXw==', '2022-05-13 14:22:11');
INSERT INTO `persistent_logins` VALUES ('admin', 'ukz99kAmoZ0TbZKATQx4pA==', 'BJbIOPkH0wUCUsGyvdcS9A==', '2022-05-13 13:40:34');

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
INSERT INTO `product` VALUES ('3e5782f2b08956f445430ee097b12f7a', '/9632c9a9-d51c-4f8b-bc98-365e86f3088e南瓜.jpg', '南瓜', '02', 21.00, 6, '02', 0, '2022-05-11 19:19:35', '1', NULL, NULL, -1);
INSERT INTO `product` VALUES ('a380f5fe4be547dfe39f172fbfb5367f', '/f9d88586-3aee-440b-982f-d1531151cfaa衣服.jpg', '测试产品', '03', 1.00, 1, '03', 1, '2022-05-11 19:19:35', '1', NULL, NULL, -1);
INSERT INTO `product` VALUES ('a7fe2222543407508f8afd503aa864fe', '/eed4b7d1-46b7-485f-8058-adde5d027c16铁观音.jpg', '铁观音', '04', 25.00, 30, '01', 0, '2022-05-11 19:19:35', '1', NULL, NULL, -1);
INSERT INTO `product` VALUES ('cad25f8bd1f18f868dd9de3502c06858', NULL, '水稻', '01', 20.00, 30, '01', 1, '2022-05-11 19:19:35', '1', NULL, NULL, -1);
INSERT INTO `product` VALUES ('d087300857fcce769250bf786ed357a3', '/eff2b36c-ca8a-4ed2-9eb2-761954d8430b哈密瓜.jpg', '哈密瓜', '03', 25.00, 10, '01', 0, '2022-05-11 19:19:35', '1', NULL, NULL, -1);
INSERT INTO `product` VALUES ('e2e970ce91e289d1f70bff8150ea938f', '/060d0070-cd55-46a1-977e-e8e406d043dd苹果树.jpg', '苹果树', '03', 200.00, 10, '01', 0, '2022-05-11 19:19:35', '1', '2022-05-11 19:19:35', '1', -1);
INSERT INTO `product` VALUES ('fae58e8be2de43e9e1debfee5e250925', '/0e9092c3-6d71-429f-ac61-44b503d452dd谷子.jpg', '谷子', '01', 20.00, 30, '01', 0, '2022-05-11 19:19:35', '1', NULL, NULL, -1);

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
INSERT INTO `product_example` VALUES ('1311ecde397b7ebdf80b15bdf6ad69b7', 'd087300857fcce769250bf786ed357a3', '品质很好的哈密瓜', 30.00, 48, '02', '2022-05-10 00:00:00', '上海市', '', '2022-05-10 19:16:49', '1', '2022-05-11 21:28:29', '1');
INSERT INTO `product_example` VALUES ('c66bb6a433d6253693022c5bcdee68c4', 'e2e970ce91e289d1f70bff8150ea938f', '优质的苹果树', 25.00, 150, '04', '2022-05-18 00:00:00', '上海市', '', '2022-05-10 19:16:49', '1', '2021-06-04 17:15:00', '1');
INSERT INTO `product_example` VALUES ('cb88197e1de576ea66ea2a408bf6dde4', '3e5782f2b08956f445430ee097b12f7a', '一般的南瓜', 1200.00, 6, '03', '2022-06-17 00:00:00', '上海市', '', '2022-05-10 19:16:49', '1', '2021-06-04 17:15:28', '1');
INSERT INTO `product_example` VALUES ('cf54d5551307876454d5b74b79de2ed9', '3e5782f2b08956f445430ee097b12f7a', '非常好的南瓜', 50.00, 200, '02', '2022-06-01 16:15:05', '上海市', '', '2022-05-10 19:16:49', '1', '2021-06-04 17:15:08', '1');
INSERT INTO `product_example` VALUES ('d2d0a60bffdd9aaa159ee24392fa97f8', 'a7fe2222543407508f8afd503aa864fe', '品质很好的铁观音', 300.00, 29, '06', '2022-05-10 12:18:59', '武夷山', '', '2022-05-10 19:16:49', '1', '2022-05-11 21:28:40', '1');

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
INSERT INTO `product_example_img` VALUES ('1da4adb50c22bddeb6156c25d8fa84da', 'cb88197e1de576ea66ea2a408bf6dde4', '/15bf9ab0-dc1f-4342-92e4-10d7eff52534南瓜生长4.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('30ce2076eb9724a201fa117ca3f3447d', 'c66bb6a433d6253693022c5bcdee68c4', '/ad7cf160-42bd-4b99-a161-268cc3f45efa苹果生长4.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('33abbcf144798b186eabc2504f82df53', 'd2d0a60bffdd9aaa159ee24392fa97f8', '/4709f890-16bf-408a-9aa8-01a5279d1ed0铁观音生子2.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('48f5b9c544b178d22161d412c19e3809', 'cb88197e1de576ea66ea2a408bf6dde4', '/b17394bc-38a0-4074-b816-bd84371bec23南瓜生长3.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('7da8d75151360e4220e1d164139d9f44', 'c66bb6a433d6253693022c5bcdee68c4', '/428df58d-558c-46e5-bbb7-b0153afe7656苹果生长1.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('a52018e09405e3f7cce03aa69ccfa447', 'd2d0a60bffdd9aaa159ee24392fa97f8', '/09346150-4b30-485d-a10f-274b61a376c9铁观音生子1.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('a84c4462c7648b66eb172c462dcf07f7', 'c66bb6a433d6253693022c5bcdee68c4', '/532c856d-8088-4f9a-98f2-303fc25656e1苹果生长2.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('aa086cbf4f754b463cd9e88c826df25a', 'c66bb6a433d6253693022c5bcdee68c4', '/530740ee-117b-41a6-9967-26a2e5a6c3a6苹果树.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('b5ad002b3373c6c6f954adbc1f51313a', '1311ecde397b7ebdf80b15bdf6ad69b7', '/769b5a25-abb6-439c-869f-a643fd84e01f哈密瓜生子1.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('cb0f7c6df7d3e39cc9857f244daca16f', 'cf54d5551307876454d5b74b79de2ed9', '/cd5f09ef-2fa0-41f8-abf4-19ccfbfe7096南瓜生长3.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('cedac8d9646e7e9dfa1503fd71b052bc', '1311ecde397b7ebdf80b15bdf6ad69b7', '/132f60eb-53c3-4ecd-9d08-86cc8d9780d4哈密瓜生子2.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('d0ab3f040e3667cc84648ec2da2766c9', 'cf54d5551307876454d5b74b79de2ed9', '/db649fdc-2a65-4a10-b451-b7423069058b南瓜生长4.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('d6a794ecafc99822e5dc21bc0eb5c689', '1311ecde397b7ebdf80b15bdf6ad69b7', '/53f4304d-9582-4b3e-9aa7-d9ae550345fe哈密瓜生子3.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('d8ec73b1bad8d0a9d5c113bf0a9f68ba', 'd2d0a60bffdd9aaa159ee24392fa97f8', '/d789b2fb-731d-4751-aa4f-b86c6d0db49d铁观音生子3.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('db4fc5d6d8c4022beee39c6d79f0c67d', 'c66bb6a433d6253693022c5bcdee68c4', '/2dfe0040-d146-4efd-86b2-0c7b7b8147b7苹果生长3.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('eb70a52f228be75a511b63669803be2a', 'cf54d5551307876454d5b74b79de2ed9', '/cac4f1ce-e3f7-40ab-b9e5-a046ec7d3e31南瓜生长1.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('ec16c8e06fb5c46ebf51cb7cb6a84f90', 'd2d0a60bffdd9aaa159ee24392fa97f8', '/ccd87e76-92e0-46c0-9af8-7dd0306ea428铁观音.jpg', '2022-05-11 19:19:35', '1');
INSERT INTO `product_example_img` VALUES ('ec6c9e848bbaab7444c0445194339639', 'cf54d5551307876454d5b74b79de2ed9', '/00a57437-5c64-4a6b-8510-c895ffbf5f9b南瓜生长2.jpg', '2022-05-11 19:19:35', '1');

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
INSERT INTO `spot_check` VALUES ('1d9b18cd68948668c43fc0f7df9903b8', 'c66bb6a433d6253693022c5bcdee68c4', '01', '01', '02', '02', '03', '测试单位', '监管部门工作人员', '2021-06-01', '', '2022-05-11 19:19:35', '1', '2022-05-12 20:44:00', '1', 0);
INSERT INTO `spot_check` VALUES ('33973a836d7274a7b299497053ac0cf2', 'c66bb6a433d6253693022c5bcdee68c4', '01', '02', '01', '01', '01', '监管部门', '监管部门工作人员', '2021-06-02', '', '2022-05-11 19:19:35', '1', '2022-05-12 20:44:16', '1', 0);
INSERT INTO `spot_check` VALUES ('3bff77150ad4d6ca280dfee63bf16392', '1311ecde397b7ebdf80b15bdf6ad69b7', '01', '01', '01', '01', '01', '测试单位', '测试人', '2021-06-06', '', '2022-05-11 19:19:35', '1', NULL, NULL, 0);
INSERT INTO `spot_check` VALUES ('8cbbbadf673509a49eaeba8463b2bced', 'c66bb6a433d6253693022c5bcdee68c4', '01', '02', '01', '02', '02', '测试单位', '监管部门工作人员', '2021-06-02', '', '2022-05-11 19:19:35', '1', '2022-05-12 20:44:07', '1', 0);

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
INSERT INTO `sys_dict_item` VALUES ('9527fec51679786b1cc65607d213fd48', '98949e7c26925862d76b3daab8ee7813', '害虫识别传感器', 'PestRecognition', '害虫识别传感器', 1, 0);
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
INSERT INTO `sys_user_role` VALUES ('ce7978e7323e40c5d3b48e5851b1ca85', '2', 'a43cfa3524894ce86ebde166338b6a26');

SET FOREIGN_KEY_CHECKS = 1;
