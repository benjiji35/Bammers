app.controller(
		
		'searchNomCtrl',
		[
				'$scope',
				'$location',
				'ClientService',
				'$routeParams',
				function($scope, $location, ClientService,$routeParams) {
					console.log("hello");
				
					$scope.src = function() {
					ClientService.srcClient($routeParams.nom,$routeParams.prenom,$routeParams.cpte) 
								.then
									(function(d) {
											$scope.users = d;													
											console
													.log($scope.users);
																								
										},
										function(errResponse) {
											console.error = ("Invalid entry");
										});						

				} ]);