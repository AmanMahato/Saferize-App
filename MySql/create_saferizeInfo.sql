
CREATE DATABASE  IF NOT EXISTS `saferize`;
USE `saferize`;

--
-- Table structure for table `saferizeInfo`
--

DROP TABLE IF EXISTS `saferizeInfo`;

CREATE TABLE `saferizeInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path_url` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;