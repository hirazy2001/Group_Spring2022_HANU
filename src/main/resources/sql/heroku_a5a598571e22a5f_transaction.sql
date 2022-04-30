-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: us-cdbr-east-05.cleardb.net    Database: heroku_a5a598571e22a5f
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `purpose` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `from_account_id` bigint(20) DEFAULT NULL,
  `to_account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrff4jlxetafju1e5cks5mfcnk` (`from_account_id`),
  KEY `FKluqt8k2pa8d4gmggx4rhl5vgv` (`to_account_id`),
  CONSTRAINT `FKluqt8k2pa8d4gmggx4rhl5vgv` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKrff4jlxetafju1e5cks5mfcnk` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (6,8.00,'2022-04-27 06:59:28','Hello bro!','TRANSFER','2022-04-27 06:59:28',2,5),(20,2.00,'2022-04-30 08:19:38','hehe','TRANSFER','2022-04-30 08:19:38',18,2),(21,14.00,'2022-04-30 09:09:20','Chuyen tien','TRANSFER','2022-04-30 09:09:20',2,3),(24,5.00,'2022-04-30 13:55:16','Dong tien hoc','WITHDRAW','2022-04-30 13:55:16',23,NULL),(27,20.00,'2022-04-30 14:29:32','Nop tien','TRANSFER','2022-04-30 14:29:32',2,26),(33,20.00,'2022-04-30 15:35:16','Nop tien','TRANSFER','2022-04-30 15:35:16',2,31),(35,15.00,'2022-04-30 15:36:50','Chuyen tien','TRANSFER','2022-04-30 15:36:50',31,18);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-30 23:23:25
