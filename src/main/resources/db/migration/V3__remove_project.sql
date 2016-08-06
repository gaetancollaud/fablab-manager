
DROP TABLE r_project_user;
DROP TABLE t_project;

DELETE FROM r_group_role WHERE role_id IN (SELECT t_role.role_id FROM t_role WHERE technicalname LIKE "ROLE_PROJECT_%");

DELETE FROM t_role WHERE technicalname LIKE "ROLE_PROJECT_%";