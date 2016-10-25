'use strict';

angular.module('myApp').factory('ClientUpdateService', ['$http', '$q', function($http, $q){

var CLIENT_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/UpdateClient/';

var factory = {
		UpdateClient : UpdateClient
};
return factory;

function UpdateClient(client,id) {
    var deferred = $q.defer();
    $http.put(CLIENT_REST_SERVICE_URI+id,client)
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