
-- -----------------------------------------------------
-- Data fot_event_personr table `fablab`.`t_membership_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (1, 'Externe', 1, 10, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (2, 'Cotisant', 1, 8, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (3, 'Etudiant', 1, 5, 1);
INSERT INTO `fablab`.`t_membership_type` (`membership_type_id`, `name`, `duration`, `price`, `active`) VALUES (4, 'Soutien', 1, 15, 1);

COMMIT;

-- brefore because of trigger
-- -----------------------------------------------------
-- Data for table `fablab`.`t_event_person`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (1, 'Fabien', 'Vuilleumier', 'fabien.vuilleumier@he-arc.ch', 0);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (2, 'Camille', 'Robin', 'camille.robin@unifr.ch', 0);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (6, 'John', 'Hirsh', 'john.hirch@email.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (7, 'Valentin', 'Stram', 'valentinStram@email.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (13, 'Jean-Daniel', 'Choppard', 'j-d.c@remail.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (14, 'Franck', 'Vallat', 'franck.vallat@fablabmanager.ch2', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (15, 'Antoine', 'Aebi', 'admin@fablabmanager.ch2', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (16, 'Mathias', 'Mercier', 'mathias.mercier@fablabmanager.ch2', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (17, 'Christian', 'Chopard', 'christian.chopard@fablabmanager.ch2', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (18, 'Jean-Baptiste', 'Dunoel', 'jb.dunoel@me.ch', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (19, 'Marcel', 'Aarger', 'm.aarger@email.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (20, 'Mikel', 'Geaorge', 'mgc@email.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (21, 'Brigita', 'Berdo', 'Berdo.b@email.com', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (22, 'Jean', 'Bombec', 'jbbc@jb.ch', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (23, 't', 't', 'asdf@asdfa', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (24, 'f', 'f', 'f@f', 1);
INSERT INTO `fablab`.`t_event_person` (`event_person_id`, `firstname`, `lastname`, `email`, `active`) VALUES (25, 'a', 'a', 'a@a', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `auth_by_sql`, `login`, `password_manager`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`, `active`) VALUES (1, 3, 1, 'franck', 'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6', 'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6', '2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx', 'Franck', 'Vallat', 'franck.vallat@fablabmanager.ch', '2015-05-26 13:05:17', 0, '120050C9ED66', 1, '0791234567', 'Rue de la rue 1', '1990-07-19', 1, 'NULL', 1);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `auth_by_sql`, `login`, `password_manager`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`, `active`) VALUES (2, 2, 1, 'admin', '7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1', '7c0deadab3077e14480bc4cfc80714b7cd8f12bd4c8968f69e4a0053c38867f1', 'tW7RjN0OvD3ltNFb27fi4nZ39qIhLM1x99ls1qFZ', 'Antoine', 'Aebi', 'admin@fablabmanager.ch', '2015-05-26 14:57:12', 0, '120050C9ED65', 1, '0791234567', 'Rue de la rue 2', '1990-07-19', 1, 'NULL', 1);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `auth_by_sql`, `login`, `password_manager`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`, `active`) VALUES (3, 1, 1, 'mercier', 'NULL', 'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6', '2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx', 'Mathias', 'Mercier', 'mathias.mercier@fablabmanager.ch', '2015-05-27 13:30:24', 0, '120050C9ED64', 1, '0791234567', 'Rue de la rue 3', '1990-07-19', 1, 'NULL', 1);
INSERT INTO `fablab`.`t_user` (`user_id`, `membership_type_id`, `auth_by_sql`, `login`, `password_manager`, `password`, `password_salt`, `firstname`, `lastname`, `email`, `date_inscr`, `balance`, `rfid`, `enabled`, `phone`, `address`, `birthdate`, `gender`, `comment`, `active`) VALUES (4, 4, 1, 'chopard', 'NULL', 'e3bc7aa498d8d58544be42e4e3473c90e96cf3e796d3f99c0b82b73f26f590c6', '2qoPnWBNOCITp6WfvVaWGmS4C3iWORdtWzyk13Bx', 'Christian', 'Chopard', 'christian.chopard@fablabmanager.ch', '2015-05-27 13:30:25', 0, '120050C9ED63', 1, '0791234567', 'Rue de la rue 4', '1990-07-19', 1, 'NULL', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`, `active`) VALUES (1, '3dprinter', 'Imprimante 3D', 1, 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`, `active`) VALUES (2, 'lasercutter', 'Decoupeuse Laser', 1, 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`, `active`) VALUES (3, 'cnc', 'CNC', 1, 1);
INSERT INTO `fablab`.`t_machine_type` (`machine_type_id`, `technicalname`, `name`, `restricted`, `active`) VALUES (4, 'other', 'Other', 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (1, 'Disponible', '#DFF0D8', 1);
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (2, 'En réparation', '#FCF8E3', 1);
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (3, 'Indisponible', '#F2DEDE', 1);
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (4, 'Endommagé', '#F2DEDE', 1);
INSERT INTO `fablab`.`t_machine_status` (`machine_status_id`, `label`, `color`, `active`) VALUES (12, 'Prêté - En location', '#FCF8E3', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine_state`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine_state` (`machine_state_id`, `label`, `active`) VALUES (1, 'Neuf', 1);
INSERT INTO `fablab`.`t_machine_state` (`machine_state_id`, `label`, `active`) VALUES (2, 'Bon état', 1);
INSERT INTO `fablab`.`t_machine_state` (`machine_state_id`, `label`, `active`) VALUES (3, 'Usagé', 1);
INSERT INTO `fablab`.`t_machine_state` (`machine_state_id`, `label`, `active`) VALUES (4, 'A remplacer', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_machine`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (1, 'ULTI1', 'Ultimaker V1', '2015-01-01', 3000.00, 'NULL', 1, 1, 2, 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (2, 'ULTI2', 'Ultimkaer V2', '2014-07-09', 3000.00, 'NULL', 1, 3, 2, 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (3, 'MARKE', 'MakerBot Replicatior 2', '2014-08-10', 12000.00, 'NULL', 1, 12, 3, 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (4, 'DECOU', 'Decoupeuse laser', '2011-08-04', 50000.00, 'NULL', 2, 1, 1, 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (5, 'CNCC1', 'CNC chinoise 1', '2015-02-12', 10000.05, 'NULL', 3, 1, 1, 1);
INSERT INTO `fablab`.`t_machine` (`machine_id`, `code`, `name`, `acquisition_date`, `buy_price`, `to_do_revision`, `machine_type_id`, `machine_status_id`, `machine_state_id`, `active`) VALUES (6, 'CNCC2', 'CNC chinoise 2', '2015-02-12', 10500.00, 'NULL', 3, 1, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_price_revision`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_price_revision` (`price_revision_id`, `date_revision`, `membership_duration`) VALUES (1, '2001-01-20 14:00:00', 365);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_reservation` (`reservation_id`, `date_start`, `date_end`, `user_id`, `machine_id`, `active`) VALUES (33, '2015-06-29 18:00:00', '2015-06-29 19:00:00', 2, 1, 1);
INSERT INTO `fablab`.`t_reservation` (`reservation_id`, `date_start`, `date_end`, `user_id`, `machine_id`, `active`) VALUES (34, '2015-06-30 18:00:00', '2015-06-30 19:00:00', 2, 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (13, 10, '2015-06-30 21:29:18', 10.00, 'Electricite', NULL, 1, 1, 'Mois de mai', 'AUTRES_CHARGES_EXPLOITATION', 'DETTES_FOURNISSEUR', 'PAYMENT', 0, 1, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (14, 50, '2015-06-30 22:42:12', 50.00, 'Amende', NULL, 1, 0, 'NULL', 'NULL', 'NULL', 'PAYMENT', 0, 1, 3, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (15, 80, '2015-06-30 23:03:51', 80.00, 'Electricité mai', NULL, 1, 1, 'Facture pour le mois de mai', 'AUTRES_CHARGES_EXPLOITATION', 'DETTES_FOURNISSEUR', 'PAYMENT', 0, 0, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (17, 36.5, '2015-07-01 14:44:05', 36.50, 'Remboursement du dû', NULL, 0, 0, 'NULL', 'NULL', 'NULL', 'REFUND', 0, 1, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (18, 250, '2015-07-01 17:33:19', 250.00, 'Chaise de bureau', NULL, 1, 1, 'NULL', 'MOBILIER', 'CAISSE_POSTE_BANQUE', 'PAYMENT', 0, 1, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (21, 120, '2015-07-02 02:19:28', 120.00, 'Workshop- Imprimante', NULL, 0, 1, 'NULL', 'CAISSE_POSTE_BANQUE', 'HONORAIRES', 'PAYMENT', 1, 1, 2, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (22, 80, '2015-07-02 13:14:31', 80.00, 'Electricité mai', NULL, 1, 1, 'NULL', 'AUTRES_CHARGES_EXPLOITATION', 'DETTES_FOURNISSEUR', 'PAYMENT', 0, 1, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (23, 30, '2015-07-02 13:56:17', 30.00, 'Achat d\'une chaise pour la cuisine', NULL, 1, 1, 'Payé cash', 'MOBILIER', 'CAISSE_POSTE_BANQUE', 'PAYMENT', 0, 0, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (24, 240, '2015-07-02 14:03:49', 240.00, 'Peindre pour tous', NULL, 0, 1, 'Neuchâtel', 'CAISSE_POSTE_BANQUE', 'HONORAIRES', 'PAYMENT', 1, 1, 1, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (26, 240, '2015-07-02 14:09:29', 240.00, 'Peindre pour tous', NULL, 0, 1, 'Neuchâtel', 'CAISSE_POSTE_BANQUE', 'HONORAIRES', 'PAYMENT', 1, 1, 1, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (27, 240, '2015-07-02 14:10:55', 240.00, 'Peindre pour tous', NULL, 0, 1, 'Neuchâtel', 'CAISSE_POSTE_BANQUE', 'HONORAIRES', 'PAYMENT', 1, 1, 1, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (28, 25, '2015-07-02 14:49:55', 25.00, 'Remboursement de la chaise cassée', NULL, 1, 0, 'Ben bravo !', 'NULL', 'NULL', 'PAYMENT', 0, 1, 2, NULL);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (31, 64.3, '2015-07-02 15:29:12', 64.30, 'Remboursement du dû', NULL, 0, 0, 'NULL', 'NULL', 'NULL', 'REFUND', 0, 1, 3, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (32, 120, '2015-07-02 15:43:49', 120.00, 'Workshop- Imprimante', NULL, 0, 1, 'NULL', 'CAISSE_POSTE_BANQUE', 'HONORAIRES', 'PAYMENT', 1, 1, 2, 2);
INSERT INTO `fablab`.`t_payment` (`payment_id`, `total`, `date_payement`, `amount`, `label`, `discount`, `discount_percent`, `payed_for_fab_lab`, `note`, `account_credit`, `account_debit`, `refund`, `event`, `active`, `user_id`, `cashier_id`) VALUES (33, 50, '2015-07-02 15:55:33', 50.00, 'Remboursement du dû partiel', NULL, 0, 0, 'NULL', 'NULL', 'NULL', 'REFUND', 0, 1, 4, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_usage`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (6, '2015-06-30 00:00:00', 20, 120, 12, 10.00, 1, 'Une remarque', 46.80, 1, 1, 1, 3, 2);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (7, '2015-06-30 19:25:30', 10, 60, 0, 0.00, 1, 'NULL', 10.00, 1, 1, 2, 2, NULL);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (8, '2015-06-30 19:31:07', 10, 60, 0, 0.00, 1, 'NULL', 10.00, 1, 1, 2, 2, NULL);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (9, '2015-06-29 00:00:00', 10, 60, 0, 0.00, 1, 'Annulation', 10.00, 0, 2, 2, 2, NULL);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (10, '2015-07-01 18:35:35', 20, 120, 5, 10.00, 1, 'NULL', 40.50, 1, 5, 2, 2, 2);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (11, '2015-07-02 13:05:38', 20, 120, 3, 10.00, 1, 'NULL', 51.30, 1, 5, 2, 3, NULL);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (12, '2015-07-02 13:06:18', 20, 120, 3, 10.00, 1, 'NULL', 51.30, 1, 5, 2, 3, 2);
INSERT INTO `fablab`.`t_usage` (`usage_id`, `date_start`, `price_hour`, `minutes`, `additional_cost`, `discount`, `discount_percent`, `note`, `total`, `active`, `machine_id`, `membership_type_id`, `user_id`, `cashier_id`) VALUES (13, '2015-07-02 15:53:53', 10, 240, 30, 0.00, 1, 'Matière', 70.00, 1, 1, 4, 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (1, 'ADMIN', 'Comité', 1);
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (2, 'CONFIRME', 'Confirmé', 1);
INSERT INTO `fablab`.`t_group` (`group_id`, `technicalname`, `name`, `active`) VALUES (3, 'MEMBER', 'Membre', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_subscription`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_subscription` (`subscription_id`, `user_id`, `membership_type_id`, `date_subscription`, `price`, `duration`, `comment`, `active`) VALUES (1, 1, 3, '2015-05-26 14:50:59', 150, 365, 'NULL', 1);
INSERT INTO `fablab`.`t_subscription` (`subscription_id`, `user_id`, `membership_type_id`, `date_subscription`, `price`, `duration`, `comment`, `active`) VALUES (2, 2, 2, '2015-05-26 15:01:50', 200, 365, 'NULL', 1);
INSERT INTO `fablab`.`t_subscription` (`subscription_id`, `user_id`, `membership_type_id`, `date_subscription`, `price`, `duration`, `comment`, `active`) VALUES (3, 3, 1, '2015-05-27 13:34:27', 0, 365, 'NULL', 1);
INSERT INTO `fablab`.`t_subscription` (`subscription_id`, `user_id`, `membership_type_id`, `date_subscription`, `price`, `duration`, `comment`, `active`) VALUES (4, 4, 4, '2015-05-27 13:40:34', 250, 365, 'NULL', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (1, 'Administration', 'ROLE_ADMIN', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (2, 'View user', 'ROLE_USER_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (3, 'Manage user', 'ROLE_USER_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (4, 'View machine', 'ROLE_MACHINE_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (5, 'Manage machine', 'ROLE_MACHINE_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (6, 'View payment', 'ROLE_PAYMENT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (7, 'Manage payment', 'ROLE_PAYMENT_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (8, 'View accounting', 'ROLE_ACCOUNTING_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (9, 'Manage accounting', 'ROLE_ACCOUNTING_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (10, 'View audit', 'ROLE_AUDIT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (11, 'View reservation', 'ROLE_RESERVATION_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (12, 'Use reservation', 'ROLE_RESERVATION_USE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (13, 'Manage reservation', 'ROLE_RESERVATION_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (14, 'Manage Mailing list', 'ROLE_MAILINGLIST_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (15, 'Ticket View', 'ROLE_TICKET_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (16, 'Ticket manage', 'ROLE_TICKET_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (17, 'Supply view', 'ROLE_SUPPLY_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (18, 'Supply manage', 'ROLE_SUPPLY_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (19, 'Training manage', 'ROLE_TRAINING_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (20, 'Training view', 'ROLE_TRAINING_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (21, 'Group manage', 'ROLE_GROUP_MANAGE', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (22, 'Event view', 'ROLE_EVENT_VIEW', 1);
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`, `active`) VALUES (23, 'Event manage', 'ROLE_EVENT_MANAGE', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_configuration`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (1, 'Nom du FabLab', 'FABLAB_NAME', 'Fablab-Manager');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (2, 'Url du site du FabLab', 'FABLAB_URL', 'fablab.ch');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (3, 'Temps après lequel l\'entrée d\'une utilisation n\'est plus possible [ENTIER], en jours', 'ACCOUNTING_EDIT_HISTORY_LIMIT', '7');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (4, 'Clé d\'appi pour Google calendar', 'GOOGLE_CALENDAR_API_KEY', 'AIzaSyAQTP0T3Bf8x5Q0UDx_zMrAdx71VChg2vo');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (6, 'Monaie courante', 'CURRENCY', 'CHF');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (9, 'Temps limite jusqu\'auquel une réservation est possible [ENTIER], en jours', 'OFFSET_RESERVATION', '10');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (12, 'Url du Google calendar', 'GOOGLE_CALENDAR_URL', 'https://www.google.com/calendar/feeds/fablabmanagerhegarc%40gmail.com/public/basic');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (13, 'Couleur des events du Google calendar [#COULEUR]', 'CALENDAR_AGENDA_COLOR', '#B2E0FF');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (14, 'Couleur des réservations du Google calendar [#COULEUR]', 'CALENDAR_RESERVATION_COLOR', '#FF0000');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (15, 'Lors d\'un rabais, l\'affichage propose d\'abord un pourcentage (PERCENT) ou en absolu (MONEY) ? [PERCENT, MONEY]', 'FIRST_PERCENT', 'PERCENT');
INSERT INTO `fablab`.`t_configuration` (`configuration_id`, `common_name`, `conf_key`, `conf_value`) VALUES (16, 'Fuseau horaire du Google calendar [REGION/VILLE DE REFERENCE]', 'CALENDAR_TIME_ZONE', 'Europe/Zurich');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_revision`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_revision` (`revision_id`, `revision_date`, `revision_time`, `note`, `revision_cost`, `machine_id`, `active`, `user_id`) VALUES (1, '2015-06-25 00:00:00', '2015-06-25 09:41:32', 'Changement de la mêche', 0.00, 6, 1, 2);
INSERT INTO `fablab`.`t_revision` (`revision_id`, `revision_date`, `revision_time`, `note`, `revision_cost`, `machine_id`, `active`, `user_id`) VALUES (2, '2015-06-25 00:00:00', '2015-06-25 14:22:54', 'Révision standard + changement de bobine de fil', 10.00, 1, 1, 2);
INSERT INTO `fablab`.`t_revision` (`revision_id`, `revision_date`, `revision_time`, `note`, `revision_cost`, `machine_id`, `active`, `user_id`) VALUES (3, '2015-07-02 00:00:00', '2015-07-02 10:24:38', 'Changement de la mèche', 30.00, 6, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_ticket_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_ticket_status` (`ticket_status_id`, `label`, `active`) VALUES (1, 'OUVERT', 1);
INSERT INTO `fablab`.`t_ticket_status` (`ticket_status_id`, `label`, `active`) VALUES (2, 'CLOS', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_ticket`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_ticket` (`ticket_id`, `title`, `description`, `creation_date`, `prevision_close_date`, `close_date`, `machine_id`, `ticket_status_id`, `creation_user_id`, `close_user_id`, `active`) VALUES (16, 'Ultimake v2 plante', 'L\'imprimante 3d Ultimate V2 plante. Elle a des ratées', '2015-06-25', '2015-07-12', NULL, 2, 1, 2, NULL, 1);
INSERT INTO `fablab`.`t_ticket` (`ticket_id`, `title`, `description`, `creation_date`, `prevision_close_date`, `close_date`, `machine_id`, `ticket_status_id`, `creation_user_id`, `close_user_id`, `active`) VALUES (17, 'CNC 2 mèche', 'La mèche de la CNC 2 est endommagée', '2015-06-25', '2015-06-30', '2015-06-25', 6, 2, 2, 2, 1);
INSERT INTO `fablab`.`t_ticket` (`ticket_id`, `title`, `description`, `creation_date`, `prevision_close_date`, `close_date`, `machine_id`, `ticket_status_id`, `creation_user_id`, `close_user_id`, `active`) VALUES (18, 'Cnc panne', 'NULL', '2015-06-26', '2015-07-01', '2015-06-26', 6, 2, 2, 2, 1);
INSERT INTO `fablab`.`t_ticket` (`ticket_id`, `title`, `description`, `creation_date`, `prevision_close_date`, `close_date`, `machine_id`, `ticket_status_id`, `creation_user_id`, `close_user_id`, `active`) VALUES (26, 'Mèche de la CNC 2 endommagée', 'La mèche de la CNC 2 est endommagée et fait des esquilles.\nLa panne a déjà été clôturée le 18.07.2015 par Antoine Aebi;\nLa panne a été ré-ouverte le 25.07.2015 par Antoine Aebi;', '2015-06-29', '2015-07-11', '2015-07-28', 6, 2, 3, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_training_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_training_level` (`training_level_id`, `label`, `active`) VALUES (1, 'Base', 1);
INSERT INTO `fablab`.`t_training_level` (`training_level_id`, `label`, `active`) VALUES (2, 'Avancé', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_training`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (10, 'Imprimante 3d base', NULL, 'NULL', 1, 1, 1);
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (13, 'Découpeuse laser base', NULL, 'NULL', 1, 1, 2);
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (16, 'Cnc base', NULL, 'NULL', 1, 1, 3);
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (17, 'Cnc avancé', 100.00, 'NULL', 1, 2, 3);
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (18, 'Découpeuse laser avancé', 250.00, 'NULL', 1, 2, 2);
INSERT INTO `fablab`.`t_training` (`training_id`, `name`, `price`, `note`, `active`, `training_level_id`, `machine_type_id`) VALUES (19, 'Imprimante 3d avancé', 150.00, 'NULL', 1, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_training_prerequisite`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_training_prerequisite` (`prerequire_training_id`, `dependent_training_id`) VALUES (17, 16);
INSERT INTO `fablab`.`r_training_prerequisite` (`prerequire_training_id`, `dependent_training_id`) VALUES (18, 13);
INSERT INTO `fablab`.`r_training_prerequisite` (`prerequire_training_id`, `dependent_training_id`) VALUES (19, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_supply_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_supply_type` (`supply_type_id`, `label`, `active`) VALUES (1, 'Food', 1);
INSERT INTO `fablab`.`t_supply_type` (`supply_type_id`, `label`, `active`) VALUES (2, 'Drink', 1);
INSERT INTO `fablab`.`t_supply_type` (`supply_type_id`, `label`, `active`) VALUES (3, 'Wood', 1);
INSERT INTO `fablab`.`t_supply_type` (`supply_type_id`, `label`, `active`) VALUES (4, 'Metal', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_supply_unity`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_supply_unity` (`supply_unity_id`, `label`, `floating`, `active`) VALUES (1, 'MCarre', 1, 1);
INSERT INTO `fablab`.`t_supply_unity` (`supply_unity_id`, `label`, `floating`, `active`) VALUES (2, 'Pieces', 0, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_supply`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (1, 'COCA', 'Coca Cola Btl 5 dl pet', 2.00, 1.50, 'Migros', 95.00, 1, 2, 2, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (2, 'THEFR', 'Thé Froid Lipton  Btl 5 dl pet', 2.00, 1.50, 'Migros', 23.00, 1, 2, 2, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (3, 'NOV2M', 'Novopan 2mm', 30.00, 22.95, 'Jumbo', 67.65, 1, 3, 1, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (5, 'METAL', 'Plaque de métal 5mm', 0.40, 1.00, 'Jumbo', 2.10, 1, 4, 1, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (6, 'LASUR', 'Lasure pour bois', 0.20, 0.10, 'http://www.auro.ch/downloads/technische-merkblaetter_f/160-fichetechnique-lasurepourbois-auro-f.pdf', 30.00, 1, 3, 1, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (7, 'FANT', 'Fanta Btl pet 5 dl', 2.00, 1.50, 'Migros', 31.00, 1, 2, 2, 2);
INSERT INTO `fablab`.`t_supply` (`supply_id`, `code`, `label`, `selling_price`, `unity_buying_price`, `order_address`, `quantity_stock`, `active`, `supply_type_id`, `supply_unity_id`, `creation_user_id`) VALUES (12, 'LEMON', 'Sprite Lemon Btl 5dl pet', 2.00, 1.50, 'Migros', 23.00, 1, 2, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_purchase`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (21, '2015-06-24', 1.25, 0.50, 0.000, 1, 1, 5, 'NULL', 3, 2);
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (22, '2015-06-16', 8.00, 14.40, 10.000, 1, 1, 1, 'NULL', 2, NULL);
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (24, '2015-06-22', 2.00, 3.00, 1.000, 0, 1, 12, 'Rabais exceptionnel car il lui manquait 1 franc', 3, NULL);
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (25, '2015-06-30', 8.00, 14.40, 10.000, 1, 1, 1, 'Rabais de sympathie pour grande quantité', 2, 2);
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (26, '2015-07-02', 8.00, 14.40, 10.000, 1, 1, 1, 'Rabais de sympathie pour grande quantité', 2, 2);
INSERT INTO `fablab`.`t_purchase` (`purchase_id`, `purchase_date`, `quantity`, `purchase_price`, `discount`, `discount_percent`, `active`, `supply_id`, `note`, `user_id`, `cashier_id`) VALUES (27, '2015-07-02', 2.00, 60.00, 0.000, 1, 1, 3, 'NULL', 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_certification`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (25, 'Certification Imprimante 3d base', '2015-06-24 18:31:37', NULL, 'NULL', 1, 10);
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (26, 'Certification Imprimante 3d avancé', '2015-06-24 18:43:23', 150.00, 'NULL', 1, 19);
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (27, 'Certification Cnc base', '2015-06-24 18:43:56', NULL, 'NULL', 1, 16);
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (29, 'Certification Découpeuse laser avancé', '2015-06-24 18:44:13', 250.00, 'NULL', 1, 18);
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (30, 'Certification Découpeuse laser base', '2015-06-24 18:44:28', NULL, 'NULL', 1, 13);
INSERT INTO `fablab`.`t_certification` (`certification_id`, `name`, `certification_date`, `certification_price`, `note`, `active`, `training_id`) VALUES (31, 'Certification Cnc avancé', '2015-07-02 01:21:41', NULL, 'NULL', 1, 16);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_motion_stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (79, '2015-06-30', 25.00, 'Entrée', 1, 12, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (80, '2015-06-30', 12.00, 'Entrée [ajout]', 1, 7, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (81, '2015-06-30', 25.00, 'Entrée [ajout]', 1, 3, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (82, '2015-06-24', 1.25, 'Sortie', 1, 5, 4);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (83, '2015-06-16', 8.00, 'Sortie', 1, 1, 3);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (84, '2015-06-16', -2.00, 'Correction', 1, 1, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (85, '2015-06-22', 2.00, 'Sortie', 1, 12, 4);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (86, '2015-06-30', 8.00, 'Sortie', 1, 1, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (87, '2015-07-02', 8.00, 'Sortie', 1, 1, 2);
INSERT INTO `fablab`.`t_motion_stock` (`motion_stock_id`, `motion_date`, `quantity`, `io`, `active`, `supply_id`, `user_id`) VALUES (88, '2015-07-02', 2.00, 'Sortie', 1, 3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_price_machine`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
TRUNCATE `fablab`.`r_price_machine`; -- remove default data
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (1, 15.00, 1, 1);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (2, 10.00, 1, 2);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (3, 12.00, 1, 3);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (4, 10.00, 1, 4);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (5, 20.00, 2, 1);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (6, 15.00, 2, 2);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (7, 13.00, 2, 3);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (8, 15.00, 2, 4);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (9, 27.00, 3, 1);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (10, 20.00, 3, 2);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (11, 20.00, 3, 3);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (12, 20.00, 3, 4);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (13, 30.00, 4, 1);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (14, 25.00, 4, 2);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (15, 22.00, 4, 3);
INSERT INTO `fablab`.`r_price_machine` (`price_machine_id`, `price`, `machine_type_id`, `membership_type_id`) VALUES (16, 25.00, 4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_user_certification`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (1, 25);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (2, 25);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (4, 25);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (1, 26);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (2, 26);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (1, 27);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (4, 27);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (1, 29);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (2, 29);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (1, 30);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (2, 30);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (3, 30);
INSERT INTO `fablab`.`r_user_certification` (`user_id`, `certification_id`) VALUES (4, 31);

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
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (6, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (7, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (8, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (9, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (10, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (13, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (14, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (15, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (16, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (17, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (18, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (19, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (20, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (21, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (22, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (23, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (2, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (5, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (6, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (7, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (12, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (13, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (15, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (16, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (17, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (18, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (20, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (22, 2);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (2, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (6, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (11, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (15, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (17, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (20, 3);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (22, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_group_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (1, 2);
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (2, 4);
INSERT INTO `fablab`.`r_group_user` (`group_id`, `user_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_event_type` (`event_type_id`, `label`, `active`) VALUES (1, 'WORKSHOP', 1);
INSERT INTO `fablab`.`t_event_type` (`event_type_id`, `label`, `active`) VALUES (2, 'PRESENTATION DU FABLAB', 1);
INSERT INTO `fablab`.`t_event_type` (`event_type_id`, `label`, `active`) VALUES (3, 'EVENEMENTS', 1);
INSERT INTO `fablab`.`t_event_type` (`event_type_id`, `label`, `active`) VALUES (4, 'PRESENTATIONS', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (1, '2015-07-02', '2015-07-02', '2015-07-02 23:57:05', '2015-07-02 23:57:05', 'Workshop- Imprimante', 'Découvrir l\'impression 3d', 'Neuchâtel', 'NULL', 120.00, 1, 1, 2);
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (2, '2015-05-10', '2015-05-15', '2015-07-02 10:00:00', '2015-07-02 22:00:00', 'Peindre pour tous', 'Les peintures', 'Neuchâtel', 'NULL', 240.00, 1, 2, 1);
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (3, '2015-06-03', '2015-06-03', '2015-07-02 14:00:43', '2015-07-02 15:00:43', 'Mise en bouche', 'Les papilles', 'Necuhâtel', 'NULL', 22.50, 1, 4, 3);
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (4, '2015-07-02', '2015-07-03', '2015-07-02 12:00:17', '2015-07-02 12:00:17', 'Manger seinement', 'Les nourriture au FabLab', 'Neuchâtel', 'NULL', 5.00, 1, 1, 1);
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (5, '2015-07-02', '2015-07-02', '2015-07-02 18:00:43', '2015-07-02 20:00:43', 'Tous au Lab', 'Présentation des machines', 'NULL', 'Présentation avec visite guidée du FabLab', 0.00, 1, 2, 1);
INSERT INTO `fablab`.`t_event` (`event_id`, `date_start`, `date_end`, `time_start`, `time_end`, `title`, `theme`, `place`, `description`, `price`, `active`, `event_type_id`, `supervisor_id`) VALUES (6, '2015-06-29', '2015-07-03', '2015-07-02 10:00:12', '2015-07-02 20:00:12', 'imprimante 3d avancé', 'Imprimante 3d', 'Neuchâtel', 'Construire et repartir avec son imprimante 3d', 2000.00, 1, 1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`t_event_module`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`t_event_module` (`event_module_id`, `name`, `description`, `active`, `machine_type_id`) VALUES (1, 'Imprimante 3d 1', 'Module 1 des imprimantes 3d', 1, NULL);
INSERT INTO `fablab`.`t_event_module` (`event_module_id`, `name`, `description`, `active`, `machine_type_id`) VALUES (2, 'Imprimante 3d 2', 'Module 2 des imprmantes 3d', 1, 1);
INSERT INTO `fablab`.`t_event_module` (`event_module_id`, `name`, `description`, `active`, `machine_type_id`) VALUES (3, 'Un module', 'Une module sans type de machine', 1, NULL);
INSERT INTO `fablab`.`t_event_module` (`event_module_id`, `name`, `description`, `active`, `machine_type_id`) VALUES (4, 'Découpeuse laser', 'Découpeuse laser', 1, 2);

COMMIT;




-- -----------------------------------------------------
-- Data for table `fablab`.`r_event_module_prerequisite`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_event_module_prerequisite` (`event_module_prerequiste`, `event_module_dependent`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_event_module`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_event_module` (`event_id`, `event_module_id`) VALUES (6, 1);
INSERT INTO `fablab`.`r_event_module` (`event_id`, `event_module_id`) VALUES (6, 2);
INSERT INTO `fablab`.`r_event_module` (`event_id`, `event_module_id`) VALUES (4, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_event_organisator`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_event_organisator` (`event_id`, `event_person_id`) VALUES (3, 1);
INSERT INTO `fablab`.`r_event_organisator` (`event_id`, `event_person_id`) VALUES (1, 2);
INSERT INTO `fablab`.`r_event_organisator` (`event_id`, `event_person_id`) VALUES (3, 2);
INSERT INTO `fablab`.`r_event_organisator` (`event_id`, `event_person_id`) VALUES (4, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_event_participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (1, 6);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (3, 6);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (4, 6);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (1, 7);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (3, 7);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (4, 7);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (6, 7);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (1, 13);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (3, 13);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (4, 13);
INSERT INTO `fablab`.`r_event_participant` (`event_id`, `event_person_id`) VALUES (6, 13);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fablab`.`r_event_aquired_module`
-- -----------------------------------------------------
START TRANSACTION;
USE `fablab`;
INSERT INTO `fablab`.`r_event_aquired_module` (`event_person_id`, `event_module_id`) VALUES (2, 1);
INSERT INTO `fablab`.`r_event_aquired_module` (`event_person_id`, `event_module_id`) VALUES (13, 1);
INSERT INTO `fablab`.`r_event_aquired_module` (`event_person_id`, `event_module_id`) VALUES (2, 2);
INSERT INTO `fablab`.`r_event_aquired_module` (`event_person_id`, `event_module_id`) VALUES (6, 2);

COMMIT;
