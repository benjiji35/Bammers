'use strict';

angular.module('myApp').factory('ClientFilterService', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/filteredClient/';

var factory = {
		fetchFilteredClients :   fetchFilteredClients
};
return factory;

function fetchFilteredClients(id) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+id)
        .then(
        function (response) {
            deferred.resolve(response.data);
        },
        function(errResponse){
            console.error('Error while updating User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}

}]);