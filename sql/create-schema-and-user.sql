CREATE SCHEMA fablab CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'fablab'@'localhost' IDENTIFIED BY 'fablab';
GRANT ALL PRIVILEGES ON fablab.* TO 'fablab'@'localhost' WITH GRANT OPTION;
COMMIT;
