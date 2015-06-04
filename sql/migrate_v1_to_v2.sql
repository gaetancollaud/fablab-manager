
-- insert configuration
INSERT INTO fablab.t_configuration VALUES (1,'FABLAB_NAME','Fablab-Fribourg'),
	(2,'FABLAB_URL','http://fablab-fribourg.ch'),
	(3,'ACCOUNTING_EDIT_HISTORY_LIMIT','7'),
	(4,'GOOGLE_CALENDAR_API_KEY','AIzaSyB6WpKyvia6OD1ePfE6JGlYXBWIjBJGOLk'),
	(5,'RECAPTCHA_SITE_KEY','6LeMcgITAAAAAGneRR7maYlG7i9LUatinVYBQZV-'),
	(6,'CURRENCY','CHF'),
	(7,'RECAPTCHA_SECRET','6LeMcgITAAAAALMhQ63a43m0Jd7D02uWCvm_TuNw');

-- insert in t_role
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (1, 'Administration', 'ROLE_ADMIN');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (2, 'View user', 'ROLE_USER_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (3, 'Manage user', 'ROLE_USER_MANAGE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (4, 'View machine', 'ROLE_MACHINE_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (5, 'Manage machine', 'ROLE_MACHINE_MANAGE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (6, 'View payment', 'ROLE_PAYMENT_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (7, 'Manage payment', 'ROLE_PAYMENT_MANAGE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (8, 'View accounting', 'ROLE_ACCOUNTING_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (9, 'Manage accounting', 'ROLE_ACCOUNTING_MANAGE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (10, 'View audit', 'ROLE_AUDIT_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (11, 'View reservation', 'ROLE_RESERVATION_VIEW');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (12, 'Use reservation', 'ROLE_RESERVATION_USE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (13, 'Manage reservation', 'ROLE_RESERVATION_MANAGE');
INSERT INTO fablab.t_role (role_id, name, technicalname) VALUES (14, 'Manage Mailing list', 'ROLE_MAILINGLIST_MANAGE');

-- insert in t_group
INSERT INTO fablab.t_group (group_id,technicalname, name) SELECT group_id, technicalname, name FROM fablab_old.t_group;

-- insert in r_group_role
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 1, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 2, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 3, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 4, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 5, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 6, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 7, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 8, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 9, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 10, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 11, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 12, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 13, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 14, group_id FROM fablab_old.t_group WHERE technicalname='comite');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 2, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 3, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 4, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 6, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 7, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 11, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 12, group_id FROM fablab_old.t_group WHERE technicalname='animateur');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 4, group_id FROM fablab_old.t_group WHERE technicalname='member');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 11, group_id FROM fablab_old.t_group WHERE technicalname='member');
INSERT INTO fablab.r_group_role (role_id, group_id) (SELECT 12, group_id FROM fablab_old.t_group WHERE technicalname='member');

-- insert t_membership_type
INSERT INTO fablab.t_membership_type (membership_type_id, name, duration, price) SELECT 
    membership_type_id,
    name,
    365,
    (SELECT 
            price
        FROM
            fablab_old.r_price_cotisation
        WHERE
            price_revision_id = (SELECT 
                    price_revision_id
                FROM
                    fablab_old.t_price_revision
                ORDER BY price_revision_id DESC
                LIMIT 1)
                AND membership_type_id = mt.membership_type_id) as price
FROM
    fablab_old.t_membership_type mt;

-- insert in t_user
INSERT INTO fablab.t_user (user_id, membership_type_id, firstname,lastname, email, date_inscr, rfid,
	enabled, phone, address) 
SELECT user_id, membership_type_id, firstname, lastname, COALESCE( email, CONCAT('notdefined@', firstname, lastname)) as email, 
	date_inscr, rfid, enabled, phone, address FROM fablab_old.t_user;

-- insert in r_group_user
INSERT INTO fablab.r_group_user (group_id, user_id) SELECT group_id, user_id FROM fablab_old.r_group_user;

-- insert in t_machine_type
INSERT INTO fablab.t_machine_type (machine_type_id, technicalname, name, restricted)
	 SELECT machine_type_id, technicalname, name,  restricted FROM fablab_old.t_machine_type;

-- insert in t_machine
INSERT INTO fablab.t_machine (machine_id, name, machine_type_id) 
	SELECT machine_id, name, machine_type_id FROM fablab_old.t_machine;

-- insert in t_audit
INSERT INTO fablab.t_audit (audit_id, who, action, object, object_id, dateandtime, success, content, detail)
	SELECT audit_id, who, action, object, object_id, dateandtime, success, content, detail FROM fablab_old.t_audit;

-- insert in r_user_authorized_machine_type
INSERT INTO fablab.r_user_authorized_machine_type (user_id, machine_type_id, formation_date)
	SELECT user_id, machine_type_id, formation_date FROM fablab_old.r_user_authorized_machine_type;

-- insert in t_payment
INSERT INTO fablab.t_payment (payment_id, total, date_payement, user_id, cashier_id, comment) 
	SELECT payment_id, total, date_payement, user_id, cashier_id, comment FROM fablab_old.t_payment;

-- insert in t_subscription
INSERT INTO fablab.t_subscription (subscription_id, user_id, membership_type_id, date_subscription, price, duration, comment)
SELECT s.subscription_id, s.user_id, s.membership_type_id, s.date_subscription, pc.price, 365, s.comment
FROM fablab_old.t_subscription s
INNER JOIN fablab_old.r_price_cotisation pc ON s.membership_type_id=pc.membership_type_id AND 
pc.price_revision_id = (SELECT price_revision_id FROM fablab_old.t_price_revision ORDER BY price_revision_id DESC LIMIT 1);


-- insert in t_usage
INSERT INTO fablab.t_usage (usage_id, date_start, price_hour, minutes, additional_cost, user_id, machine_id, membership_type_id, comment)
SELECT DISTINCT u.usage_id, u.date_start, pm.price, u.minutes, u.additional_cost, u.user_id, u.machine_id, u.membership_type_id, u.comment
FROM fablab_old.t_usage u
INNER JOIN fablab_old.t_machine m ON u.machine_id = m.machine_id
INNER JOIN fablab_old.r_price_machine pm ON m.machine_type_id = pm.machine_type_id AND pm.membership_type_id=u.membership_type_id AND 
pm.price_revision_id = (SELECT price_revision_id FROM fablab_old.t_price_revision ORDER BY price_revision_id DESC LIMIT 1)
ORDER BY u.usage_id;

UPDATE fablab.t_user SET password='a76c7b4a9ebc536c465ed7b4c64dcf0550862212f716fe1844c99b68623e1a28', 
password_salt='hCSlWI8fFOTaEYPe2xUQ8BC4dBH1ntkTtvK4wRoN' WHERE email = 'gaetancollaud@gmail.com';





