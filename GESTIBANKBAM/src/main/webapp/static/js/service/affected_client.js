'use strict';

angular.module('myApp').factory('ClientServiceAff', ['$http', '$q', function($http, $q){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/affClient/';

var factory = {
		validUser : validUser		
};
return factory;

function validUser(client, id) {
    var deferred = $q.defer();
    console.log("Validation");
    $http.put(CLIENTS_REST_SERVICE_URI+id, client)
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
}}]);