DELETE FROM r_group_role WHERE group_id=(
	SELECT group_id FROM t_group WHERE technicalname = 'comite'
) AND role_id=(
	SELECT role_id FROM t_role WHERE technicalname = 'ROLE_ACCOUNTING_MANAGE'
)

INSERT INTO t_group (technicalname,name)VALUES('accounting','Accounting');

INSERT INTO r_group_role (group_id, role_id) (SELECT 
	(SELECT group_id FROM t_group WHERE technicalname = 'accounting'),
	(SELECT role_id FROM t_role WHERE technicalname = 'ROLE_ACCOUNTING_MANAGE')
);

INSERT INTO r_group_user (group_id, user_id) (SELECT 
	(SELECT group_id FROM t_group WHERE technicalname = 'accounting'),
	(SELECT user_id FROM t_user WHERE email = 'todo')
);

