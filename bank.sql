/*
 Navicat Premium Dump SQL

 Source Server         : fjut
 Source Server Type    : MySQL
 Source Server Version : 90200 (9.2.0)
 Source Host           : localhost:3306
 Source Schema         : bank

 Target Server Type    : MySQL
 Target Server Version : 90200 (9.2.0)
 File Encoding         : 65001

 Date: 13/07/2026 14:59:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '在职',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin123', '系统管理员', '13800000000', 'admin@hrbank.com', '在职', '2026-07-13 10:00:12');
INSERT INTO `admin` VALUES (2, 'admin2', 'admin123', '张主管', '13800000001', 'zhang@hrbank.com', '在职', '2026-07-13 10:00:12');

-- ----------------------------
-- Table structure for branch
-- ----------------------------
DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `manager_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '营业中',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch
-- ----------------------------
INSERT INTO `branch` VALUES (1, '华瑞银行总行营业部', '福建省福州市鼓楼区五四路128号', '0591-87888888', '陈志远', '总行直属营业网点，提供全方位金融服务', '营业中', '2026-07-10 19:11:47');
INSERT INTO `branch` VALUES (2, '华瑞银行仓山支行', '福建省福州市仓山区闽江大道168号', '0591-83561234', '林明辉', '仓山区主要服务网点', '已关闭', '2026-07-10 19:11:47');
INSERT INTO `branch` VALUES (3, '华瑞银行晋安支行', '福建省福州市晋安区长乐北路99号', '0591-87324567', '黄丽华', '晋安区综合金融服务网点', '营业中', '2026-07-10 19:11:47');
INSERT INTO `branch` VALUES (4, '华瑞银行厦门支行', '福建省厦门市集美区168号', '13589520144', '杨总', '', '营业中', '2026-07-10 19:14:29');

-- ----------------------------
-- Table structure for bussioness
-- ----------------------------
DROP TABLE IF EXISTS `bussioness`;
CREATE TABLE `bussioness`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` decimal(12, 2) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bussioness
-- ----------------------------
INSERT INTO `bussioness` VALUES (1, 1, '存款', 5000.00, '2025-01-15 09:30:00');
INSERT INTO `bussioness` VALUES (2, 1, '取款', 2000.00, '2025-02-20 14:15:00');
INSERT INTO `bussioness` VALUES (3, 1, '转账', 3000.00, '2025-03-10 11:00:00');
INSERT INTO `bussioness` VALUES (4, 2, '存款', 10000.00, '2025-01-20 10:00:00');
INSERT INTO `bussioness` VALUES (5, 2, '取款', 5000.00, '2025-03-05 16:30:00');
INSERT INTO `bussioness` VALUES (6, 3, '存款', 50000.00, '2025-01-10 08:00:00');
INSERT INTO `bussioness` VALUES (7, 3, '转账', 20000.00, '2025-02-15 09:45:00');
INSERT INTO `bussioness` VALUES (8, 3, '存款', 30000.00, '2025-04-01 13:20:00');
INSERT INTO `bussioness` VALUES (9, 4, '存款', 20000.00, '2025-01-18 10:30:00');
INSERT INTO `bussioness` VALUES (10, 4, '取款', 8000.00, '2025-02-28 15:00:00');
INSERT INTO `bussioness` VALUES (11, 5, '存款', 100000.00, '2025-01-25 09:00:00');
INSERT INTO `bussioness` VALUES (12, 5, '转账', 50000.00, '2025-03-12 11:30:00');
INSERT INTO `bussioness` VALUES (13, 5, '取款', 10000.00, '2025-05-20 14:45:00');
INSERT INTO `bussioness` VALUES (14, 6, '存款', 15000.00, '2025-02-01 10:15:00');
INSERT INTO `bussioness` VALUES (15, 6, '取款', 3000.00, '2025-04-10 16:00:00');
INSERT INTO `bussioness` VALUES (16, 7, '存款', 8000.00, '2025-01-30 08:30:00');
INSERT INTO `bussioness` VALUES (17, 7, '转账', 2000.00, '2025-03-20 12:00:00');
INSERT INTO `bussioness` VALUES (18, 8, '存款', 30000.00, '2025-02-10 09:15:00');
INSERT INTO `bussioness` VALUES (19, 8, '取款', 5000.00, '2025-04-25 17:30:00');
INSERT INTO `bussioness` VALUES (20, 8, '存款', 10000.00, '2025-06-01 10:00:00');
INSERT INTO `bussioness` VALUES (21, 9, '存款', 25000.00, '2025-01-12 11:00:00');
INSERT INTO `bussioness` VALUES (22, 9, '转账', 12000.00, '2025-03-08 14:30:00');
INSERT INTO `bussioness` VALUES (23, 10, '存款', 45000.00, '2025-02-05 09:45:00');
INSERT INTO `bussioness` VALUES (24, 10, '取款', 20000.00, '2025-04-15 15:15:00');
INSERT INTO `bussioness` VALUES (25, 10, '存款', 15000.00, '2025-06-20 11:30:00');
INSERT INTO `bussioness` VALUES (26, 1, '利息', 150.00, '2025-06-30 08:00:00');
INSERT INTO `bussioness` VALUES (27, 2, '利息', 300.00, '2025-06-30 08:00:00');
INSERT INTO `bussioness` VALUES (28, 3, '利息', 1200.00, '2025-06-30 08:00:00');
INSERT INTO `bussioness` VALUES (29, 2, '存款', 500.00, '2026-07-10 17:55:56');
INSERT INTO `bussioness` VALUES (30, 103, '存款', 1558888.00, '2026-07-10 17:56:02');
INSERT INTO `bussioness` VALUES (31, 2, '取款', 200.00, '2026-07-10 17:56:02');
INSERT INTO `bussioness` VALUES (32, 2, '转账-转出', 100.00, '2026-07-10 17:56:09');
INSERT INTO `bussioness` VALUES (33, 3, '转账-转入', 100.00, '2026-07-10 17:56:09');
INSERT INTO `bussioness` VALUES (34, 103, '取款', 887.00, '2026-07-10 17:56:13');
INSERT INTO `bussioness` VALUES (35, 103, '存款', 1.00, '2026-07-10 18:01:38');
INSERT INTO `bussioness` VALUES (36, 103, '存款', 100.00, '2026-07-13 09:13:18');
INSERT INTO `bussioness` VALUES (37, 103, '存款', 1.00, '2026-07-13 09:22:30');
INSERT INTO `bussioness` VALUES (38, 103, '取款', 10000000.00, '2026-07-13 09:22:36');
INSERT INTO `bussioness` VALUES (39, 103, '取款', 102.00, '2026-07-13 09:28:43');
INSERT INTO `bussioness` VALUES (40, 103, '转账-转出', 102.00, '2026-07-13 09:41:40');
INSERT INTO `bussioness` VALUES (41, 60, '转账-转入', 102.00, '2026-07-13 09:41:40');
INSERT INTO `bussioness` VALUES (42, 103, '取款', 100.00, '2026-07-13 09:42:40');
INSERT INTO `bussioness` VALUES (43, 103, '存款', 1000.00, '2026-07-13 09:42:53');
INSERT INTO `bussioness` VALUES (44, 103, '转账-转出', 1000.00, '2026-07-13 09:43:09');
INSERT INTO `bussioness` VALUES (45, 60, '转账-转入', 1000.00, '2026-07-13 09:43:09');
INSERT INTO `bussioness` VALUES (46, 103, '转账-转出', 50000.00, '2026-07-13 09:43:44');
INSERT INTO `bussioness` VALUES (47, 60, '转账-转入', 50000.00, '2026-07-13 09:43:44');
INSERT INTO `bussioness` VALUES (48, 103, '转账-转出', 1.00, '2026-07-13 09:45:21');
INSERT INTO `bussioness` VALUES (49, 60, '转账-转入', 1.00, '2026-07-13 09:45:21');
INSERT INTO `bussioness` VALUES (50, 103, '转账-转出', 1.00, '2026-07-13 09:45:34');
INSERT INTO `bussioness` VALUES (51, 60, '转账-转入', 1.00, '2026-07-13 09:45:34');
INSERT INTO `bussioness` VALUES (52, 59, '取款', 100.00, '2026-07-13 09:51:03');
INSERT INTO `bussioness` VALUES (53, 59, '存款', 500.00, '2026-07-13 09:51:23');
INSERT INTO `bussioness` VALUES (54, 103, '取款', 10000.00, '2026-07-13 10:47:50');
INSERT INTO `bussioness` VALUES (55, 103, '转账-转出', 10000.00, '2026-07-13 10:49:13');
INSERT INTO `bussioness` VALUES (56, 12, '转账-转入', 10000.00, '2026-07-13 10:49:13');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salary` decimal(12, 2) NULL DEFAULT NULL,
  `branch_id` int NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '在职',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'zhangsan', '123456', '张三', '男', 32, '13800138001', 'zhangsan@hrbank.com', '柜员', 8000.00, 1, '在职', '2026-07-10 19:11:47');
INSERT INTO `employee` VALUES (2, 'lisi', '123456', '李四', '女', 28, '13800138002', 'lisi@hrbank.com', '客户经理', 12000.00, 1, '在职', '2026-07-10 19:11:47');
INSERT INTO `employee` VALUES (3, 'wangwu', '123456', '王五', '男', 35, '13800138003', 'wangwu@hrbank.com', '支行行长', 20000.00, 2, '在职', '2026-07-10 19:11:47');
INSERT INTO `employee` VALUES (4, 'zhaoliu', '123456', '赵六', '女', 26, '13800138004', 'zhaoliu@hrbank.com', '大堂经理', 9000.00, 2, '在职', '2026-07-10 19:11:47');
INSERT INTO `employee` VALUES (5, 'sunqi', '123456', '孙七', '男', 30, '13800138005', 'sunqi@hrbank.com', '柜员', 7500.00, 3, '在职', '2026-07-10 19:11:47');
INSERT INTO `employee` VALUES (6, 'zhouba', '123456', '周八', '女', 29, '13800138006', 'zhouba@hrbank.com', '理财顾问', 11000.00, 3, '离职', '2026-07-10 19:11:47');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `permissions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '系统所有权限：用户管理、员工管理、网点管理、账户管理、交易管理、角色管理、数据仪表盘');
INSERT INTO `role` VALUES (2, '客户经理', '业务管理权限：客户管理、员工管理、网点管理、数据仪表盘');
INSERT INTO `role` VALUES (3, '储户', '个人账户权限：存款、取款、转账、账户查询、修改密码');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  `money` double NULL DEFAULT NULL,
  `card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'u001', '123', '测试修改', '男', 30, 123, '40000000000000', '18888888888', 'user');
INSERT INTO `user` VALUES (2, 'u002', '123456', '小红', '女', 31, 5200, '40000000000001', '18888888889', 'user');
INSERT INTO `user` VALUES (3, 'zsbank001', '123456', '赵四', '男', 29, 100100, '40000000000002', '18888888890', 'user');
INSERT INTO `user` VALUES (4, 'wwbank001', '123456', '小明', '男', 55, 544677, '40000000000003', '18888888891', 'user');
INSERT INTO `user` VALUES (5, 'wwbank002', '123456', '李四', '男', 55, 534534, '40000000000004', '18888888892', 'user');
INSERT INTO `user` VALUES (6, 'user01', '112233', '李天柱', '女', 18, 5800, '40000000000005', '13333333333', 'user');
INSERT INTO `user` VALUES (7, 'user02', '332211', '王大喜', '男', 22, 10, '88888888888888', '15555555555', 'user');
INSERT INTO `user` VALUES (8, 'user03', 'qwer1234', '刘芳', '女', 27, 25600, '6222000011000001', '13900001111', 'user');
INSERT INTO `user` VALUES (9, 'user04', 'pass123', '陈强', '男', 34, 89000, '6222000011000002', '13900002222', 'user');
INSERT INTO `user` VALUES (10, 'user05', 'abc123', '孙丽', '女', 25, 15300, '6222000011000003', '13900003333', 'user');
INSERT INTO `user` VALUES (11, 'user06', 'test99', '周杰', '男', 30, 42100, '6222000011000004', '13900004444', 'user');
INSERT INTO `user` VALUES (12, 'user07', 'hello1', '吴婷', '女', 28, 77500, '6222000011000005', '13900005555', 'user');
INSERT INTO `user` VALUES (13, 'user08', 'admin1', '郑伟', '男', 41, 230000, '6222000011000006', '13900006666', 'user');
INSERT INTO `user` VALUES (14, 'user09', 'root01', '冯雪', '女', 36, 156000, '6222000011000007', '13900007777', 'user');
INSERT INTO `user` VALUES (15, 'user10', 'pass888', '曹磊', '男', 45, 510000, '6222000011000008', '13900008888', 'user');
INSERT INTO `user` VALUES (16, 'user11', 'mypw001', '袁静', '女', 23, 8900, '6222000011000009', '13900009999', 'user');
INSERT INTO `user` VALUES (17, 'user12', 'mypw002', '邓超', '男', 33, 76000, '6222000011000010', '13800001111', 'user');
INSERT INTO `user` VALUES (18, 'user13', 'mypw003', '许敏', '女', 29, 34500, '6222000011000011', '13800002222', 'user');
INSERT INTO `user` VALUES (19, 'user14', 'mypw004', '傅刚', '男', 38, 198000, '6222000011000012', '13800003333', 'user');
INSERT INTO `user` VALUES (20, 'user15', 'mypw005', '沈婷', '女', 26, 12400, '6222000011000013', '13800004444', 'user');
INSERT INTO `user` VALUES (21, 'user16', 'mypw006', '曾勇', '男', 52, 890000, '6222000011000014', '13800005555', 'user');
INSERT INTO `user` VALUES (22, 'user17', 'mypw007', '彭丽', '女', 31, 45600, '6222000011000015', '13800006666', 'user');
INSERT INTO `user` VALUES (23, 'user18', 'mypw008', '吕浩', '男', 27, 21300, '6222000011000016', '13800007777', 'user');
INSERT INTO `user` VALUES (24, 'user19', 'mypw009', '苏燕', '女', 35, 112000, '6222000011000017', '13800008888', 'user');
INSERT INTO `user` VALUES (25, 'user20', 'mypw010', '卢鹏', '男', 43, 345000, '6222000011000018', '13800009999', 'user');
INSERT INTO `user` VALUES (26, 'user21', 'mypw011', '蒋玲', '女', 24, 9800, '6222000011000019', '13700001111', 'user');
INSERT INTO `user` VALUES (27, 'user22', 'mypw012', '蔡明', '男', 39, 267000, '6222000011000020', '13700002222', 'user');
INSERT INTO `user` VALUES (28, 'user23', 'mypw013', '贾欣', '女', 32, 58900, '6222000011000021', '13700003333', 'user');
INSERT INTO `user` VALUES (29, 'user24', 'mypw014', '魏然', '男', 28, 33200, '6222000011000022', '13700004444', 'user');
INSERT INTO `user` VALUES (30, 'user25', 'mypw015', '薛颖', '女', 22, 6700, '6222000011000023', '13700005555', 'user');
INSERT INTO `user` VALUES (31, 'user26', 'mypw016', '叶飞', '男', 47, 620000, '6222000011000024', '13700006666', 'user');
INSERT INTO `user` VALUES (32, 'user27', 'mypw017', '阎芳', '女', 37, 189000, '6222000011000025', '13700007777', 'user');
INSERT INTO `user` VALUES (33, 'user28', 'mypw018', '潘宇', '男', 30, 44500, '6222000011000026', '13700008888', 'user');
INSERT INTO `user` VALUES (34, 'user29', 'mypw019', '杜鹃', '女', 26, 17800, '6222000011000027', '13700009999', 'user');
INSERT INTO `user` VALUES (35, 'user30', 'mypw020', '戴军', '男', 50, 780000, '6222000011000028', '13600001111', 'user');
INSERT INTO `user` VALUES (36, 'user31', 'mypw021', '夏慧', '女', 33, 95600, '6222000011000029', '13600002222', 'user');
INSERT INTO `user` VALUES (37, 'user32', 'mypw022', '钟涛', '男', 42, 410000, '6222000011000030', '13600003333', 'user');
INSERT INTO `user` VALUES (38, 'user33', 'mypw023', '汪琳', '女', 21, 5400, '6222000011000031', '13600004444', 'user');
INSERT INTO `user` VALUES (39, 'user34', 'mypw024', '田浩', '男', 36, 156000, '6222000011000032', '13600005555', 'user');
INSERT INTO `user` VALUES (40, 'user35', 'mypw025', '任婷', '女', 29, 38700, '6222000011000033', '13600006666', 'user');
INSERT INTO `user` VALUES (41, 'user36', 'mypw026', '姜伟', '男', 44, 530000, '6222000011000034', '13600007777', 'user');
INSERT INTO `user` VALUES (42, 'user37', 'mypw027', '崔娜', '女', 25, 14200, '6222000011000035', '13600008888', 'user');
INSERT INTO `user` VALUES (43, 'user38', 'mypw028', '康磊', '男', 31, 62300, '6222000011000036', '13600009999', 'user');
INSERT INTO `user` VALUES (44, 'user39', 'mypw029', '毛静', '女', 38, 201000, '6222000011000037', '13500001111', 'user');
INSERT INTO `user` VALUES (45, 'user40', 'mypw030', '邱鹏', '男', 48, 690000, '6222000011000038', '13500002222', 'user');
INSERT INTO `user` VALUES (46, 'user41', 'mypw031', '秦玲', '女', 23, 7800, '6222000011000039', '13500003333', 'user');
INSERT INTO `user` VALUES (47, 'user42', 'mypw032', '江明', '男', 35, 134000, '6222000011000040', '13500004444', 'user');
INSERT INTO `user` VALUES (48, 'user43', 'mypw033', '尹欣', '女', 27, 29500, '6222000011000041', '13500005555', 'user');
INSERT INTO `user` VALUES (49, 'user44', 'mypw034', '黎然', '男', 40, 298000, '6222000011000042', '13500006666', 'user');
INSERT INTO `user` VALUES (50, 'user45', 'mypw035', '易颖', '女', 34, 107000, '6222000011000043', '13500007777', 'user');
INSERT INTO `user` VALUES (51, 'user46', 'mypw036', '常飞', '男', 26, 19600, '6222000011000044', '13500008888', 'user');
INSERT INTO `user` VALUES (52, 'user47', 'mypw037', '武芳', '女', 32, 73400, '6222000011000045', '13500009999', 'user');
INSERT INTO `user` VALUES (53, 'user48', 'mypw038', '乔宇', '男', 46, 560000, '6222000011000046', '13400001111', 'user');
INSERT INTO `user` VALUES (54, 'user49', 'mypw039', '赖慧', '女', 28, 36800, '6222000011000047', '13400002222', 'user');
INSERT INTO `user` VALUES (55, 'user50', 'mypw040', '龚涛', '男', 53, 920000, '6222000011000048', '13400003333', 'user');
INSERT INTO `user` VALUES (56, 'user51', 'mypw041', '文琳', '女', 24, 11200, '6222000011000049', '13400004444', 'user');
INSERT INTO `user` VALUES (57, 'user52', 'mypw042', '施浩', '男', 37, 178000, '6222000011000050', '13400005555', 'user');
INSERT INTO `user` VALUES (58, 'user53', 'mypw043', '洪婷', '女', 30, 52100, '6222000011000051', '13400006666', 'user');
INSERT INTO `user` VALUES (59, 'user54', 'mypw044', '陶伟', '男', 30, 380400, '6222000011000052', '13400007777', 'user');
INSERT INTO `user` VALUES (60, 'user55', 'mypw045', '姜娜', '女', 22, 57404, '6222000011000053', '13400008888', 'user');
INSERT INTO `user` VALUES (61, 'user56', 'mypw046', '戚磊', '男', 49, 710000, '6222000011000054', '13400009999', 'user');
INSERT INTO `user` VALUES (62, 'user57', 'mypw047', '谢玲', '女', 35, 125000, '6222000011000055', '13300001111', 'user');
INSERT INTO `user` VALUES (63, 'user58', 'mypw048', '邹明', '男', 33, 88900, '6222000011000056', '13300002222', 'user');
INSERT INTO `user` VALUES (64, 'user59', 'mypw049', '柏欣', '女', 26, 22100, '6222000011000057', '13300003333', 'user');
INSERT INTO `user` VALUES (65, 'user60', 'mypw050', '水然', '男', 39, 245000, '6222000011000058', '13300004444', 'user');
INSERT INTO `user` VALUES (66, 'user61', 'mypw051', '窦颖', '女', 31, 69700, '6222000011000059', '13300005555', 'user');
INSERT INTO `user` VALUES (67, 'user62', 'mypw052', '章飞', '男', 45, 490000, '6222000011000060', '13300006666', 'user');
INSERT INTO `user` VALUES (68, 'user63', 'mypw053', '云芳', '女', 20, 3200, '6222000011000061', '13300007777', 'user');
INSERT INTO `user` VALUES (69, 'user64', 'mypw054', '苏宇', '男', 34, 112000, '6222000011000062', '13300008888', 'user');
INSERT INTO `user` VALUES (70, 'user65', 'mypw055', '潘慧', '女', 29, 47500, '6222000011000063', '13300009999', 'user');
INSERT INTO `user` VALUES (71, 'user66', 'mypw056', '葛涛', '男', 51, 850000, '6222000011000064', '13200001111', 'user');
INSERT INTO `user` VALUES (72, 'user67', 'mypw057', '奚琳', '女', 25, 16400, '6222000011000065', '13200002222', 'user');
INSERT INTO `user` VALUES (73, 'user68', 'mypw058', '范浩', '男', 38, 215000, '6222000011000066', '13200003333', 'user');
INSERT INTO `user` VALUES (74, 'user69', 'mypw059', '彭婷', '女', 32, 81200, '6222000011000067', '13200004444', 'user');
INSERT INTO `user` VALUES (75, 'user70', 'mypw060', '郎伟', '男', 43, 370000, '6222000011000068', '13200005555', 'user');
INSERT INTO `user` VALUES (76, 'user71', 'mypw061', '韦娜', '女', 27, 28900, '6222000011000069', '13200006666', 'user');
INSERT INTO `user` VALUES (77, 'user72', 'mypw062', '昌磊', '男', 54, 960000, '6222000011000070', '13200007777', 'user');
INSERT INTO `user` VALUES (78, 'user73', 'mypw063', '马玲', '女', 23, 9100, '6222000011000071', '13200008888', 'user');
INSERT INTO `user` VALUES (79, 'user74', 'mypw064', '苗明', '男', 36, 168000, '6222000011000072', '13200009999', 'user');
INSERT INTO `user` VALUES (80, 'user75', 'mypw065', '凤欣', '女', 30, 55600, '6222000011000073', '13100001111', 'user');
INSERT INTO `user` VALUES (81, 'user76', 'mypw066', '花然', '男', 42, 320000, '6222000011000074', '13100002222', 'user');
INSERT INTO `user` VALUES (82, 'user77', 'mypw067', '方颖', '女', 28, 41300, '6222000011000075', '13100003333', 'user');
INSERT INTO `user` VALUES (83, 'user78', 'mypw068', '俞飞', '男', 47, 580000, '6222000011000076', '13100004444', 'user');
INSERT INTO `user` VALUES (84, 'user79', 'mypw069', '任芳', '女', 33, 97800, '6222000011000077', '13100005555', 'user');
INSERT INTO `user` VALUES (85, 'user80', 'mypw070', '袁宇', '男', 39, 260000, '6222000011000078', '13100006666', 'user');
INSERT INTO `user` VALUES (86, 'user81', 'mypw071', '柳慧', '女', 21, 4800, '6222000011000079', '13100007777', 'user');
INSERT INTO `user` VALUES (87, 'user82', 'mypw072', '酆涛', '男', 50, 800000, '6222000011000080', '13100008888', 'user');
INSERT INTO `user` VALUES (88, 'user83', 'mypw073', '鲍琳', '女', 34, 118000, '6222000011000081', '13100009999', 'user');
INSERT INTO `user` VALUES (89, 'user84', 'mypw074', '史浩', '男', 29, 49700, '6222000011000082', '13000001111', 'user');
INSERT INTO `user` VALUES (90, 'user85', 'mypw075', '唐婷', '女', 26, 20500, '6222000011000083', '13000002222', 'user');
INSERT INTO `user` VALUES (91, 'user86', 'mypw076', '费伟', '男', 44, 450000, '6222000011000084', '13000003333', 'user');
INSERT INTO `user` VALUES (92, 'user87', 'mypw077', '廉娜', '女', 21, 76500, '6222000011000085', '13000004444', 'user');
INSERT INTO `user` VALUES (93, 'user88', 'mypw078', '岑磊', '男', 37, 185000, '6222000011000086', '13000005555', 'user');
INSERT INTO `user` VALUES (94, 'user89', 'mypw079', '薛玲', '女', 24, 13500, '6222000011000087', '13000006666', 'user');
INSERT INTO `user` VALUES (95, 'user90', 'mypw080', '雷明', '男', 46, 610000, '6222000011000088', '13000007777', 'user');
INSERT INTO `user` VALUES (96, 'user91', 'mypw081', '贺欣', '女', 28, 37900, '6222000011000089', '13000008888', 'user');
INSERT INTO `user` VALUES (97, 'user92', 'mypw082', '倪然', '男', 35, 142000, '6222000011000090', '13000009999', 'user');
INSERT INTO `user` VALUES (98, 'user93', 'mypw083', '汤颖', '女', 22, 7200, '6222000011000091', '12900001111', 'user');
INSERT INTO `user` VALUES (99, 'user94', 'mypw084', '滕飞', '男', 40, 305000, '6222000011000092', '12900002222', 'user');
INSERT INTO `user` VALUES (100, 'user95', 'mypw085', '殷芳', '女', 33, 104000, '6222000011000093', '12900003333', 'user');
INSERT INTO `user` VALUES (103, 'u101', '123456', 'yangg', '男', 18, 123448166796, '123523566325478958', '15025325200', 'user');
INSERT INTO `user` VALUES (104, 'u102', '123456', '天舒', '女', 18, 1000, '147', '1111', 'user');

-- ----------------------------
-- Table structure for user_vip
-- ----------------------------
DROP TABLE IF EXISTS `user_vip`;
CREATE TABLE `user_vip`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `level_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `min_balance` double NULL DEFAULT 0,
  `discount_rate` double NULL DEFAULT 1 COMMENT '手续费折扣',
  `color` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '展示颜色',
  `icon` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `benefits` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '权益描述',
  `sort_order` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `level_code`(`level_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'VIP等级定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_vip
-- ----------------------------
INSERT INTO `user_vip` VALUES (1, '普通会员', 'normal', 0, 1, '#94a3b8', '?', '基础金融服务；在线查询余额；小额存取款', 1);
INSERT INTO `user_vip` VALUES (2, 'VIP铜卡会员', 'copper', 10000, 0.98, '#d97706', '?', '享受9.8折手续费优惠；优先客服通道；生日祝福', 2);
INSERT INTO `user_vip` VALUES (3, 'VIP银卡会员', 'silver', 50000, 0.95, '#64748b', '?', '享受9.5折手续费优惠；专属客户经理；免费账户提醒', 3);
INSERT INTO `user_vip` VALUES (4, 'VIP金卡会员', 'gold', 100000, 0.9, '#f59e0b', '?', '享受9折手续费优惠；VIP专属柜台；理财顾问服务；免费跨行转账', 4);

SET FOREIGN_KEY_CHECKS = 1;
