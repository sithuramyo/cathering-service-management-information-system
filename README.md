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
