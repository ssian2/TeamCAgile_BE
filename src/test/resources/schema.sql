--DROP TABLE IF EXISTS `employee_role`;

CREATE TABLE IF NOT EXISTS `employee_role` (
  `role_id` smallint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(40) NOT NULL,
  `specification` varchar(300) DEFAULT NULL,
  `capability_id` smallint DEFAULT NULL,
  `band_id` smallint NOT NULL,
  `responsibility_id` smallint NOT NULL,
  PRIMARY KEY (`role_id`)--,
--  Foreign keys aren't here?
--  KEY `band_id` (`band_id`),
--  KEY `capability_id` (`capability_id`),
--  KEY `responsibility_id` (`responsibility_id`),
--  CONSTRAINT `employee_role_ibfk_1` FOREIGN KEY (`band_id`) REFERENCES `band` (`band_id`),
--  CONSTRAINT `employee_role_ibfk_2` FOREIGN KEY (`capability_id`) REFERENCES `capability` (`capability_id`),
--  CONSTRAINT `employee_role_ibfk_3` FOREIGN KEY (`responsibility_id`) REFERENCES `responsibility` (`responsibility_id`)
);

CREATE TABLE IF NOT EXISTS `capability` (
    `capability_id` smallint NOT NULL AUTO_INCREMENT,
     `capability_name` varchar(60) DEFAULT NULL,
     PRIMARY KEY (`capability_id`)
);

CREATE TABLE IF NOT EXISTS `band`(
    `band_id` smallint not null AUTO_INCREMENT,
    `band_name` varchar(30),
    PRIMARY KEY (band_id)
);

CREATE TABLE IF NOT EXISTS `responsibility`(
    `responsibility_id` smallint not null PRIMARY KEY AUTO_INCREMENT,
    `responsibility_name` varchar(30) 
);


CREATE TABLE IF NOT EXISTS `job_family` (
    `job_family_id` smallint NOT NULL AUTO_INCREMENT,
    `job_family_name` varchar(60) NOT NULL,
    `capability_id` smallint DEFAULT NULL,
    PRIMARY KEY (`job_family_id`),
    FOREIGN KEY (`capability_id`) REFERENCES `capability` (`capability_id`)
);