/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : guns

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-07-11 22:39:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `p_id` bigint(11) DEFAULT NULL COMMENT '父部门id',
  `p_ids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `name` varchar(45) DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `temp` varchar(255) DEFAULT NULL COMMENT '备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (1,1,0,'[0]','Iris集团总公司','Iris集团总公司','aaa',NULL);
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (15,1,1,'[0],[1]','广东分公司','广东分公司','广东',NULL);
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (17,3,1,'[0],[1]','湖北分公司','湖北分公司','湖北',NULL);
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (19,1,15,'[0],[1],[15]','广东党委组织部','广东党委组织部','广东党委组织部',NULL);
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (20,2,1,'[0],[1]','广西分公司','广西分公司','广西分公司',NULL);
INSERT INTO `sys_dept` (`id`,`sort`,`p_id`,`p_ids`,`name`,`full_name`,`tips`,`temp`) VALUES (21,4,1,'[0],[1]','湖南分公司','湖南分公司','湖南分公司',NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `p_id` bigint(20) DEFAULT NULL COMMENT '菜单父编号',
  `p_ids` varchar(255) DEFAULT NULL COMMENT '所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `levels` int(5) DEFAULT NULL COMMENT '菜单层级',
  `is_menu` int(2) DEFAULT NULL COMMENT '菜单/按钮:1-菜单,0-按钮',
  `others` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(2) DEFAULT NULL COMMENT '菜单状态:1-启用,0-不启用',
  `is_open` int(2) DEFAULT NULL COMMENT '默认打开:1-是,0-否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (1,'sys',0,'[0]','系统管理','fa fa-cogs',NULL,1,1,'备注11',1,1);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (2,'usermgr',1,'[0],[1]','用户管理','fa fa-user','/user/index',2,1,'用户',1,1);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (3,'menumgr',1,'[0],[1]','菜单管理','fa fa-sort-amount-desc','/menu/index',2,1,NULL,1,1);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (4,'deptmgr',1,'[0],[1]','机构管理','fa fa-university','/dept/index',2,1,'机构',1,1);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (10,'test1',1,'[0],[1]','测试1','fa fa-exchange','test1',2,1,'test1',1,0);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (11,'test1-1',10,'[0],[1],[10]','测试1-1',NULL,'test1-1',3,1,'test1-1',1,0);
INSERT INTO `sys_menu` (`id`,`code`,`p_id`,`p_ids`,`name`,`icon`,`url`,`levels`,`is_menu`,`others`,`status`,`is_open`) VALUES (12,'rolemgr',1,'[0],[1]','角色管理','fa fa-user-secret','/role/index',2,1,'角色',1,0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `remarks` varchar(255) DEFAULT NULL COMMENT '提示',
  `temp1` varchar(255) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` (`id`,`name`,`dept_id`,`remarks`,`temp1`) VALUES (1,'超级管理员',1,'备注111',NULL);
INSERT INTO `sys_role` (`id`,`name`,`dept_id`,`remarks`,`temp1`) VALUES (2,'角色1',15,'1111',NULL);
INSERT INTO `sys_role` (`id`,`name`,`dept_id`,`remarks`,`temp1`) VALUES (3,'财务',1,'123',NULL);
INSERT INTO `sys_role` (`id`,`name`,`dept_id`,`remarks`,`temp1`) VALUES (4,'测试1',21,'党1',NULL);
INSERT INTO `sys_role` (`id`,`name`,`dept_id`,`remarks`,`temp1`) VALUES (6,'啦啦啦',20,'啊啊啊',NULL);

-- ----------------------------
-- Table structure for sys_role2menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role2menu`;
CREATE TABLE `sys_role2menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role2menu
-- ----------------------------
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (18,1,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (19,2,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (20,3,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (21,4,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (22,5,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (23,12,1);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (24,1,3);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (25,2,3);
INSERT INTO `sys_role2menu` (`id`,`menu_id`,`role_id`) VALUES (26,3,3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_acc` varchar(255) DEFAULT NULL COMMENT '账号',
  `user_psw` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `role_id` varchar(255) DEFAULT NULL,
  `dept_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` (`id`,`user_acc`,`user_psw`,`nick_name`,`avatar`,`create_time`,`description`,`salt`,`status`,`role_id`,`dept_id`) VALUES (1,'admin','123456','叛逆的逆',NULL,'2017-12-08 15:04:18','描述~~~~',NULL,1,'1',1);
INSERT INTO `sys_user` (`id`,`user_acc`,`user_psw`,`nick_name`,`avatar`,`create_time`,`description`,`salt`,`status`,`role_id`,`dept_id`) VALUES (2,'wnl','123456','王大锤',NULL,'2017-12-21 15:04:18','111',NULL,1,'1',1);
INSERT INTO `sys_user` (`id`,`user_acc`,`user_psw`,`nick_name`,`avatar`,`create_time`,`description`,`salt`,`status`,`role_id`,`dept_id`) VALUES (8,'test2','111','测试人员2',NULL,'2018-01-30 16:08:49','123',NULL,1,'1',1);
INSERT INTO `sys_user` (`id`,`user_acc`,`user_psw`,`nick_name`,`avatar`,`create_time`,`description`,`salt`,`status`,`role_id`,`dept_id`) VALUES (11,'haha','123','哈哈哈1',NULL,'2018-03-19 10:28:29','啊',NULL,1,'1',17);
