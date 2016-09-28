/*
	CONVENTION UTILISEE POUR LES COMMANDES DE ROUTAGE.

	Pour l'instant seul le verbe de la commande est utilisé (cf. colonne VERB).

	Il incombe à chaque développeur de mettre à jour la liste des commandes 
	définie ci-dessous et de veiller d’éviter tout conflit.

+===================================================================+=======+================+
|     Description                                                   | CODE  |    VERB        |
+===================================================================+=======+================+
| Contacter un conseiller                                           |   0   |  contact       |
| Se connecter                                                      |   1   |  connect       |
| Inscription nouveau client                                        |   2   |  register      |
| Ajout nouveau conseiller                                          |   3   |  add           |
| Demander des informations additionnelles                          |   4   |  ask           |
| Voir le détail des comptes du client                              |   5   |  see           |
| Effectuer un virement                                             |   6   |  transfer      |
| Consulter la liste des notifications                              |   7   |  notify        |
| Effectuer une demande                                             |   8   |  request       |
| Impression de RIB                                                 |   9   |  print         |
| Consulter et/ou mettre à jour les informations personnelles       |  10   |  update        |
| Effectuer une recherche (espace Conseiller)                       |  11   |  search_ag     |
| Effectuer une recherche (espace Admin)                            |  12   |  search_ad     |
| Consulter la liste des demandes en attente (espace Conseiller)    |  13   |  consult_ag    |
| Consulter la liste des demandes en attente (espace Admin)         |  14   |  consult_ad    |
+===================================================================+=======+================+
*/

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/contact', {
			templateUrl: 'views/contact.html',
			controller: 'contactCtrl'})
		.when('/connect', {
			templateUrl: 'views/connect.html',
			controller: 'connectCtrl'})
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
			controller: 'search_adCtrl'})
		.when('/search_ad', {
			templateUrl: 'views/search_ad.html',
			controller: 'search_adCtrl'})

		.otherwise({redirectTo: '/'})
}]);
/*
app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/register', {
			templateUrl: 'views/tpl.html',
			controller: 'secondCtrl'})
		.when('/todo/:id', {
			templateUrl: 'views/tpl.html',
			controller: 'secondCtrl'})
		.when('/other', {
			template: '<h2> Other </h2>',
			controller: 'otherCtrl'})
		.otherwise({redirectTo: '/'})
}]);
*/
