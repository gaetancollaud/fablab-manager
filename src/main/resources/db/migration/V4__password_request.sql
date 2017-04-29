ALTER TABLE t_user
	ADD COLUMN password_request VARCHAR(64) NULL
	AFTER password_salt;
