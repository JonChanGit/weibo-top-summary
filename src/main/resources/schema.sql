DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS original_top_summary_data;
CREATE TABLE `original_top_summary_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` bigint(20) DEFAULT NULL,
  `data_source` int(11) DEFAULT NULL,
  `original_html` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6095 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS top_summary_data;

CREATE TABLE `top_summary_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `hots` varchar(255) DEFAULT NULL,
  `link` longtext,
  `mark` varchar(255) DEFAULT NULL,
  `original_data_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5b3bumbvww0db9jm4oa5fsxxf` (`original_data_id`),
  CONSTRAINT `FK_5b3bumbvww0db9jm4oa5fsxxf` FOREIGN KEY (`original_data_id`) REFERENCES `original_top_summary_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307899 DEFAULT CHARSET=utf8;

CREATE TABLE `bing_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `startdate` varchar(50) DEFAULT NULL,
  `fullstartdate` varchar(50) DEFAULT NULL,
  `enddate` varchar(50) DEFAULT NULL,
  `url` longtext,
  `urlbase` longtext,
  `copyright` varchar(500) DEFAULT NULL,
  `copyrightlink` longtext,
  `title` varchar(500) DEFAULT NULL,
  `quiz` longtext,
  `wp` int(11) DEFAULT NULL,
  `hsh` varchar(255) DEFAULT NULL,
  `drk` int(255) DEFAULT NULL,
  `top` int(255) DEFAULT NULL,
  `bot` int(255) DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地区',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_bing_image_1` (`startdate`,`region`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;