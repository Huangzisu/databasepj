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

 Date: 03/12/2023 21:59:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `u_id` int(0) NOT NULL,
  `c_id` int(0) NOT NULL,
  `floorprice` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`u_id`, `c_id`) USING BTREE,
  INDEX `cid4`(`c_id`) USING BTREE,
  CONSTRAINT `cid4` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uid3` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (10000001, 10000004, 100.00);
INSERT INTO `collection` VALUES (10000002, 10000004, 0.00);
INSERT INTO `collection` VALUES (10000003, 10000001, 0.00);

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `produceDate` date DEFAULT NULL,
  `s_id` int(0) DEFAULT NULL,
  `p_id` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sid8`(`s_id`) USING BTREE,
  INDEX `pid8`(`p_id`) USING BTREE,
  CONSTRAINT `pid8` FOREIGN KEY (`p_id`) REFERENCES `platform` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sid8` FOREIGN KEY (`s_id`) REFERENCES `shop` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10000005 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (10000001, '蒙牛优酸乳', '食品', '牛牛牛牛牛', '2023-11-29', 10000001, 10000001);
INSERT INTO `commodity` VALUES (10000004, 'update_administrator', 'update_administrator', 'update_administrator', '2020-12-03', 10000001, 10000001);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_id` int(0) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `u_id` int(0) NOT NULL,
  `time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`m_id`, `u_id`) USING BTREE,
  INDEX `uid1`(`u_id`) USING BTREE,
  CONSTRAINT `uid1` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (5, '您收藏的商品中有商品降到底价了！', 10000001, '2023-12-03 00:00:00');
INSERT INTO `message` VALUES (10000001, 'hello', 10000001, '2023-12-03 00:00:00');
INSERT INTO `message` VALUES (10000002, '您收藏的商品中，update_administrator 降到底价了！', 10000001, '2023-12-03 21:55:08');

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
  CONSTRAINT `cid3` FOREIGN KEY (`c_id`) REFERENCES `commodity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `price` VALUES (10000004, 1.00, '2023-12-03 21:55:05');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `owner_id` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oid`(`owner_id`) USING BTREE,
  CONSTRAINT `oid` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (10000001, 'test_update', 'test_update', 10000002);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int(0) DEFAULT NULL,
  `phoneNumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` int(0) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10000001, '张三', 18, '11111111', 0, '123456', 'male');
INSERT INTO `user` VALUES (10000002, '李四', 18, '22222222', 1, '234567', 'female');
INSERT INTO `user` VALUES (10000003, 'update_name', 30, '17321126787', 2, '345678', 'female');

SET FOREIGN_KEY_CHECKS = 1;
