CREATE SCHEMA `AulaCRUD` ;

USE `AulaCRUD` ;

CREATE TABLE `AulaCRUD`.`Alunos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `idade` INT NULL,
  PRIMARY KEY (`id`));