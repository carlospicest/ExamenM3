-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.38-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para correo
CREATE DATABASE IF NOT EXISTS `correo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `correo`;

-- Volcando estructura para tabla correo.envio
CREATE TABLE IF NOT EXISTS `envio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE_DESTINATARIO` varchar(255) NOT NULL,
  `EMAIL_DESTINATARIO` varchar(255) NOT NULL,
  `PROVINCIA_DESTINATARIO` varchar(255) NOT NULL,
  `MENSAJE_DESTINATARIO` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla correo.envio: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `envio` DISABLE KEYS */;
INSERT INTO `envio` (`ID`, `NOMBRE_DESTINATARIO`, `EMAIL_DESTINATARIO`, `PROVINCIA_DESTINATARIO`, `MENSAJE_DESTINATARIO`) VALUES
	(3, 'Carlos Picado', 'cpe17494@gmail.com', 'Zamora', 'Este es un mensaje desde el formulario web y espero que lo recibas en tu correo y que se registre en la base de datos. Un saludito!');
/*!40000 ALTER TABLE `envio` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
