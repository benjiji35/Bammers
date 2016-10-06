'use strict';

angular
		.module('myApp')
		.controller(
				'connectCtrl',
				[
						'$scope',
						'$window',
						'PersonneService',
						function($scope, $window, PersonneService) {

							$scope.valid = function() {

								PersonneService
										.connexion($scope.id, $scope.mdp)
										.then(
												function(d) {
													$scope.personne = d;													
													console
															.log($scope.personne);
													console
															.log("Valid mot de passe et id");
													
//													sessionStorage.setItem("username",myName);
													
//													if ($scope.personne.id.startsWith('C')) {
//														$window.location = 'accueil.html#/see';
//													}
//													if ($scope.personne.id.startsWith('X')) {
//														$window.location = 'accueil_ag.html#/search_ag';
//													}
//													if ($scope.personne.id.startsWith('A')) {
//														$window.location = 'accueil_ad.html#/affect';
//													}
												},
												function(errResponse) {
													$window.location = 'se_connecter';
													console.error = ("Invalid mot de passe ou id");
												});

							}

						} ]);