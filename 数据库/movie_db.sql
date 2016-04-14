DROP TABLE IF EXISTS `comment`; 
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uerid` int(11) DEFAULT NULL,
  `movieid` int(11) DEFAULT NULL,
  `text` varchar(5000) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 

 
DROP TABLE IF EXISTS `likemovie`; 
CREATE TABLE `likemovie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weibo_id` bigint(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `log`; 
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `weibo_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_login` tinyint(1) DEFAULT '0',
  `login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logout_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 
 

DROP TABLE IF EXISTS `movie`; 
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `intro` varchar(5000) DEFAULT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `large_poster_url` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `score_count` int(11) DEFAULT NULL,
  `pagelistid` int(11) DEFAULT NULL,
  `is_showing` tinyint(1) DEFAULT NULL,
  `is_coming` tinyint(1) DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 
 

DROP TABLE IF EXISTS `pagelist`;  
CREATE TABLE `pagelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(20000) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `movie_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 
 

DROP TABLE IF EXISTS `pagelistmovie`; 
CREATE TABLE `pagelistmovie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pagelistid` int(11) DEFAULT NULL,
  `movieid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 
 

DROP TABLE IF EXISTS `user`; 
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `like_movie_id` int(11) DEFAULT NULL,
  `done_movie_id` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `weibo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8; 