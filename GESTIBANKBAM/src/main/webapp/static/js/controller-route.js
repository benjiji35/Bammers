app.controller('mainCtrl', function($scope) {});
app.controller('contactCtrl', ['$scope', '$routeParams', '$location','$q','ClientService', 'ClientServiceNew',
	function($scope, $routeParams, $location,$q, ClientService,ClientServiceNew) {
    var self = this;
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


		/*$scope.sujet = $routeParams.sujet;
		$scope.msg = $routeParams.msg;
		//$scope.absUrl  = $location.absUrl();
		$scope.fichiers = $routeParams.fichiers;
		/*console.log("contactCtrl::sujet="+$routeParams.sujet);
		console.log("contactCtrl::msg="+$routeParams.msg);
		console.log("contactCtrl::files="+$routeParams.files);*/                               
}]);




/*
app.controller('connectCtrl', ['$scope', '$location', 
	function($scope, $location) {
		//$location.url('/tpl');
		console.log("connectCtrl::=");
	}]);
*/
// register : no params
app.controller('registerCtrl', ['$scope', '$location', 
	function($scope, $location) {
		//$location.url('/tpl');
		console.log("registerCtrl::");
	}]);

app.controller('addCtrl', ['$scope', '$location', 
	function($scope, $location) {
		//$location.url('/tpl');
		console.log("addCtrl::");
	}]);


app.controller('askCtrl', ['$scope', '$location', 
	function($scope, $location) {
		//$location.url('/tpl');
		console.log("askCtrl::");
	}]);

app.controller('seeCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
		//$location.url("views/see.html");
		//$scope.role = 1;
	}]);
app.controller('see_clCtrl', [ '$scope', '$location', 'ClientIdService',
                       		'$routeParams',
                       		function($scope, $location, ClientIdService, $routeParams) {
                       			console.log("hello id");
                       			ClientIdService.searchClient($routeParams.id).then(function(d) {
                       				self.user = d;
                       				$scope.user = d;
                       				console.log($scope.user);
                       			}, function(errResponse) {
                       				console.error('Error while fetching Users');
                       			});
                       		} ]);

app.controller('see_conCtrl', [ '$scope', '$location', 'EmployeService',
                          		'$routeParams',
                          		function($scope, $location, EmployeService, $routeParams) {
                          			console.log("hello id");
                          			EmployeService.searchCons($routeParams.id).then(function(d) {
                          				self.cons = d;
                          				$scope.cons = d;
                          				console.log($scope.user);
                          			}, function(errResponse) {
                          				console.error('Error while fetching Users');
                          			});
                          		} ]);
//transfer 
app.controller('transferCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
	}]);

//notify   
app.controller('notifyCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
	}]);
	
//request       
app.controller('requestCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
	}]);

//print         
app.controller('printCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
	}]);

//update        
app.controller('updateCtrl', ['$scope', '$location', 
	function($scope, $location) {
		// todo
	}]);

//search_ag     
app.controller('search_agCtrl',  ['$scope', '$location','ClientService',
  function($scope, $location, ClientService){
	console.log("fetching All Clients");
	ClientService.fetchAllClients()
        .then(
        function(d) {
            self.users = d;
            $scope.users=d;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        }
    );
	console.log("Entered");	
	$scope.sm = function (nom,prenom,cpte){
	ClientService.srcClient(nom,prenom,cpte)
	.then(
	        function(d) {
	            self.users = d;
	            $scope.users=d;
	            console.log($scope.users);
	            $window.location = 'accueil_ag.html#/search_ag';
	        },
	        function(errResponse){
	            console.error('Error while fetching Users');
	        }
	    );
	}
}
]);
//app.controller('sm_ctrlr',  ['$scope', '$location','ClientService','$routeParams','$window',
//                                  function($scope, $location, ClientService,$routeParams,$window){
//							console.log("Recherche");
//							
//							$scope.sm = function (nom,prenom,cpte){
//								console.log("Entered");	
//							ClientService.srcClient(nom,prenom,cpte)
//							.then(
//							        function(d) {
//							            self.users = d;
//							            $scope.users=d;
//							            console.log($scope.users);
//							            $window.location = 'accueil_ag.html#/search_ag';
//							        },
//							        function(errResponse){
//							            console.error('Error while fetching Users');
//							        }
//							    );
//							}}
//]);

//search_ad
app.controller('search_adCtrl', ['$scope', '$location','EmployeService',
 	 function($scope, $location, EmployeService)
 	 {
			console.log("hello2");
			EmployeService.fetchConseiller()
		        .then(
		        function(d) {
		            self.cons = d;
		            $scope.cons=d;
		            console.log($scope.cons);
		        },
		        function(errResponse){
		            console.error('Error while fetching Users');
		        }
		    );	
}]);

app.controller('valid_Ctrl', ['$scope', '$location','ClientService','$routeParams',
                             	 function($scope, $location, ClientService,$routeParams)
                             	 {
									}]);
                        
//consult_agCtrl
app.controller('consult_agCtrl', [ '$scope', '$location', 'ClientMdpService','ClientServiceAff','$routeParams',
		function($scope, $location, ClientMdpService,ClientServiceAff,$routeParams) {
			console.log("helloMdp");
			ClientMdpService.fetchMdpClient().then(function(d) {
				self.user = d;
				$scope.user = d;
				console.log($scope.user);
			}, function(errResponse) {
				console.error('Error while fetching Users');
			});
			console.log("hello id");
        	$scope.valid= function(client, id){
        		console.log(client);
        		ClientServiceAff.validUser(client, id);
                }
		} ]);
//affectCtrl
app.controller('affectCtrl',  ['$scope', '$location','ClientService','EmployeService','ClientServiceNew','$routeParams',
          function($scope, $location, ClientService,EmployeService,ClientServiceNew,$routeParams){
        	console.log("welcome");
        	ClientServiceNew.fetchNewClients()
                .then(
                function(d) {
                    self.users = d;
                    $scope.users=d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
        	console.log("hello2");
			EmployeService.fetchConseiller()
		        .then(
		        function(d) {
		            self.cons = d;
		            $scope.cons=d;
		            console.log($scope.cons);
		        },
		        function(errResponse){
		            console.error('Error while fetching Users');
		        }
		    );	
        	console.log("hello id");
        	$scope.submit= function(client, id){
        		console.log("on y croit : " + client.id);
        		ClientServiceNew.updateUser(client, id);
                }
        }]);

//rootCtrl
app.controller('rootCtrl', ['$scope', '$location', 
    function($scope, $location) {
    			
		console.log("rootCtrl::");
		console.log("=============");
    }]);

app.controller('newClCtrl', ['$scope', '$location', 'ClientService',
                              function($scope, $location, ClientService) {
	$scope.client = {};

    $scope.submit = function(client) {
      console.log(client);
      ClientService.createClient(client);
    };

}]);
app.controller(

'searchNomCtrl',
[
		'$scope',
		'$location',
		'ClientService',
		
		function($scope, $location, ClientService) {
			$scope.client = {};
			
			$scope.src = function(client){
			console.log(client);
			
			ClientService.srcClient(client) 
						.then
							(function(d) {
									$scope.users = d;													
									console
											.log($scope.users);
																						
								},
								function(errResponse) {
									console.error = ("Invalid entry");
								});						

			}} ]);


//app.controller(
//
//'searchPrenomCtrl',
//[
//		'$scope',
//		'$location',
//		'ClientService',
//		'$routeParams',
//		function($scope, $location, ClientService,$routeParams) {
//			console.log("hello");
//			ClientService.srcClientPrenom($routeParams.prenom) 
//						.then
//							(function(d) {
//									$scope.nm = d;													
//									console
//											.log($scope.nm);
//																						
//								},
//								function(errResponse) {
//									console.error = ("Invalid entry");
//								});						
//
//		} ]);
//app.controller(
//
//'searchNomPrenomCtrl',
//[
//		'$scope',
//		'$location',
//		'ClientService',
//		'$routeParams',
//		function($scope, $location, ClientService,$routeParams) {
//			console.log("hello");
//			ClientService.srcClientNomPrenom($routeParams.nom , $routeParams.prenom) 
//						.then
//							(function(d) {
//									$scope.nm = d;													
//									console
//											.log($scope.nm);
//																						
//								},
//								function(errResponse) {
//									console.error = ("Invalid entry");
//								});						
//
//		} ]);



