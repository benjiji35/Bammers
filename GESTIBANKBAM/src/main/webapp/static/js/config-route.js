/*
	CONVENTION UTILISEE POUR LES COMMANDES DE ROUTAGE.

	Pour l'instant seul le verbe de la commande est utilisé (cf. colonne VERB).
	Le champs 'REVOKED' indique que la fonctionnalité a été révoquée (si positionné à 'yes').

	Il incombe à chaque développeur de mettre à jour la liste des commandes 
	définie ci-dessous et de veiller d’éviter tout conflit.

+===================================================================+=======+================+=========+
|     Description                                                   | CODE  |    VERB        | REVOKED |
+===================================================================+=======+================+=========+
| Contacter un conseiller                                           |   0   |  contact       |   no    |
| Se connecter                                                      |   1   |  connect       |   yes   |
| Inscription nouveau client                                        |   2   |  register      |   no    |
| Ajout nouveau conseiller                                          |   3   |  add           |   no    |
| Demander des informations additionnelles                          |   4   |  ask           |   no    |
| Voir le détail des comptes du client                              |   5   |  see           |   no    |
| Effectuer un virement                                             |   6   |  transfer      |   no    |
| Consulter la liste des notifications                              |   7   |  notify        |   no    |
| Effectuer une demande                                             |   8   |  request       |   no    |
| Impression de RIB                                                 |   9   |  print         |   no    |
| Consulter et/ou mettre à jour les informations personnelles       |  10   |  update        |   no    |
| Effectuer une recherche (espace Conseiller)                       |  11   |  search_ag     |   no    |
| Effectuer une recherche (espace Admin)                            |  12   |  search_ad     |   no    |
| Consulter la liste des demandes en attente (espace Conseiller)    |  13   |  consult_ag    |   no    |
| Consulter la liste des demandes en attente (espace Admin)         |  14   |  consult_ad    |   no    |
| Consulter la liste des clients (espace Conseiller)                |  15   |  list_ag       |   no    |
| Consulter la liste des conseillers (espace Admin)                 |  16   |  list_ad       |   no    |
| Affectation de conseiller							                |  17   |  affect	     |   no    |
+===================================================================+=======+================+=========+
*/

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/contact', {
			templateUrl: 'views/contact.html',
			controller: 'contactCtrl'})
		/*.when('/connect', {
			//templateUrl: 'views/connect.html',
			controller: 'connectCtrl'}) */		
		.when('/register', {
			templateUrl: 'views/register.html',
			controller: 'registerCtrl'})
		.when('/add', {
			templateUrl: 'views/add.html',
			controller: 'addCtrl'})
		.when('/ask', {
			templateUrl: 'views/ask.html',
			controller: 'askCtrl'})
		.when('/see', {
			templateUrl: 'views/see.html',
			controller: 'seeCtrl'})
		.when('/transfer', {
			templateUrl: 'views/transfer.html',
			controller: 'transferCtrl'})
		.when('/notify', {
			templateUrl: 'views/notify.html',
			controller: 'notifyCtrl'})        
		.when('/request', {
			templateUrl: 'views/request.html',
			controller: 'requestCtrl'})
		.when('/print', {
			templateUrl: 'views/print.html',
			controller: 'printCtrl'})
		.when('/update', {
			templateUrl: 'views/update.html',
			controller: 'updateCtrl'})
		.when('/search_ag', {
			templateUrl: 'views/search_ag.html',
			controller: 'search_agCtrl'})
		.when('/search_ad', {
			templateUrl: 'views/search_ad.html',
			controller: 'search_adCtrl'})
		.when('/consult_ag', {
			templateUrl: 'views/consult_ag.html',
			controller: 'consult_agCtrl'})
		.when('/search_ad', {
			templateUrl: 'views/search_ad.html',
			controller: 'search_adCtrl'})
		.when('/list_ag', {
			templateUrl: 'views/list_ag.html',
			controller: 'list_agCtrl'})
		.when('/list_ad', {
			templateUrl: 'views/list_ad.html',
			controller: 'list_adCtrl'})
		.when('/affect', {
			templateUrl: 'views/affect.html',
			controller: 'affectCtrl'})
		.when('/list_ag', {
			templateUrl: 'views/list_ag.html',
			controller: 'listCtrl'})
		.when('/see_cl/:id', {
			templateUrl: 'views/see_cl.html',
			controller: 'see_clCtrl'})
		.when('/see_con/:id', {
			templateUrl: 'views/see_con.html',
			controller: 'see_conCtrl'})
//		.when('/search_nom/:nom/:prenom/:cpte', {
//			templateUrl: 'views/search_ag.html',
//			controller: 'sm_ctrlr'})
		.when('/', {
			templateUrl: 'views/root.html',
			controller: 'rootCtrl'})

		.otherwise({redirectTo: '/'})
}]);

