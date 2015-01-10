CREATE TABLE `r_group_role` (
  `role_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`group_id`),
  KEY `fk_t_roles_has_t_group_t_group1_idx` (`group_id`),
  KEY `fk_t_roles_has_t_group_t_roles1_idx` (`role_id`),
  CONSTRAINT `fk_t_roles_has_t_group_t_group1` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_roles_has_t_group_t_roles1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


INSERT INTO `t_role` VALUES (1,'Administration','ROLE_ADMIN'),(2,'Manage access','ROLE_MANAGE_ACCESS'),(3,'Manage machines','ROLE_MANAGE_MACHINE'),(4,'Manage payment','ROLE_MANAGE_PAYMENT'),(5,'Manage users','ROLE_MANAGE_USER'),(6,'System','ROLE_SYSTEM'),(7,'View accounting','ROLE_VIEW_ACCOUNTING'),(8,'View audit','ROLE_VIEW_AUDIT'),(9,'View machines','ROLE_VIEW_MACHINE'),(10,'Manage reservation','ROLE_MANAGE_RESERVATION'),(11,'View reservation','ROLE_VIEW_RESERVATION'),(12,'Use reservation','ROLE_USER_RESERVATION');

INSERT INTO `r_group_role` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(4,2),(9,2),(10,2),(11,2),(12,2),(9,3),(10,3),(11,3),(12,3);