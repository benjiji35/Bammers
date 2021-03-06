'use strict';

angular.module('myApp').factory('ClientMdpService', ['$http', '$q', '$window', function($http, $q, $window){

var CLIENTS_REST_SERVICE_URI = 'http://localhost:8080/SpringAngularStartProject/mdpClient/';

var factory = {
		fetchMdpClient : fetchMdpClient
};
return factory;

function fetchMdpClient(id) {
    var deferred = $q.defer();
    $http.get(CLIENTS_REST_SERVICE_URI+id)
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
}}]);