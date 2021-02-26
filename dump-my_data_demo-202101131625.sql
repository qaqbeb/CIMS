-- MySQL dump 10.13  Distrib 5.7.31, for Win64 (x86_64)
--
-- Host: localhost    Database: my_data_demo
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (40,'admin','d7b6dc4e1164b52db818a10011fcdfba','029780'),(41,'tian','748a0be5771ce0dd22b32c647c9c9544','224423'),(43,'qaqbeb','90c869f316f63bcdb31b3c04f23c9ab6','875748'),(44,'swh','6e385eb379c0372036b7fcad6d8d9a37','920405'),(45,'yuangong','f4891d46c3937a82ed353fe9884f697a','242024');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_role_fk` (`account_id`),
  KEY `account_role_fk_1` (`role_id`),
  CONSTRAINT `account_role_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `account_role_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (3,40,1),(4,43,1),(8,41,2),(10,44,1),(11,45,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'零售客户','123',1),(2,'b','123',1),(3,'c','123',1),(4,'d','123',2),(5,'e','123',2),(6,'f','123',1),(7,'g','123',2),(8,'h','123',2),(9,'i','123',2),(10,'j','123',1),(11,'k','123',1),(12,'l','123',2),(13,'m','123',1),(14,'n','123',1),(15,'o','123',2),(16,'p','123',1),(17,'aa','123',1),(18,'bb','123',2),(19,'cc','123',1),(20,'ab','123',2),(21,'ac','123',2),(22,'ad','123',2),(23,'ba','123',1),(24,'ca','123',1),(25,'da','123',1),(26,'aab','123',1),(27,'abb','123',2),(28,'acc','123',2),(29,'aad','123',1),(30,'qaqbeb','13243850819',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(100) NOT NULL,
  `price_in` double NOT NULL,
  `price_wholesale` double NOT NULL,
  `price_retail` double NOT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'apple',2,3,4),(2,'banana',4,6,11),(5,'basketball',50,100,150),(6,'shoes',60,80,110),(7,'car',10000,13000,18000),(8,'tank',100000,200000,250000);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_order`
--

DROP TABLE IF EXISTS `my_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `warehouse_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `status` int(11) NOT NULL,
  `create_time` varchar(100) NOT NULL,
  `goods_list` varchar(100) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_order`
--

LOCK TABLES `my_order` WRITE;
/*!40000 ALTER TABLE `my_order` DISABLE KEYS */;
INSERT INTO `my_order` VALUES (2,2,1,44,3,'Fri Dec 11 10:56:50 CST 2020','1-11'),(3,4,2,72,2,'Fri Dec 11 23:00:35 CST 2020','2-12'),(4,2,1,16,2,'Sat Dec 12 12:22:53 CST 2020','1-4'),(5,4,2,72,2,'Sat Dec 12 12:58:25 CST 2020','2-12'),(6,1,2,26,2,'Tue Jan 12 21:33:15 CST 2021','1-1-2-2'),(53,1,2,110,3,'Wed Jan 13 15:02:19 CST 2021','6-1'),(56,1,2,110,2,'Wed Jan 13 15:37:18 CST 2021','6-1'),(57,1,1,154,3,'Wed Jan 13 15:43:28 CST 2021','1-1-5-1');
/*!40000 ALTER TABLE `my_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'商品权限','goods',''),(2,'商品列表','goods:goodsList','/goods/goodsList'),(3,'添加商品','goods:addGoods','/goods/addGoods'),(4,'删除商品','goods:delete','/goods/delete'),(5,'客户信息','customer',NULL),(6,'客户列表','customer:customerList','/customer/customerList'),(7,'添加客户','customer:addCustomer','/customer/addCustomer'),(8,'查询客户','customer:searchCustomer','/customer/searchCustomer'),(9,'业务管理','order',NULL),(10,'下单','order:placeOrder','/order/placeOrder'),(11,'审核订单','order:checkOrder','/order/checkOrder'),(12,'查询订单','order:orderList','/order/orderList'),(13,'库存管理','warehouse',NULL),(14,'库存列表','warehouse:warehouseList','/warehouse/warehouseList'),(15,'进货','warehouse:stock','/warehouse/stock'),(16,'调拨货品','warehouse:transfer','/warehouse/transfer'),(17,'库存记录','warehouse:goodsList','/warehouse/goodsList'),(18,'用户管理','user',NULL),(19,'用户列表','user:userList','/user/userList'),(20,'用户查询','user:searchUser','/user/searchUser'),(21,'收银台','turnover',NULL),(22,'查询商品','goods:searchGoods','/goods/searchGoods'),(23,'删除客户','customer:delete','/customer/delete'),(24,'角色查询','role:roleList','/role/roleList'),(25,'添加角色','role:addRole','/role/addRole'),(26,'库存统计','warehouse:statistic','');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `intro` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'经理','manager','权力最大'),(2,'店长','shopowner','权力较大'),(3,'店员','clerk','权力最小');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_perm`
--

DROP TABLE IF EXISTS `role_perm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_perm_fk` (`role_id`),
  KEY `role_perm_fk_1` (`perm_id`),
  CONSTRAINT `role_perm_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_perm_fk_1` FOREIGN KEY (`perm_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_perm`
--

LOCK TABLES `role_perm` WRITE;
/*!40000 ALTER TABLE `role_perm` DISABLE KEYS */;
INSERT INTO `role_perm` VALUES (71,3,20),(72,2,25),(73,2,20),(74,2,16),(75,2,19),(76,1,8),(77,1,24),(78,1,25),(79,1,10),(80,1,6),(81,1,2),(82,1,12),(83,1,14),(84,1,22),(85,1,17),(86,1,20),(87,1,11),(88,1,16),(89,1,7),(90,1,19),(91,1,15),(92,1,3),(93,1,26);
/*!40000 ALTER TABLE `role_perm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_list` varchar(100) DEFAULT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'1-105-5-99',184),(2,'1-2-2-39-6-197',207);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'my_data_demo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-13 16:25:33
