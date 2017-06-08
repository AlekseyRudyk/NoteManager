# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.34)
# Database: db_courseWork
# Generation Time: 2017-04-27 20:16:47 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `category_name`)
VALUES
	(1,'everyday'),
	(2,'a'),
	(3,'aa'),
	(4,'aa1'),
	(5,'aaa'),
	(6,'aaaa'),
	(7,'???'),
	(8,'??');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table note
# ------------------------------------------------------------

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note_name` varchar(60) NOT NULL,
  `note_subnote` mediumtext,
  `note_startDate` date DEFAULT NULL,
  `note_comment` text,
  `note_category_id` int(11) DEFAULT NULL,
  `note_finalDate` date DEFAULT NULL,
  `note_progress` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;

INSERT INTO `note` (`id`, `note_name`, `note_subnote`, `note_startDate`, `note_comment`, `note_category_id`, `note_finalDate`, `note_progress`)
VALUES
	(3,'a','bla-bla',NULL,'lol',NULL,NULL,0),
	(4,'a','a',NULL,'comment',2,NULL,0),
	(5,'aa','aa',NULL,'aa',3,NULL,0),
	(6,'aa1','aa1',NULL,'aa1',4,NULL,0),
	(7,'aa','aa1',NULL,'aaa',5,NULL,0),
	(8,'aa','aaa','2017-04-27','aaa',6,NULL,0),


/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table subNote
# ------------------------------------------------------------

DROP TABLE IF EXISTS `subNote`;

CREATE TABLE `subNote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subNote_name` varchar(60) DEFAULT NULL,
  `subNote_text` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
