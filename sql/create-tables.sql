SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fablab
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fablab` ;
CREATE SCHEMA IF NOT EXISTS `fablab` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `fablab` ;

-- -----------------------------------------------------
-- Table `fablab`.`t_membership_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_membership_type` (
  `membership_type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `duration` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`membership_type_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `membership_type_id` INT NOT NULL,
  `password` VARCHAR(64) NULL,
  `password_salt` VARCHAR(45) NULL DEFAULT 'fablab',
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL DEFAULT 'nomail',
  `date_inscr` DATETIME NOT NULL,
  `balance` FLOAT NOT NULL DEFAULT 0,
  `rfid` CHAR(16) NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(200) NULL,
  `birthdate` DATE NULL,
  `gender` TINYINT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `rfid_UNIQUE` (`rfid` ASC),
  INDEX `fk_t_users_t_membership_type1_idx` (`membership_type_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_t_users_t_membership_type1`
    FOREIGN KEY (`membership_type_id`)
    REFERENCES `fablab`.`t_membership_type` (`membership_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_machine_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_machine_type` (
  `machine_type_id` INT NOT NULL AUTO_INCREMENT,
  `technicalname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `restricted` TINYINT(1) NOT NULL,
  PRIMARY KEY (`machine_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_machine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_machine` (
  `machine_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `machine_type_id` INT NOT NULL,
  PRIMARY KEY (`machine_id`),
  INDEX `fk_t_machines_t_machine_type1_idx` (`machine_type_id` ASC),
  CONSTRAINT `fk_t_machines_t_machine_type1`
    FOREIGN KEY (`machine_type_id`)
    REFERENCES `fablab`.`t_machine_type` (`machine_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_reservation` (
  `reservation_id` INT NOT NULL AUTO_INCREMENT,
  `date_start` DATETIME NOT NULL,
  `date_end` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `machine_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `fk_t_reservation_t_users_idx` (`user_id` ASC),
  INDEX `fk_t_reservation_t_machines1_idx` (`machine_id` ASC),
  CONSTRAINT `fk_t_reservation_t_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_reservation_t_machines1`
    FOREIGN KEY (`machine_id`)
    REFERENCES `fablab`.`t_machine` (`machine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`r_price_machine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`r_price_machine` (
  `machine_type_id` INT NOT NULL,
  `membership_type_id` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`machine_type_id`, `membership_type_id`),
  INDEX `fk_r_price_t_machine_type1_idx` (`machine_type_id` ASC),
  INDEX `fk_r_price_t_membership_type1_idx` (`membership_type_id` ASC),
  CONSTRAINT `fk_r_price_t_machine_type1`
    FOREIGN KEY (`machine_type_id`)
    REFERENCES `fablab`.`t_machine_type` (`machine_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_price_t_membership_type1`
    FOREIGN KEY (`membership_type_id`)
    REFERENCES `fablab`.`t_membership_type` (`membership_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `total` FLOAT NOT NULL,
  `date_payement` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `cashier_id` INT NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_t_payment_t_users2_idx` (`user_id` ASC),
  INDEX `fk_t_payment_t_users1_idx` (`cashier_id` ASC),
  CONSTRAINT `fk_t_payment_t_users2`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_payment_t_users1`
    FOREIGN KEY (`cashier_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_usage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_usage` (
  `usage_id` INT NOT NULL AUTO_INCREMENT,
  `date_start` DATETIME NOT NULL,
  `price_hour` DOUBLE NOT NULL DEFAULT -1,
  `minutes` INT(11) NOT NULL,
  `additional_cost` FLOAT NOT NULL,
  `user_id` INT NOT NULL,
  `machine_id` INT NOT NULL,
  `membership_type_id` INT NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`usage_id`),
  INDEX `fk_t_utilisations_t_users1_idx` (`user_id` ASC),
  INDEX `fk_t_utilisations_t_machines1_idx` (`machine_id` ASC),
  INDEX `fk_t_usages_t_membership_type1_idx` (`membership_type_id` ASC),
  CONSTRAINT `fk_t_utilisations_t_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_utilisations_t_machines1`
    FOREIGN KEY (`machine_id`)
    REFERENCES `fablab`.`t_machine` (`machine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_usages_t_membership_type1`
    FOREIGN KEY (`membership_type_id`)
    REFERENCES `fablab`.`t_membership_type` (`membership_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `technicalname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`group_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`r_group_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`r_group_user` (
  `group_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`group_id`, `user_id`),
  INDEX `fk_t_groups_has_t_users_t_groups1_idx` (`group_id` ASC),
  INDEX `fk_r_group_user_t_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_t_groups_has_t_users_t_groups1`
    FOREIGN KEY (`group_id`)
    REFERENCES `fablab`.`t_group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_group_user_t_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_audit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_audit` (
  `audit_id` INT NOT NULL AUTO_INCREMENT,
  `who` INT NULL,
  `action` INT NOT NULL,
  `object` INT NOT NULL,
  `object_id` INT NULL,
  `dateandtime` DATETIME NOT NULL,
  `success` TINYINT(1) NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `detail` TEXT NULL,
  PRIMARY KEY (`audit_id`),
  INDEX `fk_t_audits_t_users1_idx` (`who` ASC),
  CONSTRAINT `fk_t_audits_t_users1`
    FOREIGN KEY (`who`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_subscription` (
  `subscription_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Is like a usage. User has to pay for it',
  `user_id` INT NOT NULL,
  `membership_type_id` INT NOT NULL,
  `date_subscription` DATETIME NOT NULL,
  `price` DOUBLE NOT NULL,
  `duration` INT NOT NULL,
  `comment` VARCHAR(255) NULL,
  PRIMARY KEY (`subscription_id`),
  INDEX `fk_t_membership_t_users1_idx` (`user_id` ASC),
  INDEX `fk_t_subscription_t_membership_type1_idx` (`membership_type_id` ASC),
  CONSTRAINT `fk_t_membership_t_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_subscription_t_membership_type1`
    FOREIGN KEY (`membership_type_id`)
    REFERENCES `fablab`.`t_membership_type` (`membership_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`r_user_authorized_machine_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`r_user_authorized_machine_type` (
  `user_id` INT NOT NULL,
  `machine_type_id` INT NOT NULL,
  `formation_date` DATE NOT NULL,
  PRIMARY KEY (`user_id`, `machine_type_id`),
  INDEX `fk_t_user_has_t_machine_type_t_machine_type1_idx` (`machine_type_id` ASC),
  INDEX `fk_t_user_has_t_machine_type_t_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_t_user_has_t_machine_type_t_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fablab`.`t_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_user_has_t_machine_type_t_machine_type1`
    FOREIGN KEY (`machine_type_id`)
    REFERENCES `fablab`.`t_machine_type` (`machine_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `technicalname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `technicalname_UNIQUE` (`technicalname` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`r_group_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`r_group_role` (
  `role_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `group_id`),
  INDEX `fk_t_roles_has_t_group_t_group1_idx` (`group_id` ASC),
  INDEX `fk_t_roles_has_t_group_t_roles1_idx` (`role_id` ASC),
  CONSTRAINT `fk_t_roles_has_t_group_t_roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `fablab`.`t_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_roles_has_t_group_t_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `fablab`.`t_group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fablab`.`t_configuration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`t_configuration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `conf_key` VARCHAR(45) NOT NULL,
  `conf_value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `conf_key_UNIQUE` (`conf_key` ASC))
ENGINE = InnoDB;

USE `fablab` ;

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_group_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_group_user` (`email` INT, `group_id` INT, `user_id` INT, `technicalname` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance` (`user_id` INT, `total` INT, `nb` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance_subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance_subscription` (`firstname` INT, `lastname` INT, `user_id` INT, `total` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance_usage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance_usage` (`firstname` INT, `lastname` INT, `user_id` INT, `total` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance_payment` (`firstname` INT, `lastname` INT, `user_id` INT, `total` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance_all`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance_all` (`user_id` INT, `total` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_role_by_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_role_by_user` (`email` INT, `technicalname` INT);

-- -----------------------------------------------------
-- Placeholder table for view `fablab`.`v_user_balance_grouped`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fablab`.`v_user_balance_grouped` (`user_id` INT, `balance` INT);

-- -----------------------------------------------------
-- View `fablab`.`v_group_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_group_user`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_group_user` AS
	SELECT u.email, r.*, g.technicalname
	FROM r_group_user AS r
	INNER JOIN t_group AS g USING(group_id)
	INNER JOIN t_user AS u USING(user_id);

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance` AS
SELECT user_id, SUM(total) total, COUNT(user_id) as nb
FROM v_user_balance_all
GROUP BY user_id;

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance_subscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance_subscription`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance_subscription` AS
SELECT u.firstname, u.lastname, u.user_id, -s.price AS total
FROM t_user AS u
INNER JOIN t_subscription AS s ON u.user_id=s.user_id
;

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance_usage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance_usage`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance_usage` AS
SELECT u.firstname, u.lastname, u.user_id, -(a.additional_cost+a.minutes/60*a.price_hour) AS total
FROM t_user AS u
INNER JOIN t_usage AS a ON u.user_id=a.user_id;

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance_payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance_payment`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance_payment` AS
SELECT u.firstname, u.lastname, u.user_id, p.total
FROM t_user u
INNER JOIN t_payment p ON u.user_id = p.user_id;

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance_all`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance_all`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance_all` AS
SELECT user_id, total FROM v_user_balance_subscription
UNION ALL
SELECT user_id, total FROM v_user_balance_usage
UNION ALL
SELECT user_id, total FROM v_user_balance_payment;

-- -----------------------------------------------------
-- View `fablab`.`v_role_by_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_role_by_user`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_role_by_user` AS
SELECT DISTINCT u.email, r.technicalname
FROM t_user AS u
INNER JOIN r_group_user AS gu ON u.user_id=gu.user_id
INNER JOIN r_group_role AS gr ON gu.group_id = gr.group_id
INNER JOIN t_role AS r ON gr.role_id = r.role_id;

-- -----------------------------------------------------
-- View `fablab`.`v_user_balance_grouped`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fablab`.`v_user_balance_grouped`;
USE `fablab`;
CREATE  OR REPLACE VIEW `v_user_balance_grouped` AS
SELECT u.user_id, coalesce(SUM(total), 0) as balance
FROM t_user u
LEFT JOIN v_user_balance_all b USING(user_id)
GROUP BY u.user_id
;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


COMMIT;


