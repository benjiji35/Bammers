INSERT INTO `Personne` VALUES (1,'69000','bambank35000@gmail.com',12,'cours gambetta','+33469123456','lyon','mr','1990-07-13 00:00:00','topsecret',5000,5,'bilot','jack','banquier',1,8);
INSERT INTO `Personne` VALUES (2,'35000','bambank35000@gmail.com',34,'rue des braves','+33245678790','rennes','mr','1992-02-13 00:00:00','winner1',4000,2,'mao','yang','banquier',1,4);
INSERT INTO `Personne` VALUES (3,'44000','bambank35000@gmail.com',2,'rue gustave eiffel','+33243679844','nantes','mme','1990-07-12 00:00:00','killer1',3500,1,'bacry','jenny','banquier',1,4);
INSERT INTO `Personne` VALUES (4,'53000','bambank35000@gmail.com',37,'rue du départ','+33209876577','laval','mr','1990-12-09 00:00:00','looser1',3000,2,'sparrow','jack','banquier',1,4);
INSERT INTO `Personne` VALUES (5,'31000','bambank35000@gmail.com',118,'route de bayonne','+33562324589','toulouse','mme','1990-07-14 00:00:00','kiki123',2500,2,'egbo','mela','vendeuse',3,2);

INSERT INTO `Employe` VALUES ('2012-09-27 00:00:00','administrateur',1);
INSERT INTO `Employe` VALUES ('2012-12-07 00:00:00','conseiller',2);
INSERT INTO `Employe` VALUES ('2013-05-26 00:00:00','conseiller',3);
INSERT INTO `Employe` VALUES ('2014-08-18 00:00:00','conseiller',4);

INSERT INTO `bambank`.`Client` (`id`, `conseiller_id`) VALUES ('5', '3');

INSERT INTO `bambank`.`Compte` (`numCpt`, `montantAutorisationDecouvert`, `montantSeuilMinRemuneration`, `tauxDecouvert`, `tauxRemuneration`, `type`) VALUES ('123', '0', '0', '0.18', '0.02', '0');
INSERT INTO `bambank`.`Client_Compte` (`Client_id`, `comptes_numCpt`) VALUES ('5', '123');

INSERT INTO `bambank`.`Transaction` (`id`, `dateDebut`, `dateFin`, `montant`, `type`, `waitForMonthEnd`) VALUES ('1', '2016-10-19', '2016-10-19', '30000', '1', FALSE);

INSERT INTO `bambank`.`Credit` (`id`) VALUES ('1');
INSERT INTO `bambank`.`Compte_Transaction` (`Compte_numCpt`, `transactions_id`) VALUES ('123', '1');

