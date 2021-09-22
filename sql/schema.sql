-- MySQL Script generated by MySQL Workbench
-- Tue Aug 31 23:01:07 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema default_schema
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinema_bs
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema_bs
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinema_bs` ;
CREATE SCHEMA IF NOT EXISTS `cinema_bs` DEFAULT CHARACTER SET utf8 ;
USE `cinema_bs` ;


-- -----------------------------------------------------
-- Table `cinema_bs`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`movie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(512) NULL DEFAULT NULL,
  `photo_url` VARCHAR(255) NULL,
  `director` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `year` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_bs`.`movie_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`movie_session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `show_date` DATE NOT NULL,
  `show_time` TIME NOT NULL,
  `ticket_price` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ms_movie_id_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_ms_movie_id`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinema_bs`.`movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cinema_bs`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -- -----------------------------------------------------
-- -- Table `cinema_bs`.`shopping_cart`
-- -- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `cinema_bs`.`shopping_cart` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `user_id` INT NOT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
--   INDEX `fk_scart_user_id_idx` (`user_id` ASC) VISIBLE,
--   CONSTRAINT `fk_scart_user_id`
--     FOREIGN KEY (`user_id`)
--     REFERENCES `cinema_bs`.`user` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE)
-- ENGINE = InnoDB;

--- -----------------------------------------------------
-- Table `cinema_bs`.`row`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`row` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_bs`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`seat` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `row_id` INT NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_row_id_row_idx` (`row_id` ASC) VISIBLE,
  CONSTRAINT `fk_row_id_row`
    FOREIGN KEY (`row_id`)
    REFERENCES `cinema_bs`.`row` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_bs`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_time` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  `order_price` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema_bs`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_bs`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_session_id` INT NOT NULL,
  `row_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movie_session_idx` (`movie_session_id` ASC) VISIBLE,
  INDEX `fk_row_id_idx` (`row_id` ASC) VISIBLE,
  INDEX `fk_seat_id_idx` (`seat_id` ASC) VISIBLE,
  INDEX `fk_order_id_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_session`
    FOREIGN KEY (`movie_session_id`)
    REFERENCES `cinema_bs`.`movie_session` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_row_id`
    FOREIGN KEY (`row_id`)
    REFERENCES `cinema_bs`.`row` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seat_id`
    FOREIGN KEY (`seat_id`)
    REFERENCES `cinema_bs`.`seat` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `cinema_bs`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cinema_bs`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_bs`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema_bs`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role_id_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `cinema_bs`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cinema_bs`.`movie_session_booked_seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_bs`.`movie_session_booked_seats` (
  `movie_session_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  INDEX `fk_ms_id_idx` (`movie_session_id` ASC) VISIBLE,
  INDEX `fk_seats_id_idx` (`seat_id` ASC) VISIBLE,
  CONSTRAINT `fk_ms_id`
    FOREIGN KEY (`movie_session_id`)
    REFERENCES `cinema_bs`.`movie_session` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seats_id`
    FOREIGN KEY (`seat_id`)
    REFERENCES `cinema_bs`.`seat` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
