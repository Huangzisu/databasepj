/*
 Navicat Premium Data Transfer

 Source Server         : lab1
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : databasepj

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 29/11/2023 22:08:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collection_commodity
-- ----------------------------
DROP TABLE IF EXISTS `collection_commodity`;
CREATE TABLE `collection_commodity`  (
  `u_id` int(0) NOT NULL,
  `c_id` int(0) DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `cid4`(`c_id`) USING BTREE,
  CONSTRAINT `cid4` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uid3` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection_floorprice
-- ----------------------------
DROP TABLE IF EXISTS `collection_floorprice`;
CREATE TABLE `collection_floorprice`  (
  `u_id` int(0) NOT NULL,
  `floorPrice` decimal(10, 2) DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  CONSTRAINT `uid2` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `produceDate` datetime(0) DEFAULT NULL,
  `s_id` int(0) DEFAULT NULL,
  `p_id` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sid8`(`s_id`) USING BTREE,
  INDEX `pid8`(`p_id`) USING BTREE,
  CONSTRAINT `pid8` FOREIGN KEY (`p_id`) REFERENCES `platform` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sid8` FOREIGN KEY (`s_id`) REFERENCES `shop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (10000001, '蒙牛优酸乳', '食品', '牛牛牛牛牛', '2023-11-29 14:02:21', 10000001, 10000001);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_id` int(0) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `u_id` int(0) NOT NULL,
  PRIMARY KEY (`m_id`, `u_id`) USING BTREE,
  INDEX `uid1`(`u_id`) USING BTREE,
  CONSTRAINT `uid1` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for platform
-- ----------------------------
DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform
-- ----------------------------
INSERT INTO `platform` VALUES (10000001, '天猫');

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price`  (
  `c_id` int(0) NOT NULL,
  `price` decimal(10, 2) DEFAULT NULL,
  `time` datetime(0) NOT NULL,
  PRIMARY KEY (`c_id`, `time`) USING BTREE,
  CONSTRAINT `cid2` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES (10000001, 200.00, '2023-11-14 21:40:07');
INSERT INTO `price` VALUES (10000001, 100.00, '2023-11-29 15:16:36');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ownerid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oid`(`ownerid`) USING BTREE,
  CONSTRAINT `oid` FOREIGN KEY (`ownerid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (10000001, '李四的商店', '复旦大学', 10000002);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int(0) DEFAULT NULL,
  `phoneNumber` int(0) DEFAULT NULL,
  `role` int(0) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000001, '张三', 18, 11111111, 0, '123456');
INSERT INTO `user` VALUES (10000002, '李四', 18, 22222222, 1, '234567');
INSERT INTO `user` VALUES (10000003, '王五', 18, 33333333, 2, '345678');

SET FOREIGN_KEY_CHECKS = 1;
