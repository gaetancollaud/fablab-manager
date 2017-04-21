ALTER TABLE r_price_machine
	ADD COLUMN equation VARCHAR(45) NOT NULL
	AFTER price,
	ADD COLUMN unit VARCHAR(10) NOT NULL
	AFTER equation;

UPDATE r_price_machine
SET unit = 'HOUR', equation = CONCAT('x*', price);

ALTER TABLE r_price_machine
	DROP COLUMN price;

ALTER TABLE t_usage
	ADD COLUMN equation VARCHAR(45) NOT NULL,
	ADD COLUMN unit VARCHAR(10) NOT NULL,
	ADD COLUMN amount DOUBLE NOT NULL;

UPDATE t_usage
SET equation = CONCAT('x*', price_hour), amount = minutes / 60;