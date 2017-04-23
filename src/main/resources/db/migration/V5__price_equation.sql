ALTER TABLE r_price_machine
	ADD COLUMN equation VARCHAR(100) NOT NULL
	AFTER price,
	ADD COLUMN unit VARCHAR(20) NOT NULL
	AFTER equation;

UPDATE r_price_machine
SET unit = 'HOUR', equation = CONCAT('amount*', price);

ALTER TABLE r_price_machine
	DROP COLUMN price;

ALTER TABLE t_usage
	ADD COLUMN equation VARCHAR(100) NOT NULL,
	ADD COLUMN unit VARCHAR(20) NOT NULL,
	ADD COLUMN amount DOUBLE NOT NULL,
	ADD COLUMN total DOUBLE NOT NULL;

UPDATE t_usage
SET unit='HOUR', equation = CONCAT('amount*', price_hour), amount = minutes / 60, total = (minutes/60*price_hour+additional_cost) ;

ALTER TABLE t_usage
	DROP COLUMN minutes,
	DROP COLUMN price_hour;

DROP TABLE IF EXISTS v_user_balance_usage;
USE fablab;
CREATE  OR REPLACE VIEW v_user_balance_usage AS
	SELECT u.firstname, u.lastname, u.user_id, -a.total AS total
	FROM t_user AS u
		INNER JOIN t_usage AS a ON u.user_id=a.user_id;
