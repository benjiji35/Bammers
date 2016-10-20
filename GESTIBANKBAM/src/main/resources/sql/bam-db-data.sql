INSERT INTO `Personne` VALUES (1,'69000','bambank35000@gmail.com',12,'cours gambetta','+33469123456','lyon','mr','1990-07-13 00:00:00','topsecret',5000,5,'bilot','jack','banquier',1,8);
INSERT INTO `Personne` VALUES (2,'35000','bambank35000@gmail.com',34,'rue des braves','+33245678790','rennes','mr','1992-02-13 00:00:00','winner1',4000,2,'mao','yang','banquier',1,4);
INSERT INTO `Personne` VALUES (3,'44000','bambank35000@gmail.com',2,'rue gustave eiffel','+33243679844','nantes','mme','1990-07-12 00:00:00','killer1',3500,1,'bacry','jenny','banquier',1,4);
INSERT INTO `Personne` VALUES (4,'53000','bambank35000@gmail.com',37,'rue du départ','+33209876577','laval','mr','1990-12-09 00:00:00','looser1',3000,2,'sparrow','jack','banquier',1,4);
INSERT INTO `Personne` VALUES (5,'31000','bambank35000@gmail.com',118,'route de bayonne','+33562324589','toulouse','mme','1990-07-14 00:00:00','kiki123',2500,2,'egbo','mela','vendeuse',3,2);
INSERT INTO `Personne` VALUES (6,'34000','bambank35000@gmail.com',34,'place belcourt','+33495097652','montpellier','mme','1988-10-22 00:00:00','kilo123',2750,2,'gonzales','maria','consultante',3,2);
INSERT INTO `Personne` VALUES (7,'75000','bambank35000@gmail.com',72,'avenue du general de gaulles','+33142977630','paris','mme','1994-09-13 00:00:00','balto123',3790,2,'bower','jessica','consultante',3,2);
INSERT INTO `Personne` VALUES (8,'75000','bambank35000@gmail.com',13,'esplanade de la paix','+33142977630','paris','mme','1987-11-27 00:00:00','bilbon123',3990,2,'al-khatabi','mohammed','medecin',2,5);
INSERT INTO `Personne` VALUES (9,'75000','bambank35000@gmail.com',13,'esplanade de la paix','+33142977630','paris','mme','1987-11-27 00:00:00','bilbon123',5190,2,'benrubi','elliot','architecte',3,3);

INSERT INTO `Employe` VALUES ('2012-09-27 00:00:00','administrateur',1);
INSERT INTO `Employe` VALUES ('2012-12-07 00:00:00','conseiller',2);
INSERT INTO `Employe` VALUES ('2013-05-26 00:00:00','conseiller',3);
INSERT INTO `Employe` VALUES ('2014-08-18 00:00:00','conseiller',4);

INSERT INTO `bambank`.`Client` (`id`, `conseiller_id`) VALUES ('5', '3');
INSERT INTO `bambank`.`Client` (`id`, `conseiller_id`) VALUES ('6', '4');
INSERT INTO `bambank`.`Client` (`id`, `conseiller_id`) VALUES ('7', '3');
INSERT INTO `bambank`.`Client` (`id`, `conseiller_id`) VALUES ('8', '3');

INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('123', '0', '0', '0.18', '0.02', '0');
INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('200', '0', '0', '0.18', '0.02', '0');
INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('300', '0', '0', '0.18', '0.02', '0');
INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('400', '0', '0', '0.18', '0.02', '0');
INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('500', '0', '0', '0.18', '0.02', '0');

INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('5', '123');
INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('6', '200');
INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('7', '300');
INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('8', '400');
INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('9', '500');

INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('1', '2016-10-19', '2016-10-19', '30000', '1', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('2', '2016-01-19', '2016-01-19', '500', '1', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('3', '2016-02-19', '2016-02-19', '3000', '1', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('4', '2016-03-02', '2016-03-02', '-7000', '2', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('5', '2016-03-12', '2016-03-12', '-5080', '2', FALSE);

INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('6', '2016-03-12', '2016-03-12', '7020', '1', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('7', '2016-04-17', '2016-04-17', '-180', '2', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('8', '2016-05-24', '2016-05-24', '-70', '2', FALSE);

INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('9', '2016-07-22', '2016-07-22', '10000', '1', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('10', '2016-07-31', '2016-07-31', '-5000', '2', FALSE);
INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('11', '2016-08-02', '2016-08-02', '-3000', '2', FALSE);

INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('12', '2016-09-16', '2016-09-16', '7340', '1', FALSE);



INSERT INTO `bambank`.`Credit` (`id`) VALUES ('1');
INSERT INTO `bambank`.`Credit` (`id`) VALUES ('2');
INSERT INTO `bambank`.`Credit` (`id`) VALUES ('3');
INSERT INTO `bambank`.`Credit` (`id`) VALUES ('6');
INSERT INTO `bambank`.`Credit` (`id`) VALUES ('12');

INSERT INTO `bambank`.`Debit` (`id`) VALUES ('4');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('5');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('7');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('8');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('9');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('10');
INSERT INTO `bambank`.`Debit` (`id`) VALUES ('11');

INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('123', '1');

INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('200', '2');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('200', '3');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('200', '4');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('200', '5');

INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('300', '6');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('300', '7');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('300', '8');

INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('400', '9');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('400', '10');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('400', '11');

INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('500', '12');

