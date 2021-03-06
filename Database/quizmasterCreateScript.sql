CREATE DATABASE  IF NOT EXISTS `quizmaster`;
USE `quizmaster`;

CREATE TABLE `role` (
  `roleName` varchar(45) NOT NULL,
  PRIMARY KEY (`roleName`)
);

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `role_roleName` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  KEY `fk_user_role1_idx` (`role_roleName`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_roleName`) REFERENCES `role` (`roleName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ;

CREATE TABLE `course` (
  `idCourse` int(11) NOT NULL AUTO_INCREMENT,
  `coordinator_idUser` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idCourse`),
  KEY `fk_course_user1_idx` (`coordinator_idUser`),
  CONSTRAINT `fk_course_user1` FOREIGN KEY (`coordinator_idUser`) REFERENCES `user` (`idUser`) ON DELETE RESTRICT ON UPDATE CASCADE
);



CREATE TABLE `group` (
  `idGroup` int(11) NOT NULL AUTO_INCREMENT,
  `course_idCourse` int(11) NOT NULL,
  `teacher_idUser` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idGroup`),
  KEY `fk_Group_Course1_idx` (`Course_idCourse`),
  KEY `fk_Group_User1_idx` (`Teacher_idUser`),
  CONSTRAINT `fk_Group_Course1` FOREIGN KEY (`Course_idCourse`) REFERENCES `course` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Group_User1` FOREIGN KEY (`Teacher_idUser`) REFERENCES `user` (`idUser`) ON DELETE RESTRICT ON UPDATE RESTRICT
);



CREATE TABLE `quiz` (
  `idQuiz` int(11) NOT NULL AUTO_INCREMENT,
  `course_idCourse` int(11) NOT NULL,
  `quizName` varchar(45) NOT NULL,
  `numberOfQuestions` int(2) NOT NULL,
  `treshold` int(2) NOT NULL,
  PRIMARY KEY (`idQuiz`),
  KEY `fk_Quiz_Course_idx` (`Course_idCourse`),
  CONSTRAINT `fk_Quiz_Course` FOREIGN KEY (`Course_idCourse`) REFERENCES `course` (`idCourse`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE `question` (
  `idQuestion` int(11) NOT NULL AUTO_INCREMENT,
  `Quiz_idQuiz` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answerA` varchar(245) NOT NULL,
  `answerB` varchar(245) NOT NULL,
  `answerC` varchar(245) DEFAULT NULL,
  `answerD` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`idQuestion`),
  KEY `fk_question_Quiz1_idx` (`Quiz_idQuiz`),
  CONSTRAINT `fk_question_Quiz1` FOREIGN KEY (`Quiz_idQuiz`) REFERENCES `quiz` (`idQuiz`) ON DELETE CASCADE ON UPDATE CASCADE
) ;



CREATE TABLE `studentsInGroup` (
  `group_idGroup` int(11) NOT NULL,
  `student_idUser` int(11) NOT NULL,
  PRIMARY KEY (`Group_idGroup`,`Student_idUser`),
  KEY `fk_Group_has_User_User1_idx` (`Student_idUser`),
  KEY `fk_Group_has_User_Group1_idx` (`Group_idGroup`),
  CONSTRAINT `fk_Group_has_User_User1` FOREIGN KEY (`Student_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Group_has_User_Group1` FOREIGN KEY (`Group_idGroup`) REFERENCES `group` (`idGroup`) ON DELETE CASCADE ON UPDATE CASCADE
);
