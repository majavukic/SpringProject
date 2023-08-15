-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: projekatit355
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `KORISNIK_ID` int NOT NULL AUTO_INCREMENT,
  `KORISNIK_PERSONALNI_PODACI_ID` int DEFAULT NULL,
  `LOZINKA` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KORISNICKO_IME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`KORISNIK_ID`),
  KEY `FK_RELATIONSHIP_1` (`KORISNIK_PERSONALNI_PODACI_ID`),
  CONSTRAINT `FK_RELATIONSHIP_1` FOREIGN KEY (`KORISNIK_PERSONALNI_PODACI_ID`) REFERENCES `korisnik_personalni_podaci` (`KORISNIK_PERSONALNI_PODACI_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,1,'K1234','Katarina'),(2,2,'A1234','Aca'),(3,3,'M1234','Milan');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik_personalni_podaci`
--

DROP TABLE IF EXISTS `korisnik_personalni_podaci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik_personalni_podaci` (
  `KORISNIK_PERSONALNI_PODACI_ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL_ADRESA` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PREZIME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`KORISNIK_PERSONALNI_PODACI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik_personalni_podaci`
--

LOCK TABLES `korisnik_personalni_podaci` WRITE;
/*!40000 ALTER TABLE `korisnik_personalni_podaci` DISABLE KEYS */;
INSERT INTO `korisnik_personalni_podaci` VALUES (1,'katarina@gmail.com','Katarina','Ivezic'),(2,'aleksandar@gmail.com','Aleksandar','Aleksic'),(3,'milan@gmail.com','Milan','Milic');
/*!40000 ALTER TABLE `korisnik_personalni_podaci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kupovina` (
  `KORISNIK_ID` int NOT NULL,
  `NAMESTAJ_ID` int NOT NULL,
  PRIMARY KEY (`KORISNIK_ID`,`NAMESTAJ_ID`),
  KEY `FK_RELATIONSHIP_5` (`NAMESTAJ_ID`),
  CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`KORISNIK_ID`) REFERENCES `korisnik` (`KORISNIK_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`NAMESTAJ_ID`) REFERENCES `namestaj` (`NAMESTAJ_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupovina`
--

LOCK TABLES `kupovina` WRITE;
/*!40000 ALTER TABLE `kupovina` DISABLE KEYS */;
INSERT INTO `kupovina` VALUES (2,1);
/*!40000 ALTER TABLE `kupovina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `namestaj`
--

DROP TABLE IF EXISTS `namestaj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `namestaj` (
  `NAMESTAJ_ID` int NOT NULL AUTO_INCREMENT,
  `VRSTA_NAMESTAJA_ID` int NOT NULL,
  `SLIKA` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `BOJA` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CENA` int DEFAULT NULL,
  `NAMESTAJ_NAZIV` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NAMESTAJ_ID`),
  KEY `FK_RELATIONSHIP_3` (`VRSTA_NAMESTAJA_ID`),
  CONSTRAINT `FK_RELATIONSHIP_3` FOREIGN KEY (`VRSTA_NAMESTAJA_ID`) REFERENCES `vrsta_namestaja` (`VRSTA_NAMESTAJA_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `namestaj`
--

LOCK TABLES `namestaj` WRITE;
/*!40000 ALTER TABLE `namestaj` DISABLE KEYS */;
INSERT INTO `namestaj` VALUES (1,1,'https://sandej.rs/wp-content/uploads/2018/02/Ugaona-Garnitura-Lana.jpg','Brown',500,'G1'),(2,2,'https://www.ikea.com/rs/sr/images/products/billy-biblioteka-sa-stakl-vratima-siva-metalik__0806974_PE770197_S5.JPG?f=g','Red',400,'F1'),(3,3,'https://www.ikea.com/rs/sr/images/products/ekolsund-fotelja-s-podnozjem-gunnared-svetlosmeda-roze__0709905_PE727164_S5.JPG?f=s','Green',200,'S1'),(4,4,'https://s.cdnmpro.com/241860914/p/l/5/radna-fotelja-petersburg-crvena-eko-koza~571055.jpg','Lightblue',12313,'R1');
/*!40000 ALTER TABLE `namestaj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vrsta_namestaja`
--

DROP TABLE IF EXISTS `vrsta_namestaja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vrsta_namestaja` (
  `VRSTA_NAMESTAJA_ID` int NOT NULL AUTO_INCREMENT,
  `VRSTA_NAMESTAJA_NAZIV` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`VRSTA_NAMESTAJA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vrsta_namestaja`
--

LOCK TABLES `vrsta_namestaja` WRITE;
/*!40000 ALTER TABLE `vrsta_namestaja` DISABLE KEYS */;
INSERT INTO `vrsta_namestaja` VALUES (1,'Ugaona Garnitura'),(2,'Biblioteka'),(3,'Fotelja sa podnozjem'),(4,'Stolica');
/*!40000 ALTER TABLE `vrsta_namestaja` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-02 16:08:45
