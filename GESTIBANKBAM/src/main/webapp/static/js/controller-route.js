app.controller('mainCtrl', function($scope) {});
app.controller('contactCtrl', ['$scope', '$routeParams', '$location','$q', 
	function($scope, $routeParams, $location,$q) {
		$scope.sujet = $routeParams.sujet;
		$scope.msg = $routeParams.msg;
		//$scope.absUrl  = $location.absUrl();
		$scope.fichiers = $routeParams.fichiers;
		/*console.log("contactCtrl::sujet="+$routeParams.sujet);
		console.log("contactCtrl::msg="+$routeParams.msg);
		console.log("contactCtrl::files="+$routeParams.files);*/
}]);


app.controller('connectCtrl', ['$scope', '$location', 
	function($scope, $location) {
		//$location.url('/tpl');
		console.log("connectCtrl::=");
	}]);

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
app.controller('search_agCtrl',  ['$scope', '$location', 
  function fetchAllClients() {
	console.log("test");
    var deferred = $q.defer();
    $http.get(REST_SERVICE_URI)
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while fetching Users');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}]);

//search_ad
app.controller('search_adCtrl', ['$scope', '$location', 
    function($scope, $location) {
		
}]);

//consult_ag
app.controller('consult_agCtrl', ['$scope', '$location', 
    function($scope, $location) {
                             		
    }]);

//list_agCtrl
app.controller('list_agCtrl', ['$scope', '$location', 
    function($scope, $location) {
    	// todo
    }]);


//list_adCtrl
app.controller('list_adCtrl', ['$scope', '$location', 
    function($scope, $location) {
    	// todo
		//$scope.role = 3;
    }]);
//affectCtrl
app.controller('affectCtrl', ['$scope', '$location', 
    function($scope, $location) {
                             		
    }]);
//rootCtrl
app.controller('rootCtrl', ['$scope', '$location', 
    function($scope, $location) {
    			
		console.log("rootCtrl::");
		console.log("=============");
    }]);
