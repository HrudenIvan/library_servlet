-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- DROP existing tables
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user_type`;
DROP TABLE IF EXISTS `library`.`user`;
DROP TABLE IF EXISTS `library`.`author`;
DROP TABLE IF EXISTS `library`.`publisher`;
DROP TABLE IF EXISTS `library`.`book`;
DROP TABLE IF EXISTS `library`.`order_type`;
DROP TABLE IF EXISTS `library`.`order_status`;
DROP TABLE IF EXISTS `library`.`book_order`;

-- -----------------------------------------------------
-- Table `library`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`user_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table `library`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` BINARY(32) NOT NULL,
  `salt` BINARY(32) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `user_type_id` INT UNSIGNED NOT NULL DEFAULT 3,
  `penalty` DECIMAL(8,2) NULL DEFAULT 0,
  `is_blocked` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_user_user_type_idx` (`user_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_type`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `library`.`user_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `library`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`author` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `library`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`publisher` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `total` INT UNSIGNED NOT NULL,
  `available` INT NOT NULL,
  `author_id` INT UNSIGNED NOT NULL,
  `publisher_id` INT UNSIGNED NOT NULL,
  `release_date` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_book_author1_idx` (`author_id` ASC) VISIBLE,
  INDEX `fk_book_publisher1_idx` (`publisher_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `library`.`author` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_book_publisher1`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `library`.`publisher` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `library`.`order_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`order_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb3;


-- -----------------------------------------------------
-- Table `library`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`order_status` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `status_UNIQUE` (`status` ASC) VISIBLE)
ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `library`.`book_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`book_order` (
  `user_id` INT UNSIGNED NOT NULL,
  `book_id` INT UNSIGNED NOT NULL,
  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `open_date` TIMESTAMP NULL,
  `close_date` TIMESTAMP NULL,
  `return_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `order_type_id` INT UNSIGNED NOT NULL DEFAULT 1,
  `order_status_id` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`, `book_id`),
  INDEX `fk_book_order_book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_book_order_order_type1_idx` (`order_type_id` ASC) VISIBLE,
  INDEX `fk_book_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_book_order_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_book_order_order_type1`
    FOREIGN KEY (`order_type_id`)
    REFERENCES `library`.`order_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_book_order_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `library`.`order_status` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

DELIMITER $$
CREATE TRIGGER `library`.`book_BEFORE_UPDATE` BEFORE UPDATE ON `book` FOR EACH ROW
BEGIN
	IF NEW.available<0 then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'There is no avaibale book';
	END IF;
END$$

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Add information to `library`.`user_type`
-- -----------------------------------------------------
INSERT INTO `library`.`user_type` VALUES (DEFAULT, 'admin');
INSERT INTO `library`.`user_type` VALUES (DEFAULT, 'librarian');
INSERT INTO `library`.`user_type` VALUES (DEFAULT, 'user');

-- -----------------------------------------------------
-- Add information to `library`.`user`
-- -----------------------------------------------------
INSERT INTO `library`.`user` VALUES
(DEFAULT, 'admin', UNHEX('FA271CA06E188F1419A57ADCD088B827A6E07345D0D486A3F57F8B7B9E7B4645'),
UNHEX('FAC8213C377A2883AC7872A2516DE817EEF64B5D2C724FA00B2447ACDC0B9485'), 'Вася', 'Пупкин', 1, DEFAULT, DEFAULT);
INSERT INTO `library`.`user` VALUES
(DEFAULT, 'motana', UNHEX('FA271CA06E188F1419A57ADCD088B827A6E07345D0D486A3F57F8B7B9E7B4645'),
UNHEX('FAC8213C377A2883AC7872A2516DE817EEF64B5D2C724FA00B2447ACDC0B9485'), 'Татьяна', 'Грудень', 2, DEFAULT, DEFAULT);
INSERT INTO `library`.`user` VALUES
(DEFAULT, 'GruV', UNHEX('FA271CA06E188F1419A57ADCD088B827A6E07345D0D486A3F57F8B7B9E7B4645'),
UNHEX('FAC8213C377A2883AC7872A2516DE817EEF64B5D2C724FA00B2447ACDC0B9485'), 'Иван', 'Грудень', 3, DEFAULT, DEFAULT);

-- -----------------------------------------------------
-- Add information to `library`.`order_status`
-- -----------------------------------------------------
INSERT INTO `library`.`order_status` VALUES (DEFAULT, 'new');
INSERT INTO `library`.`order_status` VALUES (DEFAULT, 'ready');
INSERT INTO `library`.`order_status` VALUES (DEFAULT, 'open');
INSERT INTO `library`.`order_status` VALUES (DEFAULT, 'closed');

-- -----------------------------------------------------
-- Add information to `library`.`order_type`
-- -----------------------------------------------------
INSERT INTO `library`.`order_type` VALUES (DEFAULT, 'reading room');
INSERT INTO `library`.`order_type` VALUES (DEFAULT, 'subscription');


-- -----------------------------------------------------
-- Add demo information to `library`.`publisher`
-- -----------------------------------------------------
INSERT INTO `library`.`publisher` VALUES (DEFAULT, 'Єксмо');
INSERT INTO `library`.`publisher` VALUES (DEFAULT, 'Астрель');

-- -----------------------------------------------------
-- Add demo information to `library`.`author`
-- -----------------------------------------------------
INSERT INTO `library`.`author` VALUES (DEFAULT, 'Вячеслав', 'Шалыгин');
INSERT INTO `library`.`author` VALUES (DEFAULT, 'Роман', 'Куликов');
INSERT INTO `library`.`author` VALUES (DEFAULT, 'Сергей', 'Недоруб');


-- -----------------------------------------------------
-- Add demo information to `library`.`book`
-- -----------------------------------------------------
INSERT INTO `library`.`book` VALUES (DEFAULT, 'Тринадцатый сектор', 5, 5, 1, 1, "2000");
INSERT INTO `library`.`book` VALUES (DEFAULT, 'Зов припяти', 4, 4, 2, 2, "2001");
INSERT INTO `library`.`book` VALUES (DEFAULT, 'Песочные часы', 3, 3, 3, 1, "2010");
