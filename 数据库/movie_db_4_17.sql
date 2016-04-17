-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: movie
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (43,'张国荣 ',31644),(44,' 张曼玉 ',31644),(45,' 梅艳芳 ',31644),(46,' 陈友 ',31644),(47,' 汤镇宗',31644),(48,'杰克·布莱克 ',99946),(49,'亨利·卡维尔 ',176999),(50,'郭富城 ',177162),(51,' 巩俐 ',177162),(52,' 冯绍峰 ',177162),(53,' 小沈阳 ',177162),(54,' 罗仲谦 ',177162),(55,' 费翔 ',177162),(56,' 陈慧琳',177162);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coming`
--

DROP TABLE IF EXISTS `coming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coming` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `intro` varchar(5000) DEFAULT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `large_poster_url` varchar(255) DEFAULT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `wanttosee` int(11) DEFAULT NULL,
  `is_wanttosee` int(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `score_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coming`
--

LOCK TABLES `coming` WRITE;
/*!40000 ALTER TABLE `coming` DISABLE KEYS */;
/*!40000 ALTER TABLE `coming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uerid` int(11) DEFAULT NULL,
  `movieid` int(11) DEFAULT NULL,
  `text` varchar(5000) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (13,'黄泰来',31644),(14,'余仁英',99946),(15,'扎克·施奈德',176999),(16,'郑保瑞',177162);
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likemovie`
--

DROP TABLE IF EXISTS `likemovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likemovie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weibo_id` bigint(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likemovie`
--

LOCK TABLES `likemovie` WRITE;
/*!40000 ALTER TABLE `likemovie` DISABLE KEYS */;
INSERT INTO `likemovie` VALUES (25,2908727445,177066),(26,2908727445,99946);
/*!40000 ALTER TABLE `likemovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `weibo_id` bigint(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_login` tinyint(1) DEFAULT '0',
  `login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logout_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (20,'_毛通_',2908727445,NULL,0,'2016-04-16 18:22:27','2016-04-16 18:40:09'),(21,'_毛通_',2908727445,NULL,0,'2016-04-16 18:23:41','2016-04-16 18:40:09'),(22,'_毛通_',2908727445,NULL,0,'2016-04-16 18:39:39','2016-04-16 18:40:09'),(23,'_毛通_',2908727445,NULL,0,'2016-04-16 18:39:57','2016-04-16 18:40:09'),(24,'_毛通_',2908727445,NULL,0,'2016-04-17 03:22:56','2016-04-17 03:24:12'),(25,'_毛通_',2908727445,NULL,0,'2016-04-17 03:24:01','2016-04-17 03:24:12'),(26,'_毛通_',2908727445,NULL,0,'2016-04-17 03:24:08','2016-04-17 03:24:12'),(27,'_毛通_',2908727445,NULL,1,'2016-04-17 03:25:52','2016-04-17 03:25:52'),(28,'_毛通_',2908727445,NULL,1,'2016-04-17 03:26:05','2016-04-17 03:26:05'),(29,'_毛通_',2908727445,NULL,1,'2016-04-17 03:26:09','2016-04-17 03:26:09'),(30,'_毛通_',2908727445,NULL,1,'2016-04-17 07:42:37','2016-04-17 07:42:37');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `video_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181174 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (11146,'歼匪喋血战',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/11146.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(20212,'大闹天宫',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/20212_vertical.jpg',NULL,8,NULL,6705428,NULL,NULL,NULL,NULL),(22416,'逍遥骑士',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/22416_vertical.jpg',NULL,7,NULL,7735771,NULL,NULL,NULL,NULL),(23932,'教父',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/23932_vertical.jpg',NULL,9,NULL,7773087,NULL,NULL,NULL,NULL),(25845,'镜子',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/25845.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(25926,'马尼拉：在霓虹灯的魔爪下',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/25926.jpg',NULL,0,NULL,7777829,NULL,NULL,NULL,NULL),(27062,'橡皮头',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/27062.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(28895,'象人',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/28895.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(31644,'缘份','剧情 / 喜剧 / 爱情','在新开通的地下铁中，青年陈保罗（张国荣 饰）对同车的莫妮卡（张曼玉 饰）一见钟情，不幸却被另一名乘客安妮（梅艳芳 饰）趁机捉弄。这几位年轻人很快散落在茫茫人海中，职场新丁陈保罗开始为工作奔忙，莫妮卡则为了厘清和上司的一段旧情而心神不宁。某日，保罗与莫妮卡再次相遇，而富家小姐安妮亦再度出现，半捉弄半挑逗地缠上了保罗。莫妮卡找到新工作，遭遇了好色新领导的骚扰，保罗则在安妮的暗中帮衬下正式追求莫妮卡，与后者的好色上司连番交手。莫妮卡考虑接受保罗的爱意但终于退缩，声称将他们的爱情交付给一场地铁中的缘分游戏。在离乱的人流中，保罗和安妮一起，去找寻消失的莫妮卡……\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/31644_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/31644_big.jpg',8.5,17651,NULL,1,NULL,NULL,'http://v.iask.com/v_play_ipad.php?vid=139869711'),(32551,'英雄本色',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/32551_vertical.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(32571,'蓝丝绒',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/32571.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(35127,'好家伙',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/35127_vertical.jpg',NULL,7,NULL,7773087,NULL,NULL,NULL,NULL),(35150,'我心狂野',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/35150.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(35754,'末路狂花',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/35754_vertical.jpg',NULL,8,NULL,7735771,NULL,NULL,NULL,NULL),(36402,'落水狗',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/36402_vertical.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(36487,'双峰镇：与火同行',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/36487.jpg',NULL,8,NULL,7791220,NULL,NULL,NULL,NULL),(37026,'唐伯虎点秋香',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37026_vertical.jpg',NULL,8,NULL,7810449,NULL,NULL,NULL,NULL),(37049,'青木瓜之味',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37049.jpg',NULL,7,NULL,7777829,NULL,NULL,NULL,NULL),(37534,'加州杀手',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37534.jpg',NULL,7,NULL,7735771,NULL,NULL,NULL,NULL),(37642,'天生杀人狂',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37642_vertical.jpg',NULL,7,NULL,7735771,NULL,NULL,NULL,NULL),(37651,'国产凌凌漆',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37651_vertical.jpg',NULL,8,NULL,7810449,NULL,NULL,NULL,NULL),(37658,'烈日灼人',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/37658.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(38249,'破坏之王',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/38249_vertical.jpg',NULL,7,NULL,7810449,NULL,NULL,NULL,NULL),(38259,'大话西游之月光宝盒',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/38259_vertical.jpg',NULL,9,NULL,6705428,NULL,NULL,NULL,NULL),(38298,'回魂夜',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/38298_vertical.jpg',NULL,7,NULL,7810449,NULL,NULL,NULL,NULL),(39575,'妖夜慌踪',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/39575.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(39587,'不准掉头',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/39587_vertical.jpg',NULL,7,NULL,7735771,NULL,NULL,NULL,NULL),(40196,'中央车站',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40196_vertical.jpg',NULL,8,NULL,7735771,NULL,NULL,NULL,NULL),(40205,'西伯利亚的理发师',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40205.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(40847,'菊次郎的夏天',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40847_vertical.jpg',NULL,7,NULL,7735771,NULL,NULL,NULL,NULL),(40853,'枪火',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40853_vertical.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(40877,'史崔特先生的故事',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40877.jpg',NULL,8,NULL,7735771,NULL,NULL,NULL,NULL),(40887,'恋恋三季',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/40887.jpg',NULL,8,NULL,7777829,NULL,NULL,NULL,NULL),(42177,'穆赫兰道',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/42177_vertical.jpg',NULL,8,NULL,7791220,NULL,NULL,NULL,NULL),(42812,'上帝之城',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/42812_vertical.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(42814,'无间道',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/42814_vertical.jpg',NULL,9,NULL,7773087,NULL,NULL,NULL,NULL),(43499,'回归',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/43499_vertical.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(44139,'杯酒人生',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44139_vertical.jpg',NULL,8,NULL,7735771,NULL,NULL,NULL,NULL),(44164,'神秘肌肤',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44164.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(44167,'拳霸',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44167_vertical.jpg',NULL,7,NULL,7777829,NULL,NULL,NULL,NULL),(44178,'功夫3D',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44178_vertical.jpg',NULL,9,NULL,7810449,NULL,NULL,NULL,NULL),(44187,'大狗民',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44187.jpg',NULL,6,NULL,7777829,NULL,NULL,NULL,NULL),(44193,'勇往直前',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/44193_vertical.jpg',NULL,8,NULL,7814384,NULL,NULL,NULL,NULL),(45377,'图雅的婚事',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/45377_vertical.jpg',NULL,7,NULL,7814384,NULL,NULL,NULL,NULL),(45551,'内陆帝国',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/45551_vertical.jpg',NULL,7,NULL,7791220,NULL,NULL,NULL,NULL),(45642,'格巴维察',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/45642.jpg',NULL,0,NULL,7814384,NULL,NULL,NULL,NULL),(45908,'爱在暹罗',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/45908_vertical.jpg',NULL,8,NULL,7777829,NULL,NULL,NULL,NULL),(45934,'12怒汉：大审判',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/45934.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(46050,'不能说的秘密',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/46050_vertical.jpg',NULL,9,NULL,7776561,NULL,NULL,NULL,NULL),(47023,'和莎莫的500天',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/47023_vertical.jpg',NULL,8,NULL,7792786,NULL,NULL,NULL,NULL),(47499,'盗梦空间',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/47499_vertical.jpg',NULL,9,NULL,7792786,NULL,NULL,NULL,NULL),(47545,'艋舺',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/47545.jpg',NULL,8,NULL,7773087,NULL,NULL,NULL,NULL),(47694,'蜂蜜',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/47694.jpg',NULL,7,NULL,7814384,NULL,NULL,NULL,NULL),(48017,'一次别离',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48017_vertical.jpg',NULL,8,NULL,7814384,NULL,NULL,NULL,NULL),(48047,'抗癌的我',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48047_vertical.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(48536,'蝙蝠侠：黑暗骑士崛起',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48536_vertical.jpg',NULL,8,NULL,7792786,NULL,NULL,NULL,NULL),(48565,'环形使者',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48565_vertical.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(48581,'致命急件',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48581.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(48768,'凯撒必须死',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48768_vertical.jpg',NULL,8,NULL,7814384,NULL,NULL,NULL,NULL),(48986,'西游降魔篇',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/48986_vertical.jpg',NULL,8,NULL,6705428,NULL,NULL,NULL,NULL),(49028,'唐璜',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/49028_vertical.jpg',NULL,6,NULL,7792786,NULL,NULL,NULL,NULL),(50687,'长江七号',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/50687_vertical.jpg',NULL,7,NULL,7810449,NULL,NULL,NULL,NULL),(50737,'刺陵',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/50737.jpg',NULL,7,NULL,7776561,NULL,NULL,NULL,NULL),(51284,'满城尽带黄金甲',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/51284_vertical.jpg',NULL,6,NULL,7776561,NULL,NULL,NULL,NULL),(51320,'寻找周杰伦',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/51320.jpg',NULL,7,NULL,7776561,NULL,NULL,NULL,NULL),(54085,'海瑟',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/54085.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(54944,'逆战',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/54944_vertical.jpg',NULL,7,NULL,7776561,NULL,NULL,NULL,NULL),(55035,'能召回前世的布米叔叔',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/55035_vertical.jpg',NULL,6,NULL,7777829,NULL,NULL,NULL,NULL),(57939,'大灌篮',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/57939.jpg',NULL,8,NULL,7776561,NULL,NULL,NULL,NULL),(60208,'伤心的奶水',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/60208.jpg',NULL,7,NULL,7814384,NULL,NULL,NULL,NULL),(66698,'卡雅利沙的卡门',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/66698.jpg',NULL,0,NULL,7814384,NULL,NULL,NULL,NULL),(69819,'头文字D',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/69819_vertical.jpg',NULL,8,NULL,7776561,NULL,NULL,NULL,NULL),(76003,'少林足球',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/76003_vertical.jpg',NULL,8,NULL,7810449,NULL,NULL,NULL,NULL),(99946,'功夫熊猫3','喜剧 / 动作 / 动画 / 家庭 / 冒险','在新一集故事里，与阿宝失散已久的生父突然现身，重逢的父子二人一起来到了一片不为人知的熊猫乐土。在这里，阿宝遇到了很多可爱有趣的熊猫同类。当拥有神秘力量的大反派“绿眼牛”企图横扫神州大地，残害所有功夫高手时，阿宝必须迎难而上，把那些热衷享乐、笨手笨脚的熊猫村民训练成一班所向披靡的功夫熊猫！','http://weiyinyue.music.sina.com.cn/movie_cover/99946_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/99946_big.jpg',8.2,148189,NULL,1,NULL,NULL,'http://v.iask.com/v_play_ipad.php?vid=139454485'),(105467,'邦尼和克莱德的故事',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/105467.jpg',NULL,0,NULL,7735771,NULL,NULL,NULL,NULL),(107692,'天台爱情',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/107692_vertical.jpg',NULL,9,NULL,7776561,NULL,NULL,NULL,NULL),(107721,'西游记之大闹天宫',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/107721_vertical.jpg',NULL,6,NULL,6705428,NULL,NULL,NULL,NULL),(107740,'大话西游之大圣娶亲',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/107740_vertical.jpg',NULL,9,NULL,6705428,NULL,NULL,NULL,NULL),(172369,'爸妈不在家',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/172369_vertical.jpg',NULL,7,NULL,7777829,NULL,NULL,NULL,NULL),(172480,'残缺影像',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/172480_vertical.jpg',NULL,7,NULL,7777829,NULL,NULL,NULL,NULL),(172547,'白日焰火',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/172547_vertical.jpg',NULL,7,NULL,7814384,NULL,NULL,NULL,NULL),(176783,'利维坦',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/176783_vertical.jpg',NULL,7,NULL,7801304,NULL,NULL,NULL,NULL),(176971,'美人鱼','爱情 / 科幻','周星驰电影《美人鱼》，主演邓超、罗志祥、张雨绮、林允，正式定档2016年2月8日，大年初一，人鱼无敌。											','http://weiyinyue.music.sina.com.cn/movie_cover/176971_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/176971_big.jpg',8.3,1509451,NULL,1,NULL,NULL,NULL),(176996,'夏有乔木 雅望天堂','剧情 / 爱情','　　改编自著名小说《夏有乔木 雅望天堂》电影讲述的是长相俊美的夏木，因为双亲的相继去世，变得自我封闭。直到清纯可爱的舒雅望出现后，他的人生才从黑白变成彩色。但是舒雅望身边却有一个青梅竹马的唐小天，因此他们三个之间展开了一场爱情追逐战。','http://weiyinyue.music.sina.com.cn/movie_cover/176996_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/176996_big.jpg',8.3,152911,NULL,0,1,'2016.04.29',NULL),(176999,'蝙蝠侠大战超人：正义黎明','动作 / 科幻 / 奇幻','　　《超人：钢铁之躯2》已确定由前集导演扎克·施奈德继续执导，《刀锋战士》编剧大卫·S·高耶和导演一起撰写剧本。此前有消息称，影片可能会以弗兰克·米勒的绘画小说《The Dark Knight Returns》为基础，来描绘超人与蝙蝠侠之间的关系。亨利·卡维尔继续演超人，本 ·阿弗莱克出演蝙蝠侠。另外，前集中的演员艾米·亚当斯、劳伦斯·菲什伯恩、黛安·莲恩也将回归。','http://weiyinyue.music.sina.com.cn/movie_cover/176999_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/176999_big.jpg',7.4,135467,NULL,1,NULL,NULL,'http://v.iask.com/v_play_ipad.php?vid=139370293'),(177028,'美国队长3','动作 / 科幻 / 惊悚','奥创纪元之后，全球政府联合颁布法令，管控超能力活动。对这条法令的不同态度，使复仇者阵营一分为二，钢铁侠和美国队长各据一方，其他复仇者则不得不做出自己的选择，最终引发前任盟友间的史诗大战。','http://weiyinyue.music.sina.com.cn/movie_cover/177028_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177028_big.jpg',8.7,30217,NULL,0,1,'2016.05.06',NULL),(177066,'我的新野蛮女友','喜剧 / 爱情','《我的新野蛮女友》讲述了男主人公牵牛与新“野蛮”女友从相恋到结婚的浪漫经历。片中宋茜饰演牵牛的小学初恋，由于韩语不流利常常被周围同学欺负。牵牛最初仍无法忘记分手离别的前任“她”，但有一天命运般地遇见初恋宋茜后，便不顾身边人的反对和她走进婚姻殿堂，没想到摆在他面前的却是超出想象的烦恼。','http://weiyinyue.music.sina.com.cn/movie_cover/177066_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177066_big.jpg',4.6,43835,NULL,0,1,'2016.04.22',NULL),(177162,'西游记之孙悟空三打白骨精','喜剧 / 动作 / 奇幻','师徒四人在西行的路上，白骨精（巩俐饰）为夺唐僧（冯绍峰饰）而巧设圈套，被孙悟空（郭富城饰）识破，屡次受挫。唐僧却误会孙悟空滥杀无辜，将其逐出师门。白骨精趁虚而入，掳走唐僧。悟空闻讯前往营救，彻底击溃白骨精。师徒冰释前嫌，重新上路。电影《西游记之孙悟空三打白骨精》已定档2016年2月8日（猴年大年初一）上映。													','http://weiyinyue.music.sina.com.cn/movie_cover/177162_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177162_big.jpg',6.7,197511,NULL,1,NULL,NULL,'http://v.iask.com/v_play_ipad.php?vid=139700527'),(177191,'惊天魔盗团2',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177191_vertical.jpg',NULL,7,NULL,7776561,NULL,NULL,NULL,NULL),(177277,'我不是王毛','喜剧 / 战争','影片描述了豫东地区一个酿酒人家的悲欢离合。主人公狗剩为了娶自己心爱的女人为妻，自己卖自己，冒名顶替一 个叫“王毛”的“憨瓜”，三次卖到部队，又三次当“逃兵”。故事情节跌宕起伏，时而让人捧腹大笑，时而让人唏嘘不已。影片充分反映了那个年代社会底层的无奈与挣扎，发人深省。','http://weiyinyue.music.sina.com.cn/movie_cover/177277_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177277_big.jpg',7.4,2590,NULL,1,NULL,NULL,NULL),(177325,'西游记之大圣归来',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177325_vertical.jpg',NULL,9,NULL,6705428,NULL,NULL,NULL,NULL),(177338,'云中行走',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177338_vertical.jpg',NULL,7,NULL,7792786,NULL,NULL,NULL,NULL),(177438,'卧虎藏龙：青冥宝剑','剧情 / 动作 / 奇幻 / 冒险','武当弃徒戴阎王为抢夺青冥古剑夜袭铁小贝勒府，俞秀莲重出江湖，携手旧友孟思朝保护宝剑。被蒙蔽的罗小虎助纣为虐，玉娇龙养女雪瓶暗助其与铁平父子相认，揭露真相。一场江湖混战，众人力擒戴阎王，保护宝剑周全。','http://weiyinyue.music.sina.com.cn/movie_cover/177438_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177438_big.jpg',5.6,43046,NULL,1,NULL,NULL,NULL),(177463,'龙三和七个伙伴们',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177463_vertical.jpg',NULL,7,NULL,7773087,NULL,NULL,NULL,NULL),(177490,'谍影特工','惊悚','人称“十一月杀手”的彼得·德卫洛克斯（皮尔斯·布鲁斯南 Pierce Brosnan 饰）是一名前CIA探员，本已金盘洗手的他打算享受来之不易的安静生活，然而他的计划却被打乱了——一个与他有非同寻常关系的重要证人正面临杀身之祸（欧嘉·柯瑞兰寇 Olga Kurylenko 饰），而她掌握着揭发数十年阴谋的关键证据。为了保护证人彼得不得不与CIA全面开战，而局里派来解决他的正是他昔日的徒弟与挚友（卢克·布雷西 Luke Bracey 饰）。面临阴谋和背叛，一场恶战即将展开…… \r\n　　影片改编自比尔·格兰杰1987年的小说《没有间谍》。','http://weiyinyue.music.sina.com.cn/movie_cover/177490_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177490_big.jpg',7.9,3940,NULL,1,NULL,NULL,NULL),(177496,'出租车',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177496_vertical.jpg',NULL,8,NULL,7814384,NULL,NULL,NULL,NULL),(177622,'叶问3','剧情 / 动作 / 传记 / 历史','据香港媒体报道，王家卫02年开始筹备，正式开拍已经3年的电影《一代宗师》，至今却只曝光过几张宣传海报和首段预告片，影片何时拍完和上映，没人知道。不过另一部同样以叶问为题材，由叶伟信执导、甄子丹主演的电影《叶问》，4年前开拍了第一集，今年底已准备开拍第三集，还会用3D拍摄，故事内容围绕叶问与李小龙师徒之情。\r\n王家卫02年开始筹备以李小龙的师傅叶问为题材的电影《一代宗师》，但一直未见开拍，到08年黄百鸣找来叶伟信开拍同类题材的《叶问》，钦点了甄子丹做男主角，一年后王家卫终于宣布正式开拍《一》片，由梁朝伟担任男主角。出名慢工出细货的王家卫，影片拍了3年尚未完成，但甄子丹版的《叶问》已拍了两集，由于两集都叫好叫座，黄百鸣的天马公司已开始筹备年底开拍第三集《3D叶问》，除沿用原班人马外，第三集的李小龙角色正在物色适合人选。\r\n严选少年小龙\r\n《叶问》系列的编剧黄子桓是黄百鸣的儿子，昨日黄子桓表示故事大纲仍在跟导演叶伟信商讨中，他说：”可以肯定的是一定有李小龙这个角色，由他向叶问拜师学艺讲起。至于谁演李小龙就未决定，可能要海选，曾演过李小龙的合适人选是李治廷同陈国坤，不过今次要演的是16、17岁时的李小龙，可能他们年纪都大了些，都要等叶伟信决定啦。“\r\n黄子桓还透露电影主要仍是以叶问为主角，李小龙只是其中一段枝节，不过前两集的民族精神会继续保留，他说：“这个少不得，还会用当年发生的大事来配合影片故事，比如九龙城寨都应该有提到，令港人有共鸣，今次会加入更多创作元素。”甄子丹一早表示有兴趣演出第三集，对于开拍3D版，他的发言人张小姐说：“子丹是有一份期待，他同百鸣有初步接触，但现时还没有看到剧本，所以未知故事怎么样。”\r\n身为监制的黄百鸣早前与子丹开过会，他昨日说：“计划年底开拍，但故事怎样发展，叶问会不会拍到老年，我都未清楚。为了让观众有新鲜感，这集会拍3D版，观众看动作场面会更有立体感，不过因为拍3D，制作成本肯定比头两集高好多。”','http://weiyinyue.music.sina.com.cn/movie_cover/177622_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177622_big.jpg',7.4,74063,NULL,1,NULL,NULL,NULL),(177647,'邮差的白夜',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177647_vertical.jpg',NULL,7,NULL,7801304,NULL,NULL,NULL,NULL),(177662,'魔宫魅影','惊悚','　　一九三零年代的上海，争夺名利、明争暗斗。被视为阮玲玉接班人的女明星孟思凡（林心如 饰）、出身大帅家庭的不知名小导演顾维邦（杨佑宁 饰）等一班寻求着各种自身所需的青年人，也把这里当做梦想的孵化器。 \r\n　　阴差阳错之间，打算开拍新电影的顾维邦结识了孟思凡，两人在丽宫电影院遇见，孟思凡向顾维邦提议一起拍摄一部以戏院为背景的惊悚爱情电影。就这样，两人开始了密集的准备工作。之后，顾维邦创作出了一个名为《夜半歌声》的剧本，孟思凡请到了当红小生主演故事的男主角。前期准备完毕，剧组准备在丽宫电影院开机，而这一家电影院正是传说中的灵异剧院，很多人劝说顾维邦放弃拍摄，然而他不信邪，坚持剧组人员进驻戏院马上开拍。 \r\n　　拍摄开始之后，剧组离奇事件不断，摄影师拍摄到了奇怪的画面，但是胶片洗印出来全部曝光过度，剧组的人员也纷纷表示现场“闹鬼”.然而，更大的意外发生了，男主角离奇死亡，陈尸现场极其可怕，顾维邦的未婚妻费丽斯（黄幻 饰）介入了事件的调查。在影片拍摄的过程中意外不断，甚至闹出了一连多起离奇命案，而一切似乎都指向孟思凡、顾维邦，还有关于他们多年前的一段尘封已久的往事。','http://weiyinyue.music.sina.com.cn/movie_cover/177662_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177662_big.jpg',8.3,6467,NULL,0,1,'2016.04.28',NULL),(177758,'敢问路在何方',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/177758_vertical.jpg',NULL,9,NULL,6705428,NULL,NULL,NULL,NULL),(177804,'女汉子真爱公式','喜剧 / 爱情','　　悉尼大学数学统计专业女学霸何修舞在硕士论文中推导出了“真爱公式”，坚信自己公式绝对无误的何修舞，却接到了自己第一个实验对象失败自杀的噩耗…… \r\n　　为了早点送走可怕的学霸女，叶思逸决定亲自帮助她寻觅她的真命天子，只是这个过程中，到底是哪里变的不对了，竟然有了奇怪的 化学反应……','http://weiyinyue.music.sina.com.cn/movie_cover/177804_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177804_big.jpg',7.5,90874,NULL,1,NULL,NULL,NULL),(177846,'伦敦陷落','动作 / 惊悚 / 犯罪','        继前作[白宫陷落]后，杰拉德·巴特勒近日现身[伦敦陷落]位于英国萨默塞特宫的拍摄  现场，再度饰演特工迈克·班宁，驱车完成一段动作戏。故事围绕英国首相逝世时的一场刺杀活动所展开。除新任导演巴巴克·纳加非外，艾伦·艾克哈特、摩根·弗里曼以及安吉拉·贝塞特也悉数回归。','http://weiyinyue.music.sina.com.cn/movie_cover/177846_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177846_big.jpg',7.2,28478,NULL,1,NULL,NULL,NULL),(177857,'睡在我上铺的兄弟','剧情 / 爱情','沪都大学330宿舍的四位性格迥异的兄弟，在毕业之际各自遭遇了情感、学业、工作上的挫折。聪明贪玩、厚脸皮的大男孩林向宇（陈晓 饰）与女上司姐弟恋，引得周遭非议；聪明贪玩、自在逍遥的富二代李大鹏（杜天皓 饰）无所事事，拉着心爱的羊驼到处配种；硕果仅存的处男管超（刘芮麟 饰）遇到心爱的女生，却遭遇人生重大分歧；出身贫苦、仗义痴情的奋斗青年谢训（李现 饰），能否结束现实的贫穷？一方有难兄弟支援，他们为彼此两肋插刀，然而却出现了不可调和的内部矛盾……在这个过程中，四位热血兄弟成长着，改变着……								','http://weiyinyue.music.sina.com.cn/movie_cover/177857_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177857_big.jpg',7.4,144886,NULL,1,NULL,NULL,NULL),(177858,'梦想合伙人','爱情','这是一个讲述三个阶层不同性格不同的女人为金钱、家庭、地位共同淘金的故事。\r\n留学生卢珍溪在美国卖假包被驱逐回国，商场精英文清遭遇小三插足，灰姑娘顾巧音家庭贫寒只想嫁个有钱人。她们从一开始的死对头变成好闺蜜，并在事业导师孟晓骏（郭富城饰）和卢珍溪的发小俊成(李晨饰)的支持下，成为追求财富、爱情和地位的合伙人。她们短时间内取得巨大成功。不过好景不长，公司在市场激烈竞争下出现严重问题，陷入困局，同时三人的关系也出现破裂。面对困境，卢珍溪承受严峻考验的同时，为梦想和合伙人走出了坚定的一步...\r\n				','http://weiyinyue.music.sina.com.cn/movie_cover/177858_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/177858_big.jpg',8.1,23182,NULL,0,1,'2016.04.29',NULL),(178188,'垫底辣妹','喜剧','比起学习，高中女孩工藤沙耶加（有村架纯 饰）和所有年轻人一样，更乐意校园外的生活。烫发、抽烟、拍照、跳舞，这才是她的世界。直到劣迹斑斑的她被学校强制休学，她才被迫正视起前途来。母亲（吉田羊 饰）将她送进补习班，遇上了改变她一生的老师——坪田（伊藤淳史 饰）。在坪田的鼓励下，两人居然共同决定，一年后沙耶加一定要考上“帅哥如云”的日本名牌大学——庆应大学！\r\n这是个不被所有人看好的志愿，父亲的嘲笑、班主任的“裸奔”赌约、模拟考试接二连三的惨败……一个个打击让她喘不过气来。但她咬牙坚持下来。堵上一切，只为证明曾被嘲笑的自己并非一无是处。剪短头发、熬夜看书、努力再努力，她的奋力拼搏让人动容。正如坪田最后对她的寄语，“你说我改变了你的人生，其实你的努力，也改变了很多人的人生。”\r\n本片根据真人真事改编。','http://weiyinyue.music.sina.com.cn/movie_cover/178188_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178188_big.jpg',8.1,22688,NULL,1,NULL,NULL,NULL),(178291,'独鲁万的困境',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/178291_vertical.jpg',NULL,0,NULL,7777829,NULL,NULL,NULL,NULL),(178329,'废柴特工','喜剧 / 动作','宅男麦克（杰西·艾森伯格 Jesse Eisenberg 饰）是一个小镇超市的收银员，每天在柜台后面画着从未被出版的火箭猴超级英雄的漫画。他想带着他的女友兼保释官菲比（克里斯汀·斯图尔特 Kristen Stewart 饰）去夏威夷度假，但却因自己患有离镇恐惧症不能离开小镇而告吹。\r\n麦克并不知道自己其实是CIA一手打造的休眠特工。CIA开始了一次内部清洗行动，麦克是目标之一，他们派出了新一批致命的改造人特工血洗小镇。遭遇谋杀后，麦克的潜能终于被激活，由性格温顺的宅男变身杀人机器，并向CIA发起绝地反攻。这次，他将为自己心爱的人一战到底……','http://weiyinyue.music.sina.com.cn/movie_cover/178329_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178329_big.jpg',7,2554,NULL,1,NULL,NULL,NULL),(178384,'这里的黎明静悄悄',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/178384_vertical.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(178486,'荒野猎人','剧情 / 冒险','　　该片根据Michael Punke同名小说改编，由Mark L. Smith编剧。故事讲述19世纪一个皮草猎人被一头熊所伤，他乘的船的船长试图把他带回文明世界，但形势所迫，他无法做到，于是只好雇了两个人留下，目的是让他们送这个猎人最后一程。但这两个人利欲熏心，他们把这个受伤的可怜人洗劫一空，之后把他一个人抛弃在荒凉冰冷的野外等死。然而猎人奇迹般的活了下来，之后开始寻找这两个人开始复仇…\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/178486_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178486_big.jpg',8.3,106948,NULL,1,NULL,NULL,NULL),(178620,'飞鹰艾迪','喜剧 / 传记 / 运动','　　电影《飞鹰埃迪》将聚焦埃迪·爱德华兹在1988年冬奥会上的成名故事。这位患有高度近视的英国滑雪运动员虽然没有骄人战绩或是奖牌加身，可他依然是冬奥会历史上难以抹却的一笔，而国际奥运会守则上的“飞鹰规则”也因他而生。\r\n　　早在2011年初，这部关于“飞鹰”埃迪的电影就已经被提上了制作日程。当时《哈利·波特》系列中罗恩的扮演者——英国演员鲁伯特·格林特曾是埃迪本人认可的主演之选，可是由于融资和剧本等多方面原因，影片被暂时搁置。\r\n　　项目重启之后，这部电影的主创阵容也面临着重新洗牌。除了有休·杰克曼的重磅加盟，主角埃迪·爱德华兹也改由《金牌特工》新星塔伦·埃格顿扮演，而“透纳先生”蒂莫西·斯波则有望饰演埃迪的父亲。据悉，英国天团“接招乐队”的核心人物盖瑞·巴洛也会为《飞鹰埃迪》撰写新歌。\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/178620_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178620_big.jpg',8.2,6616,NULL,1,NULL,NULL,NULL),(178626,'愤怒的小鸟','动画','','http://weiyinyue.music.sina.com.cn/movie_cover/178626_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178626_big.jpg',7.5,1528,NULL,0,1,'2016.05.20',NULL),(178647,'奇幻森林','剧情 / 奇幻 / 冒险','　　刚刚与迪士尼影业达成《森林王子》合作协议的乔恩·费儒，似乎还未正式投入影片的筹备工作中，但片方已经将影片定档在了2015年10月9日。这将是一部真人电影，1994年斯蒂芬·索莫斯曾拍过一版《森林王子》，当时北美收入4322万美元。不过《森林王子》电影版中影响力最大的还是迪士尼1967年动画版，影片在上世纪六七十年代曾风靡一时。\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/178647_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178647_big.jpg',8.1,33067,NULL,1,NULL,NULL,NULL),(178651,'分歧者3: 忠诚世界','科幻 / 动作 / 冒险','在推翻了派系制度之后，翠丝与老四带领众人翻越了芝加哥的围墙来到全新世界。然而看似美好的新世界却隐藏着更大的杀机，翠丝与老四之间的信任也遭受到前所未有的考验。二人能否从阴谋中脱身并守住彼此？','http://weiyinyue.music.sina.com.cn/movie_cover/178651_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178651_big.jpg',7.1,782,NULL,0,1,'2016.05.20',NULL),(178840,'猎神:冬日之战','剧情 / 动作 / 奇幻 / 冒险','　　故事将聚焦于克里斯·海姆斯沃斯饰演的猎人和查理兹·塞隆饰演的王后在遇到白雪公主之前的故事，而在第一部中饰演白雪公主的克里斯汀·斯图尔特将不会出演这部续作。\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/178840_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178840_big.jpg',5.9,1508,NULL,0,1,'2016.04.22',NULL),(178846,'疯狂动物城','喜剧 / 动作 / 动画','疯狂动物城是一座独一无二的现代动物都市。每种动物在这里都有自己的居所，比如富丽堂皇的撒哈拉广场，或者常年严寒的冰川镇。它就像一座大熔炉，动物们在这里和平共处——无论是大象还是小老鼠，只要你努力，都能在此闯出一番名堂。不过乐观的警官兔朱迪（金妮弗·古德温 Ginnifer Goodwin 配音）却发现，作为史上第一任兔子警官，要和一群强硬的大块头动物警察合作可不是件容易事。为了证明自己，她决心侦破一桩神秘案件；追寻真相的路上她被迫与口若悬河、谎技高超的狐尼克（杰森·贝特曼 Jason Bateman 配音）联手，却发现这桩案件背后隐藏着一个意欲颠覆动物城的巨大阴谋……','http://weiyinyue.music.sina.com.cn/movie_cover/178846_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178846_big.jpg',9.3,437585,NULL,1,NULL,NULL,NULL),(178868,'北京遇上西雅图之不二情书','喜剧 / 爱情','　　对于《北京遇上西雅图》续集，是否会延续第一部的剧情、有何内容形式上的突破等问题都是大众关注的重点。对此，薛晓路导演表示，续集剧本仍然由自己亲自编写，前后构思创作了两年之久。对于剧情内容，薛晓路称会在上部基础上有一定的调整，然而具体的剧情内容与角色定位则暂时处于保密阶段。而关于为何会选择在澳门取景，薛晓路也仅透露称，澳门将涉及到电影剧情中很重要的内容。\r\n　　据悉，《北京遇上西雅图2》有望于2016年登陆全国院线。\r\n','http://weiyinyue.music.sina.com.cn/movie_cover/178868_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178868_big.jpg',7.5,16487,NULL,0,1,'2016.04.29',NULL),(178933,'澳门风云3','喜剧 / 动作','2015年6月开拍，全新的阵容，加入了更多内地演员，2016年春节档上映。','http://weiyinyue.music.sina.com.cn/movie_cover/178933_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/178933_big.jpg',5.8,177977,NULL,1,NULL,NULL,NULL),(179088,'我的特工爷爷','喜剧 / 动作 / 悬疑 / 冒险','电影讲述了退休军官老丁(洪金宝饰)因“健忘症”弄丢孙女，内疚回到老家。邻居小女孩春花(陈沛妍 饰)成为他的慰藉。可女孩的父亲(刘德华饰)无意卷入了当地黑帮团伙的争斗中。老丁为营救春花挺身而出，也寻回那份作为军人的骄傲和弥补作为爷爷的过错。				','http://weiyinyue.music.sina.com.cn/movie_cover/179088_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179088_big.jpg',7.2,53667,NULL,1,NULL,NULL,NULL),(179107,'测试',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/179107_vertical.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(179199,'谁的青春不迷茫','爱情','每个人的青春都伴随着迷茫，想好好工作，却不尽如人意；想好好恋爱，却不懂处理感情...其实迷茫不可怕，它本来就是青春应该有的样子。2016年青春片，会不一样么？','http://weiyinyue.music.sina.com.cn/movie_cover/179199_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179199_big.jpg',5.6,30312,NULL,0,1,'2016.04.22',NULL),(179204,'冰河追凶','犯罪 / 悬疑','犯罪警匪电影《冰河追凶》由徐伟执导，梁家辉、佟大为、周冬雨、邓家佳、魏晨等主演。讲述 “追凶者”深入零下40度极寒之地，追查真凶寻找真相的故事。暗藏杀机的冰河，牵一发而动全身，每个人都无法避免被卷入一场场错综复杂的事件，成为“追凶”成员。而冰河下最终真相却颠覆所有人的想象。','http://weiyinyue.music.sina.com.cn/movie_cover/179204_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179204_big.jpg',8.1,14925,NULL,1,NULL,NULL,NULL),(179220,'刑警兄弟','喜剧 / 动作','陈见飞和尊尼都是来自单亲家庭的年青警员，处事也都不成熟，加上性格迥然不同，相处中产生许多矛盾。偏偏陈飞的父亲和尊尼的母亲相恋，使两人关系更形紧张，闹出许多笑话。后来，两人通过侦查罪案增进了解、冰释前嫌。																				','http://weiyinyue.music.sina.com.cn/movie_cover/179220_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179220_big.jpg',7.7,1095,NULL,0,1,'2016.04.22',NULL),(179283,'危楼愚夫',NULL,NULL,'http://mu1.sinaimg.cn/frame.180x240/weiyinyue.music.sina.com.cn/movie_poster/179283_vertical.jpg',NULL,8,NULL,7801304,NULL,NULL,NULL,NULL),(179614,'哪一天我们会飞','剧情 / 爱情','余凤芝（杨千嬅 饰）和彭盛华（林海峰 饰），一对相识于中学的中年夫妻，过着寻常而压抑的生活。二人竭力维持的婚姻外壳，却被一次旧生同学联欢会彻底戳破。当时在学校里，曾存在一个谜样的男同学，他与她与他构成青葱的三角；在尘封的记忆深处，埋下一个令人动容的秘密……','http://weiyinyue.music.sina.com.cn/movie_cover/179614_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179614_big.jpg',7.3,4964,NULL,1,NULL,NULL,NULL),(179860,'火影忍者剧场版：博人传','动画','这部最新剧场版将围绕漩涡鸣人和日向雏田的儿子漩涡博人展开剧情，而女主角则是佐助和春日樱的女儿宇智波莎拉娜。官方在公布本片海报的同时还发布了男女主角声优确定的消息，漩涡博人将由三瓶由布子担任，宇智波莎拉娜则由菊池心演绎，而两位新生代主角的父亲漩涡鸣人和宇智波佐助也将出现在剧场版中，配音也依旧由竹内顺子和杉山纪彰担任。\r\n这部最新剧场版由这一系列的缔造者岸本齐史亲自操刀，担任脚本、角色人设和制作总指挥，本片将于今年8月7日（周五）在日本全国公映。','http://weiyinyue.music.sina.com.cn/movie_cover/179860_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/179860_big.jpg',8.6,59909,NULL,1,NULL,NULL,NULL),(180755,'大唐玄奘','历史','片方首次推出三款人物海报，描绘了黄晓明扮演的玄奘法师在西行取经路上不同的际遇。\r\n在片中徐峥扮演的凉州总督李大亮是玄奘离开长安后遇见的第一个“障碍”：于法，他必须抓私自离开长安去印度的玄奘回长安。于情，内心信奉佛教的他希望能帮助玄奘完成他的这段伟大的旅行。在片中，徐峥贴起了长须，扮起了表面不通情理，却内心善良温暖的凉州总督。而海报中，玄奘向李大亮颔首施礼，却目光坚定，表达了他一心求法的决心。\r\n蒲巴甲在片中扮演西域僧人石磐陀，在片中他主动追随玄奘去印度取经，一路上发生了许多纠葛，最终虽未能一同继续西行，但已被玄奘精神所感化。石磐陀也是史上公认最贴近《西游记》中孙悟空原型的人物。海报中“缘聚缘散无所住，慈心无量度众生”是这段短暂的师徒关系最贴切的评语。\r\n罗晋在《大唐玄奘》中扮演瓜州太守李昌。片中，李昌对于独自一人远赴印度求取佛学经典的玄奘，从一开始的怀疑，到相信再到敬佩，最后甚至冒死相助。海报中，黄晓明饰演的玄奘一身行装，前途漫漫，而罗晋佩刀跨马，完美的诠释了“身在红尘心向佛，将军原是皈依人。”','http://weiyinyue.music.sina.com.cn/movie_cover/180755_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/180755_big.jpg',7.8,2092,NULL,0,1,'2016.04.29',NULL),(180767,'地心营救','剧情 / 传记 / 历史','影片改编自智利著名矿难事件，讲述33个男人在一场突如其来的灾难中被困地心深处，出路被塌陷的岩石全部封死，是坐以待毙，还是绝境求生？整整69天，兄弟同心，一路冲关，最终全部生还，创造了人类营救史上的最大奇迹。','http://weiyinyue.music.sina.com.cn/movie_cover/180767_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/180767_big.jpg',7.2,6305,NULL,1,NULL,NULL,NULL),(180871,'火锅英雄','剧情','在布满防空洞的重庆，三个从初中就“厮混”在一起的好兄弟合伙开着一家火锅店，名为“老同学洞子火锅”。由于经营不善，几人落得只能转让店铺还债。为了店铺能“卖个好价钱”，三人打起了“扩充门面”的主意，自行往洞里开挖。没想到，在扩充工程中却凿开了银行的金库！就这样，濒临倒闭的火锅店和银行金库仅有“一洞之隔”；看着眼前随手可得的成堆现金，在“拿钱还是报案”的思想拉锯战中，三兄弟偶遇上另一个女同学——初中时给老大写过情书、现在在银行上班的于小惠。四个老同学因为这个“洞”而打开重聚之门。由此，这个略显尴尬的洞，引发了一个令人意想不到的故事……','http://weiyinyue.music.sina.com.cn/movie_cover/180871_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/180871_big.jpg',7.8,113949,NULL,1,NULL,NULL,NULL),(180882,'高跟鞋先生','喜剧 / 爱情','宅男游戏设计师杭远（杜江 饰），从小就暗恋同校才女李若欣（薛凯琪 饰），可每次要表白的时候她都已进入了新恋情。李若欣最后一段感情被未婚夫背叛，不再相信男人，转而与闺蜜Sammi（李媛 饰）越走越近。杭远顿时万念俱灰，于是富二代“好基友”林森森（陈学冬 饰）提议他做最后一搏，想方设法接近若欣，大胆追求所爱。阴差阳错下，杭远竟成为了网络红人，一时间风光无限。 但是好景不长，若欣很快识破了他们的“计谋”。他该如何挽回和女神的破裂的关系，并成功和她在一起呢？©豆瓣','http://weiyinyue.music.sina.com.cn/movie_cover/180882_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/180882_big.jpg',7.2,103470,NULL,1,NULL,NULL,NULL),(181013,'神战：权力之眼','奇幻 / 冒险','奇幻片《埃及众神》定档2016年2月12日北美公映，影片由杰拉德·巴特勒、杰弗里·拉什、尼可拉·科斯特-瓦尔道等明星主演。故事讲述了天空之神和爱神要报杀父之仇，但敌人风暴之神的力量过于强大，他们不得不联手一名凡间的盗贼来实施复仇计划。','http://weiyinyue.music.sina.com.cn/movie_cover/181013_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/181013_big.jpg',7.5,82460,NULL,1,NULL,NULL,NULL),(181055,'半熟少女','剧情','半熟，是对青春期最合理的定义，它是梦开始的地方，没有深思熟虑，只有最单纯的坚定，然而，在这个充满意外的年纪，未来似乎变得很具体，又有着无限的可能性。这个故事里有女汉子与呆萌校草的啼笑初恋，有四朵姐妹花的袍泽之谊，也有学霸乖乖女与双胞胎才子的意乱情迷，他们躁动的热情在循规蹈矩的理智中突围，写成一首欢笑与痛，梦想与爱的青春禁曲。\r\n也许，半熟与成熟之间，只差那次铤而走险的“叛逆”。																','http://weiyinyue.music.sina.com.cn/movie_cover/181055_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/181055_big.jpg',6.6,14958,NULL,1,NULL,NULL,NULL),(181086,'纽约纽约','剧情 / 爱情','上世纪90年代的上海，许多人都为出国狂热。路途（阮经天 饰）是最年轻的五星级酒店领班，处事精明、为人仗义，无论是手下阿坤（杨旭文 饰）等一众小弟，还是打算开娱乐城的金小姐（叶童 饰），都对路途很是信任。从美国归来的精英商人米先生（苗侨伟 饰）更是看中他，邀请他担任纽约新酒店的负责人。为了去纽约，许多人怀着不同目的来接近路途，其中也包括令路途着迷的女孩阿鹃（杜鹃 饰）。纽约梦能否如愿以偿？路途和阿鹃的爱情又将何去何从？一段在欲望都市中关于选择的爱欲纠缠，就此展开……©豆瓣				','http://weiyinyue.music.sina.com.cn/movie_cover/181086_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/181086_big.jpg',6.7,3697,NULL,1,NULL,NULL,NULL),(181163,'洛杉矶捣蛋计划','喜剧 / 家庭','落魄经纪人黄伯伦（夏雨 饰）来到美国洛杉矶寻找一位失踪艺人（杨紫 饰）不幸滞留在美国，却意外地成为了五名不同肤色的美国萌娃的爸爸。黄伯伦一边要继续寻找失踪艺人，一边还要照顾5个青春期的孩子。在经历一系列波折之后，这个不负责任的“临时奶爸”却逐渐成长为一个真正的好爸爸，得到了他人生的二次成长。','http://weiyinyue.music.sina.com.cn/movie_cover/181163_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/181163_big.jpg',7.4,3487,NULL,1,NULL,NULL,NULL),(181173,'失眠男女','喜剧 / 爱情','影片讲述了在当今社会中，有许多因高压生活而失眠的男男女女，颇具幽默感的失眠治疗师安定就是一个专门治疗失眠症的行家，在治疗失眠的过程中他偶遇到了一见钟情的保险经理人苗窕，但因为苗窕有一段痛苦的恋爱经历一直不敢轻易接触新的感情，因此安定的多次的追求都被苗窕拒绝了，在经历了一连串啼笑皆非的事件后，安定通过自己的努力和真诚终于打动了苗窕，最终二人终成眷属。','http://weiyinyue.music.sina.com.cn/movie_cover/181173_big.jpg','http://mu1.sinaimg.cn/frame.750x1080/weiyinyue.music.sina.com.cn/movie_cover/181173_big.jpg',6.1,75,NULL,0,1,'2016.04.22',NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagelist`
--

DROP TABLE IF EXISTS `pagelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(20000) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `movie_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7814385 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagelist`
--

LOCK TABLES `pagelist` WRITE;
/*!40000 ALTER TABLE `pagelist` DISABLE KEYS */;
INSERT INTO `pagelist` VALUES (6705428,'全西游片单','西游IP这么强，哪家泼猴你最爱？','http://mu1.sinaimg.cn/crop.126x0x298x298/weiyinyue.music.sina.com.cn/movie_game/1436765721953.jpg',8),(7735771,'路上的那些事！——公路电影','我们带着不同的理由上路，或逃亡，或寻找，或逃避，或追逐，或自愿，或被迫。路上的人和事，在一瞬间就改变了人生的走向。','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/22416.jpg',36),(7773087,'大佬，好久不见———记录黑帮的那些事','江湖是什么？义气？规矩？还是人情世故？大佬，你知道吗？','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/11146.jpg',50),(7776561,'周杰伦参演的电影影单','2016.01.18日是周杰伦的37岁生日，在这个特别的日子里，和大家一起回顾一下杰伦参演过的几部电影，满满的回忆，都是青春的味道。','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/69819.jpg',10),(7777829,'东南亚，别样的文艺情绪','近年泰国电影的发展势头在亚洲异军突起，青春片、恐怖片、动作片等类型电影纷纷呈现出优秀作品。新加坡由于语言与文化脉络的原因，总是给我们格外亲近的感觉，而越南、柬埔寨电影代表作则多要追溯到多年前的三大电影节。','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/37049.jpg',11),(7791220,'大卫·林奇导演的那些经典电影','1946年出生于美国蒙大拿州的大卫·林奇在美国小镇上长大。高中毕业后去波士顿的艺术博物馆学院学习。毕业后他曾计划去欧洲继续进行为期三年的艺术学习，但是由于不适应，15天后即返回美国。完成了几个短片之后，1977年他完成了自己的处女作《橡皮头》，该片散漫的拍摄了五年，并且一度被认为是怪异和难以理解的，在制片人Ben Barenholtz的帮助下，这部电影最终以cult片面世并吸引了许多人的目光。','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/45551.jpg',9),(7792786,'千面囧瑟夫：约瑟夫·高登-莱维特','童星出身的约瑟夫•高登-莱维特，模样简直就是个大写又可爱的囧字，人送爱称囧瑟夫，无论是独立小品还是好莱坞大制作，他都有精彩的表演！','http://mu1.sinaimg.cn/crop.84x0x416x416/weiyinyue.music.sina.com.cn/movie_game/1453380868194.jpg',15),(7801304,'战斗民族的镜头—从苏联到俄罗斯','虽然苏联解体，但是存在的时间里，还是用镜头讲述许多不错的故事。到现在，虽然好莱虎非常凶猛，但是俄罗斯特有的风格电影，还是让人无法忘记。','http://mu1.sinaimg.cn/square.180/weiyinyue.music.sina.com.cn/movie_poster/179283_vertical.jpg',50),(7810449,'喜剧之王周星驰','在华语电影圈，星爷是毫无疑问的喜剧之王！从《逃学威龙》到《功夫》，从《九品芝麻官》到《大话西游》，从小到大，我们看着星爷的电影笑过也哭过，感觉欠星爷数不清的电影票！','http://mu1.sinaimg.cn/crop.2x48x474x474/weiyinyue.music.sina.com.cn/movie_game/1453709996230.jpg',24),(7814384,'历届柏林电影节金熊奖电影（1990-）','柏林国际电影节（Berlin International Film Festival），原名西柏林国际电影节，与戛纳国际电影节、威尼斯国际电影节并称为欧洲三大国际电影节，最高奖项是“金熊奖”。','http://mu1.sinaimg.cn/crop.21x0x316x316/weiyinyue.music.sina.com.cn/movie_game/1453799214422.jpg',25);
/*!40000 ALTER TABLE `pagelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagelistmovie`
--

DROP TABLE IF EXISTS `pagelistmovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagelistmovie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pagelistid` int(11) DEFAULT NULL,
  `movieid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagelistmovie`
--

LOCK TABLES `pagelistmovie` WRITE;
/*!40000 ALTER TABLE `pagelistmovie` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagelistmovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `like_movie_id` int(11) DEFAULT NULL,
  `done_movie_id` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `weibo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'_毛通_',NULL,NULL,'http://tva3.sinaimg.cn/crop.0.0.720.720.1024/ad5fa895jw8egjqu511jtj20k00k0t9n.jpg',2908727445);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-17 22:55:48
