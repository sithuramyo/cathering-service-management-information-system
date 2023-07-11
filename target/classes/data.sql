INSERT INTO operator (employee_name, employee_email, employee_password, staff_id, status, role,door_log_no)
SELECT 'Default Admin', 'datcateringdefault2311@gmail.com', '$2a$12$A/Xfv3B6NhHupbg9n7xGauFCAAMEw807ayyIaRECVo/QbX9vHTel6', 'default_admin', 'ACTIVE', 'ADMIN','11111'
FROM DUAL
WHERE NOT EXISTS (SELECT * FROM operator WHERE staff_id = 'default_admin');

INSERT INTO emailconfig (id,host,password,port,username,cron_expression) SELECT 1,'smtp.gmail.com','hwhdkpeseqollxho','587','cateringservicedat@gmail.com','0 0 0 * * *'
FROM DUAL 
WHERE NOT EXISTS (SELECT * FROM emailconfig WHERE id = '1');