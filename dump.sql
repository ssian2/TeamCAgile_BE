-- MySQL dump 10.13  Distrib 8.0.25, for macos11.3 (x86_64)
--
-- Host: academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com    Database: TeamCWebApp
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `band`
--

DROP TABLE IF EXISTS `band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `band` (
  `band_id` smallint NOT NULL AUTO_INCREMENT,
  `band_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`band_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `band`
--

LOCK TABLES `band` WRITE;
/*!40000 ALTER TABLE `band` DISABLE KEYS */;
INSERT INTO `band` VALUES (1,'test band name'),(2,'Apprentice'),(3,'Trainee'),(4,'Associate'),(5,'Senior Associate'),(6,'Consultant'),(7,'Manager'),(8,'Principal');
/*!40000 ALTER TABLE `band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capability`
--

DROP TABLE IF EXISTS `capability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `capability` (
  `capability_id` smallint NOT NULL AUTO_INCREMENT,
  `capability_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`capability_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capability`
--

LOCK TABLES `capability` WRITE;
/*!40000 ALTER TABLE `capability` DISABLE KEYS */;
INSERT INTO `capability` VALUES (1,'test cap name'),(2,'Applied Innovation'),(3,'Business Development'),(4,'Product'),(5,'Cyber Security'),(6,'Data & Analytics'),(7,'Engineering'),(8,'Delivery Management'),(9,'Platforms');
/*!40000 ALTER TABLE `capability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competency`
--

DROP TABLE IF EXISTS `competency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competency` (
  `competency_id` tinyint NOT NULL AUTO_INCREMENT,
  `competency_type_id` tinyint NOT NULL,
  `band_id` smallint NOT NULL,
  `competency_description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`competency_id`),
  UNIQUE KEY `competency_type_id_2` (`competency_type_id`,`band_id`),
  KEY `competency_type_id` (`competency_type_id`),
  KEY `band_id` (`band_id`),
  CONSTRAINT `competency_ibfk1` FOREIGN KEY (`competency_type_id`) REFERENCES `competency_type` (`competency_type_id`),
  CONSTRAINT `competency_ibfk2` FOREIGN KEY (`band_id`) REFERENCES `band` (`band_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competency`
--

LOCK TABLES `competency` WRITE;
/*!40000 ALTER TABLE `competency` DISABLE KEYS */;
INSERT INTO `competency` VALUES (1,1,2,'Placeholder description for App. C.A'),(2,2,2,'Placeholder description for App. P&O'),(3,3,2,'Placeholder description for App. I&C.I'),(4,4,2,'Placeholder description for App. J.S.K'),(5,5,2,'Placeholder description for App. D.Y&O'),(6,6,2,'Placeholder description for App. C&T'),(7,7,2,'Placeholder description for App. C.F'),(8,7,3,'Placeholder description for Train. C.F'),(9,6,3,'Placeholder description for Train. C&T');
/*!40000 ALTER TABLE `competency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competency_type`
--

DROP TABLE IF EXISTS `competency_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competency_type` (
  `competency_type_id` tinyint NOT NULL AUTO_INCREMENT,
  `competency_type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`competency_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competency_type`
--

LOCK TABLES `competency_type` WRITE;
/*!40000 ALTER TABLE `competency_type` DISABLE KEYS */;
INSERT INTO `competency_type` VALUES (1,'Commercial Awareness'),(2,'Planning and Organising'),(3,'Innovation and Continuous Inprovement'),(4,'Job Specific Knowledge'),(5,'Developing Yourself and Others'),(6,'Communicating & Teamwork'),(7,'Customer Focus');
/*!40000 ALTER TABLE `competency_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_role` (
  `role_id` smallint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(40) NOT NULL,
  `specification` varchar(300) DEFAULT NULL,
  `spec_doc_url` varchar(300) DEFAULT NULL,
  `capability_id` smallint DEFAULT NULL,
  `band_id` smallint NOT NULL,
  `responsibility_id` smallint NOT NULL,
  `job_family_id` smallint DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `band_id` (`band_id`),
  KEY `capability_id` (`capability_id`),
  KEY `responsibility_id` (`responsibility_id`),
  KEY `job_family_id` (`job_family_id`),
  CONSTRAINT `employee_role_ibfk_1` FOREIGN KEY (`band_id`) REFERENCES `band` (`band_id`),
  CONSTRAINT `employee_role_ibfk_2` FOREIGN KEY (`capability_id`) REFERENCES `capability` (`capability_id`),
  CONSTRAINT `employee_role_ibfk_3` FOREIGN KEY (`responsibility_id`) REFERENCES `responsibility` (`responsibility_id`),
  CONSTRAINT `employee_role_ibfk_4` FOREIGN KEY (`job_family_id`) REFERENCES `job_family` (`job_family_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (1,'test role','test spec','google.com',1,1,1,9),(2,'Test Engineer','As a Test Engineer (Associate) in Kainos, you’ll be responsible for good quality of the software',NULL,5,4,1,12),(5,'Software Engineer','As a Software Engineer in Kainos, you’ll be responsible for\n developing high quality solutions which delight our customers and impact the lives of users worldwide.',NULL,7,3,1,3),(6,'Software Engineer','As a Software Engineer in Kainos, you’ll be responsible for\n developing high quality solutions which delight our customers and impact the lives of users worldwide.',NULL,5,4,1,7),(7,'Product Specialist','As a Product Specialist at Kainos you will be responsible for delivering high quality solutions which delight our customers and\nimpact the lives of users worldwide.',NULL,4,8,1,11);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_family`
--

DROP TABLE IF EXISTS `job_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_family` (
  `job_family_id` smallint NOT NULL AUTO_INCREMENT,
  `job_family_name` varchar(60) NOT NULL,
  `capability_id` smallint DEFAULT NULL,
  PRIMARY KEY (`job_family_id`),
  KEY `capability_id` (`capability_id`),
  CONSTRAINT `job_family_ibfk_1` FOREIGN KEY (`capability_id`) REFERENCES `capability` (`capability_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_family`
--

LOCK TABLES `job_family` WRITE;
/*!40000 ALTER TABLE `job_family` DISABLE KEYS */;
INSERT INTO `job_family` VALUES (1,'Corporate Security',5),(2,'Engineering Strategy and Planning',7),(3,'Engineering',7),(4,'Architecture',7),(5,'Testing and Quality Assurance',7),(6,'Product Specialist',7),(7,'Security Strategy and Planning',5),(9,'Test Job family',1),(10,'Platform Engineering',2),(11,'Product Consultancy',4),(12,'Testing and Quality Assurance',5);
/*!40000 ALTER TABLE `job_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsibility`
--

DROP TABLE IF EXISTS `responsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsibility` (
  `responsibility_id` smallint NOT NULL AUTO_INCREMENT,
  `responsibility_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`responsibility_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsibility`
--

LOCK TABLES `responsibility` WRITE;
/*!40000 ALTER TABLE `responsibility` DISABLE KEYS */;
INSERT INTO `responsibility` VALUES (1,'test responsibility name'),(2,'developing high quality soluti');
/*!40000 ALTER TABLE `responsibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsibility_employee_role`
--

DROP TABLE IF EXISTS `responsibility_employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsibility_employee_role` (
  `indexing_id` smallint NOT NULL AUTO_INCREMENT,
  `responsibility_id` smallint NOT NULL,
  `role_id` smallint NOT NULL,
  PRIMARY KEY (`indexing_id`),
  KEY `responsibility_id` (`responsibility_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `responsibility_employee_role_ibfk_1` FOREIGN KEY (`responsibility_id`) REFERENCES `responsibility` (`responsibility_id`),
  CONSTRAINT `responsibility_employee_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `employee_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsibility_employee_role`
--

LOCK TABLES `responsibility_employee_role` WRITE;
/*!40000 ALTER TABLE `responsibility_employee_role` DISABLE KEYS */;
INSERT INTO `responsibility_employee_role` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `responsibility_employee_role` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-26 11:27:39
