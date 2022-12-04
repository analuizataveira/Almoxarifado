-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BancoAlmoxarifado
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BancoAlmoxarifado
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BancoAlmoxarifado` DEFAULT CHARACTER SET utf8 ;
USE `BancoAlmoxarifado` ;

-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Componente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Componente` (
  `Tipo` VARCHAR(45) NOT NULL,
  `idComponente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComponente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Resistor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Resistor` (
  `resistencia` FLOAT NOT NULL,
  `qtd` INT NOT NULL,
  `Componente_idComponente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`resistencia`),
  INDEX `fk_Resistor_Componente1_idx` (`Componente_idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Resistor_Componente1`
    FOREIGN KEY (`Componente_idComponente`)
    REFERENCES `BancoAlmoxarifado`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Capacitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Capacitor` (
  `capacitancia` FLOAT NOT NULL,
  `qtd` INT NOT NULL,
  `Componente_idComponente` VARCHAR(45) NOT NULL,
  INDEX `fk_Capacitor_Componente1_idx` (`Componente_idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Capacitor_Componente1`
    FOREIGN KEY (`Componente_idComponente`)
    REFERENCES `BancoAlmoxarifado`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Colaborador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Colaborador` (
  `idColab` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idColab`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Professor` (
  `id` INT NOT NULL,
  `Colaborador_idColab` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Professor_Colaborador1_idx` (`Colaborador_idColab` ASC) VISIBLE,
  CONSTRAINT `fk_Professor_Colaborador1`
    FOREIGN KEY (`Colaborador_idColab`)
    REFERENCES `BancoAlmoxarifado`.`Colaborador` (`idColab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SELECT * FROM RESISTOR;
SELECT * FROM Componente;

-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Aluno` (
  `matricula` INT NOT NULL,
  `periodo` INT NOT NULL,
  `Curso` VARCHAR(45) NOT NULL,
  `Colaborador_idColab` INT NOT NULL,
  PRIMARY KEY (`matricula`),
  INDEX `fk_Aluno_Colaborador1_idx` (`Colaborador_idColab` ASC) VISIBLE,
  CONSTRAINT `fk_Aluno_Colaborador1`
    FOREIGN KEY (`Colaborador_idColab`)
    REFERENCES `BancoAlmoxarifado`.`Colaborador` (`idColab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`ColabAlmo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`ColabAlmo` (
  `id` INT NOT NULL,
  `Colaborador_idColab` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ColabAlmo_Colaborador1_idx` (`Colaborador_idColab` ASC) VISIBLE,
  CONSTRAINT `fk_ColabAlmo_Colaborador1`
    FOREIGN KEY (`Colaborador_idColab`)
    REFERENCES `BancoAlmoxarifado`.`Colaborador` (`idColab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BancoAlmoxarifado`.`Requere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BancoAlmoxarifado`.`Requere` (
  `Componente_idComponente` VARCHAR(45) NOT NULL,
  `Colaborador_idColab` INT NOT NULL,
  PRIMARY KEY (`Componente_idComponente`, `Colaborador_idColab`),
  INDEX `fk_Componente_has_Colaborador_Colaborador1_idx` (`Colaborador_idColab` ASC) VISIBLE,
  INDEX `fk_Componente_has_Colaborador_Componente1_idx` (`Componente_idComponente` ASC) VISIBLE,
  CONSTRAINT `fk_Componente_has_Colaborador_Componente1`
    FOREIGN KEY (`Componente_idComponente`)
    REFERENCES `BancoAlmoxarifado`.`Componente` (`idComponente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componente_has_Colaborador_Colaborador1`
    FOREIGN KEY (`Colaborador_idColab`)
    REFERENCES `BancoAlmoxarifado`.`Colaborador` (`idColab`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

      
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
