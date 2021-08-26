
USE TeamCWebApp;

DROP TABLE IF EXISTS `band_competency`;
DROP TABLE IF EXISTS `competency`;
CREATE TABLE `competency` (
  `competency_id` smallint NOT NULL AUTO_INCREMENT,
  `competency_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`competency_id`)
);

CREATE TABLE `band_competency` (
  `band_id` smallint NOT NULL,
  `competency_id` smallint NOT NULL,
  `competency_description` varchar(500),
  KEY `band_id` (`band_id`),
  KEY `competency_id` (`competency_id`),
  CONSTRAINT `band_competency_ibfk_1` FOREIGN KEY (`band_id`) REFERENCES `band` (`band_id`),
  CONSTRAINT `band_competency_ibfk_2` FOREIGN KEY (`competency_id`) REFERENCES `competency` (`competency_id`),
  CONSTRAINT `band_competency_uniq_1` UNIQUE (`band_id`, `competency_id`)
);

LOCK TABLES `competency` WRITE;
INSERT INTO `competency` VALUES 
    (1,'test competency'),
    (2,'Commercial Awareness'),
    (3,'Planning and Organising'),
    (4,'Innovation & Continuous Improvement'),
    (5,'Job Specific Knowledge'),
    (6,'Developing Yourself and Others'),
    (7,'Communicating & Teamwork'),
    (8,'Customer Focus');
UNLOCK TABLES;

LOCK TABLES `band_competency` WRITE;
INSERT INTO `band_competency` VALUES 
    (1, 1, 'test description'),
    (2, 2, 'Description of Commercial Awareness for Apprentices'),
    (3, 2, 'Description of Commercial Awareness for Trainees'),
    (4, 4, 'Description of Innovation and Continuous Improvement for Associates');
UNLOCK TABLES;