DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);


CREATE TABLE `m_original_data` (
  `p_i_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `l_create_date_time` bigint(20) DEFAULT NULL,
  `i_data_source` int(11) DEFAULT NULL,
  `c_original_html` longtext,
  PRIMARY KEY (`p_i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6095 DEFAULT CHARSET=utf8;



CREATE TABLE `m_data` (
  `p_i_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `c_content` varchar(255) DEFAULT NULL,
  `c_hots` varchar(255) DEFAULT NULL,
  `c_link` longtext,
  `c_mark` varchar(255) DEFAULT NULL,
  `f_i_original_data_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`p_i_id`),
  KEY `FK_5b3bumbvww0db9jm4oa5fsxxf` (`f_i_original_data_id`),
  CONSTRAINT `FK_5b3bumbvww0db9jm4oa5fsxxf` FOREIGN KEY (`f_i_original_data_id`) REFERENCES `m_original_data` (`p_i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=307899 DEFAULT CHARSET=utf8;

