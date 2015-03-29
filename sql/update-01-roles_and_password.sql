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
INSERT INTO `fablab`.`t_role` (`role_id`, `name`, `technicalname`) VALUES (14, 'Manage Mailing list', 'ROLE_MAILINGLIST_MANAGE');

INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (1, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (2, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (3, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (4, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (5, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (6, 1);
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
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (13, 1);
INSERT INTO `fablab`.`r_group_role` (`role_id`, `group_id`) VALUES (14, 1);



UPDATE t_user SET 
password_manager=password, 
password="a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28",
password_salt="hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN"
 WHERE user_id!=0;
 
 -- Ne pas oublier de changer le realm pour password_manager (redémarré aussi)

