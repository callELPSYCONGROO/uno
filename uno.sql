CREATE DATABASE `uno` CHARACTER SET utf8mb4;

CREATE TABLE `uno`.`video`(
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(128) NOT NULL COMMENT '标题',
	`download` VARCHAR(256) NOT NULL COMMENT '下载地址',
	`vDate` DATETIME COMMENT '插入时间',
	`category` VARCHAR(10) COMMENT '分类',
	`isShow` INT(2) DEFAULT 1 COMMENT '是否显示',
	`createTime` DATETIME COMMENT '创建时间',
	PRIMARY KEY (`id`),
	UNIQUE KEY title_unique (`title`)
) ENGINE=INNODB CHARSET=utf8mb4;
