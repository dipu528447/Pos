-- MySQL dump 10.13  Distrib 6.0.9-alpha, for Win32 (ia32)
--
-- Host: localhost    Database: ctgcomtech
-- ------------------------------------------------------
-- Server version	6.0.9-alpha-community

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
-- Table structure for table `tbbrandinfo`
--

DROP TABLE IF EXISTS `tbbrandinfo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbbrandinfo` (
  `brandId` int(11) NOT NULL,
  `brandName` varchar(50) DEFAULT NULL,
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbbrandinfo`
--

LOCK TABLES `tbbrandinfo` WRITE;
/*!40000 ALTER TABLE `tbbrandinfo` DISABLE KEYS */;
INSERT INTO `tbbrandinfo` VALUES (2,'HP','2016-10-13 23:34:54','1001',''),(3,'Dell','2016-10-13 23:34:59','1001',''),(4,'Acer','2016-10-13 23:35:06','1001',''),(5,'Toshiba','2016-10-13 23:35:18','1001','');
/*!40000 ALTER TABLE `tbbrandinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbbrandwisedepartmentinfo`
--

DROP TABLE IF EXISTS `tbbrandwisedepartmentinfo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbbrandwisedepartmentinfo` (
  `brandid` int(11) DEFAULT NULL,
  `modelId` int(11) DEFAULT NULL,
  `ModelName` varchar(50) DEFAULT NULL,
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `updateBy` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbbrandwisedepartmentinfo`
--

LOCK TABLES `tbbrandwisedepartmentinfo` WRITE;
/*!40000 ALTER TABLE `tbbrandwisedepartmentinfo` DISABLE KEYS */;
INSERT INTO `tbbrandwisedepartmentinfo` VALUES (4,1,'Acer 2','2016-10-13 23:35:29','1001',''),(2,2,'Dut1010','2016-10-13 23:35:38','1001',''),(2,3,'D10to3u','2016-10-13 23:35:48','1001',''),(3,4,'N4050','2016-10-13 23:35:56','1001','');
/*!40000 ALTER TABLE `tbbrandwisedepartmentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcompanyinfo`
--

DROP TABLE IF EXISTS `tbcompanyinfo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbcompanyinfo` (
  `autoId` int(10) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(50) DEFAULT '0',
  `address` tinytext,
  `telePhone` varchar(50) DEFAULT '0',
  `Mobile` varchar(50) DEFAULT '0',
  `email` varchar(50) DEFAULT '0',
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT '0',
  UNIQUE KEY `autoId` (`autoId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbcompanyinfo`
--

LOCK TABLES `tbcompanyinfo` WRITE;
/*!40000 ALTER TABLE `tbcompanyinfo` DISABLE KEYS */;
INSERT INTO `tbcompanyinfo` VALUES (1,'Chittagong Computer Technology','New Market 2nd floor,3 no Goli ,Keranihat,Chittagong. ','0','01813180901,01913180901','ctgInfo@gmail.com',NULL,'1001');
/*!40000 ALTER TABLE `tbcompanyinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbinvoice`
--

DROP TABLE IF EXISTS `tbinvoice`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbinvoice` (
  `invoiceNo` int(11) DEFAULT NULL,
  `Customer` varchar(25) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `selltype` int(11) DEFAULT NULL,
  `ledgerId` int(11) DEFAULT NULL,
  `Mobile` varchar(50) DEFAULT NULL,
  `salesMan` varchar(50) DEFAULT NULL,
  `amount` double DEFAULT '0',
  `netAmount` double DEFAULT '0',
  `discountPer` double DEFAULT '0',
  `discountManual` double DEFAULT '0',
  `discount` double DEFAULT '0',
  `paid` double DEFAULT '0',
  `cash` double DEFAULT '0',
  `card` double DEFAULT '0',
  `p_type` varchar(50) DEFAULT NULL,
  `transportCost` double DEFAULT '0',
  `date` date DEFAULT NULL,
  `transectionId` int(11) DEFAULT '0',
  `costId` int(11) DEFAULT '0',
  `cashId` int(11) DEFAULT '0',
  `cardId` int(11) DEFAULT '0',
  `discountid` int(11) DEFAULT '0',
  `remark` tinytext,
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `edit` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbinvoice`
--

LOCK TABLES `tbinvoice` WRITE;
/*!40000 ALTER TABLE `tbinvoice` DISABLE KEYS */;
INSERT INTO `tbinvoice` VALUES (1,'Mamun Mia','1',NULL,NULL,NULL,NULL,256900,256900,0,0,0,256900,256900,0,'Cash',0,'2016-10-13',0,0,0,0,0,'','2016-10-15 03:02:00','1001',NULL),(1,'Mamun Mia','2',NULL,NULL,NULL,NULL,64000,64000,0,0,0,64000,64000,0,'Cash',0,'2016-10-14',0,0,0,0,0,'','2016-10-14 12:03:00','1001',NULL),(2,'Mamun Mia','2',NULL,NULL,NULL,NULL,128000,128000,0,0,0,128000,128000,0,'Cash',0,'2016-10-14',0,0,0,0,0,'','2016-10-15 03:08:00','1001',NULL),(1,'adf','3',1,NULL,NULL,'',40000,40000,0,0,0,36000,20000,16000,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 00:53:33','1001','1001'),(2,'ad','3',1,NULL,NULL,'',30000,30000,0,0,0,30000,30000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-15 03:24:00','1001','1001'),(3,'ad','3',1,NULL,NULL,'',20000,20000,0,0,0,20000,20000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:03:29','1001',NULL),(4,'Nasir Uddin','3',1,NULL,NULL,'',62000,62000,0,0,0,62000,62000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-15 03:26:00','1001','1001'),(5,'adsfa','3',1,NULL,NULL,'',20000,20000,0,0,0,20000,20000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:08:18','1001',NULL),(6,'Nasir Uddin','3',1,NULL,NULL,'',40000,40000,0,0,0,40000,40000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-15 03:31:00','1001','1001'),(1,'Nasir Uddin','4',1,NULL,NULL,NULL,20000,20000,0,0,0,0,0,0,NULL,0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:08:00','1001',NULL),(2,'Nasir Uddin','4',1,NULL,NULL,NULL,20000,20000,0,0,0,0,0,0,NULL,0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:15:00','1001',NULL),(3,'Nasir Uddin','4',1,NULL,NULL,NULL,20000,20000,0,0,0,0,0,0,NULL,0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:16:00','1001',NULL),(7,'Kamal Hossain','3',1,NULL,NULL,'',20000,20000,0,0,0,20000,20000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:17:23','1001',NULL),(8,'Kamal Hossain','3',1,NULL,NULL,'',21000,21000,0,0,0,21000,21000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-15 03:31:00','1001','1001'),(9,'Jamal Hossain','3',1,NULL,NULL,'',20000,20000,0,0,0,20000,20000,0,'Cash',0,'2016-10-14',0,0,0,0,0,NULL,'2016-10-14 01:19:04','1001',NULL),(1,NULL,'5',NULL,NULL,NULL,NULL,128000,0,0,0,0,0,0,0,NULL,0,'2016-10-14',0,0,0,0,0,'','2016-10-14 01:24:12','1001',NULL),(10,'Nasir Uddin','3',1,NULL,NULL,'',20000,20000,0,0,0,20000,20000,0,'Cash',0,'2016-10-15',0,0,0,0,0,NULL,'2016-10-15 14:21:34','1001',NULL),(2,'Mamun Mia','1',NULL,NULL,NULL,NULL,80000,80000,0,0,0,80000,80000,0,'Cash',0,'2016-10-15',0,0,0,0,0,'','2016-10-15 03:01:00','1001',NULL),(3,'Mamun Mia','1',NULL,NULL,NULL,NULL,32000,32000,0,0,0,32000,32000,0,'Cash',0,'2016-10-15',0,0,0,0,0,'','2016-10-15 03:02:00','1001',NULL),(11,'Mamun Mia','3',1,NULL,NULL,'',78000,78000,0,0,0,78000,78000,0,'Cash',0,'2016-10-15',0,0,0,0,0,NULL,'2016-10-15 15:28:11','1001',NULL),(2,NULL,'5',NULL,NULL,NULL,NULL,24000,0,0,0,0,0,0,0,NULL,0,'2016-10-15',0,0,0,0,0,'','2016-10-15 15:39:31','1001',NULL),(4,'Mamun Mia','1',NULL,NULL,NULL,NULL,16000,16000,0,0,0,16000,16000,0,'Cash',0,'2016-10-15',0,0,0,0,0,'','2016-10-15 04:13:00','1001',NULL),(3,'Mamun Mia','2',NULL,NULL,NULL,NULL,40000,40000,0,0,0,40000,40000,0,'Cash',0,'2016-10-15',0,0,0,0,0,'','2016-10-15 04:51:00','1001',NULL),(12,'Nadir','3',1,NULL,NULL,'',68000,68000,0,0,0,68000,68000,0,'Cash',0,'2016-10-15',0,0,0,0,0,NULL,'2016-10-15 17:06:08','1001',NULL),(3,NULL,'5',NULL,NULL,NULL,NULL,16000,0,0,0,0,0,0,0,NULL,0,'2016-10-15',0,0,0,0,0,'','2016-10-15 17:28:05','1001',NULL);
/*!40000 ALTER TABLE `tbinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbitemcatagory`
--

DROP TABLE IF EXISTS `tbitemcatagory`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbitemcatagory` (
  `headid` int(11) DEFAULT NULL,
  `headTitle` varchar(25) DEFAULT NULL,
  `pheadId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbitemcatagory`
--

LOCK TABLES `tbitemcatagory` WRITE;
/*!40000 ALTER TABLE `tbitemcatagory` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbitemcatagory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbiteminformation`
--

DROP TABLE IF EXISTS `tbiteminformation`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbiteminformation` (
  `productId` int(11) DEFAULT NULL,
  `ProductName` varchar(30) DEFAULT NULL,
  `BrandId` varchar(10) DEFAULT NULL,
  `ModelId` varchar(10) DEFAULT NULL,
  `buyPrice` double DEFAULT NULL,
  `sellPrice` double DEFAULT NULL,
  `openingStock` int(11) DEFAULT NULL,
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbiteminformation`
--

LOCK TABLES `tbiteminformation` WRITE;
/*!40000 ALTER TABLE `tbiteminformation` DISABLE KEYS */;
INSERT INTO `tbiteminformation` VALUES (1,'Hp Lapton duton','2','2',8000,10000,20,'2016-10-14 01:39:54','1001'),(2,'Keyboard','','',450,500,10,'2016-10-15 14:25:29','1001'),(3,'hp Labtop Du10','2','2',30000,34000,10,'2016-10-15 14:25:59','1001'),(4,'Mouse','','',330,350,6,'2016-10-15 14:26:16','1001'),(5,'Dell N4050','3','4',30000,34000,5,'2016-10-15 14:26:38','1001');
/*!40000 ALTER TABLE `tbiteminformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblogin`
--

DROP TABLE IF EXISTS `tblogin`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tblogin` (
  `user_id` int(11) NOT NULL,
  `name` varchar(34) DEFAULT NULL,
  `userType` varchar(11) DEFAULT NULL,
  `username` varchar(34) DEFAULT NULL,
  `password` varchar(34) DEFAULT NULL,
  `insert` int(11) DEFAULT '0',
  `edit` int(11) DEFAULT '0',
  `delete` int(11) DEFAULT '0',
  `EntryTime` datetime DEFAULT NULL,
  `CreateBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tblogin`
--

LOCK TABLES `tblogin` WRITE;
/*!40000 ALTER TABLE `tblogin` DISABLE KEYS */;
INSERT INTO `tblogin` VALUES (1001,'Nasir Uddin','Admin','admin','nwr',1,1,1,NULL,'1001'),(1002,'Nasir Uddin','General','Nasir','123',0,0,0,NULL,'1001');
/*!40000 ALTER TABLE `tblogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpharmacystore`
--

DROP TABLE IF EXISTS `tbpharmacystore`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbpharmacystore` (
  `autoId` int(10) DEFAULT NULL,
  `productId` varchar(50) DEFAULT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `catagoryId` varchar(11) DEFAULT NULL,
  `expiredate` varchar(11) DEFAULT NULL,
  `qty` int(30) DEFAULT '0',
  `buyPrice` double DEFAULT '0',
  `sellPrice` double DEFAULT '0',
  `discount` double DEFAULT '0',
  `totalPrice` double DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `selltype` int(11) DEFAULT NULL,
  `counter` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `invoiceNo` int(11) DEFAULT NULL,
  `returnInovice` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `entryTime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL,
  `edit` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbpharmacystore`
--

LOCK TABLES `tbpharmacystore` WRITE;
/*!40000 ALTER TABLE `tbpharmacystore` DISABLE KEYS */;
INSERT INTO `tbpharmacystore` VALUES (1,'1','Hp Lapton duton','','2',NULL,4,64000,0,0,256000,1,NULL,NULL,'2016-10-13',1,NULL,1,'2016-10-15 03:02:00','1001',NULL),(2,'1','Hp Lapton duton','','2',NULL,1,64000,0,0,64000,2,NULL,NULL,'2016-10-14',1,NULL,1,'2016-10-14 12:03:00','1001',NULL),(3,'1','Hp Lapton duton','','2',NULL,2,64000,0,0,128000,2,NULL,NULL,'2016-10-14',2,NULL,1,'2016-10-15 03:08:00','1001',NULL),(4,'1','Hp Lapton duton','Pcs','2',NULL,4,0,10000,0,40000,3,1,1,'2016-10-14',1,NULL,1,'2016-10-14 00:53:13','1001','1001'),(5,'1','Hp Lapton duton','Pcs','2',NULL,3,10000,10000,0,30000,3,1,1,'2016-10-14',2,NULL,1,'2016-10-15 03:24:00','1001','1001'),(6,'1','Hp Lapton duton','Pcs','2',NULL,6,10000,10000,0,60000,3,1,1,'2016-10-14',4,NULL,1,'2016-10-15 03:26:00','1001','1001'),(7,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,3,1,1,'2016-10-14',5,NULL,1,'2016-10-14 01:08:18','1001',NULL),(8,'1','Hp Lapton duton','Pcs','2',NULL,4,0,10000,0,40000,3,1,1,'2016-10-14',6,NULL,1,'2016-10-15 03:31:00','1001','1001'),(9,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,4,1,1,'2016-10-14',1,1,0,'2016-10-14 01:08:00','1001',NULL),(10,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,4,1,1,'2016-10-14',2,2,0,'2016-10-14 01:15:00','1001',NULL),(11,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,4,1,1,'2016-10-14',3,3,0,'2016-10-14 01:16:00','1001',NULL),(12,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,3,1,1,'2016-10-14',7,NULL,1,'2016-10-14 01:17:23','1001',NULL),(13,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,4,1,1,'2016-10-14',8,4,1,'2016-10-14 01:17:58','1001',NULL),(14,'1','Hp Lapton duton','Pcs','2',NULL,2,10000,10000,0,20000,3,1,1,'2016-10-14',8,NULL,1,'2016-10-15 03:31:00','1001','1001'),(15,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,3,1,1,'2016-10-14',9,NULL,1,'2016-10-14 01:19:04','1001',NULL),(16,'1','Hp Lapton duton','','2',NULL,2,64000,0,0,128000,5,NULL,NULL,'2016-10-14',1,NULL,1,'2016-10-14 01:24:00','1001',NULL),(17,'1','Hp Lapton duton','Pcs','2',NULL,2,0,10000,0,20000,3,1,1,'2016-10-15',10,NULL,1,'2016-10-15 14:21:34','1001',NULL),(18,'1','Hp Lapton duton','','2',NULL,5,8000,0,0,80000,1,NULL,NULL,'2016-10-15',2,NULL,1,'2016-10-15 03:01:00','1001',NULL),(19,'1','Hp Lapton duton','','2',NULL,4,8000,0,0,32000,1,NULL,NULL,'2016-10-15',3,NULL,1,'2016-10-15 03:02:00','1001',NULL),(20,'2','Keyboard','','',NULL,2,450,0,0,900,1,NULL,NULL,'2016-10-15',1,NULL,1,'2016-10-15 03:02:00','1001',NULL),(21,'2','Keyboard','Pcs','',NULL,4,0,500,0,2000,3,1,1,'2016-10-14',4,NULL,1,'2016-10-15 03:26:00','1001','1001'),(22,'5','Dell N4050','Pcs','3',NULL,2,0,34000,0,68000,3,1,1,'2016-10-14',11,NULL,1,'2016-10-15 15:28:11','1001',NULL),(23,'1','Hp Lapton duton','Pcs','2',NULL,1,0,10000,0,10000,3,1,1,'2016-10-14',11,NULL,1,'2016-10-15 15:28:11','1001',NULL),(24,'2','Keyboard','Pcs','',NULL,2,500,500,0,1000,3,1,1,'2016-10-14',8,NULL,1,'2016-10-15 03:31:00','1001','1001'),(25,'1','Hp Lapton duton','','2',NULL,3,8000,0,0,24000,5,NULL,NULL,'2016-10-15',2,NULL,1,'2016-10-15 03:39:00','1001',NULL),(26,'1','Hp Lapton duton','','2',NULL,2,8000,0,0,16000,1,NULL,NULL,'2016-10-15',4,NULL,1,'2016-10-15 04:13:00','1001',NULL),(27,'1','Hp Lapton duton','','2',NULL,5,8000,0,0,40000,2,NULL,NULL,'2016-10-15',3,NULL,1,'2016-10-15 04:51:00','1001',NULL),(28,'3','hp Labtop Du10','Pcs','2',NULL,2,0,34000,0,68000,3,1,1,'2016-10-15',12,NULL,1,'2016-10-15 17:06:08','1001',NULL),(29,'1','Hp Lapton duton','','2',NULL,2,8000,0,0,16000,5,NULL,NULL,'2016-10-15',3,NULL,1,'2016-10-15 05:27:00','1001',NULL);
/*!40000 ALTER TABLE `tbpharmacystore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbsupplierinfo`
--

DROP TABLE IF EXISTS `tbsupplierinfo`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tbsupplierinfo` (
  `supplierId` int(11) DEFAULT NULL,
  `supplierName` varchar(50) DEFAULT NULL,
  `entrytime` datetime DEFAULT NULL,
  `createBy` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tbsupplierinfo`
--

LOCK TABLES `tbsupplierinfo` WRITE;
/*!40000 ALTER TABLE `tbsupplierinfo` DISABLE KEYS */;
INSERT INTO `tbsupplierinfo` VALUES (1,'Mamun Mia','2016-10-10 00:59:26','1001');
/*!40000 ALTER TABLE `tbsupplierinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-27  9:02:11
