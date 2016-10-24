'use strict';

angular.module('myApp').factory('ClientServiceAff', ['$http', '$q','$window', function($http, $q,$window){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/affClient/';

var factory = {
		validUser : validUser		
};
return factory;

function validUser(client, id) {
    var deferred = $q.defer();
    console.log("Validation");
    $http.put(CLIENTS_REST_SERVICE_URI+id)
        .then(
        function (response) {
            deferred.resolve(response.data);
            $window.alert("Le client a bien été validé");
            window.location.reload();
        },
        function(errResponse){
            console.error('Error while updating User');
            deferred.reject(errResponse);
        }
    );
    return deferred.promise;
}}]);