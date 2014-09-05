-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 03-Set-2014 às 11:45
-- Versão do servidor: 5.5.34-MariaDB
-- versão do PHP: 5.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `sistemadelogisticadb`
--
CREATE DATABASE IF NOT EXISTS `sistemadelogisticadb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `sistemadelogisticadb`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mapas`
--

CREATE TABLE IF NOT EXISTS `mapas` (
  `ID` int(11) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  `AUTONOMIA_DO_CAMINHAO_EM_KM_L` decimal(10,0) NOT NULL,
  `LITRO_DO_COMBUSTIVEL` decimal(10,0) NOT NULL,
  `PONTOS_DA_ROTA` varchar(20) NOT NULL,
  `CUSTO` decimal(10,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `rotas`
--

CREATE TABLE IF NOT EXISTS `rotas` (
  `ID` int(11) NOT NULL,
  `ORIGEM` char(1) NOT NULL,
  `DESTINO` char(1) NOT NULL,
  `DISTANCIA_EM_KM` decimal(10,0) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
