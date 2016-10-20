'use strict';

angular.module('myApp').factory('CompteService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/compte/';

var factory = {
		fetchCompte: fetchCompte
};
return factory;
/*recherche de tous les utilisateurs */
function fetchCompte(cpt) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+cpt)
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
}
}]);
