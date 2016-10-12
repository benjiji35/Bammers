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
													
																									
													switch ($scope.personne.type){
														case 1:
															Console.log("Bienvenue chez BamBank")
															break;
														case 2:
															$window.location = 'accueil.html#/see';
															console.log(pers);
															break;
														case 4:
															$window.location = 'accueil_ag.html#/search_ag';
															console.log(pers);
															break;
														case 8:
															$window.location = 'accueil_ad.html#/affect';
															console.log(pers);
															break;
														default:
															$window.location = 'se_connecter.html';
															break;
													}
												},
												function(errResponse) {
													$window.location = 'se_connecter';
													console.error = ("Invalid mot de passe ou id");
												});

							}

						} ]);