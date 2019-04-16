CREATE DATABASE `uno` CHARACTER SET utf8mb4;

CREATE TABLE `video` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `download` varchar(256) DEFAULT NULL COMMENT '下载地址',
  `category` varchar(10) DEFAULT NULL COMMENT '分类',
  `date` date DEFAULT NULL COMMENT '更新日期',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_show` int(2) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
