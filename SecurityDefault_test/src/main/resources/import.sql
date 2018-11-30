-- --------------------------------------------------------
-- 호스트:                          192.169.0.201
-- 서버 버전:                        10.2.13-MariaDB-10.2.13+maria~xenial-log - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

-- 테이블 데이터 security.account:~1 rows (대략적) 내보내기
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `address1`, `address2`, `email`, `enable`, `hp`, `password`, `post`, `tel`, `user_id`, `username`) VALUES
	(1, '광주광역시 동구 문화전당로23번길8-2', '해성기획', 'mslee3847@nate.com', 1, '010-9302-1029', '$2a$10$qqCl/NupYFIbSXzuiyvdFO7CWALxyRY.JGAu7MWC4p3qiwc8q2Kg6', '61487', '062-236-3860', 'admin', '이명신'),
	(2, '광주광역시 북구 우산동 611-4', '1층', 'mslee3847@nate.com', 1, '010-9302-1029', '$2a$10$PxDC9gsei5Wikcl8ma7W..RhjkaR9UNKb6pbS.pdCCddTMHKZgTmO', '500-080', '062-262-6764', 'lms1029', '이명신');

-- 테이블 데이터 security.role:~4 rows (대략적) 내보내기
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `description`, `name`) VALUES
	(1, '관리자', 'ROLE_ADMIN'),
	(2, '관리자 보안', 'ROLE_SUPER'),
	(3, '사용자', 'ROLE_USER'),
	(4, '임시접근자', 'ROLE_TEMP');

-- 테이블 데이터 security.account_roles:~1 rows (대략적) 내보내기
DELETE FROM `account_roles`;
/*!40000 ALTER TABLE `account_roles` DISABLE KEYS */;
INSERT INTO `account_roles` (`account_id`, `roles_id`) VALUES(1, 1), (2, 3);