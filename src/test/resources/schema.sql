CREATE TABLE IF NOT EXISTS `band`(
    `band_id` smallint not null AUTO_INCREMENT,
    `band_name` varchar(30),
    PRIMARY KEY (band_id)
);

CREATE TABLE IF NOT EXISTS `responsibility`(
    `responsibility_id` smallint not null PRIMARY KEY AUTO_INCREMENT,
    `responsibility_name` varchar(30) 
);

CREATE TABLE IF NOT EXISTS `capability` (
  `capability_id` smallint NOT NULL AUTO_INCREMENT,
  `capability_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`capability_id`)
);

CREATE TABLE IF NOT EXISTS `employee_role`(
    `role_id` smallint not null AUTO_INCREMENT,
    `role_name` varchar(40) not null,
    `specification` varchar(300),
    `capability_id` smallint,
    `band_id` smallint not null,
    `responsibility_id` smallint not null,
    PRIMARY KEY (role_id),
    FOREIGN KEY (band_id) REFERENCES band(band_id),
    FOREIGN KEY (capability_id) REFERENCES capability(capability_id),
    FOREIGN KEY (responsibility_id) REFERENCES responsibility(responsibility_id)
);
