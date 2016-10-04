'use strict';

angular.module('myApp').factory('ClientService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/client/';

var factory = {
		fetchAllClients: fetchAllClients,
		searchClient: searchClient
};
return factory;
/*recherche de tous les utilisateurs */
function fetchAllClients() {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI)
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

function searchClient(id) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+id)
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while finding User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}
}]);
