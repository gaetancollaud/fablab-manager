ALTER TABLE t_machine
	ADD COLUMN introduction VARCHAR(255) NULL DEFAULT NULL AFTER machine_type_id,
	ADD COLUMN description TEXT NULL DEFAULT NULL AFTER introduction,
	ADD COLUMN image_url VARCHAR(255) NULL DEFAULT NULL AFTER description;

CREATE TABLE t_project (
	project_id INT(11) NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	introduction VARCHAR(255) NULL DEFAULT NULL,
	description LONGTEXT NOT NULL,
	state VARCHAR(45) NOT NULL,
	date_start DATETIME NOT NULL,
	date_end DATETIME NULL DEFAULT NULL,
	image_url VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (project_id))
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COLLATE = utf8_general_ci;

CREATE TABLE r_project_user (
	project_id INT(11) NOT NULL,
	user_id INT(11) NOT NULL,
	can_edit TINYINT(1) NOT NULL,
	role VARCHAR(45) NULL DEFAULT NULL,
	PRIMARY KEY (project_id, user_id),
	INDEX fk_t_project_has_t_user_t_user1_idx (user_id ASC),
	INDEX fk_t_project_has_t_user_t_project1_idx (project_id ASC),
	CONSTRAINT fk_t_project_has_t_user_t_project1
	FOREIGN KEY (project_id)
	REFERENCES t_project (project_id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_t_project_has_t_user_t_user1
	FOREIGN KEY (user_id)
	REFERENCES t_user (user_id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COLLATE = utf8_general_ci;

CREATE TABLE t_asset (
	asset_id INT(11) NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NULL DEFAULT NULL,
	asset_data MEDIUMBLOB NOT NULL,
	mime VARCHAR(45) NOT NULL,
	owner_id INT(11) NOT NULL,
	date_upload DATETIME NOT NULL,
	data_size INT(11) NOT NULL,
	extension VARCHAR(6) NOT NULL,
	PRIMARY KEY (asset_id),
	INDEX fk_t_asset_t_user1_idx (owner_id ASC),
	CONSTRAINT fk_t_asset_t_user1
	FOREIGN KEY (owner_id)
	REFERENCES t_user (user_id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
	ENGINE = InnoDB
	DEFAULT CHARACTER SET = utf8
	COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- View v_group_user
-- -----------------------------------------------------
DROP TABLE IF EXISTS v_group_user;

CREATE  OR REPLACE VIEW v_group_user AS
	SELECT u.email, r.*, g.technicalname
	FROM r_group_user AS r
		INNER JOIN t_group AS g USING(group_id)
		INNER JOIN t_user AS u USING(user_id);

DROP TABLE IF EXISTS v_user_balance;

CREATE  OR REPLACE VIEW v_user_balance AS
	SELECT user_id, SUM(total) total, COUNT(user_id) as nb
	FROM v_user_balance_all
	GROUP BY user_id;

DROP TABLE IF EXISTS v_user_balance_subscription;
CREATE  OR REPLACE VIEW v_user_balance_subscription AS
	SELECT u.firstname, u.lastname, u.user_id, -s.price AS total
	FROM t_user AS u
		INNER JOIN t_subscription AS s ON u.user_id=s.user_id
;

DROP TABLE IF EXISTS v_user_balance_usage;
CREATE  OR REPLACE VIEW v_user_balance_usage AS
	SELECT u.firstname, u.lastname, u.user_id, -(a.additional_cost+a.minutes/60*a.price_hour) AS total
	FROM t_user AS u
		INNER JOIN t_usage AS a ON u.user_id=a.user_id;

DROP TABLE IF EXISTS v_user_balance_payment;

CREATE  OR REPLACE VIEW v_user_balance_payment AS
	SELECT u.firstname, u.lastname, u.user_id, p.total
	FROM t_user u
		INNER JOIN t_payment p ON u.user_id = p.user_id;

DROP TABLE IF EXISTS v_user_balance_all;
CREATE  OR REPLACE VIEW v_user_balance_all AS
	SELECT user_id, total FROM v_user_balance_subscription
	UNION ALL
	SELECT user_id, total FROM v_user_balance_usage
	UNION ALL
	SELECT user_id, total FROM v_user_balance_payment;


DROP TABLE IF EXISTS v_role_by_user;
CREATE  OR REPLACE VIEW v_role_by_user AS
	SELECT DISTINCT u.email, r.technicalname
	FROM t_user AS u
		INNER JOIN r_group_user AS gu ON u.user_id=gu.user_id
		INNER JOIN r_group_role AS gr ON gu.group_id = gr.group_id
		INNER JOIN t_role AS r ON gr.role_id = r.role_id;
DROP TABLE IF EXISTS v_user_balance_grouped;
CREATE  OR REPLACE VIEW v_user_balance_grouped AS
	SELECT u.user_id, coalesce(SUM(total), 0) as balance
	FROM t_user u
		LEFT JOIN v_user_balance_all b USING(user_id)
	GROUP BY u.user_id
;
