

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

CREATE TABLE r_group_role (
  role_id int(11) NOT NULL,
  group_id int(11) NOT NULL,
  PRIMARY KEY (role_id,group_id),
  KEY fk_t_roles_has_t_group_t_group1_idx (group_id),
  KEY fk_t_roles_has_t_group_t_roles1_idx (role_id),
  CONSTRAINT fk_t_roles_has_t_group_t_group1 FOREIGN KEY (group_id) REFERENCES t_group (group_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_roles_has_t_group_t_roles1 FOREIGN KEY (role_id) REFERENCES t_role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE r_group_user (
  group_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  PRIMARY KEY (group_id,user_id),
  KEY fk_t_groups_has_t_users_t_groups1_idx (group_id),
  KEY fk_r_group_user_t_user1_idx (user_id),
  CONSTRAINT fk_r_group_user_t_user1 FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_groups_has_t_users_t_groups1 FOREIGN KEY (group_id) REFERENCES t_group (group_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE r_price_machine (
  machine_type_id int(11) NOT NULL,
  membership_type_id int(11) NOT NULL,
  price float NOT NULL,
  PRIMARY KEY (machine_type_id,membership_type_id),
  KEY fk_r_price_t_machine_type1_idx (machine_type_id),
  KEY fk_r_price_t_membership_type1_idx (membership_type_id),
  CONSTRAINT fk_r_price_t_machine_type1 FOREIGN KEY (machine_type_id) REFERENCES t_machine_type (machine_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_r_price_t_membership_type1 FOREIGN KEY (membership_type_id) REFERENCES t_membership_type (membership_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE r_user_authorized_machine_type (
  user_id int(11) NOT NULL,
  machine_type_id int(11) NOT NULL,
  formation_date date NOT NULL,
  PRIMARY KEY (user_id,machine_type_id),
  KEY fk_t_user_has_t_machine_type_t_machine_type1_idx (machine_type_id),
  KEY fk_t_user_has_t_machine_type_t_user1_idx (user_id),
  CONSTRAINT fk_t_user_has_t_machine_type_t_machine_type1 FOREIGN KEY (machine_type_id) REFERENCES t_machine_type (machine_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_user_has_t_machine_type_t_user1 FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_audit (
  audit_id int(11) NOT NULL AUTO_INCREMENT,
  who int(11) DEFAULT NULL,
  action int(11) NOT NULL,
  object int(11) NOT NULL,
  object_id int(11) DEFAULT NULL,
  dateandtime datetime NOT NULL,
  success tinyint(1) NOT NULL,
  content varchar(1000) NOT NULL,
  detail text,
  PRIMARY KEY (audit_id),
  KEY fk_t_audits_t_users1_idx (who),
  CONSTRAINT fk_t_audits_t_users1 FOREIGN KEY (who) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5152 DEFAULT CHARSET=utf8;

CREATE TABLE t_configuration (
  id int(11) NOT NULL AUTO_INCREMENT,
  conf_key varchar(45) NOT NULL,
  conf_value varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY conf_key_UNIQUE (conf_key)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE t_group (
  group_id int(11) NOT NULL AUTO_INCREMENT,
  technicalname varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  PRIMARY KEY (group_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE t_machine (
  machine_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  machine_type_id int(11) NOT NULL,
  PRIMARY KEY (machine_id),
  KEY fk_t_machines_t_machine_type1_idx (machine_type_id),
  CONSTRAINT fk_t_machines_t_machine_type1 FOREIGN KEY (machine_type_id) REFERENCES t_machine_type (machine_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE t_machine_type (
  machine_type_id int(11) NOT NULL AUTO_INCREMENT,
  technicalname varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  restricted tinyint(1) NOT NULL,
  PRIMARY KEY (machine_type_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE t_membership_type (
  membership_type_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  duration int(11) NOT NULL,
  price double NOT NULL,
  PRIMARY KEY (membership_type_id),
  UNIQUE KEY name_UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE t_payment (
  payment_id int(11) NOT NULL AUTO_INCREMENT,
  total float NOT NULL,
  date_payement datetime NOT NULL,
  user_id int(11) NOT NULL,
  cashier_id int(11) NOT NULL,
  comment varchar(255) DEFAULT NULL,
  PRIMARY KEY (payment_id),
  KEY fk_t_payment_t_users2_idx (user_id),
  KEY fk_t_payment_t_users1_idx (cashier_id),
  CONSTRAINT fk_t_payment_t_users1 FOREIGN KEY (cashier_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_payment_t_users2 FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=463 DEFAULT CHARSET=utf8;

CREATE TABLE t_reservation (
  reservation_id int(11) NOT NULL AUTO_INCREMENT,
  date_start datetime NOT NULL,
  date_end datetime NOT NULL,
  user_id int(11) NOT NULL,
  machine_id int(11) NOT NULL,
  PRIMARY KEY (reservation_id),
  KEY fk_t_reservation_t_users_idx (user_id),
  KEY fk_t_reservation_t_machines1_idx (machine_id),
  CONSTRAINT fk_t_reservation_t_machines1 FOREIGN KEY (machine_id) REFERENCES t_machine (machine_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_reservation_t_users FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE t_role (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  technicalname varchar(45) NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE KEY technicalname_UNIQUE (technicalname)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE t_subscription (
  subscription_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Is like a usage. User has to pay for it',
  user_id int(11) NOT NULL,
  membership_type_id int(11) NOT NULL,
  date_subscription datetime NOT NULL,
  price double NOT NULL,
  duration int(11) NOT NULL,
  comment varchar(255) DEFAULT NULL,
  PRIMARY KEY (subscription_id),
  KEY fk_t_membership_t_users1_idx (user_id),
  KEY fk_t_subscription_t_membership_type1_idx (membership_type_id),
  CONSTRAINT fk_t_membership_t_users1 FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_subscription_t_membership_type1 FOREIGN KEY (membership_type_id) REFERENCES t_membership_type (membership_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;

CREATE TABLE t_usage (
  usage_id int(11) NOT NULL AUTO_INCREMENT,
  date_start datetime NOT NULL,
  price_hour double NOT NULL DEFAULT '-1',
  minutes int(11) NOT NULL,
  additional_cost float NOT NULL,
  user_id int(11) NOT NULL,
  machine_id int(11) NOT NULL,
  membership_type_id int(11) NOT NULL,
  comment varchar(255) DEFAULT NULL,
  PRIMARY KEY (usage_id),
  KEY fk_t_utilisations_t_users1_idx (user_id),
  KEY fk_t_utilisations_t_machines1_idx (machine_id),
  KEY fk_t_usages_t_membership_type1_idx (membership_type_id),
  CONSTRAINT fk_t_usages_t_membership_type1 FOREIGN KEY (membership_type_id) REFERENCES t_membership_type (membership_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_utilisations_t_machines1 FOREIGN KEY (machine_id) REFERENCES t_machine (machine_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_t_utilisations_t_users1 FOREIGN KEY (user_id) REFERENCES t_user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=574 DEFAULT CHARSET=utf8;

CREATE TABLE t_user (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  membership_type_id int(11) NOT NULL,
  password varchar(64) DEFAULT NULL,
  password_salt varchar(45) DEFAULT 'fablab',
  firstname varchar(45) NOT NULL,
  lastname varchar(45) NOT NULL,
  email varchar(100) NOT NULL DEFAULT 'nomail',
  date_inscr datetime NOT NULL,
  balance float NOT NULL DEFAULT '0',
  rfid char(16) DEFAULT NULL,
  enabled tinyint(1) NOT NULL DEFAULT '1',
  phone varchar(45) DEFAULT NULL,
  address varchar(200) DEFAULT NULL,
  birthdate date DEFAULT NULL,
  gender tinyint(4) DEFAULT NULL,
  comment text,
  PRIMARY KEY (user_id),
  UNIQUE KEY email_UNIQUE (email),
  UNIQUE KEY rfid_UNIQUE (rfid),
  KEY fk_t_users_t_membership_type1_idx (membership_type_id),
  CONSTRAINT fk_t_users_t_membership_type1 FOREIGN KEY (membership_type_id) REFERENCES t_membership_type (membership_type_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;



SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_group_user (
  email tinyint NOT NULL,
  group_id tinyint NOT NULL,
  user_id tinyint NOT NULL,
  technicalname tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_role_by_user (
  email tinyint NOT NULL,
  technicalname tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance (
  user_id tinyint NOT NULL,
  total tinyint NOT NULL,
  nb tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance_all (
  user_id tinyint NOT NULL,
  total tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance_grouped (
  user_id tinyint NOT NULL,
  balance tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance_payment (
  firstname tinyint NOT NULL,
  lastname tinyint NOT NULL,
  user_id tinyint NOT NULL,
  total tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance_subscription (
  firstname tinyint NOT NULL,
  lastname tinyint NOT NULL,
  user_id tinyint NOT NULL,
  total tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
  /*!50001 CREATE TABLE v_user_balance_usage (
  firstname tinyint NOT NULL,
  lastname tinyint NOT NULL,
  user_id tinyint NOT NULL,
  total tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

INSERT INTO t_membership_type (membership_type_id, name, duration, price) VALUES (1, 'extern', 360, 0);
INSERT INTO t_membership_type (membership_type_id, name, duration, price) VALUES (2, 'normal', 360, 100);
INSERT INTO t_membership_type (membership_type_id, name, duration, price) VALUES (3, 'student', 360, 50);
INSERT INTO t_membership_type (membership_type_id, name, duration, price) VALUES (4, 'angel', 360, 500);

INSERT INTO t_user (user_id, membership_type_id, password, password_salt, firstname, lastname, email, date_inscr, balance, rfid, enabled, phone, address, birthdate, gender, comment) VALUES (1, 3, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'Admin', 'test', 'admin@gmail.com', '2014-01-09', 0, '120050C9ED66', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_user (user_id, membership_type_id, password, password_salt, firstname, lastname, email, date_inscr, balance, rfid, enabled, phone, address, birthdate, gender, comment) VALUES (2, 2, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'animator', 'test', 'animator@gmail.com', '2014-01-11', 0, '6F005CC09467', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_user (user_id, membership_type_id, password, password_salt, firstname, lastname, email, date_inscr, balance, rfid, enabled, phone, address, birthdate, gender, comment) VALUES (3, 2, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'member', 'test', 'member@gmail.com', '2014-01-11', 0, '02', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO t_user (user_id, membership_type_id, password, password_salt, firstname, lastname, email, date_inscr, balance, rfid, enabled, phone, address, birthdate, gender, comment) VALUES (4, 1, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'extern', 'test', 'extern@gmail.com', '2014-01-11', 0, '654', 1, NULL, NULL, NULL, NULL, NULL);

INSERT INTO t_machine_type (machine_type_id, technicalname, name, restricted) VALUES (1, '3dprinter', 'Imprimante 3D', 1);
INSERT INTO t_machine_type (machine_type_id, technicalname, name, restricted) VALUES (2, 'lasercutter', 'Decoupeuse Laser', 1);
INSERT INTO t_machine_type (machine_type_id, technicalname, name, restricted) VALUES (3, 'cnc', 'CNC', 1);
INSERT INTO t_machine_type (machine_type_id, technicalname, name, restricted) VALUES (4, 'other', 'Other', 0);

INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (1, 'Ultimaker V1', 1);
INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (2, 'Ultimkaer V2', 1);
INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (3, 'MakerBot Replicatior 2', 1);
INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (4, 'Decoupeuse laser', 2);
INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (5, 'CNC chinoise 1', 3);
INSERT INTO t_machine (machine_id, name, machine_type_id) VALUES (6, 'CNC chinoise 2', 3);

INSERT INTO t_reservation (reservation_id, date_start, date_end, user_id, machine_id) VALUES (1, '2014-06-23 18:00', '2014-06-23 19:00', 3, 1);
INSERT INTO t_reservation (reservation_id, date_start, date_end, user_id, machine_id) VALUES (2, '2014-06-23 18:00', '2014-06-23 19:00', 3, 4);
INSERT INTO t_reservation (reservation_id, date_start, date_end, user_id, machine_id) VALUES (3, '2014-06-23 18:00', '2014-06-23 20:00', 1, 2);

INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (1, 1, 10);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (1, 2, 7);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (1, 3, 5);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (1, 4, 5);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (2, 1, 40);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (2, 2, 20);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (2, 3, 10);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (2, 4, 10);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (3, 1, 40);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (3, 2, 20);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (3, 3, 10);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (3, 4, 10);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (4, 1, 0);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (4, 2, 0);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (4, 3, 0);
INSERT INTO r_price_machine (machine_type_id, membership_type_id, price) VALUES (4, 4, 0);

INSERT INTO t_group (group_id, technicalname, name) VALUES (1, 'comite', 'Comité');
INSERT INTO t_group (group_id, technicalname, name) VALUES (2, 'animator', 'Animateur');
INSERT INTO t_group (group_id, technicalname, name) VALUES (3, 'member', 'Membre');

INSERT INTO r_group_user (group_id, user_id) VALUES (1, 1);
INSERT INTO r_group_user (group_id, user_id) VALUES (2, 2);
INSERT INTO r_group_user (group_id, user_id) VALUES (3, 3);

INSERT INTO r_user_authorized_machine_type (user_id, machine_type_id, formation_date) VALUES (1, 1, '2014-01-01');

INSERT INTO t_role (role_id, name, technicalname) VALUES (1, 'Administration', 'ROLE_ADMIN');
INSERT INTO t_role (role_id, name, technicalname) VALUES (2, 'View user', 'ROLE_USER_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (3, 'Manage user', 'ROLE_USER_MANAGE');
INSERT INTO t_role (role_id, name, technicalname) VALUES (4, 'View machine', 'ROLE_MACHINE_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (5, 'Manage machine', 'ROLE_MACHINE_MANAGE');
INSERT INTO t_role (role_id, name, technicalname) VALUES (6, 'View payment', 'ROLE_PAYMENT_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (7, 'Manage payment', 'ROLE_PAYMENT_MANAGE');
INSERT INTO t_role (role_id, name, technicalname) VALUES (8, 'View accounting', 'ROLE_ACCOUNTING_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (9, 'Manage accounting', 'ROLE_ACCOUNTING_MANAGE');
INSERT INTO t_role (role_id, name, technicalname) VALUES (10, 'View audit', 'ROLE_AUDIT_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (11, 'View reservation', 'ROLE_RESERVATION_VIEW');
INSERT INTO t_role (role_id, name, technicalname) VALUES (12, 'Use reservation', 'ROLE_RESERVATION_USE');
INSERT INTO t_role (role_id, name, technicalname) VALUES (13, 'Manage reservation', 'ROLE_RESERVATION_MANAGE');

INSERT INTO r_group_role (role_id, group_id) VALUES (1, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (2, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (3, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (4, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (5, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (6, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (7, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (8, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (9, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (4, 2);
INSERT INTO r_group_role (role_id, group_id) VALUES (9, 2);
INSERT INTO r_group_role (role_id, group_id) VALUES (9, 3);
INSERT INTO r_group_role (role_id, group_id) VALUES (10, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (10, 2);
INSERT INTO r_group_role (role_id, group_id) VALUES (10, 3);
INSERT INTO r_group_role (role_id, group_id) VALUES (11, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (11, 2);
INSERT INTO r_group_role (role_id, group_id) VALUES (11, 3);
INSERT INTO r_group_role (role_id, group_id) VALUES (12, 1);
INSERT INTO r_group_role (role_id, group_id) VALUES (12, 2);
INSERT INTO r_group_role (role_id, group_id) VALUES (12, 3);

INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (1, 'FABLAB_NAME', 'Fablab-Fribourg');
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (2, 'FABLAB_URL', 'http://fablab-fribourg.ch');
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (3, 'ACCOUNTING_EDIT_HISTORY_LIMIT', '7');
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (4, 'GOOGLE_CALENDAR_API_KEY', NULL);
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (5, 'RECAPTCHA_SITE_KEY', NULL);
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (6, 'CURRENCY', 'CHF');
INSERT INTO t_configuration (id, conf_key, conf_value) VALUES (7, 'RECAPTCHA_SECRET', NULL);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;