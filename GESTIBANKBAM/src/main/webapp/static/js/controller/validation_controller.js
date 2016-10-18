'use strict';

app.controller(
				'connectCtrl',
				[
						'$scope',
						'$window',
						'PersonneService',
						function($scope, $window,PersonneService) {

							$scope.valid = function() {

								PersonneService
										.connexion($scope.id, $scope.mdp) //the id and mdp values here comes from outside
										.then(
												function(d) {
													$scope.personne = d;													
													console
															.log($scope.personne);
													console
															.log("Valid mot de passe et id");
													

													sessionStorage.setItem("pers",JSON.stringify($scope.personne));
													
//													sessionStorage.personne = JSON.stringify($scope.personne);
//													$scope.personne = JSON.parse(sessionStorage.personne);
													
													//var data = sessionStorage.getItem("pers");
													
																								
															$window.location = 'accueil.html#';
															console.log(pers);

												},
												function(errResponse) {
													$window.location = 'se_connecter.html';
													console.error = ("Invalid mot de passe ou id");
												});

							}

						} ]);