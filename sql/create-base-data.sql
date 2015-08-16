USE fablab;

BEGIN;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_membership_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`) VALUES (1, 'extern', 360, 0);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`) VALUES (2, 'normal', 360, 100);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`) VALUES (3, 'student', 360, 50);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`) VALUES (4, 'angel', 360, 500);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`) VALUES (1, 3, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'Gaétan', 'Collaud', 'gaetancollaud@gmail.com', '2014-01-09', 0, '120050C9ED66', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`) VALUES (2, 2, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'sdf', 'ator', 'animator1@gmail.com', '2014-01-11', 0, '6F005CC09467', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`) VALUES (3, 2, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'mem', 'ber', 'member1@gmail.com', '2014-01-11', 0, '02', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`) VALUES (4, 1, 'a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 'hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN', 'extern', '1', 'exter1@gmail.com', '2014-01-11', 0, '654', 1, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`) VALUES (1, '3dprinter', 'Imprimante 3D', 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`) VALUES (2, 'lasercutter', 'Decoupeuse Laser', 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`) VALUES (3, 'cnc', 'CNC', 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`) VALUES (4, 'other', 'Other', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (1, 'Ultimaker V1', 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (2, 'Ultimkaer V2', 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (3, 'MakerBot Replicatior 2', 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (4, 'Decoupeuse laser', 2);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (5, 'CNC chinoise 1', 3);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `name`, `machine_type_id`) VALUES (6, 'CNC chinoise 2', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_reservation` (`reservation_id`, `date_start`, `date_end`, `user_id`, `machine_id`) VALUES (1, '2014-06-23 18:00', '2014-06-23 19:00', 3, 1);
INSERT INTO `fablab`.`t_reservation` (`reservation_id`, `date_start`, `date_end`, `user_id`, `machine_id`) VALUES (2, '2014-06-23 18:00', '2014-06-23 19:00', 3, 4);
INSERT INTO `fablab`.`t_reservation` (`reservation_id`, `date_start`, `date_end`, `user_id`, `machine_id`) VALUES (3, '2014-06-23 18:00', '2014-06-23 20:00', 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_price_machine`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (1, 1, 10);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (1, 2, 7);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (1, 3, 5);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (1, 4, 5);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (2, 1, 40);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (2, 2, 20);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (2, 3, 10);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (2, 4, 10);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (3, 1, 40);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (3, 2, 20);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (3, 3, 10);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (3, 4, 10);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (4, 1, 0);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (4, 2, 0);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (4, 3, 0);
INSERT INTO `fablab`.`r_price_machine` (`machine_type_id`, `membership_type_id`, `price`) VALUES (4, 4, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`) VALUES (1, 'comite', 'Comité');
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`) VALUES (2, 'animator', 'Animateur');
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`) VALUES (3, 'member', 'Membre');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_group_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (1, 1);
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (2, 2);
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_user_authorized_machine_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_user_authorized_machine_type` (`user_id`, `machine_type_id`, `formation_date`) VALUES (1, 1, '2014-01-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (1, 'Administration', 'ROLE_ADMIN');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (2, 'View user', 'ROLE_USER_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (3, 'Manage user', 'ROLE_USER_MANAGE');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (4, 'View machine', 'ROLE_MACHINE_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (5, 'Manage machine', 'ROLE_MACHINE_MANAGE');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (6, 'View payment', 'ROLE_PAYMENT_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (7, 'Manage payment', 'ROLE_PAYMENT_MANAGE');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (8, 'View accounting', 'ROLE_ACCOUNTING_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (9, 'Manage accounting', 'ROLE_ACCOUNTING_MANAGE');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (10, 'View audit', 'ROLE_AUDIT_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (11, 'View reservation', 'ROLE_RESERVATION_VIEW');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (12, 'Use reservation', 'ROLE_RESERVATION_USE');
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (13, 'Manage reservation', 'ROLE_RESERVATION_MANAGE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_group_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (1, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (2, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (3, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (5, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (7, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (8, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (9, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (9, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (9, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (10, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (10, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (10, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_configuration`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (1, 'FABLAB_NAME', 'Fablab-Fribourg');
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (2, 'FABLAB_URL', 'http://fablab-fribourg.ch');
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (3, 'ACCOUNTING_EDIT_HISTORY_LIMIT', '7');
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (4, 'GOOGLE_CALENDAR_API_KEY', NULL);
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (5, 'RECAPTCHA_SITE_KEY', NULL);
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (6, 'CURRENCY', 'CHF');
INSERT INTO `fablab`.`t_configuration` (`id`, `conf_key`, `conf_value`) VALUES (7, 'RECAPTCHA_SECRET', NULL);

COMMIT;
