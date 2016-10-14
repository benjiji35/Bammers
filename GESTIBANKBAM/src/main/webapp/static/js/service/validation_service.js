'use strict';
// declaration de module


app.factory(
				'PersonneService',
				[
						'$http',
						'$q',
						function($http, $q) {

							var REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/personne/';

							var factory = {
								connexion : connexion,
							};
							return factory;

							function connexion(id, mdp) {
								var deferred = $q.defer();
								
								$http
										.get(
												REST_SERVICE_URI + 'id-' + id+ '/mdp-' + mdp)
										.then(
												function(response) {
													deferred
															.resolve(response.data);
												},
												function(errResponse) {
													console
															.error('Error while connecting');
													deferred
															.reject(errResponse);
												});
								return deferred.promise; // here i did return
															// deffered promise
															// so at the
															// controller i will
															// use then function
															// for success and
															// un success
							}
						} ]);