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

