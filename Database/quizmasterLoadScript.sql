-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: quizmaster
-- ------------------------------------------------------
-- Server version	8.0.15




INSERT INTO `role` VALUES ('Coordinator'),('Teacher'),('Administrator'),('Student'),('SystemAdministrator');

INSERT INTO `user` VALUES (1,'Student','Cyntia','cyntia'),
						(2,'Teacher','Einstein','einstein'),
						(3,'Coordinator','Mark','mark'),
                        (4,'Administrator','Otto','otto'),
                        (5,'SystemAdministrator','Linda','linda'),
                        (6,'Student','Henk','henk'),
                        (7,'Teacher','Floor','floor'),
                        (8,'Coordinator','Twan','twan'),
                        (9,'Administrator','Fleur','fleur'),
                        (10,'SystemAdministrator','Klaas','klaas');

INSERT INTO `quizmaster`.`course` (`coordinator_idUser`, `name`) VALUES ('3', 'Bouldercursus');
INSERT INTO `quizmaster`.`course` (`coordinator_idUser`, `name`) VALUES ('8', 'Rotsklimcursus');
INSERT INTO `quizmaster`.`course` (`coordinator_idUser`, `name`) VALUES ('3', 'C1Cursus');
INSERT INTO `quizmaster`.`quiz` (`course_idCourse`, `quizName`,`numberOfQuestions`, `treshold') VALUES ('1', 'Boulderquiz', '3', '60');
INSERT INTO `quizmaster`.`quiz` (`course_idCourse`, `quizName`,`numberOfQuestions`, `treshold') VALUES ('2', 'Rotsklimquiz1', '3', '60');
INSERT INTO `quizmaster`.`quiz` (`course_idCourse`, `quizName`,`numberOfQuestions`, `treshold') VALUES ('2', 'Rotsklimquiz2','3', '60');
INSERT INTO `quizmaster`.`quiz` (`course_idCourse`, `quizName`,`numberOfQuestions`, `treshold') VALUES ('3', 'C1quiz','4', '50');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('1', 'Wat is spotten?', 'Spotten is de klimmer volgen met je handen en lichaam om de klimmer te kunnen opvangen als die valt', 'Vogeltjes kijken ', 'Vliegtuignummers noteren', 'Iemand voor de gek zetten');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('1', 'Wat is een crashpad?', 'Een mat die wordt gebruikt om onder de klimmer te leggen zodat een val wordt verzacht', 'Een mat voor een crashtestdummy', 'een koffiepad', 'Ik weet het niet');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('1', 'Hoe wordt bepaald of een route getopt is?', 'De klimmer heeft de laatste greep met beide handen vast.', 'Boven aan de route staat een topvlag', 'een route is al een keer geklommen door iemand anders', 'Alleen een topklimmer kan de route klimmen.');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('2', 'Wat is een topo?', 'Een boekje waar de klimroutes in staan beschreven', 'Een opgave bij aardrijkskunde', 'Een onderdeel van de wiskunde', 'Een tent van een indiaan');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('2', 'Hoe bereken je de valfactor?', 'De valhoogte gedeeld door het uitgegeven touw', 'De kans dat je valt', 'De remweg gedeeld door het uitgegeven touw', 'Hoe vaak een voorklimmer valt');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`) VALUES ('2', 'Welke subwaarderingen heeft een zesdegraads route?', 'zes a, zes b of zes c', 'Geen idee', 'Die bestaan niet.');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('3', 'Wat is een factor voor de gebruiksduur van een touw?', 'Het aantal normvallen dat een touw kan houden vanaf de verkoopdatum', 'Wie er met het touw klimt', 'Een touw moet je altijd na een jaar gebruik weggooien', 'Het aantal dagen dat een touw niet wordt gebuikt.');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('3', 'Waar voor dient een helm?', 'Om bescherming te bieden tegen steenslag', 'Om bescherming te bieden bij een val op de grond', 'Om niet op te zetten als je op de foto gaat', 'Voor de show.');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`) VALUES ('3', 'Hoe voorkom je een trilbeen?', 'Door de hak van je voet laag te houden', 'Door op tijd rust te nemen', 'Door je poot stijf te houden');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`) VALUES ('4', 'Wie was Ronald Naar?', 'Een bekende Nederlandse alpinist', 'Een nare man', 'Een van de vele klimmers die zijn gestopt met klimmen');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('4', 'Waar kun je in de alpen klimmen?', 'In de Alpen', 'Overal waar een berg te vinden is.', 'Op een weiland op een berghelling', 'Ik weet het niet.');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`) VALUES ('4', 'Waarom ga je op een gletsjer aan het touw?', 'Om iemand die in een spleet valt eruit te kunnen halen', 'Om dat het touw in de sneeuw snijdt en je val remt', 'Omdat je dan mooie foto\'s kan maken');
INSERT INTO `quizmaster`.`question` (`Quiz_idQuiz`, `question`, `answerA`, `answerB`, `answerC`, `answerD`) VALUES ('4', 'Waarom beklimmen klimmers bergen?', 'Omdat ze er zijn', 'Daarvoor is geen enkele reden', 'Zolang er bergen zijn willen mensen klimmen', 'Om thuis mooie verhalen te kunnen vertellen');

INSERT INTO `group` VALUES (1, 1, 7, 'Boulder beginnend'),
							(2, 1, 2, 'Boulder gevorderd'),
                            (3, 2, 3, 'Rotspartij'),
                            (4, 3, 7, 'Alpinisme');

INSERT INTO `studentsingroup` VALUES (1, 1),
									(3, 1),
                                    (4, 6);
