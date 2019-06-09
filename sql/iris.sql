CREATE DATABASE  IF NOT EXISTS `old9` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `old9`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: iris
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,1,0,'[0]','Iris集团总公司','Iris集团总公司','aaa',NULL),(15,1,1,'[0],[1]','广东分公司','广东分公司','广东',NULL),(17,3,1,'[0],[1]','湖北分公司','湖北分公司','湖北',NULL),(19,1,15,'[0],[1],[15]','广东党委组织部','广东党委组织部','广东党委组织部',NULL),(20,2,1,'[0],[1]','广西分公司','广西分公司','广西分公司',NULL),(21,4,1,'[0],[1]','湖南分公司','湖南分公司','湖南分公司',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'sys',0,'[0]','系统管理','fa fa-cogs',NULL,1,1,'备注11',1,1),(2,'usermgr',1,'[0],[1]','用户管理','fa fa-user','/user/index',2,1,'用户',1,1),(3,'menumgr',1,'[0],[1]','菜单管理','fa fa-sort-amount-desc','/menu/index',2,1,NULL,1,1),(4,'deptmgr',1,'[0],[1]','机构管理','fa fa-university','/dept/index',2,1,'机构',1,0),(10,'test1',1,'[0],[1]','测试1','fa fa-exchange','test1',2,1,'test1',1,0),(11,'test1-1',10,'[0],[1],[10]','测试1-1',NULL,'test1-1',3,1,'test1-1',1,0),(12,'rolemgr',1,'[0],[1]','角色管理','fa fa-user-secret','/role/index',2,1,'角色',1,0),(13,'ocr',1,'[0],[1]','文字识别','fa fa-houzz','/ocr/index',2,1,'百度OCR文字识别',1,0),(14,'usermgr_add',2,'[0],[1],[2]','用户新增','','',3,0,'用户新增权限',1,0),(15,'usermgr_del',2,'[0],[1],[2]','用户删除','','',3,0,'权限-删除',1,0),(16,'usermgr_show',2,'[0],[1],[2]','用户查看',NULL,NULL,3,0,'查看权限',1,0),(17,'usermgr_edit',2,'[0],[1],[2]','用户编辑',NULL,NULL,3,0,NULL,1,0),(18,'menumgr_add',3,'[0],[1],[3]','菜单新增',NULL,NULL,3,0,NULL,1,0),(19,'menumgr_edit',3,'[0],[1],[3]','菜单编辑',NULL,NULL,3,0,NULL,1,0),(20,'menumgr_del',3,'[0],[1],[3]','菜单删除',NULL,NULL,3,0,NULL,1,0),(21,'deptmgr_add',4,'[0],[1],[4]','机构新增',NULL,NULL,3,0,NULL,1,0),(22,'deptmgr_edit',4,'[0],[1],[4]','机构编辑',NULL,NULL,3,0,NULL,1,0),(23,'deptmgr_del',4,'[0],[1],[4]','机构删除',NULL,NULL,3,0,NULL,1,0),(24,'rolemgr_add',12,'[0],[1],[12]','角色新增',NULL,NULL,3,0,NULL,1,0),(25,'rolemgr_edit',12,'[0],[1],[12]','角色编辑',NULL,NULL,3,0,NULL,1,0),(26,'rolemgr_del',12,'[0],[1],[12]','角色删除',NULL,NULL,3,0,NULL,1,0),(27,'rolemgr_perm',12,'[0],[1],[12]','权限配置',NULL,NULL,3,0,NULL,1,0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `remarks` varchar(255) DEFAULT NULL COMMENT '提示',
  `temp1` varchar(255) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员',1,'备注111',NULL),(2,'角色1',15,'1111',NULL),(3,'财务',1,'123',NULL),(4,'测试1',21,'党1',NULL),(6,'啦啦啦',20,'啊啊啊',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role2menu`
--

DROP TABLE IF EXISTS `sys_role2menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role2menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role2menu`
--

LOCK TABLES `sys_role2menu` WRITE;
/*!40000 ALTER TABLE `sys_role2menu` DISABLE KEYS */;
INSERT INTO `sys_role2menu` VALUES (51,1,1),(52,2,1),(53,14,1),(54,15,1),(55,16,1),(56,17,1),(57,3,1),(58,18,1),(59,19,1),(60,20,1),(61,4,1),(62,21,1),(63,22,1),(64,23,1),(65,12,1),(66,24,1),(67,25,1),(68,26,1),(69,27,1),(70,13,1),(71,1,3),(72,2,3),(73,3,3),(74,4,3),(75,21,3),(76,22,3);
/*!40000 ALTER TABLE `sys_role2menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','123456','叛逆的逆','http://pb882w3bl.bkt.clouddn.com/5af41ea4-a0f7-4ef9-bcf9-f26a2e5cc3cf.jpg','2017-12-08 15:04:18','描述~~~~',NULL,1,'1,3,2',1),(2,'wnl','123456','王大锤','http://pb882w3bl.bkt.clouddn.com/46be5795-3caa-48bd-afa1-10e8d1f89edd.jpeg','2018-07-19 13:32:57','111',NULL,1,'3',1),(8,'test2','111','测试人员2','http://pb882w3bl.bkt.clouddn.com/2e244d17-26dd-430e-83e7-1d469d1dee7f.jpeg','2018-01-30 16:08:49','123',NULL,1,'1',1),(11,'haha','123','哈哈哈1',NULL,'2018-03-19 10:28:29','啊',NULL,1,'1',17),(12,'iris','123456','猫了个咪1',NULL,'2018-06-28 10:44:56','无111',NULL,1,'1',20),(13,'wangnew','123456','王大锤2','http://pb882w3bl.bkt.clouddn.com/c7bcb215-be6f-4cca-b98a-29911947d855.jpg','2018-07-19 13:47:37','',NULL,1,'1',15);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-19 13:57:15
