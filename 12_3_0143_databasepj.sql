/*
 Navicat Premium Data Transfer

 Source Server         : Huangzisu
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : database_design_pj

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 03/12/2023 01:42:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collection_commodity
-- ----------------------------
DROP TABLE IF EXISTS `collection_commodity`;
CREATE TABLE `collection_commodity`  (
  `u_id` int(11) NOT NULL,
  `c_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `cid4`(`c_id` ASC) USING BTREE,
  CONSTRAINT `cid4` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uid3` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collection_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for collection_floorprice
-- ----------------------------
DROP TABLE IF EXISTS `collection_floorprice`;
CREATE TABLE `collection_floorprice`  (
  `u_id` int(11) NOT NULL,
  `floorPrice` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  CONSTRAINT `uid2` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collection_floorprice
-- ----------------------------

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `produceDate` date NULL DEFAULT NULL,
  `s_id` int(11) NULL DEFAULT NULL,
  `p_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sid8`(`s_id` ASC) USING BTREE,
  INDEX `pid8`(`p_id` ASC) USING BTREE,
  CONSTRAINT `pid8` FOREIGN KEY (`p_id`) REFERENCES `platform` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sid8` FOREIGN KEY (`s_id`) REFERENCES `shop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10000005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (10000001, '蒙牛优酸乳', '食品', '牛牛牛牛牛', '2023-11-29', 10000001, 10000001);
INSERT INTO `commodity` VALUES (10000004, 'update_test_commodity', 'update_test_category', 'update_test_description', '2020-12-02', 10000001, 10000001);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_id` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`m_id`, `u_id`) USING BTREE,
  INDEX `uid1`(`u_id` ASC) USING BTREE,
  CONSTRAINT `uid1` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (10000001, '你的消息内容', 10000001, '2023-12-02 18:25:49');
INSERT INTO `message` VALUES (10000002, '你的消息内容', 10000002, '2023-12-02 18:26:19');
INSERT INTO `message` VALUES (10000003, '你的消息内容', 10000003, '2023-12-02 18:26:28');
INSERT INTO `message` VALUES (10000004, 'test message2', 10000001, '2023-12-02 18:26:47');

-- ----------------------------
-- Table structure for platform
-- ----------------------------
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform`  (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of platform
-- ----------------------------
INSERT INTO `platform` VALUES (10000001, '天猫');

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price`  (
  `c_id` int(11) NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`c_id`, `time`) USING BTREE,
  CONSTRAINT `cid3` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES (10000001, 200.00, '2023-11-14 21:40:07');
INSERT INTO `price` VALUES (10000001, 100.00, '2023-11-29 15:16:36');
INSERT INTO `price` VALUES (10000001, 500.00, '2023-12-01 20:16:31');
INSERT INTO `price` VALUES (10000001, 500.00, '2023-12-01 20:47:15');
INSERT INTO `price` VALUES (10000001, 1500.00, '2023-12-03 01:36:47');
INSERT INTO `price` VALUES (10000004, 1500.00, '2023-12-03 01:33:58');
INSERT INTO `price` VALUES (10000004, 15.00, '2023-12-03 01:36:09');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `owner_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oid`(`owner_id` ASC) USING BTREE,
  CONSTRAINT `oid` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (10000001, '李四的商店', '复旦大学', 10000002);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `phoneNumber` int(11) NULL DEFAULT NULL,
  `role` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000001, '张三', 18, 11111111, 0, '123456');
INSERT INTO `user` VALUES (10000002, '李四', 18, 22222222, 1, '234567');
INSERT INTO `user` VALUES (10000003, '王五', 18, 33333333, 2, '345678');

SET FOREIGN_KEY_CHECKS = 1;
