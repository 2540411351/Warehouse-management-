/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : warehouse

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2022-12-11 21:10:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '货名',
  `storage` int(11) NOT NULL COMMENT '仓库',
  `goodsType` int(11) NOT NULL COMMENT '分类',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '足球', '2', '2', '250', '用于商用');
INSERT INTO `goods` VALUES ('2', '篮球', '2', '2', '160', '用于上课');
INSERT INTO `goods` VALUES ('3', '排球', '2', '2', '102', '用于举办比赛');
INSERT INTO `goods` VALUES ('4', '乒乓球', '2', '2', '121', '用于比赛');
INSERT INTO `goods` VALUES ('6', '口罩', '1', '3', '600', '用于防疫');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '分类名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES ('1', '工具类', '锤子、镊子之类的工具');
INSERT INTO `goodstype` VALUES ('2', '体育类', '足球、篮球之类的运动');
INSERT INTO `goodstype` VALUES ('3', '生活用品类', '毛巾、口罩之类的生活用品');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `menuCode` varchar(8) DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) DEFAULT NULL,
  `menuIcon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '001', '管理员管理', '1', null, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES ('2', '002', '用户管理', '1', null, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES ('3', '003', '仓库管理', '1', null, 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-office-building');
INSERT INTO `menu` VALUES ('4', '004', '物品分类管理', '1', null, 'Goodstype', '0,1', 'goodstype/GoodstypeManage.vue', 'el-icon-menu');
INSERT INTO `menu` VALUES ('5', '005', '物品管理', '1', null, 'Goods', '0,1,2', 'goods/GoodsManage.vue', 'el-icon-menu');
INSERT INTO `menu` VALUES ('6', '006', '记录管理', '1', null, 'Record', '0,1,2', 'record/RecordManage.vue', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int(11) NOT NULL COMMENT '货品id',
  `userId` int(11) DEFAULT NULL COMMENT '取货人/补货人',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `createtime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('1', '6', '5', '1', '-200', '2022-12-10 13:55:13', '用于普宁市的防疫');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(225) NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1', '仓库1', '这是个小仓库');
INSERT INTO `storage` VALUES ('2', '仓库2', '这是个大仓库');
INSERT INTO `storage` VALUES ('3', '仓库3', '这是个第三仓库');
INSERT INTO `storage` VALUES ('4', '仓库4', '这是第四个仓库');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL COMMENT '账号',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `age` int(11) DEFAULT NULL COMMENT '性别',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `role_id` int(11) DEFAULT NULL COMMENT '角色: 0超级管理员 1管理员 2普通账号',
  `isValid` varchar(255) DEFAULT 'Y' COMMENT '是否有效 Y有效 其他无效',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sa', '超级管理员', '123', '18', '1', '13471417105', '0', 'Y');
INSERT INTO `user` VALUES ('2', 'admin', '集合', '1234', '58', '1', '54512345751', '1', 'Y');
INSERT INTO `user` VALUES ('3', 'test1', '多线程', '14723', '12', '0', '13645121044', '1', 'Y');
INSERT INTO `user` VALUES ('4', 'cxz', '初学者', '12369', '35', '1', '1364851514', '1', 'Y');
INSERT INTO `user` VALUES ('5', 'xiaoliao', '小年糕', '6666', '12', '1', '15245185412', '2', 'Y');
INSERT INTO `user` VALUES ('6', 'grj', '高人杰', '132', '52', '0', '15248154511', '1', 'Y');
INSERT INTO `user` VALUES ('7', 'xiaochen', '小陈', '12358', '24', '0', '16845134511', '2', 'Y');
