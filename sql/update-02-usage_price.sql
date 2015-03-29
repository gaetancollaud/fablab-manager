

-- Update price
UPDATE t_usage u, (
	SELECT u.usage_id, pm.price
	FROM t_usage u
	INNER JOIN t_user user ON user.user_id = u.user_id
	INNER JOIN t_machine m ON m.machine_id = u.machine_id
	INNER JOIN r_price_machine pm ON pm.price_revision_id=u.price_revision_id AND pm.machine_type_id=m.machine_type_id AND pm.membership_type_id=user.membership_type_id
	
) AS i
SET u.price_hour=i.price
WHERE u.usage_id=i.usage_id

-- CHECK
SELECT u.usage_id, u.price_hour, pm.price
	FROM t_usage u
	INNER JOIN t_user user ON user.user_id = u.user_id
	INNER JOIN t_machine m ON m.machine_id = u.machine_id
	INNER JOIN r_price_machine pm ON pm.price_revision_id=u.price_revision_id AND pm.machine_type_id=m.machine_type_id AND pm.membership_type_id=user.membership_type_id