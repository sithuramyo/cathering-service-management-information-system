# CSMIS
CSMIS-Catering Service Management Information System
For AvoidMeat Table
```sql
CREATE TABLE `avoidmeat` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `meat_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
For AvoidMeatList Table
```sql
CREATE TABLE `avoidmeatlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avoid` varchar(255) DEFAULT NULL,
  `door_log_no_meats` varchar(255) DEFAULT NULL,
  `doorlognomeats` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj2jb3nd2ery5de4il9vi3q6fx` (`door_log_no_meats`),
  KEY `FKtpn610jhu5svtbp3wvlkb3unt` (`doorlognomeats`),
  CONSTRAINT `FKj2jb3nd2ery5de4il9vi3q6fx` FOREIGN KEY (`door_log_no_meats`) REFERENCES `registercatering` (`door_log_no`),
  CONSTRAINT `FKtpn610jhu5svtbp3wvlkb3unt` FOREIGN KEY (`doorlognomeats`) REFERENCES `registercatering` (`door_log_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
For Contact Table
```sql
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `creativeinvoice` (
  `id` varchar(255) NOT NULL,
  `approvedby` varchar(255) DEFAULT NULL,
  `cashier` varchar(255) DEFAULT NULL,
  `enddate` varchar(255) DEFAULT NULL,
  `paymethod` varchar(255) DEFAULT NULL,
  `receivedby` varchar(255) DEFAULT NULL,
  `resid` varchar(255) DEFAULT NULL,
  `startdate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `doorlog` (
  `date` varchar(255) NOT NULL,
  `doorlogno` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`date`,`doorlogno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `emailconfig` (
  `id` int NOT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `port` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cleanese` int NOT NULL,
  `cost` int NOT NULL,
  `date_post` datetime(6) DEFAULT NULL,
  `quality` int NOT NULL,
  `suggest` varchar(255) DEFAULT NULL,
  `operator_id` bigint NOT NULL,
  `restaurant_id` varchar(255) DEFAULT NULL,
  `staff_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5n7f5w2dc8a65y1clvyd68xu4` (`operator_id`),
  KEY `FKjrymqggwaclqeg7j6sjdpulox` (`restaurant_id`),
  KEY `FK11a6vssmch4g7ipiuwqp2monh` (`staff_id`),
  CONSTRAINT `FK11a6vssmch4g7ipiuwqp2monh` FOREIGN KEY (`staff_id`) REFERENCES `operator` (`id`),
  CONSTRAINT `FK5n7f5w2dc8a65y1clvyd68xu4` FOREIGN KEY (`operator_id`) REFERENCES `operator` (`id`),
  CONSTRAINT `FKjrymqggwaclqeg7j6sjdpulox` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurantinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `holidaydata` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `holiday_date` date NOT NULL,
  `holiday_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `menuimport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longblob,
  `image` varchar(255) DEFAULT NULL,
  `size` bigint NOT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `operator` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dept` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `door_log_no` varchar(255) DEFAULT NULL,
  `employee_email` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `employee_password` varchar(255) DEFAULT NULL,
  `otp_code` varchar(255) DEFAULT NULL,
  `otp_creation_time` datetime(6) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `staff_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `team` varchar(255) DEFAULT NULL,
  `toggle` varchar(255) DEFAULT 'false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `orderdetail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dates` varchar(255) DEFAULT NULL,
  `extrapax` int NOT NULL,
  `quantity` int NOT NULL,
  `weekly_order_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe46hqylm42tcb8yr4putm2nrj` (`weekly_order_id`),
  CONSTRAINT `FKe46hqylm42tcb8yr4putm2nrj` FOREIGN KEY (`weekly_order_id`) REFERENCES `weeklyorder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  `feedback_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK87b7tfns5o5expt92dxfieyg0` (`feedback_id`),
  CONSTRAINT `FK87b7tfns5o5expt92dxfieyg0` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `registercatering` (
  `door_log_no` varchar(255) NOT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `employee_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `staff_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `team` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`door_log_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `registerdate` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dates` varchar(255) DEFAULT NULL,
  `door_log_no_dates` varchar(255) DEFAULT NULL,
  `doorlognodates` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhcaspuht07d4a23nreu408hy7` (`doorlognodates`),
  CONSTRAINT `FKkkyrkitnbbljlqnu63d7qbadh` FOREIGN KEY (`door_log_no_dates`) REFERENCES `registercatering` (`door_log_no`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `restaurantinfo` (
  `id` varchar(255) NOT NULL,
  `resaddress` varchar(255) DEFAULT NULL,
  `resemail` varchar(255) DEFAULT NULL,
  `resph` varchar(255) DEFAULT NULL,
  `resturantname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `weeklyinvoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createinvoiceid` varchar(255) DEFAULT NULL,
  `datcost` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `employeecost` double DEFAULT NULL,
  `invoicedate` varchar(255) DEFAULT NULL,
  `orderid` bigint DEFAULT NULL,
  `paymentdate` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `resid` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'unpaid',
  `totalamount` double DEFAULT NULL,
  `totalpax` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `weeklyorder` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `datcost` double NOT NULL,
  `doorlogid` varchar(255) DEFAULT NULL,
  `employeecost` double NOT NULL,
  `orderdate` varchar(255) DEFAULT NULL,
  `starttoenddate` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_cost` double NOT NULL,
  `totalpax` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
#View
```sql
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `csmis-khaingfamily`.`avoidcount` AS select `rc`.`door_log_no` AS `id`,`rc`.`door_log_no` AS `doorLogNo`,`rd`.`dates` AS `dates`,`aml`.`avoid` AS `avoid` from ((`csmis-khaingfamily`.`registercatering` `rc` join `csmis-khaingfamily`.`registerdate` `rd` on((`rc`.`door_log_no` = `rd`.`door_log_no_dates`))) join `csmis-khaingfamily`.`avoidmeatlist` `aml` on((`rc`.`door_log_no` = `aml`.`door_log_no_meats`)));
```
```sql
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `csmis-khaingfamily`.`lunchcount` AS select `all_dates`.`Date` AS `Date`,coalesce(`re`.`count`,0) AS `RE`,coalesce(`rne`.`count`,0) AS `RNE`,coalesce(`ue`.`count`,0) AS `UE` from (((((select `registerfinal1`.`dates` AS `Date` from `csmis-khaingfamily`.`registerfinal1` union select `csmis-khaingfamily`.`doorlog`.`date` AS `date` from `csmis-khaingfamily`.`doorlog`) `all_dates` left join (select `registerfinal1`.`dates` AS `dates`,count(0) AS `count` from `csmis-khaingfamily`.`registerfinal1` group by `registerfinal1`.`dates`) `rf` on((`all_dates`.`Date` = `rf`.`dates`))) left join (select `csmis-khaingfamily`.`doorlog`.`date` AS `Date`,count(0) AS `count` from (`csmis-khaingfamily`.`doorlog` left join `csmis-khaingfamily`.`registerfinal1` on(((`csmis-khaingfamily`.`doorlog`.`doorlogno` = `registerfinal1`.`door_log_no`) and (`csmis-khaingfamily`.`doorlog`.`date` = `registerfinal1`.`dates`)))) where (`registerfinal1`.`door_log_no` is null) group by `csmis-khaingfamily`.`doorlog`.`date`) `ue` on((`all_dates`.`Date` = `ue`.`Date`))) left join (select `registerfinal1`.`dates` AS `Date`,count(0) AS `count` from (`csmis-khaingfamily`.`registerfinal1` join `csmis-khaingfamily`.`doorlog` on(((`registerfinal1`.`door_log_no` = `csmis-khaingfamily`.`doorlog`.`doorlogno`) and (`registerfinal1`.`dates` = `csmis-khaingfamily`.`doorlog`.`date`)))) group by `registerfinal1`.`dates`) `re` on((`all_dates`.`Date` = `re`.`Date`))) left join (select `registerfinal1`.`dates` AS `Date`,count(0) AS `count` from (`csmis-khaingfamily`.`registerfinal1` left join `csmis-khaingfamily`.`doorlog` on(((`registerfinal1`.`door_log_no` = `csmis-khaingfamily`.`doorlog`.`doorlogno`) and (`registerfinal1`.`dates` = `csmis-khaingfamily`.`doorlog`.`date`)))) where ((`csmis-khaingfamily`.`doorlog`.`doorlogno` is null) and (`registerfinal1`.`dates` between (select min(`csmis-khaingfamily`.`doorlog`.`date`) from `csmis-khaingfamily`.`doorlog`) and (select max(`csmis-khaingfamily`.`doorlog`.`date`) from `csmis-khaingfamily`.`doorlog`))) group by `registerfinal1`.`dates`) `rne` on((`all_dates`.`Date` = `rne`.`Date`))) where ((`re`.`count` > 0) or (`rne`.`count` > 0) or (`ue`.`count` > 0));
```

