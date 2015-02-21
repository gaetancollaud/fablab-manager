UPDATE t_user SET 
login=CONCAT("member", CAST(user_id as CHAR)),
password="94ad0573ed0f4a7607f857e571a2d997f1208b868d7f580c646267b63fea25ec",
firstname=CONCAT("first", CAST(user_id as CHAR)),
lastname=CONCAT("last", CAST(user_id as CHAR)),
email=CONCAT(CAST(user_id as CHAR), "@email.com"),
rfid=null,
phone="",
address=""
WHERE user_id!=0;

TRUNCATE TABLE t_audit;