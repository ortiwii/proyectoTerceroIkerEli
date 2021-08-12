-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sanluis
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `idComponenteAlmacen` int NOT NULL,
  `stock` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `clase` varchar(60) DEFAULT NULL,
  `informacion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idComponenteAlmacen`),
  KEY `fk_stock_almacen_idx` (`stock`),
  CONSTRAINT `fk_stock_almacen` FOREIGN KEY (`stock`) REFERENCES `stock` (`idStock`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (1,1,10,'componente 1','h','componente 1'),(2,1,5,'componente 2 ','s','componente 2'),(3,2,1,'Componente 3','s','Componente 3'),(4,3,4,'componente 4','s','componente 4'),(5,1,10,'componente 5','s','componente 5');
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aula`
--

DROP TABLE IF EXISTS `aula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aula` (
  `codigo` varchar(60) NOT NULL,
  `tamaño` int DEFAULT NULL,
  `disponibles` int DEFAULT NULL,
  `centro` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `fk_centro_idx` (`centro`),
  CONSTRAINT `fk_centro` FOREIGN KEY (`centro`) REFERENCES `centro` (`nombre`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aula`
--

LOCK TABLES `aula` WRITE;
/*!40000 ALTER TABLE `aula` DISABLE KEYS */;
INSERT INTO `aula` VALUES ('1',10,6,'San Luis'),('2',10,10,'San Luis'),('3',10,10,'San Luis'),('4',8,8,'San Luis');
/*!40000 ALTER TABLE `aula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centro`
--

DROP TABLE IF EXISTS `centro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centro` (
  `nombre` varchar(60) NOT NULL,
  `informacion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centro`
--

LOCK TABLES `centro` WRITE;
/*!40000 ALTER TABLE `centro` DISABLE KEYS */;
INSERT INTO `centro` VALUES ('San Luis','San Luis'),('San Mames','San Mames');
/*!40000 ALTER TABLE `centro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `componente`
--

DROP TABLE IF EXISTS `componente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `componente` (
  `idComponente` int NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `clase` varchar(1) DEFAULT NULL,
  `equipo` int DEFAULT NULL,
  `centro` varchar(60) DEFAULT NULL,
  `informacion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idComponente`),
  KEY `fk_centro_componente_idx` (`centro`),
  KEY `fk_equipo_componente_idx` (`equipo`),
  KEY `fk_stock_componente_idx` (`stock`),
  CONSTRAINT `fk_centro_componente` FOREIGN KEY (`centro`) REFERENCES `centro` (`nombre`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_componente` FOREIGN KEY (`equipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_stock_componente` FOREIGN KEY (`stock`) REFERENCES `stock` (`idStock`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `componente`
--

LOCK TABLES `componente` WRITE;
/*!40000 ALTER TABLE `componente` DISABLE KEYS */;
INSERT INTO `componente` VALUES (1,'Nvidia GTX 3090',1,'h',1,'San Luis',NULL),(2,'Monitor',1,'h',2,'San Luis',NULL),(3,'Licencia windows',1,'s',2,'San Luis',NULL),(4,'AMD 3600x',1,'h',NULL,'San Luis',NULL),(5,'Licencia office',1,'s',NULL,'San Luis',NULL),(6,'Tarjeta de red',1,'h',NULL,'San Luis',NULL);
/*!40000 ALTER TABLE `componente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `idEquipo` int NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `almacenamiento` int DEFAULT NULL,
  `ram` int DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `aula` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idEquipo`),
  KEY `fk_aula_idx` (`aula`),
  CONSTRAINT `fk_aula` FOREIGN KEY (`aula`) REFERENCES `aula` (`codigo`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Informatica 1',200,8,'192.168.1.1','1'),(2,'Informatica 2',500,16,'192.168.1.2','1'),(3,'Administracion 1',150,4,'192.168.2.1','2'),(4,'informatica 3',300,16,'192.168.1.3','1'),(5,'Informatica 4',200,8,'192.168.1.1','2'),(6,'prueba',1,1,'1','1'),(7,'prueba 2',1,1,'1','1');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peticiones`
--

DROP TABLE IF EXISTS `peticiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `peticiones` (
  `idPeticion` int NOT NULL AUTO_INCREMENT,
  `componenteAlmacen` int DEFAULT NULL,
  `tecnico` varchar(60) DEFAULT NULL,
  `administrador` varchar(60) DEFAULT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `centro` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idPeticion`),
  UNIQUE KEY `idPeticion_UNIQUE` (`idPeticion`),
  KEY `fk_almacen_peticion_idx` (`componenteAlmacen`),
  KEY `fk_tecnico_peticion_idx` (`tecnico`),
  KEY `fk_administrador_peticion_idx` (`administrador`),
  KEY `fk_centro_peticion_idx` (`centro`),
  CONSTRAINT `fk_administrador_peticion` FOREIGN KEY (`administrador`) REFERENCES `usuario` (`user`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_almacen_peticion` FOREIGN KEY (`componenteAlmacen`) REFERENCES `almacen` (`idComponenteAlmacen`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_centro_peticion` FOREIGN KEY (`centro`) REFERENCES `centro` (`nombre`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_tecnico_peticion` FOREIGN KEY (`tecnico`) REFERENCES `usuario` (`user`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peticiones`
--

LOCK TABLES `peticiones` WRITE;
/*!40000 ALTER TABLE `peticiones` DISABLE KEYS */;
INSERT INTO `peticiones` VALUES (1,1,'iker','iker','prueba compra 1',2,'v','San Luis'),(2,1,'iker','iker','Queiero comprar este componente por x razon',0,'r','San Luis'),(3,1,'iker','iker','prueba peticion 1',0,'r','San Luis'),(4,4,'iker','iker','prueba 2',4,'r','San Luis'),(5,4,'iker','iker','prueba 3',2,'n','San Luis'),(6,4,'iker','iker','prueba compra 1',2,'n','San Luis');
/*!40000 ALTER TABLE `peticiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` varchar(60) NOT NULL,
  `user` varchar(60) DEFAULT NULL,
  `passw` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `centro` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `fk_centro_idx` (`centro`),
  CONSTRAINT `fk_centro_proveedor` FOREIGN KEY (`centro`) REFERENCES `centro` (`nombre`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES ('1','amazon','1234','amazonbien@gmail.com','San Luis'),('2','Pccomponentes','12345','pccomponentes@gmail.com','San Luis');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudincidencia`
--

DROP TABLE IF EXISTS `solicitudincidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudincidencia` (
  `idSolInc` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(1) NOT NULL,
  `fechaInicio` varchar(60) DEFAULT NULL,
  `fechaFin` varchar(60) DEFAULT NULL,
  `estado` varchar(1) DEFAULT NULL,
  `user` varchar(60) DEFAULT NULL,
  `tecnico` varchar(60) DEFAULT NULL,
  `componente` int DEFAULT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idSolInc`),
  UNIQUE KEY `idSolInc_UNIQUE` (`idSolInc`),
  KEY `fk_solinc_user_idx` (`user`),
  KEY `fk_solinc_tecnico_idx` (`tecnico`),
  KEY `fk_solinc_componente_idx` (`componente`),
  CONSTRAINT `fk_solinc_componente` FOREIGN KEY (`componente`) REFERENCES `componente` (`idComponente`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_solinc_tecnico` FOREIGN KEY (`tecnico`) REFERENCES `usuario` (`user`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_solinc_user` FOREIGN KEY (`user`) REFERENCES `usuario` (`user`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudincidencia`
--

LOCK TABLES `solicitudincidencia` WRITE;
/*!40000 ALTER TABLE `solicitudincidencia` DISABLE KEYS */;
INSERT INTO `solicitudincidencia` VALUES (5,'s','Sat Jul 24 18:10:34 CEST 2021','Fri Jul 30 10:33:16 CEST 2021','a','iker','alfredo',NULL,'Soy una mierda de persona porque me gusta la kaka'),(6,'s','Sat Jul 24 18:07:57 CEST 2021',NULL,'n','iker','alfredo',1,'asdf'),(8,'s','Sat Jul 24 18:07:57 CEST 2021',NULL,'n','alfredo','toni',3,'toni'),(9,'i','Sun Jul 25 14:14:19 CEST 2021',NULL,'n','iker','gorka',NULL,'Incidencia de prueba 1');
/*!40000 ALTER TABLE `solicitudincidencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `idStock` int NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(60) NOT NULL,
  PRIMARY KEY (`idStock`),
  UNIQUE KEY `idStock_UNIQUE` (`idStock`),
  KEY `fk_proveedor_stock_idx` (`proveedor`),
  CONSTRAINT `fk_proveedor_stock` FOREIGN KEY (`proveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'1'),(2,'1'),(4,'1'),(6,'1'),(3,'2'),(5,'2');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `user` varchar(60) NOT NULL,
  `passw` varchar(60) DEFAULT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `permisos` tinyint DEFAULT NULL,
  `equipo` int DEFAULT NULL,
  `centro` varchar(60) DEFAULT NULL,
  `solicitudes` int DEFAULT '0',
  PRIMARY KEY (`user`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `fk_equipo_usuario_idx` (`equipo`),
  KEY `fk_centro_usuario_idx` (`centro`),
  CONSTRAINT `fk_centro_usuario` FOREIGN KEY (`centro`) REFERENCES `centro` (`nombre`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_equipo_usuario` FOREIGN KEY (`equipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('alfredo','12345','Alfredo','De la Calva','alfred@gmail.com','u',NULL,1,'San Mames',-3),('gorka','123','gorka','ortiz de la calva','gorka@gmail.com','t',NULL,1,'San Luis',3),('iker','1234','Iker','Ortiz','ikerortiweed@gmail.com','a',1,1,'San Luis',3),('prueba','1234','prueba','prueba','prueba','a',1,1,'San Luis',2),('toni','12345','toni','ortiz','toni@gmail.com','t',NULL,4,'San Luis',3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `idVenta` int NOT NULL AUTO_INCREMENT,
  `proveedor` varchar(60) NOT NULL,
  `fechaVenta` varchar(60) NOT NULL,
  `fechaCierre` varchar(60) DEFAULT NULL,
  `estado` varchar(60) NOT NULL,
  `peticion` int NOT NULL,
  `informacion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `fk_proveedor_venta_idx` (`proveedor`),
  KEY `fk_peticion_venta_idx` (`peticion`),
  CONSTRAINT `fk_peticion_venta` FOREIGN KEY (`peticion`) REFERENCES `peticiones` (`idPeticion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_proveedor_venta` FOREIGN KEY (`proveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (3,'1','Mon Aug 02 13:07:05 CEST 2021','Wed Aug 04 16:19:42 CEST 2021','Cerrada',1,'info');
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-12 18:13:42
