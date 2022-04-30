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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dob` datetime DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `gender` bit(1) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `middle_name` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `picture` varchar(1000) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKjolnwy9lwp82aoyavymxpolhl` (`phone_number`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Hehe','2022-04-26 19:07:40','2002-08-21 00:00:00','19010401422824@s.hanu.edu.vn','Le ',_binary '','Nang','Duc','$2a$10$lmpzTn.fB54xmQfngxPzduCQUCtcyedApyKtz3hfEYGCw0QzhQBX2','0394286950','https://scontent.fhan4-3.fna.fbcdn.net/v/t1.6435-9/142915405_106600208107240_8287176908190349092_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=RnQAoyWscEUAX8IM3Yb&_nc_ht=scontent.fhan4-3.fna&oh=00_AT8hFb5EP8V1VOsDPoPWy5vovnytAw_Jdx93WU1qDP9iLQ&oe=628A1F86','USER','2022-04-26 19:07:40','nang1234'),(4,'Hehe','2022-04-27 04:48:00','2001-08-26 00:00:00','1901040122824@s.hanu.edu.vn','Tran',_binary '','Tung','Tien','$2a$10$cM1FWEHtdk3EvwVL6LHsZ.4r.TC2.OIhqkgnDfYgu8WnXqfOWrOa6','0394286951',NULL,'USER','2022-04-27 04:48:00','nang12345'),(7,'Hehe','2022-04-27 10:46:11','2001-08-26 00:00:00','19010401228524@s.hanu.edu.vn','Tran',_binary '','Tung','Tien','$2a$10$utsRlgWoGSyPYMjb4i41JeOz1v9Y5DbsWVdN0CW2hAXMOdc5090oe','0324286951',NULL,'USER','2022-04-27 10:46:11','nang122345'),(9,'Hehe','2022-04-27 12:56:10','2001-08-26 00:00:00','1901040195@s.hanu.edu.vn','Tran 1',_binary '','Tung','Tien 1','$2a$10$kAEaUn.d6IM5/vqpzdefr.yJc/GGuvM618ffO84TKOTUN0i9M8HsO','0324286950','https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp','USER','2022-04-27 12:56:10','nangle123456'),(10,'HCM','2022-04-27 15:33:52','2022-03-28 00:00:00','vana@gmail.com','Nguyen',_binary '','A','Van','$2a$10$p7wECGK9XJ2AW1rx2lWrj.QTgaKS4njiupo2ake6lXKYl90JdcSfu','0336525252',NULL,'USER','2022-04-27 15:33:52','vana'),(11,'Ha Noi','2022-04-29 15:55:50','2001-08-26 00:00:00','nangle123456@gmail.com','Le',_binary '','Nang','Duc','$2a$10$D6m4pWOlaOmmAVbGENZFAe5b5q48hkLG1xy5N3pOfZuTp3J1zKi.a','0397286900',NULL,'USER','2022-04-29 15:55:50','nangle123456@gmail.com'),(12,'Hehe','2022-04-30 04:13:15','2001-08-26 00:00:00','1901060195@s.hanu.edu.vn','Tran',_binary '','Tung','Tien','$2a$10$9IwafeyNuopzkCp9zE/MB.NCri8IU6ktP8Xwz9QcHk63uUMlYxIBa','0324386950',NULL,'USER','2022-04-30 04:13:15','nangle1234'),(14,'Nam Dinh','2022-04-30 05:28:47','2001-02-25 00:00:00','hung2522001@gmail.com','Tung',_binary '','KeslyKomen','Rob','$2a$10$cNPpDChfwjbRCKYn9qp3XOSSCD0rmJQko77k0T5Qp16PVbpTeT8Xq','0359433597',NULL,'USER','2022-04-30 05:28:47','TungEVOLHanh'),(15,'Số 39 Ngõ 102 Nguyễn Đình Hoàn, Nghĩa Đô, Cầu Giấy, Hà Nội','2022-04-30 05:35:55','2001-12-29 00:00:00','tdtu29@gmail.com','Trinh',_binary '','Dinh Tu','0365092009','$2a$10$az/CYDJ2cstPk0KyB3TfAO8vVBke.a7dHwXly5bCFpL1O9wn8jy3.','0365092009',NULL,'USER','2022-04-30 05:35:55','tudinh'),(17,'Ha Noi','2022-04-30 06:36:33','2022-03-30 00:00:00','nangle12345@gmail.com','Le',_binary '','Nang','Duc','$2a$10$/Jyx/JvoorzNT.HOnm55y.alX2wucdJp1iBe.qUUuaGFYODLQWKpy','0397286865','https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp','USER','2022-04-30 06:36:33','nangle123'),(19,'ass aasasas','2022-04-30 07:37:04','2022-04-30 00:00:00','nang123456789@gmai.com','Hi',_binary '\0','He','Ho','$2a$10$cEcdGjuQwT7f4aR/ivJO/OwYQ1Egy6G5IKwK8xfbU5.W4NhFlZhMe','0396286900',NULL,'USER','2022-04-30 07:37:04','nang123456789'),(29,'Ha Noi','2022-04-30 15:23:37','2022-04-30 00:00:00','nangle12345612@gmail.com','Le',_binary '','Nang','Duc','$2a$10$y7a.jyBHj0S/wXiC19dq2e5i4KKBlCWucFWXiFKOuvV.CtFUZwBZ6','0397285400',NULL,'USER','2022-04-30 15:23:37','nangle12389'),(32,'sontay','2022-04-30 15:35:15','2001-01-06 00:00:00','nguyenhoanglanhtc@gmail.com','Nguyen',_binary '','Lan','Hoang','$2a$10$RphDZHW0JQweWsMyQLX9Uez4Cn1vnv0he27WvZ5upG8OFg1qrlCdq','0987656711','https://img.nimo.tv/t/1599514158915/202105041620141250244_1599514158915_avatar.png/w120_l0/img.webp','USER','2022-04-30 15:35:15','lan123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-30 23:23:51
